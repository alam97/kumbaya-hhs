package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;

import java.io.IOException;

public class MainSceneController {

    @FXML
    private void switchToFertilizers() throws IOException {
        App.setRoot("fertilizerPaneScene");
    }

    @FXML
    private void switchToPrices() throws IOException {
        App.setRoot("pricesScene");
    }

    @FXML
    private void switchToSoilMeasurement() throws IOException{
        App.setRoot("soilmeasurementScene");
    }

    @FXML
    private void playMeasurement() {
        AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Soil measure.mp3").toString());
        mp3.play();
    }

    @FXML
    private void playFertilizers() {
        AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Fertilizers.mp3").toString());
        mp3.play();
    }

    @FXML
    private void playPrices() {
        AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Prices.mp3").toString());
        mp3.play();
    }
}
