package viccrubs.bfide.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viccrubs.bfide.bfmachine.ProgramLanguage;
import viccrubs.bfide.client.MainClient;
import viccrubs.bfide.client.socket.Connection;
import viccrubs.bfide.model.ProjectInfo;
import viccrubs.bfide.model.request.CreateNewProjectRequest;
import viccrubs.bfide.model.response.CreateNewProjectResponse;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Created by viccrubs on 2017/6/13.
 */
public class NewProjectDialogController {
    @FXML
    private TextField tfProjectName;

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnCancel;
    @FXML
    private MenuButton mbtnLanguage;


    private Stage stage;
    private Connection connection;
    private Consumer<ProjectInfo> eventOnProjectSelect;


    private ProgramLanguage language = ProgramLanguage.BF;

    public void setStage(Stage stage){
        this.stage = stage;
    }


    public void registerOnProjectCreated(Consumer<ProjectInfo> event){
        this.eventOnProjectSelect = event;
    }

    public void onCreate(){

        CreateNewProjectResponse res = (CreateNewProjectResponse)connection.sendRequest(new CreateNewProjectRequest(tfProjectName.getText(),language));
        if (res.success){
            if (eventOnProjectSelect != null){
                eventOnProjectSelect.accept(res.newProjectInfo);
                onCancel();
            }
        }else{
                Alert alertDialog = new Alert(Alert.AlertType.ERROR);
                alertDialog.setContentText("An error occurred."+System.lineSeparator()+res.description);
                alertDialog.setHeaderText(null);
                alertDialog.showAndWait();

        }
    }

    public void onCancel(){
        stage.close();
    }

    public void bfSelected(){
        this.language = ProgramLanguage.BF;
        mbtnLanguage.setText("BrainFuck");
    }

    public void ookSelected(){
        this.language = ProgramLanguage.Ook;
        mbtnLanguage.setText("Ook!");
    }

    public static NewProjectDialogController open(Connection connection){
        if (connection==null){
            return null;
        }
        try {
            Stage stage= new Stage();
            stage.setTitle("New Project");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClient.class.getResource("/fxml/NewProjectDialog.fxml"));

            Scene scene = new Scene(loader.load());
            NewProjectDialogController controller = loader.getController();
            controller.setStage(stage);
            controller.connection = connection;
            stage.setScene(scene);
            stage.show();
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}