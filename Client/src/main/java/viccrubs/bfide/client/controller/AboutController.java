package viccrubs.bfide.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import viccrubs.bfide.client.MainClient;

import java.io.IOException;

/**
 * Created by viccrubs on 2017/5/30.
 */
public class AboutController {

    @FXML
    private Hyperlink linkHomepage;
    public static void open(){
        try {
            Stage stage= new Stage();
            stage.setTitle("About");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainClient.class.getResource("/fxml/About.fxml"));

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openHomepage(){
        openURL("https://viccrubs.tk");
    }

    public static void openURL(String url){
        try {
            java.net.URI uri = java.net.URI.create(url);
            java.awt.Desktop dp = java.awt.Desktop.getDesktop();
            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                dp.browse(uri);
            }
        } catch (NullPointerException | IOException e) {
            // 此为uri为空时抛出异常
            e.printStackTrace();
        }
    }

}
