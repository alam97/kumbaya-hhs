package org.kumbaya.hhs;
import java.io.IOException;
import javafx.fxml.FXML;


public class ThirdController {
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
