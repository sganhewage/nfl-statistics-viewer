package edu.guilford;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.guilford.WebScrape.InvalidYearException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PlayerSearchPageController {
    private static ArrayList<Integer> validYears;
    private static ArrayList<Player> allPlayers;
    private static ArrayList<Player> searchPlayers;

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
    private VBox yearRangeBox;

    @FXML
    private TextField startYearField;

    @FXML
    private TextField endYearField;

    @FXML
    private ChoiceBox<String> yearsChoiceBox;

    @FXML
    private Label yearFieldWarning;

    @FXML
    private void initialize() {
        searchResults.setOnMouseClicked(e -> {
            getPlayerSelected();
        });
        searchVBox.getChildren().remove(searchResults);
        searchVBox.getChildren().remove(noPlayersFoundLabel);
        yearRangeBox.getChildren().remove(yearFieldWarning);
        
        yearsChoiceBox.getItems().add("Select Sort Method");
        yearsChoiceBox.getItems().add("Sort Years (Ascending)");
        yearsChoiceBox.getItems().add("Sort Years (Descending)");
        yearsChoiceBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void search() throws IOException {
        searchVBox.getChildren().remove(searchResults);
        searchVBox.getChildren().remove(noPlayersFoundLabel);

        if (!searchBar.getText().isEmpty()) {
            searchResults.getItems().clear();

            searchPlayers = WebScrape.nameSearch(allPlayers, searchBar.getText());
            HashMap<String, String> teamAbbreviation = new HashMap<String, String>();
            WebScrape.teamNametoAbbreviation(teamAbbreviation);

            searchPlayers.sort((Player p1, Player p2) -> p2.getYear() - p1.getYear());
            searchPlayers = applyFilters(searchPlayers);

            for (Player player : searchPlayers) {
                searchResults.getItems().add(teamAbbreviation.get(player.getTeam()) + " (" + player.getYear() + "):   \t" + player.getName());
            }

            if (searchResults.getItems().isEmpty()) {
                searchVBox.getChildren().add(noPlayersFoundLabel);
            } else {
                searchVBox.getChildren().add(searchResults);
            }
        } else {
            searchVBox.getChildren().remove(searchResults);
            searchVBox.getChildren().add(noPlayersFoundLabel);
        }
    }

    @FXML
    private ArrayList<Player> applyFilters(ArrayList<Player> searchPlayers) {
        yearRangeBox.getChildren().remove(yearFieldWarning);

        int startYear = validYears.get(0);
        int endYear = validYears.get(validYears.size() - 1);
        
        try {
            if (!startYearField.getText().isEmpty()) {
                startYear = Integer.parseInt(startYearField.getText());
            }
            if (!endYearField.getText().isEmpty()) {
                endYear = Integer.parseInt(endYearField.getText());
            }

            if ((endYear > validYears.get(validYears.size() - 1) || startYear < validYears.get(0))
            ||  (endYear < validYears.get(0) || startYear > validYears.get(validYears.size() - 1))) {
                throw new InvalidYearException("Only enter years between " + validYears.get(0) + " and " + validYears.get(validYears.size()-1) + ".");
            }
            if (startYear > endYear) {
                throw new InvalidYearException("Start year must be less than or equal to end year.");
            }
        } catch (InvalidYearException e) {
            yearFieldWarning.setText(e.getMessage().toString());
            yearRangeBox.getChildren().add(yearFieldWarning);
        }

        if (yearsChoiceBox.getSelectionModel().getSelectedIndex() == 1) {
            searchPlayers.sort((Player p1, Player p2) -> p1.getYear() - p2.getYear());
        } else if (yearsChoiceBox.getSelectionModel().getSelectedIndex() == 2) {
            searchPlayers.sort((Player p1, Player p2) -> p2.getYear() - p1.getYear());
        }

        final int finalStartYear = startYear;
        final int finalEndYear = endYear;
        searchPlayers.removeIf(player -> player.getYear() < finalStartYear || player.getYear() > finalEndYear);

        return searchPlayers;
    }

    @FXML
    private void getPlayerSelected() {
        int selectedPlayerIndex = searchResults.getSelectionModel().getSelectedIndex();
        PlayerProfilePageController.player = searchPlayers.get(selectedPlayerIndex);
        try {
            App.setRoot("playerProfilePage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void resetFilters() {
        yearsChoiceBox.getSelectionModel().selectFirst();
        startYearField.clear();
        endYearField.clear();
        yearRangeBox.getChildren().remove(yearFieldWarning);
    }

    @FXML
    private void handleBackButton() throws IOException {
        App.setRoot("homePage");
    }
}
