package viccrubs.bfide.client.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import viccrubs.bfide.bfmachine.program.ProgramLanguage;
import viccrubs.bfide.client.MainClient;
import viccrubs.bfide.client.model.ProjectInfoModel;
import viccrubs.bfide.client.socket.Connection;
import viccrubs.bfide.model.ProjectInfo;
import viccrubs.bfide.model.request.DeleteProjectRequest;
import viccrubs.bfide.model.request.GetAllProjectsRequest;
import viccrubs.bfide.model.response.DeleteProjectResponse;
import viccrubs.bfide.model.response.GetAllProjectsResponse;
import viccrubs.bfide.util.DateUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

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
    private Runnable eventOnProjectCreate;

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
        updateProjects();
    }

    public void updateProjects(){
        projects.clear();
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
    public void registerOnProjectCreate(Runnable event){this.eventOnProjectCreate = event;}

    public void onSelect(){
        if (eventOnProjectSelect!=null){
            getSelected().ifPresent(t->eventOnProjectSelect.accept(t));
        }
        onCancel();
    }

    public void onCreateNew(){
        if (eventOnProjectCreate!=null){
            this.stage.close();
            eventOnProjectCreate.run();
        }
    }

    public Optional<ProjectInfo> getSelected(){
        ProjectInfoModel selected = projectTable.getSelectionModel().getSelectedItem();
        if (selected!=null){
            return Optional.of(selected.toProjectInfo());
        }else{
            return Optional.empty();
        }
    }

    public void onDelete(){
        ProjectInfo info = getSelected().orElse(null);
        if (info==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Please select one.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("You are going to delete project "+info.projectName+". Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && !result.get().equals(ButtonType.OK)) {
            return;
        }

        DeleteProjectResponse response = (DeleteProjectResponse)connection.sendRequest(new DeleteProjectRequest(info));
        if (response.success){
            updateProjects();
        }else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed!"+System.lineSeparator()+response.description);
            alert.showAndWait();
        }
    }

    public void onCancel(){
        stage.close();
    }


    public static OpenProjectDialogController open(){
        try {
            Stage stage= new Stage();
            stage.setTitle("Open a Project");

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
