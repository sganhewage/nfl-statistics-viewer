package edu.guilford;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.guilford.WebScrape.InvalidYearException;

public class SeasonStatsPageController {
    private int listYear = PlayerTable.initYear;
    private ArrayList<Player> players;

    private int fadeTime = 500; //milliseconds

    @FXML
    private PlayerTable playerTable;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scrollPane;

    public SeasonStatsPageController() throws IOException {
        players = WebScrape.createPlayerList(listYear);
    }

    @FXML
    private VBox tableRoot;

    @FXML
    private Label title;

    @FXML
    private Label subtitle;

    @FXML
    private TextField yearField;

    @FXML
    private Label yearFieldWarning;

    @FXML
    private Slider yearSlider;

    @FXML
    private VBox yearVBox;

    @FXML
    private VBox yearRangeVBox;

    @FXML
    private VBox leftPanel;

    @FXML
    private RadioButton yearRButton;

    @FXML
    private RadioButton yearRangeRButton;

    @FXML
    private ToggleGroup listTypeGroup;

    @FXML
    private Label subtitle1;

    @FXML
    private TextField startYearField;

    @FXML
    private TextField endYearField;

    @FXML
    private Label yearFieldWarning1;

    @FXML
    private VBox playerOverviewBox;

    @FXML
    private GridPane playerData;

    @FXML
    private Label playerDataPrompt;

    @FXML
    private Label playerOverviewTitle;

    @FXML
    private ImageView playerImage;

    @FXML
    private Label nameLabel;

    @FXML
    private Label posLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label teamLabel;

    @FXML
    private Button nextYearButton;

    @FXML
    private Button previousYearButton;

    @FXML
    private void initialize() throws IOException {
        updatePlayerTable(false);
        handleToggleGroup();
        fadeIn();
    }

    @FXML
    private void handleBackButton() throws IOException {
        App.setRoot("homePage");
    }

    @FXML
    private void fadeIn() {
        FadeTransition ft = new FadeTransition(Duration.millis(fadeTime), grid);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    @FXML
    private void selectYear(int year) throws IOException {
        players = WebScrape.createPlayerList(year);
    }

    @FXML
    private void updatePlayerTable(boolean isRange) {
        playerTable = new PlayerTable(players, isRange);
        tableRoot.getChildren().clear();
        tableRoot.getChildren().add(playerTable);
        playerTable.prefWidthProperty().bind(tableRoot.widthProperty());
        playerTable.prefHeightProperty().bind(tableRoot.heightProperty());

        if (!isRange) {
            subtitle.setText(listYear + " Season");
        }

        ArrayList<Integer> validYears = null;
        try {
            validYears = WebScrape.getValidYears();
        } catch (IOException e) {
            e.printStackTrace();
        }

        yearSlider.setMin(validYears.get(0));
        yearSlider.setMax(validYears.get(validYears.size()-1));

        if (listYear == validYears.get(0)) {
            previousYearButton.setDisable(true);
        } else {
            previousYearButton.setDisable(false);
        }

        if (listYear == validYears.get(validYears.size()-1)) {
            nextYearButton.setDisable(true);
        } else {
            nextYearButton.setDisable(false);
        }

        setSlider();
        resetYearField();
        getTableSelected();

        playerTable.setOnMouseClicked(e -> {
            getTableSelected();
        });
        playerTable.setOnKeyPressed(e -> {
            getTableSelected();
        });
    }

    @FXML
    private void resetYearField() {
        yearField.clear();
        yearField.setPromptText("Select Year");
        yearFieldWarning.setVisible(false);
    }

    @FXML
    private void getYear() throws IOException {
        try {
            listYear = Integer.parseInt(yearField.getText());
            selectYear(listYear);
            updatePlayerTable(false);
        } catch (InvalidYearException e) {
            yearFieldWarning.setText(e.getMessage().toString());
            yearFieldWarning.setVisible(true);
        } catch (NumberFormatException e) {
            yearFieldWarning.setText("Please enter a valid integer for the year.");
            yearFieldWarning.setVisible(true);
        }
    }

    @FXML
    private void getYearRange() throws IOException {
        try {
            int startYear = Integer.parseInt(startYearField.getText());
            int endYear = Integer.parseInt(endYearField.getText());
            if (startYear > endYear) {
                throw new InvalidYearException("Start year must be less than or equal to end year.");
            } else if (startYearField.getText().isEmpty() || endYearField.getText().isEmpty()) {
                throw new InvalidYearException("Please enter valid a year for both fields.");
            }
            players = WebScrape.careerTotals(startYear, endYear);
            updatePlayerTable(true);
            yearFieldWarning1.setVisible(false);
            subtitle1.setText(startYear + " - " + endYear + " Seasons");
        } catch (NumberFormatException e) {
            yearFieldWarning1.setText("Please enter a valid integer for each year.");
            yearFieldWarning1.setVisible(true);
        } catch (InvalidYearException e) {
            yearFieldWarning1.setText(e.getMessage().toString());
            yearFieldWarning1.setVisible(true);
        }
    }

    @FXML
    private void getTableSelected() {
        HashMap<String, String> teamAbbreviation = new HashMap<String, String>();
        WebScrape.teamNametoAbbreviation(teamAbbreviation);
        
        playerOverviewBox.getChildren().clear();
        playerOverviewBox.getChildren().add(playerOverviewTitle);
        playerOverviewBox.getChildren().add(playerDataPrompt);

        Player selectedPlayer = playerTable.getSelectionModel().getSelectedItem();

        if (selectedPlayer != null) {
            playerOverviewBox.getChildren().clear();
            playerOverviewBox.getChildren().add(playerOverviewTitle);
            playerOverviewBox.getChildren().add(playerData);
            try {
                Image img = WebScrape.playerProfileImage(selectedPlayer.getID());
                playerImage.setImage(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
            nameLabel.setText(selectedPlayer.getName());
            posLabel.setText("Pos: " + selectedPlayer.getPOS());
            ageLabel.setText("Age: " + String.valueOf(selectedPlayer.getAge()));
            teamLabel.setText("Team: " + teamAbbreviation.get(selectedPlayer.getTeam()));
        }
    }

    @FXML
    private void getYearSlider() throws IOException {
        listYear = (int) yearSlider.getValue();
        selectYear(listYear);
        updatePlayerTable(false);
    }

    @FXML
    private void setSlider() {
        yearSlider.setValue(listYear);
    }

    @FXML
    private void handleToggleGroup() {
        leftPanel.getChildren().remove(yearVBox);
        leftPanel.getChildren().remove(yearRangeVBox);
        if (yearRButton.isSelected()) {
            leftPanel.getChildren().add(yearVBox);
        } else if (yearRangeRButton.isSelected()) {
            leftPanel.getChildren().add(yearRangeVBox);
        }
    }

    @FXML
    private void handlePreviousYear() throws IOException {
        listYear--;
        selectYear(listYear);
        updatePlayerTable(false);
    }

    @FXML
    private void handleNextYear() throws IOException {
        listYear++;
        selectYear(listYear);
        updatePlayerTable(false);
    }

    @FXML
    private void switchToPlayerProfilePage() throws IOException {
        PlayerProfilePageController.player = playerTable.getSelectionModel().getSelectedItem();
        PlayerProfilePageController.previousPage = "seasonStatsPage";
        App.setRoot("playerProfilePage");
    }
}
