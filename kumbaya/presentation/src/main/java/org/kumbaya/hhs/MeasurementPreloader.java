package org.kumbaya.hhs;

import applogic.SoilDefiner;
import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MeasurementPreloader implements Initializable {

    private String soiltype;

    /*private void showResult(String path){
        try {
              FXMLLoader loader = new FXMLLoader(App.class.getResource(""));
              loader.load();
            SoilMeasurementResult soilMeasurementResult = loader.getController();
            soilMeasurementResult.setResult(path);
            App.setRoot("measurementTransitionScene");
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Preloader().start();
    }

    class Preloader extends Thread{
        @Override
        public void run() {
            try{
                SoilDefiner soilDefiner = new SoilDefiner();
                try {
                    soiltype = soilDefiner.defineSoil("user1");
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(soiltype.equals("Medium")){
                            try {
                                App.setRoot("measurementMedium");
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        if(soiltype.equals("High")){
                            try {
                                App.setRoot("measurementHigh");
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        if(soiltype.equals("Low")){
                            try {
                                App.setRoot("measurementLow");
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
