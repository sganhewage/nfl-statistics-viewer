package edu.guilford;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerProfilePageController {
    protected static String previousPage = null;
    protected static Player player = null;

    @FXML
    private Label title;

    @FXML
    private VBox tableRoot;

    @FXML
    private VBox careerTableRoot;

    @FXML
    private PlayerProfileTable playerTable;

    @FXML
    private PlayerCareerTable careerTable;

    @FXML
    private ImageView playerImage;

    @FXML
    private GridPane playerInfoGrid;

    @FXML
    private Text name;

    @FXML
    private Text position;

    @FXML
    private Text team;

    @FXML
    private Text draft;

    @FXML
    private Text htwt;

    @FXML
    private Text born;

    @FXML
    private Text college;

    @FXML
    private Text highSchool;

    @FXML
    private Text careerSubtitle;


    @FXML
    private void initialize() throws IOException, InterruptedException {
        title.setText(player.getName());
        try {
            Image img = WebScrape.playerProfileImage(player.getID());
            playerImage.setImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        updatePlayerTable();
        updateCareerTable();

        careerSubtitle.setText("Career Totals (" + playerTable.getItems().size() + " seasons)");

        ArrayList<String> playerInfo = WebScrape.getPlayerInfo(player);
        System.out.println(playerInfo);

        name.setText(playerInfo.get(0));
        position.setText(playerInfo.get(1));
        htwt.setText(playerInfo.get(2));
        team.setText(playerInfo.get(3));
        born.setText(playerInfo.get(4));
        college.setText(playerInfo.get(5));
        highSchool.setText(playerInfo.get(6));
        draft.setText(playerInfo.get(7));
    }

    private void updateCareerTable() {
        try {
            careerTable = new PlayerCareerTable(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
        careerTableRoot.getChildren().clear();
        careerTableRoot.getChildren().add(careerTable);
        careerTable.prefWidthProperty().bind(careerTableRoot.widthProperty());
        careerTableRoot.setMaxHeight(careerTable.getPrefHeight());
    }

    @FXML
    private void updatePlayerTable() {
        try {
            playerTable = new PlayerProfileTable(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableRoot.getChildren().clear();
        tableRoot.getChildren().add(playerTable);
        playerTable.prefWidthProperty().bind(tableRoot.widthProperty());
        tableRoot.setMaxHeight(playerTable.getPrefHeight());
    }

    @FXML
    private void handleSearchButton() throws IOException {
        App.setRoot("playerSearchPage");
    }

    @FXML
    private void handleBackButton() throws IOException {
        App.setRoot(previousPage);
    }
}
