package edu.guilford;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StartPageController {

    private long fadeTime = 1500; // milliseconds
    private long pinWheelSpeed = 10; // millis between movements

    @FXML
    private VBox pageContents;

    @FXML
    private Label title;

    @FXML
    private Button startButton;

    @FXML
    private Button quitButton;

    @FXML
    private Pane ribbon1;

    @FXML
    private Pane ribbon2;

    @FXML
    private void initialize() {
        fadeIn();
        teamLogoPinWheel(ribbon1, true);
        teamLogoPinWheel(ribbon2, false);
    }

    @FXML
    private void quit() {
        System.exit(0);
    }

    @FXML
    private void teamLogoPinWheel(Pane ribbon, boolean forward) {
        int imageWidth = 120;
        int imageSpread = 150;
        int spacing = 2;
        String teamLogosFolderLoc = App.class.getResource("NFLTeamLogos/").getPath();
        File[] files = new File(teamLogosFolderLoc).listFiles();
        ArrayList<ImageView> teamLogos = new ArrayList<ImageView>();
        for (File file : files) {
            ImageView teamLogo = new ImageView(new Image(file.toURI().toString()));
            teamLogo.setFitHeight(100);
            teamLogo.setFitWidth(imageWidth);
            teamLogos.add(teamLogo);
            ribbon.getChildren().add(teamLogo);
        }
        if (forward) {
            for (int i = 0; i < teamLogos.size(); i++) {
                teamLogos.get(i).setLayoutX(imageSpread * i);
            }
        } else {
            for (int i = 0; i < teamLogos.size(); i++) {
                teamLogos.get(i).setLayoutX(ribbon1.getPrefWidth() - (imageSpread * i));
            }
        }
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(pinWheelSpeed), e -> {
                for (ImageView teamLogo : teamLogos) {
                    double newX = forward ? teamLogo.getLayoutX() - 1 : teamLogo.getLayoutX() + 1;
                    if (forward && newX < -imageWidth) {
                        newX = (teamLogos.size() - 1) * (imageSpread + spacing) + imageWidth - imageWidth; // Move the image to the end
                    } else if (!forward && newX > (teamLogos.size() - 2) * (imageSpread + spacing) + imageWidth) {
                        newX = -imageWidth; // Move the image to the beginning
                    }
                    teamLogo.setLayoutX(newX);
                }
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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
        FadeTransition FT = new FadeTransition(Duration.millis(fadeTime), pageContents);
        FT.setFromValue(0.0);
        FT.setToValue(1.0);

        FT.play();
    }

    @FXML
    private void fadeOut() {
        FadeTransition FT = new FadeTransition(Duration.millis(fadeTime), pageContents);
        FT.setFromValue(1.0);
        FT.setToValue(0.0);

        FT.play();
    }
}
