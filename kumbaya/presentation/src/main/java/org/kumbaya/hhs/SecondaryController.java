package org.kumbaya.hhs;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("third");
    }
}