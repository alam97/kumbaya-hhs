package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;

public class SoilMeasurementVideoController {

    @FXML
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;

    @FXML
    private void goBacktoSoilMeasurement() throws IOException {
        mp.stop();
        App.setRoot("soilmeasurementScene");
    }
    @FXML
    private void goBackMain() throws IOException {
        mp.stop();
        App.switchToMain();
    }

    @FXML
    private void initialize() {
        me = new Media(this.getClass().getResource("videos/HowToUseSensor.mp4").toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        mp.setAutoPlay(true);
    }

}