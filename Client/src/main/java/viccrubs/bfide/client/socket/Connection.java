package viccrubs.bfide.client.socket;

import com.google.gson.Gson;
import javafx.scene.control.Alert;
import viccrubs.bfide.util.ConfiguredGson;
import viccrubs.bfide.model.request.Request;
import viccrubs.bfide.model.request.TerminateConnectionRequest;
import viccrubs.bfide.model.response.Response;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by viccrubs on 2017/5/22.
 */
public class Connection implements Closeable {
    private Socket socket;
    private Scanner reader; // receive data from server
    private PrintStream out; //send data to server
    private Gson gson = ConfiguredGson.get();
    private Runnable onConnectionLost;

    public Connection(Socket socket){

        try {
            this.socket = socket;
            this.reader = new Scanner(socket.getInputStream());
            this.reader.useDelimiter("\r\n\r\n");
            this.out = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setOnConnectionLost(Runnable onConnectionLost){
        this.onConnectionLost = onConnectionLost;
    }

    public Response sendRequest(Request request){
        out.print(gson.toJson(request));
        out.print("\r\n\r\n");
        try{
            String raw = reader.next();
            return gson.fromJson(raw,Response.class);
        }catch (NoSuchElementException ex){
            onConnectionFailed();
            return null;
        }
    }

    public void onConnectionFailed(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Connection to server has been lost."+System.lineSeparator()+"You need to re-login.");
        alert.showAndWait();
        if (onConnectionLost!=null){
            onConnectionLost.run();
        }
    }



    @Override
    public void close() {
        out.print(gson.toJson(new TerminateConnectionRequest()));
        out.print("\r\n\r\n");
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
