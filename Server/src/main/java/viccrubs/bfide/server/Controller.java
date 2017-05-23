package viccrubs.bfide.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import viccrubs.bfide.bfmachine.*;
import viccrubs.bfide.models.ConfiguredGson;
import viccrubs.bfide.models.ExecutionResult;
import viccrubs.bfide.models.User;
import viccrubs.bfide.models.requests.*;
import viccrubs.bfide.models.response.LoginResponse;
import viccrubs.bfide.models.response.Response;
import viccrubs.bfide.models.response.ResponseParser;
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
    private DynamicInStream machineInStream;
    private DynamicOutStream machineOutStream;
    private Gson gson;

    public Controller(Socket client){
        this.client = client;
        this.gson = ConfiguredGson.get();
        this.machineInStream = new DynamicInStream();
        this.machineOutStream = new DynamicOutStream();

    }

    public void run() {
        try{



            //获取Socket的输出流，用来向客户端发送数据
            out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            inScanner = new Scanner(client.getInputStream());
            inScanner.useDelimiter("\r\n");



            boolean terminate = false;

            //auth
            Authentication auth = new Authentication();
            Request firstRequest = gson.fromJson(inScanner.next(), Request.class);

            if (firstRequest instanceof LoginRequest ){
                LoginRequest user = (LoginRequest)firstRequest;
                User authenticated = auth.authenticate(user.username, user.password).orElse(null);
                if (authenticated==null){
                    out.println(gson.toJson(new LoginResponse(false)));
                    terminate=true;
                }else{
                    out.println(gson.toJson(new LoginResponse(true)));
                }
            }else if(firstRequest instanceof RegisterRequest){
                RegisterRequest register =(RegisterRequest)firstRequest;
                if (Register.register(register.username, register.password).isPresent()){
                    out.println();
                }
            }





            while(inScanner.hasNext() && !terminate){
                String str = inScanner.next();
                Request request= gson.fromJson(str, Request.class);
                if (request instanceof LoginRequest){
                    LoginRequest trueRequest = (LoginRequest)request;
                    out.printf("Login Request Detected. %s, %s",  trueRequest.username, trueRequest.password);
                }else if (request instanceof RunProgramRequest){
                    RunProgramRequest trueRequest = (RunProgramRequest)request;
                    out.printf("%s. program is %s", trueRequest.type, trueRequest.program);
                }else{
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

