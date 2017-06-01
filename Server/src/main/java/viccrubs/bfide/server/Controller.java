package viccrubs.bfide.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import viccrubs.bfide.bfmachine.*;
import viccrubs.bfide.models.*;
import viccrubs.bfide.models.requests.*;
import viccrubs.bfide.models.response.*;
import viccrubs.bfide.server.storage.UserManager;
import viccrubs.bfide.server.storage.authentication.Authentication;
import viccrubs.bfide.server.storage.authentication.Register;
import viccrubs.bfide.utilities.DynamicInStream;
import viccrubs.bfide.utilities.DynamicOutStream;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by viccrubs on 2017/5/6.
 */

public class Controller implements Runnable {
    private Socket client = null;
    private BFMachine machine;
    private PrintStream out;
    private Scanner inScanner;
    private UserManager userManager;
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

    public void run() {
        try {


            //获取Socket的输出流，用来向客户端发送数据
            out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            inScanner = new Scanner(client.getInputStream());
            inScanner.useDelimiter("\r\n");


            boolean terminate = false;

            //auth
            Authentication auth = new Authentication();



            while (inScanner.hasNext() && !terminate) {
                Request request = gson.fromJson(inScanner.next(), Request.class);
                if (request instanceof RunProgramRequest) {
                    RunProgramRequest trueRequest = (RunProgramRequest) request;
                    if (currentUser==null){
                        output(new RequireLoginResponse());
                    }else{
                        ExecutionResult result = machine.execute(Program.translateProgram(trueRequest.program, trueRequest.language), trueRequest.input);
                        output(new ExecutionResponse(result));
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
                        userManager = new UserManager(currentUser);
                    }
                } else if (request instanceof RegisterRequest) {
                    RegisterRequest register = (RegisterRequest) request;
                    if (Register.register(register.username, register.password).isPresent()) {
                        output(new RegisterResponse(true));
                    }else{
                        output(new RegisterResponse(false));
                    }
                } else if (request instanceof GetProjectInfoRequest){
                    GetProjectInfoRequest trueRequest = (GetProjectInfoRequest)request;
                    if (!userManager.projectExists(trueRequest.projectName)){
                        output(new GetProjectInfoResponse(null));
                    }else{
                        output(new GetProjectInfoResponse(userManager.getProjectInfo(trueRequest.projectName)));
                    }
                } else if (request instanceof SaveVersionRequest){
                    SaveVersionRequest trueRequest = (SaveVersionRequest)request;
                    Version latestVersion = userManager.getLatestVersionOfAProject(trueRequest.projectName);
                    String oldContent = userManager.getContentOfAVersion(trueRequest.projectName, userManager.getLatestVersionOfAProject(trueRequest.projectName));
                    if (trueRequest.content.equals(oldContent)){
                        output(new SaveVersionResponse(false, latestVersion));
                    }else{
                        latestVersion = userManager.createNewVersion(trueRequest.projectName, trueRequest.content, new Version(trueRequest.timestamp));
                        output(new SaveVersionResponse(true, latestVersion));
                    }
                } else if (request instanceof CreateNewProjectRequest){
                    CreateNewProjectRequest trueRequest = (CreateNewProjectRequest)request;
                    if (userManager.projectExists(trueRequest.projectName)){
                        output(new CreateNewProjectResponse(false,null));
                        continue;
                    }

                    ProjectInfo info = userManager.createNewProject(trueRequest.projectName, trueRequest.language);
                    output(new CreateNewProjectResponse(info==null,info));
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

