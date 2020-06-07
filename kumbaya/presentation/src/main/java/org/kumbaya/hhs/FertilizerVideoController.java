package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;

public class FertilizerVideoController extends MainViewsSwitcher {


    private String path;
    private String goBackScene;

    public FertilizerVideoController(String path, String goBackScene) {
        this.path = path;
        this.goBackScene = goBackScene;
    }

    @FXML
    private void goBackToFertilizers() throws IOException {
        App.setRoot(goBackScene);
    }

    @FXML
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;
    @FXML
    private ImageView back;



    @FXML
    private void initialize() {
        me = new Media(this.getClass().getResource(path).toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        mp.setAutoPlay(true);
        back.setOnMouseClicked((event) -> {
                try{goBackToFertilizers();}
                catch (IOException e) {e.printStackTrace();}});
    }
}
