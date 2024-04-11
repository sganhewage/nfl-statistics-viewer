package edu.guilford;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PlayerProfilePageController {
    protected static String previousPage = null;
    protected static Player player = null;

    @FXML
    private Label title;

    @FXML
    private VBox tableRoot;

    @FXML
    private PlayerProfileTable playerTable;

    @FXML
    private ImageView playerImage;

    @FXML
    private void initialize() {
        title.setText(player.getName());
        try {
            Image img = WebScrape.playerProfileImage(player.getID());
            playerImage.setImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        updatePlayerTable();
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
        playerTable.prefHeightProperty().bind(tableRoot.heightProperty());
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
