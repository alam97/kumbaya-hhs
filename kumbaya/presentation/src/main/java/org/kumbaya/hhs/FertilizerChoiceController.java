package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.io.IOException;


public class FertilizerChoiceController extends MainViewsSwitcher{

    @FXML
    void goBackToFertilizers() throws IOException {
        App.setRoot("fertilizerPaneScene");
    }

}
