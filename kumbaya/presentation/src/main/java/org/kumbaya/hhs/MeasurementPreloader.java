package org.kumbaya.hhs;

import applogic.SoilDefiner;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MeasurementPreloader implements Initializable {

    private String soiltype;

    private void showResult(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("scenes/measurementResult.fxml"));
            SoilMeasurementResult soilMeasurementResult = new SoilMeasurementResult(path);
            loader.setController(soilMeasurementResult);
            App.setRoot(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Preloader().start();
    }

    class Preloader extends Thread {
        @Override
        public void run() {
            try {
                SoilDefiner soilDefiner = new SoilDefiner();
                try {
                    soiltype = soilDefiner.defineSoil("user1");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (soiltype.equals("Medium")) {
                            String path_ = "images/measurement_medium.png";
                            showResult(path_);
                        }
                        if (soiltype.equals("High")) {
                            String path_ = "images/measurement_high.png";
                            showResult(path_);
                        }
                        if (soiltype.equals("Low")) {
                            String path_ = "images/measurement_low.png";
                            showResult(path_);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}