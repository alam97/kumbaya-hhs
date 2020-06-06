package org.kumbaya.hhs;

import javafx.fxml.FXML;
import java.io.IOException;

public class SoilMeasurementResult extends MainViewsSwitcher {

    @FXML
    private void goBackToSoilMeasurement() throws IOException {
        App.setRoot("soilmeasurementScene");
    }

}
