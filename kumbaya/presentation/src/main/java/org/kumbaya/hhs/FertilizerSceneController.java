package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.media.AudioClip;

import java.io.IOException;

public class FertilizerSceneController extends MainViewsSwitcher {


    @FXML
    private void goToSweetPotato() throws IOException {
        App.setRoot("fertilizerChoiceScene");
    }



    @FXML
    private void playSweetPotato() {
        AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Fertilizers_crops_Sweetpotato.mp3").toString());
        mp3.play();
    }
    @FXML
    private void playMaize() {
        AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Fertilizers_crops_Maize.mp3").toString());
        mp3.play();
    }

    @FXML
    private void playSoybeans() {
        AudioClip mp3 = new AudioClip(this.getClass().getResource("voice_feedback/Screen Fertilizers_crops_Soybean.mp3").toString());
        mp3.play();
    }


}