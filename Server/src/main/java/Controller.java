import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.ir.RuntimeNode;
import viccrubs.bfide.bfmachine.*;
import viccrubs.bfide.models.ExecutionResult;
import viccrubs.bfide.models.Operation;
import viccrubs.bfide.models.Response;

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
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public Controller(Socket client){
        this.client = client;

    }

    public void run() {
        try{
            //获取Socket的输出流，用来向客户端发送数据
            out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            inScanner = new Scanner(client.getInputStream());
            boolean terminate = false;
            //接收从客户端发送过来的数据
            while(inScanner.hasNextLine() && !terminate){
                String str = inScanner.nextLine();
                Operation op = Operation.fromString(str);
                switch(op){
                    case Unknown:
                        out.println(Response.UnknownCommand);
                        break;
                    case TerminateConnection:
                        out.println(Response.TerminateConnection);
                        terminate = true;
                        break;
                    case TestConnection:
                        out.println(Response.TestConnection);
                        break;
                    case LoadProgram:
                        StringBuilder program = new StringBuilder();
                        while (inScanner.hasNextLine()){
                            String nextLine = inScanner.nextLine();
                            if (nextLine.equalsIgnoreCase("#")){
                                break;
                            }else{
                                program.append(inScanner.nextLine()).append("\n");
                            }
                        }
                        machine.loadProgram(program.toString());
                        out.println(Response.Success);
                        break;
                    case RunProgram:
                        ExecutionResult result = machine.executeProgram();
                        out.println(gson.toJson(result));
                        break;
                    case GetMachineStates:
                        out.println(gson.toJson(machine.getStates()));
                        break;
                    default:
                        out.println(Response.NotImplemented);
                        break;


                }

            }



            out.close();
            client.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

