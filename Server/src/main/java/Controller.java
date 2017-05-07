import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;
/**
 * Created by viccrubs on 2017/5/6.
 */

public class Controller implements Runnable {
    private Socket client = null;
    private String machineUUID= UUID.randomUUID().toString();

    public Controller(Socket client){
        this.client = client;
    }

    public void run() {
        try{
            //获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            Scanner scanner = new Scanner(client.getInputStream());
            boolean terminate = false;
            //接收从客户端发送过来的数据
            while(scanner.hasNextLine() && !terminate){
                String str = scanner.nextLine();
                OpCode code =


            }
            while(scanner.hasNextLine() && !terminate){
                String str=  scanner.nextLine();
                Request request = gson.fromJson(str, Request.class);
                if (request==null){
                    out.print("Unknown request!");
                }else{
                    switch(request.type){
                        case TerminateConnection:
                            BFIDEServer.machines.remove(machineUUID);
                            terminate = true;
                            break;
                        case TestConnection:
                            out.print("Connection established.");
                            break;
                        default:
                            out.print("Unknown request!");
                            break;
                    }
                }

            }



            out.close();
            client.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
