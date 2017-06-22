package viccrubs.bfide.server;

import com.google.gson.Gson;
import viccrubs.bfide.bfmachine.*;
import viccrubs.bfide.bfmachine.exception.UnknownInstructionException;
import viccrubs.bfide.model.*;
import viccrubs.bfide.model.request.*;
import viccrubs.bfide.model.response.*;
import viccrubs.bfide.server.storage.ProjectManager;
import viccrubs.bfide.server.storage.UserManager;
import viccrubs.bfide.server.storage.exception.ProjectExistsException;
import viccrubs.bfide.server.storage.exception.ProjectNotExistException;
import viccrubs.bfide.server.storage.exception.UserExistsException;
import viccrubs.bfide.util.ConfiguredGson;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by viccrubs on 2017/5/6.
 */

public class Controller implements Runnable {
    private Socket client = null;
    private BFMachine machine;
    private PrintStream out;
    private Scanner inScanner;
    private ProjectManager projectManager;
    private Gson gson;
    private User currentUser;

    public Controller(Socket client){
        this.client = client;
        this.gson = ConfiguredGson.get();
    }

    public void output(Response res){
        out.print(gson.toJson(res));
        out.print("\r\n");
    }

    @Override
    public void run() {
        try {


            //获取Socket的输出流，用来向客户端发送数据
            out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            inScanner = new Scanner(client.getInputStream());
            inScanner.useDelimiter("\r\n");


            boolean terminate = false;

            //auth
            UserManager auth = new UserManager();



            while (inScanner.hasNext() && !terminate) {
                Request request = gson.fromJson(inScanner.next(), Request.class);
                if (request instanceof RunProgramRequest) {
                    RunProgramRequest trueRequest = (RunProgramRequest) request;
                    if (currentUser==null){
                        output(new RequireLoginResponse());
                    }else{
                        try{
                            if (trueRequest.program==null){
                                output(new ExecutionResponse(new ExecutionResult("",null,0)));
                                continue;
                            }
                            Program program = Program.translateProgram(trueRequest.program, trueRequest.language);
                            ExecutionResult result = machine.execute(program, trueRequest.input);
                            output(new ExecutionResponse(result));
                        }catch (UnknownInstructionException ex){
                            output(new ExecutionResponse(new ExecutionResult("",ex, 0)));
                        }
                    }
                } else if (request instanceof TerminateConnectionRequest) {
                    System.out.println("Terminating the connection.");
                    output(new TerminateConnectionResponse());
                    terminate = true;
                } else if (request instanceof TestConnectionRequest) {
                    output(new TestConnectionResponse());
                } else if (request instanceof LoginRequest) {
                    LoginRequest user = (LoginRequest) request;
                    currentUser = auth.authenticate(user.username, user.password).orElse(null);
                    output(new LoginResponse(currentUser!=null, currentUser));
                    if (currentUser!=null){
                        machine = new BFMachine();
                        projectManager = new ProjectManager(currentUser);
                    }
                } else if (request instanceof RegisterRequest) {
                    RegisterRequest trueRequest = (RegisterRequest) request;
                    try{
                        User user = auth.register(trueRequest.username, trueRequest.password);
                        output(new RegisterResponse(true,""));
                    }catch (UserExistsException ex){
                        output(new RegisterResponse(false, "User exists."));
                    }catch(Exception ex){
                        output(new RegisterResponse(false, ""));
                    }

                } else if (request instanceof GetProjectInfoRequest){
                    GetProjectInfoRequest trueRequest = (GetProjectInfoRequest)request;
                    if (!projectManager.projectExists(trueRequest.projectName)){
                        output(new GetProjectInfoResponse(null));
                    }else{
                        output(new GetProjectInfoResponse(projectManager.getProjectInfo(trueRequest.projectName)));
                    }
                } else if (request instanceof SaveVersionRequest){
                    SaveVersionRequest trueRequest = (SaveVersionRequest)request;
                    ProjectInfo info = projectManager.getProjectInfo(trueRequest.project.projectName);
                    if (info==null){
                        output(new SaveVersionResponse(false,null));
                        continue;
                    }
                    Version latestVersion = info.latestVersion;
                    String latestContent = projectManager.getContentOfAVersion(info,latestVersion).trim();
                    if (latestVersion == null || !trueRequest.content.trim().equals(latestContent)){
                        latestVersion = projectManager.createNewVersion(info, trueRequest.content, new Version(trueRequest.timestamp));
                        output(new SaveVersionResponse(true, latestVersion));

                    }else {
                        output(new SaveVersionResponse(false, latestVersion));
                    }
                } else if (request instanceof CreateNewProjectRequest){
                    CreateNewProjectRequest trueRequest = (CreateNewProjectRequest)request;
                    if (projectManager.projectExists(trueRequest.projectName)){
                        output(new CreateNewProjectResponse(false,null, ProjectExistsException.description));
                    }else{
                        ProjectInfo info = projectManager.createNewProject(trueRequest.projectName, trueRequest.language);
                        output(new CreateNewProjectResponse(info!=null,info, ""));
                    }
                } else if (request instanceof GetAllProjectsRequest){
                    output(new GetAllProjectsResponse(projectManager.getAllProjects()));
                } else if (request instanceof  GetASpecificVersionRequest){
                    GetASpecificVersionRequest trueRequest = (GetASpecificVersionRequest)request;
                    output(new GetASpecificVersionResponse(projectManager.getContentOfAVersion(trueRequest.projectInfo, trueRequest.version), trueRequest.version));
                } else if (request instanceof  DeleteProjectRequest){
                    DeleteProjectRequest trueRequest = (DeleteProjectRequest)request;
                    try{
                        projectManager.deleteProject(trueRequest.projectInfo);
                        output(new DeleteProjectResponse(true, ""));
                    }catch(ProjectNotExistException ex){
                        output(new DeleteProjectResponse(false, ProjectNotExistException.description));
                    }

                }

                else{
                    out.println("WRONG COMMAND");
                }
            }

            inScanner.close();

            out.close();
            client.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

