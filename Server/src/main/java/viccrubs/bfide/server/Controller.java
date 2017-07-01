package viccrubs.bfide.server;

import com.google.gson.Gson;
import viccrubs.bfide.bfmachine.*;
import viccrubs.bfide.bfmachine.exception.UnknownInstructionException;
import viccrubs.bfide.bfmachine.program.*;
import viccrubs.bfide.model.*;
import viccrubs.bfide.model.request.*;
import viccrubs.bfide.model.response.*;
import viccrubs.bfide.server.annotation.RequestHandler;
import viccrubs.bfide.server.storage.ProjectManager;
import viccrubs.bfide.server.storage.UserManager;
import viccrubs.bfide.server.storage.exception.ProjectExistsException;
import viccrubs.bfide.server.storage.exception.ProjectNotExistException;
import viccrubs.bfide.server.storage.exception.UserExistsException;
import viccrubs.bfide.util.ConfiguredGson;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
/**
 * Created by viccrubs on 2017/5/6.
 */

public class Controller implements Runnable {
    private Socket client;
    private BFMachine machine;
    private PrintStream out;
    private ProjectManager projectManager;
    private Gson gson = ConfiguredGson.get();
    private User currentUser;
    private boolean terminate =false;
    private static Hashtable<Class, Method> handlerTable = new Hashtable<>();
    static {
        Arrays.stream(Controller.class.getMethods()).filter(x->x.isAnnotationPresent(RequestHandler.class)).forEach(x->{
            handlerTable.put(x.getAnnotation(RequestHandler.class).requestType().correspondingClass,x);
        });
    }

    public Controller(Socket client){
        this.client = client;
    }

    public void output(Response res){
        out.print(gson.toJson(res));
        out.print("\r\n");
    }


    @RequestHandler(requestType = RequestType.CreateNewProject)
    public Response handleCreateNewProject(CreateNewProjectRequest request){
        if (projectManager.projectExists(request.projectName)){
            return new CreateNewProjectResponse(false,null, ProjectExistsException.description);
        }else{
            try {
                ProjectInfo info = projectManager.createNewProject(request.projectName, request.language);
                return new CreateNewProjectResponse(info!=null,info, "");
            } catch (ProjectExistsException e) {
                return new CreateNewProjectResponse(false,null, ProjectExistsException.description);
            }

        }
    }

    @RequestHandler(requestType = RequestType.DeleteProject)
    public Response handleDeleteProject(DeleteProjectRequest request){
        try{
            boolean result = projectManager.deleteProject(request.projectInfo);
            return new DeleteProjectResponse(true, "");
        }catch(ProjectNotExistException ex){
            return new DeleteProjectResponse(false, ProjectNotExistException.description);
        }

    }

    @RequestHandler(requestType =  RequestType.GetAllProjects)
    public Response handleGetAllProjects(GetAllProjectsRequest request){
        return new GetAllProjectsResponse(projectManager.getAllProjects());
    }

    @RequestHandler(requestType = RequestType.GetASpecificVersion)
    public Response handleGetASpecificVersion(GetASpecificVersionRequest request){
        try {
            return new GetASpecificVersionResponse(projectManager.getContentOfAVersion(request.projectInfo, request.version), request.version);
        } catch (ProjectNotExistException e) {
            return new GetASpecificVersionResponse(null,request.version);
        }
    }

    @RequestHandler(requestType = RequestType.GetProjectInfo)
    public Response handleGetProjectInfo(GetProjectInfoRequest request){
        try {
            return new GetProjectInfoResponse(projectManager.getProjectInfo(request.projectName));
        } catch (ProjectNotExistException e) {
           return new GetProjectInfoResponse(null);
        }
    }

    @RequestHandler(requestType = RequestType.Login)
    public Response handleLogin(LoginRequest request){
        UserManager auth = new UserManager();

        currentUser = auth.authenticate(request.username, request.password).orElse(null);

        if (currentUser!=null){
            machine = new BFMachine();
            projectManager = new ProjectManager(currentUser);
            return new LoginResponse(true,currentUser);
        }
        return new LoginResponse(false, null);
    }

    @RequestHandler(requestType = RequestType.Register)
    public Response handleRegister(RegisterRequest request){
        UserManager auth = new UserManager();
        try{
            User user = auth.register(request.username, request.password);
            return new RegisterResponse(true,"");
        }catch (UserExistsException ex){
            return new RegisterResponse(false, UserExistsException.description);
        }catch(Exception ex){
            return new RegisterResponse(false, "");
        }
    }

    @RequestHandler(requestType = RequestType.ExecuteProgram)
    public Response handleRunProgram(ExecuteProgramRequest request){
        if (currentUser==null){
            return new RequireLoginResponse();
        }else{
            try{
                if (request.program==null){
                    return new ExecuteProgramResponse(new ExecutionResult("",null,0));
                }

                if (request.language.equals(ProgramLanguage.BF)) {
                    Program program = new BFCompiler(request.program).compile();
                    ExecutionResult result = machine.execute(program, request.input);
                    return new ExecuteProgramResponse(result);
                }
                else if (request.language.equals(ProgramLanguage.Ook)) {
                    Program program = new OokCompiler(request.program).compile();
                    ExecutionResult result = machine.execute(program, request.input);
                    return new ExecuteProgramResponse(result);
                }
                else {
                    return new ExecuteProgramResponse(new ExecutionResult("",null,0));
                }
            }catch (UnknownInstructionException ex){
                return new ExecuteProgramResponse(new ExecutionResult("",ex, 0));
            }
        }
    }

    @RequestHandler(requestType = RequestType.SaveVersion)
    public Response handleSaveVersion(SaveVersionRequest request){
        try {
            ProjectInfo info = projectManager.getProjectInfo(request.project.projectName);
            Version latestVersion = info.latestVersion;
            String latestContent = projectManager.getContentOfAVersion(info,latestVersion).trim();
            if (latestVersion == null || !request.content.trim().equals(latestContent)){
                latestVersion = projectManager.createNewVersion(info, request.content, new Version(request.timestamp));
                return new SaveVersionResponse(true, "", latestVersion);
            }else {
                return new SaveVersionResponse(false, "Nothing has changed from last version.", latestVersion);
            }

        } catch (ProjectNotExistException e) {
            return new SaveVersionResponse(false, ProjectNotExistException.description, null);
        }

    }
    @RequestHandler(requestType = RequestType.TestConnection)
    public Response handleTestConnection(TestConnectionRequest request){
        return new TestConnectionResponse();
    }

    @RequestHandler(requestType = RequestType.TerminateConnection)
    public Response handleTerminateConnection(TerminateConnectionRequest request){
        terminate=true;
        System.out.println("Terminating the connection.");
        return new TerminateConnectionResponse();
    }



    @Override
    public void run() {
        try {

            out = new PrintStream(client.getOutputStream());
            Scanner inScanner = new Scanner(client.getInputStream());
            inScanner.useDelimiter("\r\n");

            while (inScanner.hasNext() && !terminate) {
                Request request = gson.fromJson(inScanner.next(), Request.class);
                output((Response) handlerTable.get(request.type.correspondingClass).invoke(this, request));
            }
            inScanner.close();

            out.close();
            client.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

