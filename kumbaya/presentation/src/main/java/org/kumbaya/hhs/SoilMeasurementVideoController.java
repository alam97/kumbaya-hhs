package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class SoilMeasurementVideoController extends MainViewsSwitcher {


    @FXML
    private WebView deviceVideoWebView;

    @FXML
    private void initialize() {
        deviceVideoWebView.getEngine().loadContent("<iframe width='775' height='435' src='http://www.youtube.com/embed/4bvrMDpVv2I' />");
        deviceVideoWebView.setContextMenuEnabled(true);
        }


    @FXML
    private void switchToMeasuring(){

    }

}
