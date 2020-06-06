package org.kumbaya.hhs;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;

import java.io.IOException;

public class FertilizerVideoController extends MainViewsSwitcher {


    @FXML
    private void goBackToFertilizers() throws IOException {
        App.setRoot("fertilizerPaneScene");
    }

    @FXML
    private String videoLink;

    @FXML
    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    @FXML
    private WebView fertilizerWebView;
    public Text  fertilizerVideoTitle;

    @FXML
    private void initialize() {
        fertilizerVideoTitle.setText("Sweet Potato");
        fertilizerWebView.getEngine().loadContent("<iframe width='905' height='556' src='http://www.youtube.com/embed/MCnWi-OBtCg' />");
        fertilizerWebView.setContextMenuEnabled(true);
    }
}
