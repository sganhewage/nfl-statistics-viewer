package edu.guilford;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlayerTable extends TableView<Player> {
    private ArrayList<Player> players;
    private String[] columnNames = Player.getAttributeNames();

    public PlayerTable(int year) throws IOException {
        this.players = WebScrape.createPlayerList(year);
        setColumns();
    }

    private void setColumns() {
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn<Player, String> column = new TableColumn<>(columnNames[i]);
            column.setCellValueFactory(new PropertyValueFactory<>(columnNames[i]));
            this.getColumns().add(column);
        }
    }
}
