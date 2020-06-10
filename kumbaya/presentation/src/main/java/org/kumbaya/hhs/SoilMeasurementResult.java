package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SoilMeasurementResult {

    @FXML
    private ImageView result;
    @FXML
    private ImageView back;
    @FXML
    private ImageView home;
    @FXML
    private ImageView fertilizer;

    private String path;

    SoilMeasurementResult(String path) {
        this.path = path;
    }

    public void initialize() {
        result.setImage(new Image(getClass().getResource(path).toString()));

        back.setOnMouseClicked((event) -> {
            try {
                App.setRoot("soilmeasurementScene");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        back.setOnTouchPressed((event) -> {
            try {
                App.setRoot("soilmeasurementScene");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        home.setOnMouseClicked((event) -> {
            try {
                App.setRoot("mainScene");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        home.setOnTouchPressed((event) -> {
            try {
                App.setRoot("mainScene");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        fertilizer.setOnMouseClicked((event) -> {
            try {
                App.setRoot("fertilizerPaneScene");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fertilizer.setOnTouchPressed((event) -> {
            try {
                App.setRoot("fertilizerPaneScene");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}