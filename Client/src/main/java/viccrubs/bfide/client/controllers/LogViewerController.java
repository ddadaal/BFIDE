package viccrubs.bfide.client.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import viccrubs.bfide.client.MainClient;
import viccrubs.bfide.client.log.ApplicationLog;
import viccrubs.bfide.client.models.Log;
import viccrubs.bfide.utilities.DateUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Created by viccrubs on 2017/5/30.
 */
public class LogViewerController {
    @FXML
    private TableView<Log> logTable;
    @FXML
    private TableColumn<Log,String> descriptionColumn;
    @FXML
    private TableColumn<Log,String> timeColumn;
    @FXML
    private TableColumn<Log,String> typeColumn;



    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(DateUtil.format(cellData.getValue().getTime())));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));
    }

    public void setData(ObservableList<Log> logList){
        logTable.setItems(logList);
    }




}
