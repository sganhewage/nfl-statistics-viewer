package edu.guilford;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PlayerCareerTable extends TableView<Player> {
    private Player player;
    private ArrayList<Player> playerTable = new ArrayList<>();
    private String[] columnNames;
    private String[] attributeTypes;

    public PlayerCareerTable(Player player) throws IOException {
        super();
        this.player = player;
        columnNames = Player.getProfileCareerAttributes();
        attributeTypes = Player.getProfileCareerAttributeType();
        setTable();
    }

    public void setTable() {
        getPlayerTable();
        setColumns();
        setPlayerData();
    }

    private void getPlayerTable() {
        ArrayList<Player> careerTotals = new ArrayList<>();
        try {
            ArrayList<Integer> validYears = WebScrape.getValidYears();
            careerTotals = WebScrape.careerTotals(validYears.get(0), validYears.get(validYears.size()-1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Player p : careerTotals) {
            if (this.player.getID().equals(p.getID())) {
                playerTable.add(p);
                break;
            }
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
        for (int i = playerTable.size()-1; i >= 0; i--) {
            this.getItems().add(playerTable.get(i));
        }
    }
}