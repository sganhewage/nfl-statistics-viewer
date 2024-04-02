package edu.guilford;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlayerTable extends TableView<Player> {
    private ArrayList<Player> players;
    private String[] columnNames = Player.getAttributes();
    private String[] attributeTypes = Player.getAttributeType();
    public static int initYear = 2023;

    public PlayerTable() throws IOException {
        super();
        this.players = WebScrape.createPlayerList(initYear);
        setTable();
    }

    public PlayerTable(ArrayList<Player> players) {
        super();
        this.players = players;
        setTable();
    }

    public void setTable() {
        setColumns();
        setPlayerData();
    }

    public void setPlayerList(ArrayList<Player> players) {
        this.players = players;
    }

    private void setColumns() {
        for (int i = 0; i < columnNames.length-1; i++) {
            
            switch (attributeTypes[i]) {
                case "int":
                    TableColumn<Player, Integer> intColumn = new TableColumn<>(columnNames[i]);
                    intColumn.setCellValueFactory(new PropertyValueFactory<>(columnNames[i]));
                    this.getColumns().add(intColumn);
                    break;
                case "double":
                    TableColumn<Player, Double> doubleColumn = new TableColumn<>(columnNames[i]);
                    doubleColumn.setCellValueFactory(new PropertyValueFactory<>(columnNames[i]));
                    this.getColumns().add(doubleColumn);
                    break;
                case "String":
                    TableColumn<Player, String> stringColumn = new TableColumn<>(columnNames[i]);
                    stringColumn.setCellValueFactory(new PropertyValueFactory<>(columnNames[i]));
                    this.getColumns().add(stringColumn);
                    break;
            }
        }
    }

    // grab each relevant player attribute and add it to the table under the correct column
    public void setPlayerData() {
        for (Player player : players) {
            this.getItems().add(player);
        }
    }
}
