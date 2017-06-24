package viccrubs.bfide.client.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viccrubs.bfide.client.model.Log;
import viccrubs.bfide.util.DateUtil;

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
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(DateUtil.format(cellData.getValue().getTime())));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));
    }

    public void setData(ObservableList<Log> logList){
        logTable.setItems(logList);
    }




}
