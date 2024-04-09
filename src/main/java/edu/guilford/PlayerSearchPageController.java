package edu.guilford;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PlayerSearchPageController {
    private static ArrayList<Integer> validYears;
    private static ArrayList<Player> allPlayers;

    public PlayerSearchPageController() {
        try {
            validYears = WebScrape.getValidYears();
            allPlayers = WebScrape.careerTotals(validYears.get(0), validYears.get(validYears.size() - 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> searchResults;

    @FXML
    private VBox searchVBox;

    @FXML
    private Label noPlayersFoundLabel;

    @FXML
    private void initialize() {
        searchVBox.getChildren().remove(searchResults);
        searchVBox.getChildren().remove(noPlayersFoundLabel);
    }

    @FXML
    private void search() {
        searchResults.getItems().clear();
        searchVBox.getChildren().remove(searchResults);
        searchVBox.getChildren().remove(noPlayersFoundLabel);

        String query = searchBar.getText().toLowerCase().strip().replaceAll(" ", "");
        for (Player player : allPlayers) {
            if (player.getName().toLowerCase().contains(query)) {
                searchResults.getItems().add(player.getName());
            }
        }

        if (searchResults.getItems().isEmpty()) {
            searchVBox.getChildren().add(noPlayersFoundLabel);
        } else {
            searchVBox.getChildren().add(searchResults);
        }
    }
}
