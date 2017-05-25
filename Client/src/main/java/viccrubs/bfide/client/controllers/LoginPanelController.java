package viccrubs.bfide.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import viccrubs.bfide.client.MainClient;
import viccrubs.bfide.client.models.Log;
import viccrubs.bfide.client.socket.Connection;
import viccrubs.bfide.models.requests.LoginRequest;
import viccrubs.bfide.models.requests.RegisterRequest;
import viccrubs.bfide.models.requests.TestConnectionRequest;
import viccrubs.bfide.models.response.LoginResponse;
import viccrubs.bfide.models.response.RegisterResponse;
import viccrubs.bfide.models.response.Response;
import viccrubs.bfide.models.response.TestConnectionResponse;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by viccrubs on 2017/5/22.
 */
public class LoginPanelController extends Controller {


    private final static String CONNECT = "Attempting to connect to ";
    private final static String CONNECTION_ESTABLISHED = "Connection established.";
    private final static String CONNECTION_FAILED = "Connection failed.";
    private final static String CONNECTION_TERMINATED = "Connection terminated.";
    private final static String LOGIN_STARTED = "Login started...";
    private final static String LOGIN_SUCCESS = "Login success.";
    private final static String LOGIN_FAILURE = "Login failure.";
    private final static String REGISTER_STARTED = "Register started...";
    private final static String REGISTER_SUCCESS = "Register success.";
    private final static String REGISTER_FAILURE = "Register failure.";

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
    private PasswordField pfRegisterPassword;
    @FXML
    private TextField tfLoginUsername;
    @FXML
    private PasswordField pfLoginPassword;
    @FXML
    private Text textStatus;

    private ObservableList<Log> logs = FXCollections.observableArrayList();

    public void btnConnectOnClick(){
        //TODO: Connect to Remote server
        btnConnect.setText("Connecting...");
        btnConnect.setDisable(true);
        setEnableForm(false);



        String[] ipAndPort = tfIP.getText().split(":");

        String ip = ipAndPort[0];
        int port = Integer.parseInt(ipAndPort[1]);

        addLog(CONNECT+tfIP.getText());

        try {
            Socket socket = new Socket(ip,port);

            connection = new Connection(socket);

            Response response = connection.sendRequest(new TestConnectionRequest());
            if (!(response instanceof TestConnectionResponse)){
                throw new IOException();
            }

        } catch (IOException e) {
            addLog(CONNECTION_FAILED);
            btnConnect.setText("Connect");
            btnConnect.setDisable(false);
            return;
        }

        setEnableForm(true);
        btnConnect.setText("Connect");
        btnDisconnect.setDisable(false);
        addLog(CONNECTION_ESTABLISHED);

    }

    public void btnDisconnectOnClick(){
        btnConnect.setDisable(false);
        btnDisconnect.setDisable(true);
        setEnableForm(false);
        connection.close();
        addLog(CONNECTION_TERMINATED);
    }

    public void setEnableForm(boolean enable){
        btnRegister.setDisable(!enable);
        btnLogin.setDisable(!enable);
        tfLoginUsername.setDisable(!enable);
        pfLoginPassword.setDisable(!enable);
        pfRegisterPassword.setDisable(!enable);
        tfRegisterUsername.setDisable(!enable);
    }

    public void login(){
        addLog(LOGIN_STARTED);
        LoginResponse res = (LoginResponse)connection.sendRequest(new LoginRequest(tfLoginUsername.getText(), pfLoginPassword.getText()));
        if (res.success){
            addLog(LOGIN_SUCCESS);
        }else{
            addLog(LOGIN_FAILURE);
        }
    }

    public void register(){
        addLog(REGISTER_STARTED);
        RegisterResponse res = (RegisterResponse)connection.sendRequest(new RegisterRequest(tfRegisterUsername.getText(), pfRegisterPassword.getText()));
        if (res.success){
            addLog(REGISTER_SUCCESS);
        }else{
            addLog(REGISTER_FAILURE);
        }
    }





    public void addLog(String log){
        logs.add(new Log(log));
        textStatus.setText(log);
    }



}
