package viccrubs.bfide.client.socket;

import com.google.gson.Gson;
import com.sun.istack.internal.NotNull;
import viccrubs.bfide.models.ConfiguredGson;
import viccrubs.bfide.models.requests.Request;
import viccrubs.bfide.models.requests.TerminateConnectionRequest;
import viccrubs.bfide.models.response.Response;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by viccrubs on 2017/5/22.
 */
public class Connection implements Closeable {
    private Socket socket;
    private Scanner reader; // receive data from server
    private PrintStream out; //send data to server
    private Gson gson = ConfiguredGson.get();

    public Connection(Socket socket){

        try {
            this.socket = socket;
            this.reader = new Scanner(socket.getInputStream());
            this.reader.useDelimiter("\r\n");
            this.out = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Response sendRequest(Request request){
        out.print(gson.toJson(request));
        out.print("\r\n");
        return gson.fromJson(reader.next(),Response.class);

    }



    @Override
    public void close() {
        out.print(gson.toJson(new TerminateConnectionRequest()));
        out.print("\r\n");
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
