package org.kumbaya.hhs;


import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class FertilizerListController {

    private FertilizerSceneController fertilizerSceneController = new FertilizerSceneController();

    private String cropimage;
    private String fertilizerimage;
    private String goBackScene;

    @FXML
    private ImageView cropbox;
    @FXML
    private ImageView fertilizerbox;
    @FXML
    private ImageView home;
    @FXML
    private ImageView back;

    FertilizerListController(String cropimage, String fertilizerimage, String goBackScene) {
        this.cropimage = cropimage;
        this.fertilizerimage = fertilizerimage;
        this.goBackScene = goBackScene;
    }

    @FXML
    private void goBackToFertilizers() throws IOException {
        if (goBackScene.equals("SP")) {
            fertilizerSceneController.goToSweetPotato();
        }
        if (goBackScene.equals("MZ")) {
            fertilizerSceneController.goToMaize();
        }
        if (goBackScene.equals("SB")) {
            fertilizerSceneController.goToSoybean();
        }
    }

    public void initialize() {
        cropbox.setImage(new Image(getClass().getResource(cropimage).toString()));
        fertilizerbox.setImage(new Image(getClass().getResource(fertilizerimage).toString()));
        back.setOnMouseClicked((event) -> {
            try {
                goBackToFertilizers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        back.setOnTouchPressed((event) -> {
            try {
                goBackToFertilizers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        home.setOnMouseClicked((event) -> {
            try {
                App.switchToMain();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        home.setOnTouchPressed((event) -> {
            try {
                App.switchToMain();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}