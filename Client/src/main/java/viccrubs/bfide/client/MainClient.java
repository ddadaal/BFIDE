package viccrubs.bfide.client;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viccrubs.bfide.client.controller.LoginPanelController;
import viccrubs.bfide.client.log.ApplicationLog;
import viccrubs.bfide.client.model.Log;

public class MainClient extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");
        try {
            new ApplicationLog().addLog(new Log("App started."));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/LoginPanel.fxml"));
            Scene scene = new Scene(loader.load());
            LoginPanelController controller = loader.getController();
            controller.setStage(primaryStage);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}