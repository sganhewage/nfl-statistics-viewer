package edu.guilford;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class is the driver program for the NFL Statistics Application.
 * This holds the general layout of the application, instantiating the scene class, sets
 * its width and height, and sets a style sheet. This class also controls the fxml page shown on screen,
 * using a static method to change the page from any other class.
 * @author Sandith Ganhewage
 * @version 1.0 4-16-24
 */
public class NFLStatisticsViewer extends Application {
    private static Scene scene;

    private int sceneWidth = 1280;
    private int sceneHeight = 720;
    
    private static String styleSheet = NFLStatisticsViewer.class.getResource("primer-dark.css").toExternalForm();

    /**
     * Sets the value of the scene object using the loadFXML method, and set the style sheet.
     * It then adds the scene to the stage.
     * @param stage The stage object that the scene is added to.
     * @throws IOException If the fxml file is not found. 
     */
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        scene = new Scene(loadFXML("startPage"), sceneWidth, sceneHeight);
        scene.getStylesheets().add(styleSheet);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to change the page shown on screen. It can be 
     * called from other classes
     * @param fxml The name of the fxml file to be shown on screen.
     * @throws IOException If the fxml file is not found.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        scene.getStylesheets().add(styleSheet);
    }

    /**
     * This method is used to load the fxml file and return the parent object.
     * @param fxml The name of the fxml file to be loaded.
     * @return The parent object of the fxml file.
     * @throws IOException If the fxml file is not found.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NFLStatisticsViewer.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        launch();
    }
}