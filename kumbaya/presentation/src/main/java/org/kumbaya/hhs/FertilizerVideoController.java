package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import java.io.IOException;
import java.util.ResourceBundle;

public class FertilizerVideoController {

    private FertilizerSceneController fertilizerSceneController = new FertilizerSceneController();
    private ResourceBundle bundle = ResourceBundle.getBundle("org/kumbaya/hhs/videos/cropvideos");

    private String key;
    private String goBackScene;

    FertilizerVideoController(String key) {
        this.key = bundle.getString(key);
        this.goBackScene = key;
    }

    @FXML
    private void goBackToFertilizers() throws IOException {
        if(goBackScene.equals("SP")){
            fertilizerSceneController.goToSweetPotato();
        }
        if (goBackScene.equals("MZ")){
            fertilizerSceneController.goToMaize();
        }
        if (goBackScene.equals("SB")){
            fertilizerSceneController.goToSoybean();
        }
    }

    @FXML
    private WebView webview;
    @FXML
    private ImageView back;
    @FXML
    private ImageView home;



    @FXML
    private void initialize() {

        webview.getEngine().loadContent(key);
        webview.setContextMenuEnabled(true);

        back.setOnMouseClicked((event) -> {
                try{goBackToFertilizers();}
                catch (IOException e) {e.printStackTrace();}});
        back.setOnTouchPressed((event) -> {
            try{goBackToFertilizers();}
            catch (IOException e) {e.printStackTrace();}});
        home.setOnMouseClicked((event) -> {
            try {
                App.switchToMain();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        home.setOnTouchPressed((event) -> {
            try {
                App.switchToMain();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
    }
}
