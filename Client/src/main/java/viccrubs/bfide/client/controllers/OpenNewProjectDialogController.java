package viccrubs.bfide.client.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import viccrubs.bfide.bfmachine.ProgramLanguage;
import viccrubs.bfide.client.MainClient;
import viccrubs.bfide.client.models.Log;
import viccrubs.bfide.client.models.ProjectInfoModel;
import viccrubs.bfide.client.socket.Connection;
import viccrubs.bfide.models.ProjectInfo;
import viccrubs.bfide.utilities.DateUtil;

import java.io.IOException;

/**
 * Created by viccrubs on 2017/6/8.
 */
public class OpenNewProjectDialogController {

    @FXML
    private TableView<ProjectInfoModel> projectsTable;
    @FXML
    private TableColumn<ProjectInfoModel,String> nameColumn;
    @FXML
    private TableColumn<ProjectInfoModel,ProgramLanguage> languageColumn;
    @FXML
    private TableColumn<ProjectInfoModel,String> latestVersionColumn;
    @FXML
    private TableColumn<ProjectInfoModel,String> versionCountColumn;


    private Connection connection;


    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().projectNameProperty());
        languageColumn.setCellValueFactory(cellData -> cellData.getValue().languageProperty());
        latestVersionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(DateUtil.format(cellData.getValue().getLatestVersion())));
        versionCountColumn.setCellValueFactory(cellData -> cellData.getValue().versionCountProperty());
    }

    public void setData(ObservableList<ProjectInfoModel> infos){
        projectsTable.setItems(infos);
    }

    public static OpenNewProjectDialogController open(){
        try {
            Stage stage= new Stage();
            stage.setTitle("Open New Project");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClient.class.getResource("/fxml/OpenNewProjectDialog.fxml"));

            Scene scene = new Scene(loader.load());
            OpenNewProjectDialogController controller = loader.getController();

            stage.setScene(scene);
            stage.show();
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }
}
