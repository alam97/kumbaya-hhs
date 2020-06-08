package org.kumbaya.hhs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainScene"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setRoot(FXMLLoader loader) throws IOException{
        Parent root = loader.load();
        scene.setRoot(root);
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("scenes/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static void switchToMain() throws IOException {
        setRoot("mainScene");
    }

    public static void main(String[] args) {
        launch();
    }

}