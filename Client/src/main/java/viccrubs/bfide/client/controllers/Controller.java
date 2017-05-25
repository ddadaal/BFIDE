package viccrubs.bfide.client.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by viccrubs on 2017/5/25.
 */
public class Controller {
    protected Stage primaryStage;

    public void setStage(Stage stage){
        this.primaryStage = stage;
    }


    public <T extends Controller> void changeScene(T controller){
        primaryStage.setScene();
        primaryStage.sizeToScene();
    }
}
