package edu.guilford;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PlayerProfilePageController {
    protected static Player player = null;

    @FXML
    private Label title;

    @FXML
    private void initialize() {
        title.setText(player.getName());
    }

    @FXML
    private void handleReturnButton() throws IOException {
        App.setRoot("playerSearchPage");
    }
}
