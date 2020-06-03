package org.kumbaya.hhs;

import applogic.SoilDefiner;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class SoilMeasurementTransitionController extends MainViewsSwitcher {

    // will be the measurement changing view once the design is complete

    @FXML
    Text typeofSoil;

    @FXML
    private void initialize() {
        setText();
    }

    private void setText(){
        SoilDefiner soilDefiner = new SoilDefiner();
        String output = null;
        try {
            output = soilDefiner.defineSoil("user1");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        typeofSoil.setText(output);
    }

}
