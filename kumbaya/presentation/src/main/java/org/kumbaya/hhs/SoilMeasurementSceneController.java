package org.kumbaya.hhs;

import javafx.fxml.FXML;

import java.io.IOException;

public class SoilMeasurementSceneController extends MainViewsSwitcher {

    @FXML
    private void goToVideo() throws IOException {
        App.setRoot("soilmeasurementVideoScene");
    }

    @FXML
    private void goToMeasurement() throws IOException{
        App.setRoot("measurementTransitionScene");
    }
}
