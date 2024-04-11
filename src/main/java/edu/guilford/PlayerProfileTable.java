package edu.guilford;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlayerProfileTable extends TableView<Player> {
    private Player player;
    private ArrayList<Player> playerYears = new ArrayList<>();
    private String[] columnNames;
    private String[] attributeTypes;

    public PlayerProfileTable(Player player) throws IOException {
        super();
        this.player = player;
        columnNames = Player.getProfileAttributes();
        attributeTypes = Player.getProfileAttributeType();
        setTable();
    }

    public void setTable() {
        getPlayerYears();
        setColumns();
        setPlayerData();
    }

    private void getPlayerYears() {
        ArrayList<Integer> validYears = new ArrayList<>();
        try {
            validYears = WebScrape.getValidYears();
            for (int year : validYears) {
                ArrayList<Player> yearList = WebScrape.createPlayerList(year);
                for (Player p : yearList) {
                    if (this.player.getID().equals(p.getID())) {
                        playerYears.add(p);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
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
        for (Player p : playerYears) {
            System.out.println(p);
            this.getItems().add(p);
        }
    }
}
