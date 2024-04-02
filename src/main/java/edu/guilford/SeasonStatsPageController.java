package edu.guilford;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

import edu.guilford.WebScrape.InvalidYearException;

public class SeasonStatsPageController {
    private ArrayList<Player> players;
    private int listYear = PlayerTable.initYear;

    @FXML
    private PlayerTable playerTable;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scrollPane;

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
    private void initialize() throws IOException {
        playerTable = new PlayerTable();
        tableRoot.getChildren().add(playerTable);
        
        // resize the table to fit the group
        playerTable.prefWidthProperty().bind(tableRoot.widthProperty());
        playerTable.prefHeightProperty().bind(tableRoot.heightProperty());

        subtitle.setText(PlayerTable.initYear + " Season");

        setSlider();
        resetYearField();
    }

    @FXML
    private void handleBackButton() throws IOException {
        App.setRoot("startPage");
    }

    @FXML
    private void fadeIn() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), grid);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    @FXML
    private void selectYear(int year) throws IOException {
        players = WebScrape.createPlayerList(year);
    }

    @FXML
    private void updatePlayerTable() {
        playerTable = new PlayerTable(players);
        tableRoot.getChildren().clear();
        tableRoot.getChildren().add(playerTable);
        playerTable.prefWidthProperty().bind(tableRoot.widthProperty());
        playerTable.prefHeightProperty().bind(tableRoot.heightProperty());
        subtitle.setText(listYear + " Season");

        try {
            yearSlider.setMin(WebScrape.getValidYears().get(0));
            yearSlider.setMax(WebScrape.getValidYears().get(WebScrape.getValidYears().size()-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSlider();
        resetYearField();
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
            updatePlayerTable();
        } catch (InvalidYearException e) {
            yearFieldWarning.setText(e.getMessage().toString());
            yearFieldWarning.setVisible(true);
        } catch (NumberFormatException e) {
            yearFieldWarning.setText("Please enter a valid integer for the year.");
            yearFieldWarning.setVisible(true);
        }
    }

    @FXML
    private void getYearSlider() throws IOException {
        listYear = (int) yearSlider.getValue();
        selectYear(listYear);
        updatePlayerTable();
    }

    @FXML
    private void setSlider() {
        yearSlider.setValue(listYear);
    }
    
}
