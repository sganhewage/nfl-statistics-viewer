package edu.guilford;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.ArrayList;

public class SeasonStatsPageController {
    private ArrayList<Player> players;

    @FXML
    private TableView<Player> playerTable;

    @FXML
    private void initialize() throws IOException {
        playerTable = new PlayerTable(2020);
    }
}
