package edu.guilford;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * This class creates a TableView object that displays a player's career statistics.
 * It is used in the PlayerProfilePageController class to display a table of a player's career statistics.
 * @author Sandith Ganhewage
 * @version 1.0
 * @see PlayerProfilePageController 
 */
public class PlayerCareerTable extends TableView<Player> {
    private Player player;
    private ArrayList<Player> playerTable = new ArrayList<>();
    private String[] columnNames;
    private String[] attributeTypes;

    /**
     * Constructor for the PlayerCareerTable class. Generates a table based on the player passed in.
     * @param player The player whose career statistics will be displayed in the table.
     * @throws IOException
     */
    public PlayerCareerTable(Player player) throws IOException {
        super();
        this.player = player;
        columnNames = Player.getProfileCareerAttributes();
        attributeTypes = Player.getProfileCareerAttributeType();
        setTable();
    }

    /**
     * This method calls the necessary methods to set the table with the proper columns and data.
     */
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
    
    private void setPlayerData() {
        for (int i = playerTable.size()-1; i >= 0; i--) {
            this.getItems().add(playerTable.get(i));
        }
    }
}