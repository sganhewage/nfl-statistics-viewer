package edu.guilford;

import java.io.Serializable;

/**
 * This class is used to store the player data. It is used to store the player's
 * statistics for a single year. The class also contains methods to get and set
 * the player's data, as well as methods to get the attributes of the player
 * class.
 * The class also contains methods to get and set the player's data, as well as
 * methods to get the attributes of the player class.
 * @author Sandith Ganhewage
 * @version 1.0 4-16-24
 */
public class Player implements Serializable {
    /**
     * This is a serial version UID for the Player class.
     */
    private static final long serialVersionUID = 8265251544591419712L;

    private int Year;
    private String Name;
    private String ID;
    private String Team;
    private String POS;
    private int Age;
    private int GP;
    private int GS;
    private int CMP;
    private int PASS_ATT;
    private int PASS_YDS;
    private int PASS_TDS;
    private int INT;
    private int RUSH_ATT;
    private int RUSH_YDS;
    private double RUSH_YA;
    private int RUSH_TDS;
    private int TGT;
    private int REC;
    private int REC_YDS;
    private double REC_YA;
    private int REC_TDS;
    private int FUM;
    private int FUM_LOST;
    private int TOTAL_TDS;
    private int POS_RANK;
    private int OVR_RANK;

    /**
     * This is a toString method that returns the player's data in a string format.
     * @return player data
     */
    @Override
    public String toString() {
        return "Player [Year=" + Year + ", Name=" + Name + ", ID=" + ID + ", Team=" + Team + ", Position=" + POS
            + ", Age=" + Age + ", Games_Played=" + GP + ", Games_Started=" + GS
            + ", Passing_Completions=" + CMP + ", Passing_Attempts=" + PASS_ATT
            + ", Passing_Yards=" + PASS_YDS + ", Passing_Touchdowns=" + PASS_TDS + ", Interceptions="
            + INT + ", Rushing_Attempts=" + RUSH_ATT + ", Rushing_Yards=" + RUSH_YDS
            + ", Rushing_Yards_Per_Attempt=" + RUSH_YA + ", Rushing_Touchdowns="
            + RUSH_TDS + ", Receiving_Targets=" + TGT + ", Receptions=" + REC
            + ", Receiving_Yards=" + REC_YDS + ", Yards_Per_Reception=" + REC_YA
            + ", Receiving_Touchdowns=" + REC_TDS + ", Fumbles=" + FUM + ", Fumbles_Lost="
            + FUM_LOST + ", Total_Touchdowns=" + TOTAL_TDS + ", Position_Rank=" + POS_RANK
            + ", Overall_Rank=" + OVR_RANK + "]";
    }

    /**
     * This method is used to get the attributes of the player class.
     * This is used to populate the table in the PlayerTable class.
     * @see PlayerTable
     * @return array of strings with attributes of the player class
     */
    public static String[] getAttributes() {
        return new String[]{
            "Year", "Name", "Team", "POS", "Age", "OVR_RANK", "POS_RANK", "GP", "GS", "CMP", "PASS_ATT",
            "PASS_YDS", "PASS_TDS", "INT", "RUSH_ATT", "RUSH_YDS", "RUSH_YA", "RUSH_TDS",
            "TGT", "REC", "REC_YDS", "REC_YA", "REC_TDS", "FUM", "FUM_LOST", "TOTAL_TDS"
        };
    }

    /**
     * This method is used to get the names of the attributes of the player class.
     * This is used to populate the columns of the Player Table.
     * @see PlayerTable
     * @return array of strings with the names of attributes of the player class
     */
    public static String[] getAttributeNames() {
        return new String[]{
            "Year", "Name", "Team", "Position", "Age","Overall Rank","Position Rank", "Games Played", "Games Started",
            "Passing Completions", "Passing Attempts", "Passing Yards", "Passing Touchdowns",
            "Interceptions", "Rushing Attempts", "Rushing Yards", "Rushing Yards Per Attempt",
            "Rushing Touchdowns", "Receiving Targets", "Receptions", "Receiving Yards",
            "Yards Per Reception", "Receiving Touchdowns", "Fumbles", "Fumbles Lost",
            "Total Touchdowns"
        };
    }

    /**
     * This method is used to get the type of the attributes of the player class.
     * This is used to set column data types in the PlayerTable class.
     * @see PlayerTable
     * @return an array of Strings with the data types of the attributes
     */
    public static String[] getAttributeType() {
        return new String[]{
            "int", "String", "String", "String", "int","int","int", "int", "int",
            "int", "int", "int", "int",
            "int", "int", "int", "double",
            "int", "int", "int", "int",
            "double", "int", "int", "int",
            "int"
        };
    }

    /**
     * This method returns the specific attributes that are used in creating tables
     * for player totals over a range of years. This is used to populate the columns of the Player Table.
     * @see PlayerTable
     * @return array of strings with attributes of the player class for year range tables
     */
    public static String[] getRangeAttributes() {
        return new String[]{
            "Name", "Team", "POS", "Age", "GP", "GS", "CMP", "PASS_ATT",
            "PASS_YDS", "PASS_TDS", "INT", "RUSH_ATT", "RUSH_YDS", "RUSH_YA", "RUSH_TDS",
            "TGT", "REC", "REC_YDS", "REC_YA", "REC_TDS", "FUM", "FUM_LOST", "TOTAL_TDS"
        };
    }

    /**
     * This method returns the specific attribute types that are used in creating tables
     * for player totals over a range of years. This is used to set the datatypes for the columns of the Player Table.
     * @see PlayerTable
     * @return array of strings with attribute types of the player class for year range tables
     */
    public static String[] getRangeAttributeType() {
        return new String[]{
            "String", "String", "String", "int", "int", "int", "int", "int",
            "int", "int", "int", "int", "int", "double", "int",
            "int", "int", "int", "double", "int", "int", "int", "int"
        };
    }

    /**
     * This method returns the names of the specific attributes that are used in creating tables
     * for player totals over a range of years. This is used to populate the columns of the Player Table.
     * @see PlayerTable
     * @return array of strings with the names of attributes of the player class for year range tables
     */
    public static String[] getRangeAttributeNames() {
        return new String[]{
            "Name", "Team", "Position", "Age", "Games Played", "Games Started",
            "Passing Completions", "Passing Attempts", "Passing Yards", "Passing Touchdowns",
            "Interceptions", "Rushing Attempts", "Rushing Yards", "Rushing Yards Per Attempt",
            "Rushing Touchdowns", "Receiving Targets", "Receptions", "Receiving Yards",
            "Yards Per Reception", "Receiving Touchdowns", "Fumbles", "Fumbles Lost",
            "Total Touchdowns"
        };
    }

    /**
     * This method returns the specific attribute types that are used in creating tables
     * for player profile tables. This is used to populate the Player Table.
     * @see PlayerTable
     * @return array of strings with attribute names of the player class for player profile tables
     */
    public static String[] getProfileAttributes() {
        return new String[]{
            "Year", "Team", "Age", "POS", "OVR_RANK", "POS_RANK", "GP", "GS", "CMP", "PASS_ATT",
            "PASS_YDS", "PASS_TDS", "INT", "RUSH_ATT", "RUSH_YDS", "RUSH_YA", "RUSH_TDS",
            "TGT", "REC", "REC_YDS", "REC_YA", "REC_TDS", "FUM", "FUM_LOST", "TOTAL_TDS"
        };
    }

    /**
     * This method returns the names of the specific attributes that are used in creating tables
     * for player profile tables. This is used to populate the columns of the Player Table.
     * @see PlayerTable
     * @return array of strings with the names of attributes of the player class for player profile tables
     */
    public static String[] getProfileAttributeNames() {
        return new String[]{
            "Year", "Team", "Age", "Position", "Overall Rank","Position Rank", "Games Played", "Games Started",
            "Passing Completions", "Passing Attempts", "Passing Yards", "Passing Touchdowns",
            "Interceptions", "Rushing Attempts", "Rushing Yards", "Rushing Yards Per Attempt",
            "Rushing Touchdowns", "Receiving Targets", "Receptions", "Receiving Yards",
            "Yards Per Reception", "Receiving Touchdowns", "Fumbles", "Fumbles Lost",
            "Total Touchdowns"
        };
    }

    /**
     * This method returns the specific attribute types that are used in creating tables
     * for player profile tables. This is used to set the datatypes for the columns of the Player Table.
     * @see PlayerTable
     * @return array of strings with attribute types of the player class for player profile tables
     */
    public static String[] getProfileAttributeType() {
        return new String[]{
            "int", "String", "int", "String", "int", "int", "int", "int", "int", "int",
            "int", "int", "int", "int", "int", "double", "int",
            "int", "int", "int", "double", "int", "int", "int", "int"
        };
    }

    /**
     * This method returns the specific attributes that are used in creating tables
     * for player profile career totals. This is used to populate the columns of the Player Table.
     * @see PlayerTable
     * @return array of strings with attributes of the player class for player profile career totals
     */
    public static String[] getProfileCareerAttributes() {
        return new String[]{
            "GP", "GS", "CMP", "PASS_ATT",
            "PASS_YDS", "PASS_TDS", "INT", "RUSH_ATT", "RUSH_YDS", "RUSH_YA", "RUSH_TDS",
            "TGT", "REC", "REC_YDS", "REC_YA", "REC_TDS", "FUM", "FUM_LOST", "TOTAL_TDS"
        };
    }

    /**
     * This method returns the names of the specific attributes that are used in creating tables
     * for player profile career totals. This is used to populate the columns of the Player Table.
     * @see PlayerTable
     * @return array of strings with the names of attributes of the player class for player profile career totals
     */
    public static String[] getProfileCareerAttributeNames() {
        return new String[]{
            "Games Played", "Games Started",
            "Passing Completions", "Passing Attempts", "Passing Yards", "Passing Touchdowns",
            "Interceptions", "Rushing Attempts", "Rushing Yards", "Rushing Yards Per Attempt",
            "Rushing Touchdowns", "Receiving Targets", "Receptions", "Receiving Yards",
            "Yards Per Reception", "Receiving Touchdowns", "Fumbles", "Fumbles Lost",
            "Total Touchdowns"
        };
    }

    /**
     * This method returns the specific attribute types that are used in creating tables
     * for player profile career totals. This is used to set the datatypes for the columns of the Player Table.
     * @see PlayerTable
     * @return array of strings with attribute types of the player class for player profile career totals
     */
    public static String[] getProfileCareerAttributeType() {
        return new String[]{
            "int", "int", "int", "int",
            "int", "int", "int", "int", "int", "double", "int",
            "int", "int", "int", "double", "int",
            "int", "int", "int"
        };
    }

    /**
     * This is a generic method to get the attributes of the player class.
     * @return array of strings with the attributes of the player class
     */
    public String[] attributesToStringList() {
        return new String[]{
                String.valueOf(Year),
                Name,
                Team,
                POS,
                String.valueOf(Age),
                String.valueOf(OVR_RANK),
                String.valueOf(POS_RANK),
                String.valueOf(GP),
                String.valueOf(GS),
                String.valueOf(CMP),
                String.valueOf(PASS_ATT),
                String.valueOf(PASS_YDS),
                String.valueOf(PASS_TDS),
                String.valueOf(INT),
                String.valueOf(RUSH_ATT),
                String.valueOf(RUSH_YDS),
                String.valueOf(RUSH_YA),
                String.valueOf(RUSH_TDS),
                String.valueOf(TGT),
                String.valueOf(REC),
                String.valueOf(REC_YDS),
                String.valueOf(REC_YA),
                String.valueOf(REC_TDS),
                String.valueOf(FUM),
                String.valueOf(FUM_LOST),
                String.valueOf(TOTAL_TDS),
        };
    }

    /**
     * Gets the year of the player object
     * @return int year
     */
    public int getYear() {
        return Year;
    }

    /**
     * Sets the year of the player object
     * @param year int year
     */
    public void setYear(int year) {
        Year = year;
    }

    /**
     * Gets the name of the player object
     * @return String name
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets the name of the player object
     * @param name String name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Gets the team of the player object
     * @return String team
     */
    public String getTeam() {
        return Team;
    }

    /**
     * Sets the team of the player object
     * @param team String team
     */
    public void setTeam(String team) {
        Team = team;
    }

    /**
     * Gets the position of the player object
     * @return String position
     */
    public String getPOS() {
        return POS;
    }

    /**
     * Sets the position of the player object
     * @param position String position
     */
    public void setPOS(String position) {
        POS = position;
    }

    /**
     * Gets the age of the player object
     * @return int age
     */
    public int getAge() {
        return Age;
    }

    /**
     * Sets the age of the player object
     * @param age int age
     */
    public void setAge(int age) {
        Age = age;
    }

    /**
     * Gets the number of games played of the player object
     * @return int games played
     */
    public int getGP() {
        return GP;
    }

    /**
     * Sets the number of games played of the player object
     * @param games_Played int games played
     */
    public void setGP(int games_Played) {
        GP = games_Played;
    }

    /**
     * Gets the number of games started of the player object
     * @return int games started
     */
    public int getGS() {
        return GS;
    }

    /**
     * Sets the number of games started of the player object
     * @param games_Started int games started
     */
    public void setGS(int games_Started) {
        GS = games_Started;
    }

    /**
     * Gets the number of passing completions of the player object
     * @return int passing completions
     */
    public int getCMP() {
        return CMP;
    }

    /**
     * Sets the number of passing completions of the player object
     * @param passing_Completions int passing completions
     */
    public void setCMP(int passing_Completions) {
        CMP = passing_Completions;
    }

    /**
     * Gets the number of passing attempts of the player object
     * @return int passing attempts
     */
    public int getPASS_ATT() {
        return PASS_ATT;
    }

    /**
     * Sets the number of passing attempts of the player object
     * @param passing_Attempts int passing attempts
     */
    public void setPASS_ATT(int passing_Attempts) {
        PASS_ATT = passing_Attempts;
    }

    /**
     * Gets the number of passing yards of the player object
     * @return int passing yards
     */
    public int getPASS_YDS() {
        return PASS_YDS;
    }

    /**
     * Sets the number of passing yards of the player object
     * @param passing_Yards int passing yards
     */
    public void setPASS_YDS(int passing_Yards) {
        PASS_YDS = passing_Yards;
    }

    /**
     * Gets the number of passing touchdowns of the player object
     * @return int passing touchdowns
     */
    public int getPASS_TDS() {
        return PASS_TDS;
    }

    /**
     * Sets the number of passing touchdowns of the player object
     * @param passing_Touchdowns int passing touchdowns
     */
    public void setPASS_TDS(int passing_Touchdowns) {
        PASS_TDS = passing_Touchdowns;
    }

    /**
     * Gets the number of interceptions of the player object
     * @return int interceptions
     */
    public int getINT() {
        return INT;
    }

    /**
     * Sets the number of interceptions of the player object
     * @param interceptions int interceptions
     */
    public void setINT(int interceptions) {
        INT = interceptions;
    }

    /**
     * Gets the number of rushing attempts of the player object
     * @return int rushing attempts
     */
    public int getRUSH_ATT() {
        return RUSH_ATT;
    }

    /**
     * Sets the number of rushing attempts of the player object
     * @param rushing_Attempts int rushing attempts
     */
    public void setRUSH_ATT(int rushing_Attempts) {
        RUSH_ATT = rushing_Attempts;
    }

    /**
     * Gets the number of rushing yards of the player object
     * @return int rushing yards
     */
    public int getRUSH_YDS() {
        return RUSH_YDS;
    }

    /**
     * Sets the number of rushing yards of the player object
     * @param rushing_Yards int rushing yards
     */
    public void setRUSH_YDS(int rushing_Yards) {
        RUSH_YDS = rushing_Yards;
    }

    /**
     * Gets the number of rushing yards per attempt of the player object
     * @return double rushing yards per attempt
     */
    public double getRUSH_YA() {
        return RUSH_YA;
    }

    /**
     * Sets the number of rushing yards per attempt of the player object
     * @param rushing_Yards_Per_Attempt double rushing yards per attempt
     */
    public void setRUSH_YA(double rushing_Yards_Per_Attempt) {
        RUSH_YA = rushing_Yards_Per_Attempt;
    }

    /**
     * Gets the number of rushing touchdowns of the player object
     * @return int rushing touchdowns
     */
    public int getRUSH_TDS() {
        return RUSH_TDS;
    }

    /**
     * Sets the number of rushing touchdowns of the player object
     * @param rushing_Touchdowns int rushing touchdowns
     */
    public void setRUSH_TDS(int rushing_Touchdowns) {
        RUSH_TDS = rushing_Touchdowns;
    }

    /**
     * Gets the number of receiving targets of the player object
     * @return int receiving targets
     */
    public int getTGT() {
        return TGT;
    }

    /**
     * Sets the number of receiving targets of the player object
     * @param receiving_Targets int receiving targets
     */
    public void setTGT(int receiving_Targets) {
        TGT = receiving_Targets;
    }

    /**
     * Gets the number of reception yards of the player object
     * @return int reception yards
     */
    public int getREC_YDS() {
        return REC_YDS;
    }

    /**
     * Sets the number of reception yards of the player object
     * @param receiving_Yards int reception yards
     */
    public void setREC_YDS(int receiving_Yards) {
        REC_YDS = receiving_Yards;
    }

    /**
     * Gets the number of yards per reception of the player object
     * @return double yards per reception
     */
    public double getREC_YA() {
        return REC_YA;
    }

    /**
     * Sets the number of yards per reception of the player object
     * @param yards_Per_Reception double yards per reception
     */
    public void setREC_YA(double yards_Per_Reception) {
        REC_YA = yards_Per_Reception;
    }

    /**
     * Gets the number of receiving touchdowns of the player object
     * @return int receiving touchdowns
     */
    public int getREC_TDS() {
        return REC_TDS;
    }

    /**
     * Sets the number of receiving touchdowns of the player object
     * @param receiving_Touchdowns int receiving touchdowns
     */
    public void setREC_TDS(int receiving_Touchdowns) {
        REC_TDS = receiving_Touchdowns;
    }

    /**
     * Gets the number of fumbles of the player object
     * @return int fumbles
     */
    public int getFUM() {
        return FUM;
    }

    /**
     * Sets the number of fumbles of the player object
     * @param fumbles int fumbles
     */
    public void setFUM(int fumbles) {
        FUM = fumbles;
    }

    /**
     * Gets the number of fumbles lost of the player object
     * @return int fumbles lost
     */
    public int getFUM_LOST() {
        return FUM_LOST;
    }

    /**
     * Sets the number of fumbles lost of the player object
     * @param fumbles_Lost int fumbles lost
     */
    public void setFUM_LOST(int fumbles_Lost) {
        FUM_LOST = fumbles_Lost;
    }

    /**
     * Gets the number of total touchdowns of the player object
     * @return int total touchdowns
     */
    public int getTOTAL_TDS() {
        return TOTAL_TDS;
    }

    /**
     * Sets the number of total touchdowns of the player object
     * @param total_Touchdowns int total touchdowns
     */
    public void setTOTAL_TDS(int total_Touchdowns) {
        TOTAL_TDS = total_Touchdowns;
    }

    /**
     * Gets the rank for the player's position
     * @return int position rank
     */
    public int getPOS_RANK() {
        return POS_RANK;
    }

    /**
     * Sets the rank for the player's position
     * @param position_Rank int position rank
     */
    public void setPOS_RANK(int position_Rank) {
        POS_RANK = position_Rank;
    }

    /**
     * Gets the overall rank of the player
     * @return int overall rank
     */
    public int getOVR_RANK() {
        return OVR_RANK;
    }

    /**
     * Sets the overall rank of the player
     * @param overall_Rank int overall rank
     */
    public void setOVR_RANK(int overall_Rank) {
        OVR_RANK = overall_Rank;
    }

    /**
     * Gets the number of receptions of the player object
     * @return int number of receptions
     */
    public int getREC() {
        return REC;
    }

    /**
     * Sets the number of receptions of the player object
     * @param receptions int number of receptions
     */
    public void setREC(int receptions) {
        REC = receptions;
    }

    /**
     * Gets the Pro Football Reference ID of the player
     * @return String Pro Football Reference ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Sets the Pro Football Reference ID of the player
     * @param ID String Pro Football Reference ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    
}
