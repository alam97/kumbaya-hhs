package org.kumbaya.hhs;

import javafx.fxml.FXML;

import java.io.IOException;


public class FertilizerChoiceController extends MainViewsSwitcher{

    @FXML
    void goBackToFertilizers() throws IOException {
        App.setRoot("fertilizerPaneScene");
    }

}
