package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;

import java.io.IOException;

public class SoilMeasurementSceneController extends MainChoice {

    @FXML
    private void goToVideo() throws IOException {
        App.setRoot("soilmeasurementVideoScene");
    }

    @FXML
    private void goToMeasurement() throws IOException{
        App.setRoot("measuringLoading");
    }

    @FXML
    private void playMeasurementVideo() {
        AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Video.mp3").toString());
        mp3.play();
    }
    @FXML
    private void playsoilMeasurement() {
        AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Measurement.mp3").toString());
        mp3.play();
    }

}
