package org.kumbaya.hhs;

import javafx.fxml.FXML;

import java.io.IOException;

public class MainChoice {

    @FXML
    protected void goToMain() throws IOException {
        App.switchToMain();
    }
}
