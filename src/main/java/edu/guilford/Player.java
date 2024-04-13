package edu.guilford;

import java.io.Serializable;

public class Player implements Serializable {
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

    public static String[] getAttributes() {
        return new String[]{
            "Year", "Name", "Team", "POS", "Age", "OVR_RANK", "POS_RANK", "GP", "GS", "CMP", "PASS_ATT",
            "PASS_YDS", "PASS_TDS", "INT", "RUSH_ATT", "RUSH_YDS", "RUSH_YA", "RUSH_TDS",
            "TGT", "REC", "REC_YDS", "REC_YA", "REC_TDS", "FUM", "FUM_LOST", "TOTAL_TDS"
        };
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

    public static String[] getRangeAttributes() {
        return new String[]{
            "Name", "Team", "POS", "Age", "GP", "GS", "CMP", "PASS_ATT",
            "PASS_YDS", "PASS_TDS", "INT", "RUSH_ATT", "RUSH_YDS", "RUSH_YA", "RUSH_TDS",
            "TGT", "REC", "REC_YDS", "REC_YA", "REC_TDS", "FUM", "FUM_LOST", "TOTAL_TDS"
        };
    }

    public static String[] getRangeAttributeType() {
        return new String[]{
            "String", "String", "String", "int", "int", "int", "int", "int",
            "int", "int", "int", "int", "int", "double", "int",
            "int", "int", "int", "double", "int", "int", "int", "int"
        };
    }

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

    public static String[] getProfileAttributes() {
        return new String[]{
            "Year", "Team", "Age", "POS", "GP", "GS", "CMP", "PASS_ATT",
            "PASS_YDS", "PASS_TDS", "INT", "RUSH_ATT", "RUSH_YDS", "RUSH_YA", "RUSH_TDS",
            "TGT", "REC", "REC_YDS", "REC_YA", "REC_TDS", "FUM", "FUM_LOST", "TOTAL_TDS"
        };
    }

    public static String[] getProfileAttributeNames() {
        return new String[]{
            "Year", "Team", "Age", "Position", "Games Played", "Games Started",
            "Passing Completions", "Passing Attempts", "Passing Yards", "Passing Touchdowns",
            "Interceptions", "Rushing Attempts", "Rushing Yards", "Rushing Yards Per Attempt",
            "Rushing Touchdowns", "Receiving Targets", "Receptions", "Receiving Yards",
            "Yards Per Reception", "Receiving Touchdowns", "Fumbles", "Fumbles Lost",
            "Total Touchdowns"
        };
    }

    public static String[] getProfileAttributeType() {
        return new String[]{
            "int", "String", "int", "String", "int", "int", "int", "int",
            "int", "int", "int", "int", "int", "double", "int",
            "int", "int", "int", "double", "int", "int", "int", "int"
        };
    }

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
    public String getPOS() {
        return POS;
    }
    public void setPOS(String position) {
        POS = position;
    }
    public int getAge() {
        return Age;
    }
    public void setAge(int age) {
        Age = age;
    }
    public int getGP() {
        return GP;
    }
    public void setGP(int games_Played) {
        GP = games_Played;
    }
    public int getGS() {
        return GS;
    }
    public void setGS(int games_Started) {
        GS = games_Started;
    }
    public int getCMP() {
        return CMP;
    }
    public void setCMP(int passing_Completions) {
        CMP = passing_Completions;
    }
    public int getPASS_ATT() {
        return PASS_ATT;
    }
    public void setPASS_ATT(int passing_Attempts) {
        PASS_ATT = passing_Attempts;
    }
    public int getPASS_YDS() {
        return PASS_YDS;
    }
    public void setPASS_YDS(int passing_Yards) {
        PASS_YDS = passing_Yards;
    }
    public int getPASS_TDS() {
        return PASS_TDS;
    }
    public void setPASS_TDS(int passing_Touchdowns) {
        PASS_TDS = passing_Touchdowns;
    }
    public int getINT() {
        return INT;
    }
    public void setINT(int interceptions) {
        INT = interceptions;
    }
    public int getRUSH_ATT() {
        return RUSH_ATT;
    }
    public void setRUSH_ATT(int rushing_Attempts) {
        RUSH_ATT = rushing_Attempts;
    }
    public int getRUSH_YDS() {
        return RUSH_YDS;
    }
    public void setRUSH_YDS(int rushing_Yards) {
        RUSH_YDS = rushing_Yards;
    }
    public double getRUSH_YA() {
        return RUSH_YA;
    }
    public void setRUSH_YA(double rushing_Yards_Per_Attempt) {
        RUSH_YA = rushing_Yards_Per_Attempt;
    }
    public int getRUSH_TDS() {
        return RUSH_TDS;
    }
    public void setRUSH_TDS(int rushing_Touchdowns) {
        RUSH_TDS = rushing_Touchdowns;
    }
    public int getTGT() {
        return TGT;
    }
    public void setTGT(int receiving_Targets) {
        TGT = receiving_Targets;
    }
    public int getREC_YDS() {
        return REC_YDS;
    }
    public void setREC_YDS(int receiving_Yards) {
        REC_YDS = receiving_Yards;
    }
    public double getREC_YA() {
        return REC_YA;
    }
    public void setREC_YA(double yards_Per_Reception) {
        REC_YA = yards_Per_Reception;
    }
    public int getREC_TDS() {
        return REC_TDS;
    }
    public void setREC_TDS(int receiving_Touchdowns) {
        REC_TDS = receiving_Touchdowns;
    }
    public int getFUM() {
        return FUM;
    }
    public void setFUM(int fumbles) {
        FUM = fumbles;
    }
    public int getFUM_LOST() {
        return FUM_LOST;
    }
    public void setFUM_LOST(int fumbles_Lost) {
        FUM_LOST = fumbles_Lost;
    }
    public int getTOTAL_TDS() {
        return TOTAL_TDS;
    }
    public void setTOTAL_TDS(int total_Touchdowns) {
        TOTAL_TDS = total_Touchdowns;
    }
    public int getPOS_RANK() {
        return POS_RANK;
    }
    public void setPOS_RANK(int position_Rank) {
        POS_RANK = position_Rank;
    }
    public int getOVR_RANK() {
        return OVR_RANK;
    }
    public void setOVR_RANK(int overall_Rank) {
        OVR_RANK = overall_Rank;
    }
    public int getREC() {
        return REC;
    }
    public void setREC(int receptions) {
        REC = receptions;
    }
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }

    
}
