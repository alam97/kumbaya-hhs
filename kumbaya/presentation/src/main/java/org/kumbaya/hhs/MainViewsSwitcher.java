package org.kumbaya.hhs;

import javafx.fxml.FXML;

import java.io.IOException;

public class MainViewsSwitcher {

    @FXML
    private void switchtoMain() throws IOException {
        App.setRoot("mainScene");
    }

}
