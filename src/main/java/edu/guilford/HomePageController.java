package edu.guilford;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HomePageController {

    private long fadeTime = 500; // milliseconds

    @FXML
    private ImageView logo;

    @FXML
    private Label title;

    @FXML
    private Label subtitle;

    @FXML
    private Button choice1;

    @FXML
    private Button choice2;

    @FXML
    private Text choice1Description;

    @FXML
    private Text choice2Description;

    @FXML
    private Button exitButton;

    @FXML
    private void switchToStart() throws IOException {
        fadeOut();

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(fadeTime);
                    NFLStatisticsViewer.setRoot("startPage");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @FXML
    private void switchToSeasonStats() throws IOException {
        fadeOut();

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(fadeTime);
                    NFLStatisticsViewer.setRoot("seasonStatsPage");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @FXML
    private void switchToPlayerProfile() throws IOException {
        fadeOut();

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(fadeTime);
                    NFLStatisticsViewer.setRoot("playerSearchPage");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @FXML
    private void initialize() {
        fadeIn();
    }

    @FXML
    private void fadeIn() {
        FadeTransition FTlogo = new FadeTransition(Duration.millis(fadeTime), logo);
        FTlogo.setFromValue(0.0);
        FTlogo.setToValue(1.0);

        FadeTransition FTtitle = new FadeTransition(Duration.millis(fadeTime), title);
        FTtitle.setFromValue(0.0);
        FTtitle.setToValue(1.0);

        FadeTransition FTsubtitle = new FadeTransition(Duration.millis(fadeTime), subtitle);
        FTsubtitle.setFromValue(0.0);
        FTsubtitle.setToValue(1.0);

        FadeTransition FTchoice1 = new FadeTransition(Duration.millis(fadeTime), choice1);
        FTchoice1.setFromValue(0.0);
        FTchoice1.setToValue(1.0);

        FadeTransition FTchoice2 = new FadeTransition(Duration.millis(fadeTime), choice2);
        FTchoice2.setFromValue(0.0);
        FTchoice2.setToValue(1.0);

        FadeTransition FTchoice1Description = new FadeTransition(Duration.millis(fadeTime), choice1Description);
        FTchoice1Description.setFromValue(0.0);
        FTchoice1Description.setToValue(1.0);

        FadeTransition FTchoice2Description = new FadeTransition(Duration.millis(fadeTime), choice2Description);
        FTchoice2Description.setFromValue(0.0);
        FTchoice2Description.setToValue(1.0);

        FadeTransition FTexitButton = new FadeTransition(Duration.millis(fadeTime), exitButton);
        FTexitButton.setFromValue(0.0);
        FTexitButton.setToValue(1.0);

        FTlogo.play();
        FTtitle.play();
        FTsubtitle.play();
        FTchoice1.play();
        FTchoice2.play();
        FTchoice1Description.play();
        FTchoice2Description.play();
        FTexitButton.play();
    }

    @FXML
    private void fadeOut() {
        FadeTransition FTlogo = new FadeTransition(Duration.millis(fadeTime), logo);
        FTlogo.setFromValue(1.0);
        FTlogo.setToValue(0.0);

        FadeTransition FTtitle = new FadeTransition(Duration.millis(fadeTime), title);
        FTtitle.setFromValue(1.0);
        FTtitle.setToValue(0.0);

        FadeTransition FTsubtitle = new FadeTransition(Duration.millis(fadeTime), subtitle);
        FTsubtitle.setFromValue(1.0);
        FTsubtitle.setToValue(0.0);

        FadeTransition FTchoice1 = new FadeTransition(Duration.millis(fadeTime), choice1);
        FTchoice1.setFromValue(1.0);
        FTchoice1.setToValue(0.0);

        FadeTransition FTchoice2 = new FadeTransition(Duration.millis(fadeTime), choice2);
        FTchoice2.setFromValue(1.0);
        FTchoice2.setToValue(0.0);

        FadeTransition FTchoice1Description = new FadeTransition(Duration.millis(fadeTime), choice1Description);
        FTchoice1Description.setFromValue(1.0);
        FTchoice1Description.setToValue(0.0);

        FadeTransition FTchoice2Description = new FadeTransition(Duration.millis(fadeTime), choice2Description);
        FTchoice2Description.setFromValue(1.0);
        FTchoice2Description.setToValue(0.0);

        FadeTransition FTexitButton = new FadeTransition(Duration.millis(fadeTime), exitButton);
        FTexitButton.setFromValue(1.0);
        FTexitButton.setToValue(0.0);

        FTlogo.play();
        FTtitle.play();
        FTsubtitle.play();
        FTchoice1.play();
        FTchoice2.play();
        FTchoice1Description.play();
        FTchoice2Description.play();
        FTexitButton.play();
    }
}