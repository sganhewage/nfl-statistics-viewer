package edu.guilford;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.HashMap;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    private int sceneWidth = 1280;
    private int sceneHeight = 720;
    
    private static String styleSheet = App.class.getResource("primer-dark.css").toExternalForm();

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        scene = new Scene(loadFXML("playerProfilePage"), sceneWidth, sceneHeight);
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

    public static void main(String[] args) throws IOException, InterruptedException {
        Player player = WebScrape.createPlayerList(2018).get(2);
        System.out.println(player);
        HashMap<String, ? extends Object> map = WebScrape.getPlayerInfo(player);
        System.out.println(map);

        launch();
    }

}