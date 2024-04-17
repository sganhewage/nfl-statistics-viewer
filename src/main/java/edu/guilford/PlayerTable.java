package edu.guilford;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This class creates a TableView object that displays the players with data in a season.
 * It is used in the SeasonStatsPageController class to create a Table for each season.
 * @author Sandith Ganhewage
 * @version 1.0
 * @see SeasonStatsPageController 
 */
public class PlayerTable extends TableView<Player> {
    private ArrayList<Player> players;
    private String[] columnNames;
    private String[] attributeTypes;
    public static int initYear = 2023;

    /**
     * Constructor for the PlayerTable class. Generates a table based on the initYear variable.
     * @throws IOException
     */
    public PlayerTable() throws IOException {
        super();
        this.players = WebScrape.createPlayerList(initYear);

        columnNames = Player.getAttributes();
        attributeTypes = Player.getAttributeType();
        setTable();
    }
    
    /**
     * Constructor for the PlayerTable class. Generates a table based on an ArrayList of Players passed in.
     * @param players ArrayList of the players whose statistics will be displayed in the table.
     * @param isRange A boolean that determines if the table is for a range of years or a single year.
     */
    public PlayerTable(ArrayList<Player> players, boolean isRange) {
        super();
        this.players = players;
        if (isRange) {
            columnNames = Player.getRangeAttributes();
            attributeTypes = Player.getRangeAttributeType();
        } else {
            columnNames = Player.getAttributes();
            attributeTypes = Player.getAttributeType();
        }
        setTable();
    }

    /**
     * This method calls the necessary methods to set the table with the proper columns and data.
     */
    public void setTable() {
        setColumns();
        setPlayerData();
    }

    /**
     * This method sets the list of players that will be displayed in the table.
     * @param players ArrayList of the players whose statistics will be displayed in the table.
     */
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
    private void setPlayerData() {
        for (Player player : players) {
            this.getItems().add(player);
        }
    }
}
