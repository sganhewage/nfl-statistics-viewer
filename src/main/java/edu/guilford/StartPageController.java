package edu.guilford;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class StartPageController {

    private long fadeTime = 1500; // milliseconds

    @FXML
    private Label title;

    @FXML
    private Button startButton;

    @FXML
    private Button quitButton;

    @FXML
    private void initialize() {
        fadeIn();
    }

    @FXML
    private void quit() {
        System.exit(0);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        fadeOut();

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(fadeTime);
                    App.setRoot("homePage");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @FXML
    private void fadeIn() {
        FadeTransition FTtitle = new FadeTransition(Duration.millis(fadeTime), title);
        FTtitle.setFromValue(0.0);
        FTtitle.setToValue(1.0);

        FadeTransition FTstartButton = new FadeTransition(Duration.millis(fadeTime), startButton);
        FTstartButton.setFromValue(0.0);
        FTstartButton.setToValue(1.0);

        FadeTransition FTquitButton = new FadeTransition(Duration.millis(fadeTime), quitButton);
        FTquitButton.setFromValue(0.0);
        FTquitButton.setToValue(1.0);

        FTtitle.play();
        FTstartButton.play();
        FTquitButton.play();
    }

    @FXML
    private void fadeOut() {
        FadeTransition FTtitle = new FadeTransition(Duration.millis(fadeTime), title);
        FTtitle.setFromValue(1.0);
        FTtitle.setToValue(0.0);

        FadeTransition FTstartButton = new FadeTransition(Duration.millis(fadeTime), startButton);
        FTstartButton.setFromValue(1.0);
        FTstartButton.setToValue(0.0);

        FadeTransition FTquitButton = new FadeTransition(Duration.millis(fadeTime), quitButton);
        FTquitButton.setFromValue(1.0);
        FTquitButton.setToValue(0.0);

        FTtitle.play();
        FTstartButton.play();
        FTquitButton.play();
    }
}
