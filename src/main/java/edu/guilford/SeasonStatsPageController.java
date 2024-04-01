package edu.guilford;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class SeasonStatsPageController {
    private ArrayList<Player> players;

    @FXML
    private TableView<Player> playerTable;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox tableRoot;

    @FXML
    private void initialize() throws IOException {
        playerTable = new PlayerTable();
        tableRoot.getChildren().add(playerTable);
        
        // resize the table to fit the group
        playerTable.prefWidthProperty().bind(tableRoot.widthProperty());
        playerTable.prefHeightProperty().bind(tableRoot.heightProperty());

    }
}
