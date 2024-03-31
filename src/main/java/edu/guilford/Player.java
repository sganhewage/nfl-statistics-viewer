package edu.guilford;

import java.io.Serializable;

public class Player implements Serializable {
    private int Year;
    private String Name;
    private String ID;
    private String Team;
    private String Position;
    private int Age;
    private int Games_Played;
    private int Games_Started;
    private int Passing_Completions;
    private int Passing_Attempts;
    private int Passing_Yards;
    private int Passing_Touchdowns;
    private int Interceptions;
    private int Rushing_Attempts;
    private int Rushing_Yards;
    private double Rushing_Yards_Per_Attempt;
    private int Rushing_Touchdowns;
    private int Receiving_Targets;
    private int Receptions;
    private int Receiving_Yards;
    private double Yards_Per_Reception;
    private int Receiving_Touchdowns;
    private int Fumbles;
    private int Fumbles_Lost;
    private int Total_Touchdowns;
    private int Position_Rank;
    private int Overall_Rank;


    @Override
    public String toString() {
        return "Player [Year=" + Year + ", Name=" + Name + ", ID=" + ID + ", Team=" + Team + ", Position=" + Position
                + ", Age=" + Age + ", Games_Played=" + Games_Played + ", Games_Started=" + Games_Started
                + ", Passing_Completions=" + Passing_Completions + ", Passing_Attempts=" + Passing_Attempts
                + ", Passing_Yards=" + Passing_Yards + ", Passing_Touchdowns=" + Passing_Touchdowns + ", Interceptions="
                + Interceptions + ", Rushing_Attempts=" + Rushing_Attempts + ", Rushing_Yards=" + Rushing_Yards
                + ", Rushing_Yards_Per_Attempt=" + Rushing_Yards_Per_Attempt + ", Rushing_Touchdowns="
                + Rushing_Touchdowns + ", Receiving_Targets=" + Receiving_Targets + ", Receptions=" + Receptions
                + ", Receiving_Yards=" + Receiving_Yards + ", Yards_Per_Reception=" + Yards_Per_Reception
                + ", Receiving_Touchdowns=" + Receiving_Touchdowns + ", Fumbles=" + Fumbles + ", Fumbles_Lost="
                + Fumbles_Lost + ", Total_Touchdowns=" + Total_Touchdowns + ", Position_Rank=" + Position_Rank
                + ", Overall_Rank=" + Overall_Rank + "]";
    }

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

    public String[] attributesToStringList() {
        return new String[]{
                String.valueOf(Year),
                Name,
                Team,
                Position,
                String.valueOf(Age),
                String.valueOf(Overall_Rank),
                String.valueOf(Position_Rank),
                String.valueOf(Games_Played),
                String.valueOf(Games_Started),
                String.valueOf(Passing_Completions),
                String.valueOf(Passing_Attempts),
                String.valueOf(Passing_Yards),
                String.valueOf(Passing_Touchdowns),
                String.valueOf(Interceptions),
                String.valueOf(Rushing_Attempts),
                String.valueOf(Rushing_Yards),
                String.valueOf(Rushing_Yards_Per_Attempt),
                String.valueOf(Rushing_Touchdowns),
                String.valueOf(Receiving_Targets),
                String.valueOf(Receptions),
                String.valueOf(Receiving_Yards),
                String.valueOf(Yards_Per_Reception),
                String.valueOf(Receiving_Touchdowns),
                String.valueOf(Fumbles),
                String.valueOf(Fumbles_Lost),
                String.valueOf(Total_Touchdowns),
        };
    }


    
    public int getYear() {
        return Year;
    }
    public void setYear(int year) {
        Year = year;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getTeam() {
        return Team;
    }
    public void setTeam(String team) {
        Team = team;
    }
    public String getPosition() {
        return Position;
    }
    public void setPosition(String position) {
        Position = position;
    }
    public int getAge() {
        return Age;
    }
    public void setAge(int age) {
        Age = age;
    }
    public int getGames_Played() {
        return Games_Played;
    }
    public void setGames_Played(int games_Played) {
        Games_Played = games_Played;
    }
    public int getGames_Started() {
        return Games_Started;
    }
    public void setGames_Started(int games_Started) {
        Games_Started = games_Started;
    }
    public int getPassing_Completions() {
        return Passing_Completions;
    }
    public void setPassing_Completions(int passing_Completions) {
        Passing_Completions = passing_Completions;
    }
    public int getPassing_Attempts() {
        return Passing_Attempts;
    }
    public void setPassing_Attempts(int passing_Attempts) {
        Passing_Attempts = passing_Attempts;
    }
    public int getPassing_Yards() {
        return Passing_Yards;
    }
    public void setPassing_Yards(int passing_Yards) {
        Passing_Yards = passing_Yards;
    }
    public int getPassing_Touchdowns() {
        return Passing_Touchdowns;
    }
    public void setPassing_Touchdowns(int passing_Touchdowns) {
        Passing_Touchdowns = passing_Touchdowns;
    }
    public int getInterceptions() {
        return Interceptions;
    }
    public void setInterceptions(int interceptions) {
        Interceptions = interceptions;
    }
    public int getRushing_Attempts() {
        return Rushing_Attempts;
    }
    public void setRushing_Attempts(int rushing_Attempts) {
        Rushing_Attempts = rushing_Attempts;
    }
    public int getRushing_Yards() {
        return Rushing_Yards;
    }
    public void setRushing_Yards(int rushing_Yards) {
        Rushing_Yards = rushing_Yards;
    }
    public double getRushing_Yards_Per_Attempt() {
        return Rushing_Yards_Per_Attempt;
    }
    public void setRushing_Yards_Per_Attempt(double rushing_Yards_Per_Attempt) {
        Rushing_Yards_Per_Attempt = rushing_Yards_Per_Attempt;
    }
    public int getRushing_Touchdowns() {
        return Rushing_Touchdowns;
    }
    public void setRushing_Touchdowns(int rushing_Touchdowns) {
        Rushing_Touchdowns = rushing_Touchdowns;
    }
    public int getReceiving_Targets() {
        return Receiving_Targets;
    }
    public void setReceiving_Targets(int receiving_Targets) {
        Receiving_Targets = receiving_Targets;
    }
    public int getReceiving_Yards() {
        return Receiving_Yards;
    }
    public void setReceiving_Yards(int receiving_Yards) {
        Receiving_Yards = receiving_Yards;
    }
    public double getYards_Per_Reception() {
        return Yards_Per_Reception;
    }
    public void setYards_Per_Reception(double yards_Per_Reception) {
        Yards_Per_Reception = yards_Per_Reception;
    }
    public int getReceiving_Touchdowns() {
        return Receiving_Touchdowns;
    }
    public void setReceiving_Touchdowns(int receiving_Touchdowns) {
        Receiving_Touchdowns = receiving_Touchdowns;
    }
    public int getFumbles() {
        return Fumbles;
    }
    public void setFumbles(int fumbles) {
        Fumbles = fumbles;
    }
    public int getFumbles_Lost() {
        return Fumbles_Lost;
    }
    public void setFumbles_Lost(int fumbles_Lost) {
        Fumbles_Lost = fumbles_Lost;
    }
    public int getTotal_Touchdowns() {
        return Total_Touchdowns;
    }
    public void setTotal_Touchdowns(int total_Touchdowns) {
        Total_Touchdowns = total_Touchdowns;
    }
    public int getPosition_Rank() {
        return Position_Rank;
    }
    public void setPosition_Rank(int position_Rank) {
        Position_Rank = position_Rank;
    }
    public int getOverall_Rank() {
        return Overall_Rank;
    }
    public void setOverall_Rank(int overall_Rank) {
        Overall_Rank = overall_Rank;
    }
    public int getReceptions() {
        return Receptions;
    }
    public void setReceptions(int receptions) {
        Receptions = receptions;
    }
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }

    
}
