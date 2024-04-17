package edu.guilford;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This class creates a TableView object that displays the various years of a players career.
 * It is used in the PlayerProfilePageController class to display a table of each year.
 * @author Sandith Ganhewage
 * @version 1.0
 * @see PlayerProfilePageController 
 */
public class PlayerProfileTable extends TableView<Player> {
    private Player player;
    private ArrayList<Player> playerYears = new ArrayList<>();
    private String[] columnNames;
    private String[] attributeTypes;

    private int rowHeight = 40;

    /**
     * Constructor for the PlayerProfileTable class. Generates a table based on the player passed in.
     * @param player The player whose statistics will be displayed in the table.
     * @throws IOException
     */
    public PlayerProfileTable(Player player) throws IOException {
        super();
        this.player = player;
        columnNames = Player.getProfileAttributes();
        attributeTypes = Player.getProfileAttributeType();
        this.setFixedCellSize(rowHeight);
        setTable();
    }

    /**
     * This method calls the necessary methods to set the table with the proper columns and data.
     */
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
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sets the player for this class
     * @param player Player object to be set
     */
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
    private void setPlayerData() {
        for (int i = playerYears.size()-1; i >= 0; i--) {
            this.getItems().add(playerYears.get(i));
        }
    }
}
