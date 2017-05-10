package viccrubs.bfide.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by viccrubs on 2017/5/6.
 */
public class BFIDEServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(20006);
        Socket client = null;
        boolean f = true;
        while(f){
            //等待客户端的连接，如果没有获取连接
            client = server.accept();
            System.out.println("与客户端连接成功！");
            //为每个客户端连接开启一个线程
            new Thread(new Controller(client)).start();
        }
        server.close();
    }
}
