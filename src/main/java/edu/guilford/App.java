package edu.guilford;

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

    private int sceneWidth = 1280;
    private int sceneHeight = 720;

    private static String styleSheet = App.class.getResource("primer-dark.css").toExternalForm();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("seasonStatsPage"), sceneWidth, sceneHeight);
        scene.getStylesheets().add(styleSheet);

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        scene.getStylesheets().add(styleSheet);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}