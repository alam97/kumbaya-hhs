package org.kumbaya.hhs;

import javafx.fxml.FXML;

import java.io.IOException;

public class SecondaryController extends MainViewsSwitcher {

    FertilizerVideoController fertilizerVideoController;

    @FXML
    private void goToSweetPotatoVideo() throws IOException {
        App.setRoot("fertilizerVideoScene");
    }


}