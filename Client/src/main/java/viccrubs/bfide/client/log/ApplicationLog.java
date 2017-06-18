package viccrubs.bfide.client.log;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viccrubs.bfide.client.MainClient;
import viccrubs.bfide.client.controller.LogViewerController;
import viccrubs.bfide.client.model.Log;

import java.io.IOException;

/**
 * Created by viccrubs on 2017/5/30.
 */
public class ApplicationLog {
    private static ObservableList<Log> applicationLogList = FXCollections.observableArrayList();

    public ObservableList<Log> getLogList(){
        return applicationLogList;
    }

    public void addLog(Log log){
        applicationLogList.add(log);
    }

    public void showLogViewer(){
        try {
            Stage stage= new Stage();
            stage.setTitle("Log");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClient.class.getResource("/fxml/LogViewer.fxml"));

            Scene scene = new Scene(loader.load());
            LogViewerController controller = loader.getController();
            controller.setData(applicationLogList);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
