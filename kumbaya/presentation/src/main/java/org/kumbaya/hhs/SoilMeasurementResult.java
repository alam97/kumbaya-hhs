package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SoilMeasurementResult {

    @FXML
    ImageView result;
    @FXML
    ImageView back;
    @FXML
    ImageView home;


    private String path;



   public SoilMeasurementResult(String path) {
        this.path = path;
    }

    public void initialize() {
        result.setImage(new Image(getClass().getResource(path).toString()));

        back.setOnMouseClicked((event) -> {    // lambda expression
            try {
                App.setRoot("soilmeasurementScene");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        back.setOnTouchPressed((event) -> {    // lambda expression
            try {
                App.setRoot("soilmeasurementScene");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        home.setOnMouseClicked((event) -> {    // lambda expression
            try {
                App.setRoot("mainScene");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        home.setOnTouchPressed((event) -> {    // lambda expression
            try {
                App.setRoot("mainScene");
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
    }

}
