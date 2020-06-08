package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.AudioClip;

import java.io.IOException;

public class FertilizerSceneController extends MainChoice {

    @FXML
    private void goTo(String path, boolean SP, boolean MZ, boolean SB) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            FertilizerChoiceController controller = new FertilizerChoiceController(SP, MZ, SB);
            loader.setController(controller);
            App.setRoot(loader);
    }

    @FXML
    public void goToSweetPotato() throws IOException {
       goTo("scenes/fertilizerChoiceScene.fxml", true, false, false);
    }

    @FXML
    public void goToMaize() throws IOException {
        goTo("scenes/fertilizerChoiceScene.fxml", false, true, false);
    }

    @FXML
    public void goToSoybean() throws IOException {
        goTo("scenes/fertilizerChoiceScene.fxml", false, false, true);
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