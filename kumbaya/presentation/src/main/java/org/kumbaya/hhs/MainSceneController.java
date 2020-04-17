package org.kumbaya.hhs;

import javafx.fxml.FXML;

import java.io.IOException;

public class MainSceneController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("fertilizerPaneScene");
    }

    @FXML
    private void switchToPrices() throws IOException {
        App.setRoot("pricesScene");
    }

    @FXML
    private void switchToSoilMeasurement() throws IOException{
        App.setRoot("soilmeasurementVideoScene");
    }
}
