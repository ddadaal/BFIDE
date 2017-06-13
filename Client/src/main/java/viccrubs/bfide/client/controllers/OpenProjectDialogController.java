package viccrubs.bfide.client.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
import viccrubs.bfide.models.requests.GetAllProjectsRequest;
import viccrubs.bfide.models.response.GetAllProjectsResponse;
import viccrubs.bfide.utilities.DateUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by viccrubs on 2017/6/8.
 */
public class OpenProjectDialogController {

    @FXML
    private TableView<ProjectInfoModel> projectTable;
    @FXML
    private TableColumn<ProjectInfoModel,String> nameColumn;
    @FXML
    private TableColumn<ProjectInfoModel,ProgramLanguage> languageColumn;
    @FXML
    private TableColumn<ProjectInfoModel,String> latestVersionColumn;
    @FXML
    private TableColumn<ProjectInfoModel,String> versionCountColumn;


    private Connection connection;
    private ObservableList<ProjectInfoModel> projects = FXCollections.observableArrayList();
    private Stage stage;
    private Consumer<ProjectInfo> eventOnProjectSelect;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    private void initialize() {

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().projectNameProperty());
        languageColumn.setCellValueFactory(cellData -> cellData.getValue().languageProperty());
        latestVersionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(DateUtil.format(cellData.getValue().getLatestVersion())));
        versionCountColumn.setCellValueFactory(cellData -> cellData.getValue().versionCountProperty());
    }

    public void setConnection(Connection connection){
        this.connection = connection;
        updateVersion();
    }

    public void updateVersion(){
        GetAllProjectsResponse projectRes = (GetAllProjectsResponse) connection.sendRequest(new GetAllProjectsRequest());
        projects.addAll(Arrays.stream(projectRes.projects).map(ProjectInfoModel::new).toArray(ProjectInfoModel[]::new));
        projectTable.setItems(projects);
    }

    public ObservableList<ProjectInfoModel> getProjects(){
        return projects;
    }

    public void registerOnProjectSelect(Consumer<ProjectInfo> event){
        this.eventOnProjectSelect = event;
    }

    public void onSelect(){
        if (eventOnProjectSelect!=null){
            TableView.TableViewSelectionModel<ProjectInfoModel> selected = projectTable.getSelectionModel();
            if (selected!=null){
                eventOnProjectSelect.accept(selected.getSelectedItem().toProjectInfo());
            }

        }
        onCancel();
    }

    public void onCancel(){
        stage.close();
    }


    public static OpenProjectDialogController open(){
        try {
            Stage stage= new Stage();
            stage.setTitle("Open New Project");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClient.class.getResource("/fxml/OpenProjectDialog.fxml"));

            Scene scene = new Scene(loader.load());
            OpenProjectDialogController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.show();
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
