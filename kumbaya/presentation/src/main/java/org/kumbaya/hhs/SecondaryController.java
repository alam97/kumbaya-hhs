package org.kumbaya.hhs;
import java.net.URI;

import javafx.application.HostServices;
import javafx.fxml.FXML;

import java.io.IOException;

public class SecondaryController {

    private HostServices hostServices ;

    public HostServices getHostServices() {
        return hostServices ;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices ;
    }

    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("mainScene");
    }

    @FXML
    private void goToSweetPotatoVideo(){
        // doesn't work
        // hostServices.showDocument("http://www.google.com");
    }


}