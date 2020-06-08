package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;

public class FertilizerVideoController {

    private FertilizerSceneController fertilizerSceneController = new FertilizerSceneController();

    private String path;
    private String goBackScene;

    FertilizerVideoController(String path, String goBackScene) {
        this.path = path;
        this.goBackScene = goBackScene;
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
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;
    @FXML
    private ImageView back;
    @FXML
    private ImageView home;



    @FXML
    private void initialize() {
        me = new Media(this.getClass().getResource(path).toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        mp.setAutoPlay(true);
        back.setOnMouseClicked((event) -> {
            mp.stop();
                try{goBackToFertilizers();}
                catch (IOException e) {e.printStackTrace();}});
        back.setOnTouchPressed((event) -> {
            mp.stop();
            try{goBackToFertilizers();}
            catch (IOException e) {e.printStackTrace();}});
        home.setOnMouseClicked((event) -> {
            mp.stop();
            try {
                App.switchToMain();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        home.setOnTouchPressed((event) -> {
            mp.stop();
            try {
                App.switchToMain();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
    }
}
