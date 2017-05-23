package viccrubs.bfide.client.controllers;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import viccrubs.bfide.client.models.Log;
import viccrubs.bfide.client.socket.Connection;
import viccrubs.bfide.models.requests.TestConnectionRequest;
import viccrubs.bfide.models.response.TestConnectionResponse;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by viccrubs on 2017/5/22.
 */
public class LoginPanelController {


    private final static String CONNECT = "Attempting to connect to ";
    private final static String CONNECTION_ESTABLISHED = "Connection established.";
    private final static String CONNECTION_FAILED = "Connection failed.";

    private Connection connection;

    @FXML
    private TextField tfIP;
    @FXML
    private Button btnConnect;
    @FXML
    private Button btnDisconnect;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField tfRegisterUsername;
    @FXML
    private TextField tfRegisterPassword;
    @FXML
    private TextField tfLoginUsername;
    @FXML
    private TextField tfLoginPassword;
    @FXML
    private Text textStatus;

    private ObservableList<Log> logs = FXCollections.observableArrayList();

    public void btnConnectOnClick(){
        //TODO: Connect to Remote server
        btnConnect.setText("Connecting...");
        btnConnect.setDisable(true);




        String[] ipAndPort = tfIP.getText().split(":");

        String ip = ipAndPort[0];
        int port = Integer.parseInt(ipAndPort[1]);

        addLog(CONNECT+tfIP.getText());

        try {
            Socket socket = new Socket(ip,port);

            connection = new Connection(socket);

            if (connection.sendRequest(new TestConnectionRequest()).equals(new TestConnectionResponse())){

            }

        } catch (IOException e) {
            addLog(CONNECTION_FAILED);
            btnConnect.setText("Connect");
            btnConnect.setDisable(false);
            return;
        }

        btnConnect.setText("Connect");
        btnDisconnect.setDisable(false);
        addLog(CONNECTION_ESTABLISHED);

    }





    public void addLog(String log){
        logs.add(new Log(log));
        textStatus.setText(log);
    }



}
