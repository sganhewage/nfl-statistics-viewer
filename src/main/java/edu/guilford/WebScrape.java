package edu.guilford;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;

import javafx.scene.image.Image;




public class WebScrape {
    final static int FILE_REFRESH_TIME = 5; //hours
    final static boolean UPDATE_OLD_LISTS = false;

    static ArrayList<Player> nameSearch(ArrayList<Player> playerList, String input) throws IOException {
        ArrayList<Player> searchResults = new ArrayList<Player>();
        String searchTerm = new String(input).toLowerCase().replaceAll(" ","");
        for (Player player : playerList) {
            String playerName = new String(player.getName()).toLowerCase().replaceAll(" ","");
            if (playerName.contains(searchTerm)) {
                searchResults.add(player);
            }
        }
        return searchResults;
    }

    //@SuppressWarnings("unused")
    static ArrayList<Player> careerTotals(int startYear, int endYear) throws IOException {
        //create an array of years to search
        int[] yearsArray = IntStream.rangeClosed(startYear, endYear).toArray();
        
        Random rand = new Random();
        Boolean delay = false;

        if (UPDATE_OLD_LISTS) {
            int refreshFileQuant = 0;
            for (int year : yearsArray) {
                String yearListFileLoc = App.class.getResource("yearLists/").getPath();
                yearListFileLoc = yearListFileLoc + year + "List.ser";
                File tmpDir = new File(yearListFileLoc);
                
                if (tmpDir.exists()) {
                    FileInputStream readData = new FileInputStream(yearListFileLoc);
                    ObjectInputStream readStream = new ObjectInputStream(readData);
                    LocalDateTime fileDate;
                    long hoursElapsed = 0;
                    try {
                        fileDate = (LocalDateTime) readStream.readObject();
                        hoursElapsed = fileDate.until(LocalDateTime.now(), ChronoUnit.HOURS);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    readStream.close();
                    
                    //System.out.println(hoursElapsed);
                    if (hoursElapsed >= FILE_REFRESH_TIME) {
                        refreshFileQuant++;
                    }
                }    
            }
            if (refreshFileQuant > 3) {
                delay = true;   
            }
        } else {
            delay = false;
        }
        final boolean finalDelay = delay;

        //System.out.println(yearsArray);
        ArrayList<Player> careerList = new ArrayList<Player>();
        ArrayList<String> IDList = new ArrayList<String>();
        ArrayList<Player> yearList = new ArrayList<Player>(); 

        for (int year : yearsArray) {
            try {
                for (Player person : createPlayerList(year)) {
                    yearList.add(person);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (finalDelay) {
                try {
                    Thread.sleep(500+rand.nextInt(200));
                    System.out.println("delayed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
            
        for (Player player : yearList) {
            String ID = new String(player.getID());
            Boolean IDmatch = false;
            int index = 0;
            for (String id : IDList) {
                if (id.contains(ID)) {
                    IDmatch = true;
                    index = IDList.indexOf(id);
                }
            } 

            if (IDmatch) {
                //System.out.println("repeat player " + ID);
                
                Player p = careerList.get(index);

                p.setGP(p.getGP()+player.getGP());
                p.setGS(p.getGS()+player.getGS());
                p.setPASS_ATT(p.getPASS_ATT()+player.getPASS_ATT());
                p.setCMP(p.getCMP()+player.getCMP());
                p.setPASS_YDS(p.getPASS_YDS()+player.getPASS_YDS());
                p.setPASS_TDS(p.getPASS_TDS()+player.getPASS_TDS());
                p.setINT(p.getINT()+player.getINT());
                p.setRUSH_ATT(p.getRUSH_ATT()+player.getRUSH_ATT());
                p.setRUSH_YDS(p.getRUSH_YDS()+player.getRUSH_YDS());
                try {p.setRUSH_YA(p.getRUSH_YDS()/p.getRUSH_ATT());} catch (Exception e) {p.setRUSH_YA(0);}
                p.setRUSH_TDS(p.getRUSH_TDS()+player.getRUSH_TDS());
                p.setTGT(p.getTGT()+player.getTGT());
                p.setREC(p.getREC()+player.getREC());
                p.setREC_YDS(p.getREC_YDS()+player.getREC_YDS());
                try {p.setREC_YA(p.getREC_YDS()/p.getREC());} catch (Exception e) {p.setREC_YA(0);}
                p.setREC_TDS(p.getREC_TDS()+player.getREC_TDS());
                p.setFUM(p.getFUM()+player.getFUM());
                p.setFUM_LOST(p.getFUM_LOST()+player.getFUM_LOST());
                p.setTOTAL_TDS(p.getTOTAL_TDS()+player.getTOTAL_TDS());
                p.setTeam(player.getTeam());
                p.setAge(player.getAge());

                careerList.set(index,p);
            } else {
                IDList.add(ID);
                careerList.add(player);
                //careerList.get(IDList.indexOf(ID)).setYear(0000);
            }
            
        }
        //System.out.println(IDList);
        return careerList;
    }

    static Image playerProfileImage(String playerCode) throws IOException {
        String imgFilePath = App.class.getResource("PlayerImages/").getPath();
        imgFilePath = imgFilePath + playerCode + ".jpeg";
        File tempDirectory = new File(imgFilePath);
        Image img = new Image(App.class.getResourceAsStream("imageUnavailable.png"));

        if (tempDirectory.exists()) {
            System.out.println("Player pulled from files");
            img = new Image(new FileInputStream(imgFilePath));
        } else {
            try {
                String url = "https://www.pro-football-reference.com/players/"+playerCode.charAt(0)+"/"+playerCode+".htm";
                Document document = Jsoup.connect(url).get();
                
                //Get images from document object.
                Elements images = 
                        document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");  
            
                String imageSrc = "";
                //Iterate images and print image attributes.
                for (Element image : images) {
                if (image.attr("alt").contains("Photo of")) {
                    imageSrc = image.attr("src"); 

                    Response resultImageResponse = Jsoup.connect(imageSrc).ignoreContentType(true).execute();

                    // output here
                    FileOutputStream out = new FileOutputStream(new java.io.File(imgFilePath));
                    out.write(resultImageResponse.bodyAsBytes());  // resultImageResponse.body() is where the image's contents are.
                    out.close();

                    img = new Image(new FileInputStream(imgFilePath));
                    System.out.println("Pulling Image from Website...");
                }
                }   
            } catch (HttpStatusException e) {
                img = new Image(App.class.getResourceAsStream("imageUnavailable.png"));
                System.out.println("Image Unavailable (429 Status Error)");
            }    
        } 
        return img;
    }

    static ArrayList<Integer> getValidYears() throws IOException {
        ArrayList<Integer> yearList = new ArrayList<Integer>();

        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int currentYear;
        if (date.getMonth() == Month.SEPTEMBER ||
            date.getMonth() == Month.OCTOBER ||
            date.getMonth() == Month.NOVEMBER ||
            date.getMonth() == Month.DECEMBER) {
                currentYear = year;
        } else {currentYear = year-1;}
        
        int startYear = 1992;

        for(int i = startYear; i < currentYear+1; i++){yearList.add(i);}

        return yearList;
    }

    @SuppressWarnings("unchecked")
    static ArrayList<Player> createPlayerList(int year) throws IOException {
        if (!getValidYears().contains(year)) {
            throw new InvalidYearException("Invalid Year. Select a year between " + getValidYears().get(0) + " and " + getValidYears().get(getValidYears().size()-1) + ".");
        }

        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<Player> filePlayers = new ArrayList<Player>();

        String yearListFileLoc = App.class.getResource("yearLists/").getPath();
        yearListFileLoc = yearListFileLoc + year + "List.ser";
        File tmpDir = new File(yearListFileLoc);

        Boolean createNewList = true;
        try{
            if (tmpDir.exists()) {
                if (year < java.time.Year.now().getValue()-1 && !UPDATE_OLD_LISTS) {
                    createNewList = false;
                    FileInputStream readData = new FileInputStream(yearListFileLoc);
                    ObjectInputStream readStream = new ObjectInputStream(readData);
                    readStream.readObject();
                    filePlayers = (ArrayList<Player>) readStream.readObject();
                    players = filePlayers;
                    readStream.close();
                    return players;
                } else {
                    FileInputStream readData = new FileInputStream(yearListFileLoc);
                    ObjectInputStream readStream = new ObjectInputStream(readData);
                    LocalDateTime fileDate = (LocalDateTime) readStream.readObject();
                    filePlayers = (ArrayList<Player>) readStream.readObject();
                    readStream.close();
                    long hoursElapsed = fileDate.until(LocalDateTime.now(), ChronoUnit.HOURS);
                    if (hoursElapsed <= 5) {
                        players = filePlayers;
                        createNewList = false;
                        return players;
                    }
                    
                }
            }

            // createNewList = true; //Uncomment this line to remove file bypass

            if (createNewList) {
                //System.out.println("beginning web scrape");
                    players = new ArrayList<Player>();
                    String url = "https://www.pro-football-reference.com/years/"+year+"/fantasy.htm";
                    Document doc = Jsoup.connect(url).get();
                    
                    //Initializes Hashmap to convert team abbreivations to full team names
                    HashMap<String, String> teamAbbreviations = new HashMap<>();
                    teamAbbreviationtoName(teamAbbreviations);

                    //Creates list of Player Names
                    ArrayList<String> playerNames = new ArrayList<String>(); 
                    ArrayList<String> playerTeams = new ArrayList<String>();
                    ArrayList<String> playerID = new ArrayList<String>(); 
                    Elements names = doc.getElementsByClass("left");
                    for (Element element : names) {
                        String elementString = new String(element.toString());
                        
                        String secondHalf = new String("");
                        try {secondHalf = new String(elementString.substring(5+elementString.indexOf("csv=\"")));} catch(Exception e) {secondHalf = "";}
                        String finalString = new String(secondHalf.substring(0, secondHalf.indexOf("\"")));
                        if (secondHalf.contains("class") == false) {playerID.add(finalString);}

                        String name = new String("");
                        if (element.toString().contains("data-stat=\"player\" csk")) {name = element.text().toString();}
                        if (name.isEmpty() == false) {playerNames.add(name.replaceAll("[^a-zA-Z0-9\\s]",""));}

                        String team = new String("");
                        if (element.toString().contains("stat=\"team\"")) {team = element.text().toString();}
                        if (team.contains("Tm") == false && team.isEmpty() == false) {
                            String fullTeamName = teamAbbreviations.get(team);
                            playerTeams.add(fullTeamName);
                        }
                    }

                    //System.out.println("Gathered Player Names");

                    //gathers stastics from table
                    Elements elements = doc.getElementsByClass("right");

                    //Creates list of Player Positions
                    ArrayList<String> positions = new ArrayList<String>(); 
                    for (Element e : elements) {   
                        char[] chars = e.text().toCharArray();
                            boolean lettersOnly = true;
                            for (char c : chars) {if (Character.isDigit(c)) {lettersOnly = false;}}
                            if (e.toString().contains("pos") && lettersOnly) {
                                String position = e.text().toString();
                                if (position == "") {position = "N/A";}
                                positions.add(position);   
                            }
                    }

                    //System.out.println("Gathered Player Positions");

                    //create lists for stat types
                    ArrayList<Integer> ages = new ArrayList<Integer>();
                    ArrayList<Integer> GP = new ArrayList<Integer>();
                    ArrayList<Integer> G = new ArrayList<Integer>();
                    ArrayList<Integer> pass_cmp = new ArrayList<Integer>();
                    ArrayList<Integer> pass_att = new ArrayList<Integer>();
                    ArrayList<Integer> pass_yds = new ArrayList<Integer>();
                    ArrayList<Integer> pass_tds = new ArrayList<Integer>();
                    ArrayList<Integer> ints = new ArrayList<Integer>();
                    ArrayList<Integer> rush_att = new ArrayList<Integer>();
                    ArrayList<Integer> rush_yds = new ArrayList<Integer>();
                    ArrayList<Double> rush_ya = new ArrayList<Double>();
                    ArrayList<Integer> rush_tds = new ArrayList<Integer>();
                    ArrayList<Integer> rec_tgts = new ArrayList<Integer>();
                    ArrayList<Integer> rec = new ArrayList<Integer>();
                    ArrayList<Integer> rec_yds = new ArrayList<Integer>();
                    ArrayList<Double> rec_yr = new ArrayList<Double>();
                    ArrayList<Integer> rec_tds = new ArrayList<Integer>();
                    ArrayList<Integer> fum = new ArrayList<Integer>();
                    ArrayList<Integer> FL = new ArrayList<Integer>();
                    ArrayList<Integer> total_tds = new ArrayList<Integer>();
                    ArrayList<Integer> pos_rank = new ArrayList<Integer>();
                    ArrayList<Integer> ovr_rank = new ArrayList<Integer>();

                    for (Element statElements : elements) {
                        try {if (statElements.toString().contains("age")) {ages.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {ages.add(0);}
                        try {if (statElements.toString().contains("stat=\"g\"")) {GP.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {GP.add(0);}
                        try {if (statElements.toString().contains("stat=\"gs")) {G.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {G.add(0);}
                        try {if (statElements.toString().contains("stat=\"pass_att")) {pass_att.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {pass_att.add(0);}

                        try {if (statElements.toString().contains("stat=\"pass_cmp")) {pass_cmp.add(Integer.valueOf(statElements.text().toString()));}} 
                        catch(Exception e) {pass_cmp.add(0);}

                        try {if (statElements.toString().contains("stat=\"pass_yds\"")) {pass_yds.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {pass_yds.add(0);}
                        try {if (statElements.toString().contains("stat=\"pass_td")) {pass_tds.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {pass_tds.add(0);}
                        try {if (statElements.toString().contains("stat=\"pass_int")) {ints.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {ints.add(0);}
                        try {if (statElements.toString().contains("stat=\"rush_att")) {rush_att.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {rush_att.add(0);}
                        try {if (statElements.toString().contains("stat=\"rush_yds\"")) {rush_yds.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {rush_yds.add(0);}

                        try {if (statElements.toString().contains("stat=\"rush_yds_per_att")) {rush_ya.add(Double.valueOf(statElements.text().toString()));}} 
                        catch(Exception e) {rush_ya.add(0.);}

                        try {if (statElements.toString().contains("stat=\"rush_td")) {rush_tds.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {rush_tds.add(0);}
                        try {if (statElements.toString().contains("stat=\"targets")) {rec_tgts.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {rec_tgts.add(0);}
                        try {if (statElements.toString().contains("stat=\"rec\"")) {rec.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {rec.add(0);}
                        try {if (statElements.toString().contains("stat=\"rec_yds\">")) {rec_yds.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {rec_yds.add(0);}
                        
                        try {if (statElements.toString().contains("stat=\"rec_yds_per_rec")) {rec_yr.add(Double.valueOf(statElements.text().toString()));}} 
                        catch(Exception e) {rec_yr.add(0.);}

                        try {if (statElements.toString().contains("stat=\"rec_td")) {rec_tds.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {rec_tds.add(0);}
                        try {if (statElements.toString().contains("stat=\"fumbles\"")) {fum.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {fum.add(0);}
                        try {if (statElements.toString().contains("stat=\"fumbles_lost")) {FL.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {FL.add(0);}
                        try {if (statElements.toString().contains("stat=\"all_td")) {total_tds.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {total_tds.add(0);}
                        try {if (statElements.toString().contains("stat=\"fantasy_rank_pos")) {pos_rank.add(Integer.valueOf(statElements.text().toString()));}} catch(Exception e) {pos_rank.add(0);}
                        
                        try {if (statElements.toString().contains("stat=\"ranker")) {ovr_rank.add(Integer.valueOf(statElements.text().toString()));}} 
                        catch(Exception e) {ovr_rank.add(999);}
                    }            

                    //System.out.println("Gathered Player Stats");

                    //Adds List to array of Player Objects
                    int numberOfPlayers = playerNames.size();
                    int playerIndex = 0;
                    while (playerIndex < numberOfPlayers) {
                        Player p = new Player();
                        p.setYear(year);
                        p.setName(playerNames.get(playerIndex));
                        p.setID(playerID.get(playerIndex));
                        p.setTeam(playerTeams.get(playerIndex));
                        p.setPOS(positions.get(playerIndex));
                        p.setAge(ages.get(playerIndex));
                        p.setGP(GP.get(playerIndex));
                        p.setGS(G.get(playerIndex));
                        p.setPASS_ATT(pass_att.get(playerIndex));
                        p.setCMP(pass_cmp.get(playerIndex));
                        p.setPASS_YDS(pass_yds.get(playerIndex));
                        p.setPASS_TDS(pass_tds.get(playerIndex));
                        p.setINT(ints.get(playerIndex));
                        p.setRUSH_ATT(rush_att.get(playerIndex));
                        p.setRUSH_YDS(rush_yds.get(playerIndex));
                        p.setRUSH_YA(rush_ya.get(playerIndex));
                        p.setRUSH_TDS(rush_tds.get(playerIndex));
                        p.setTGT(rec_tgts.get(playerIndex));
                        p.setREC(rec.get(playerIndex));
                        p.setREC_YDS(rec_yds.get(playerIndex));
                        p.setREC_YA(rec_yr.get(playerIndex));
                        p.setREC_TDS(rec_tds.get(playerIndex));
                        p.setFUM(fum.get(playerIndex));
                        p.setFUM_LOST(FL.get(playerIndex));
                        p.setPOS_RANK(pos_rank.get(playerIndex));
                        p.setTOTAL_TDS(total_tds.get(playerIndex));
                        p.setOVR_RANK(ovr_rank.get(playerIndex));

                        players.add(p);
                        playerIndex += 1;
                    }
                    System.out.println(players.size());

                    //System.out.println("Player Class Build Finished");    //Completed Players Object Array
                    System.out.println("created new list");
                    try {
                        LocalDateTime date = LocalDateTime.now();

                        tmpDir.delete();

                        FileOutputStream writeData = new FileOutputStream(yearListFileLoc, false);
                        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

                        writeStream.writeObject(date);
                        writeStream.writeObject(players);
                        writeStream.flush();
                        writeStream.close();
                        System.out.println("new "+year+" file written");
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }  
            } catch (Exception e) {
                e.printStackTrace();
                players = filePlayers;
            }

        return players;
    }
    
    //Hashmap to convert between team names and abbreviations
    public static void teamAbbreviationtoName(HashMap<String, String> teamAbbreviations) {
        teamAbbreviations.put("ARI", "Arizona Cardinals");
        teamAbbreviations.put("ATL", "Atlanta Falcons");
        teamAbbreviations.put("BAL", "Baltimore Ravens");
        teamAbbreviations.put("BUF", "Buffalo Bills");
        teamAbbreviations.put("CAR", "Carolina Panthers");
        teamAbbreviations.put("CHI", "Chicago Bears");
        teamAbbreviations.put("CIN", "Cincinnati Bengals");
        teamAbbreviations.put("CLE", "Cleveland Browns");
        teamAbbreviations.put("DAL", "Dallas Cowboys");
        teamAbbreviations.put("DEN", "Denver Broncos");
        teamAbbreviations.put("DET", "Detroit Lions");
        teamAbbreviations.put("GNB", "Green Bay Packers");
        teamAbbreviations.put("HOU", "Houston Texans");
        teamAbbreviations.put("IND", "Indianapolis Colts");
        teamAbbreviations.put("JAX", "Jacksonville Jaguars");
        teamAbbreviations.put("KAN", "Kansas City Chiefs");
        teamAbbreviations.put("LAC", "Los Angeles Chargers");
        teamAbbreviations.put("LAR", "Los Angeles Rams");
        teamAbbreviations.put("MIA", "Miami Dolphins");
        teamAbbreviations.put("MIN", "Minnesota Vikings");
        teamAbbreviations.put("NWE", "New England Patriots");
        teamAbbreviations.put("NOR", "New Orleans Saints");
        teamAbbreviations.put("NYG", "New York Giants");
        teamAbbreviations.put("NYJ", "New York Jets");
        teamAbbreviations.put("LVR", "Las Vegas Raiders");
        teamAbbreviations.put("PHI", "Philadelphia Eagles");
        teamAbbreviations.put("PIT", "Pittsburgh Steelers");
        teamAbbreviations.put("SFO", "San Francisco 49ers");
        teamAbbreviations.put("SEA", "Seattle Seahawks");
        teamAbbreviations.put("TAM", "Tampa Bay Buccaneers");
        teamAbbreviations.put("TEN", "Tennessee Titans");
        teamAbbreviations.put("WAS", "Washington Football Team");
        teamAbbreviations.put("WAC", "Washington Commanders");
        teamAbbreviations.put("STL", "St. Louis Rams");
        teamAbbreviations.put("OAK", "Oakland Raiders");
        teamAbbreviations.put("RAI", "Los Angeles Raiders");
        teamAbbreviations.put("SDG", "San Diego Chargers");
        teamAbbreviations.put("PHO", "Phoenix Cardinals");
        teamAbbreviations.put("2TM", "2 Different Teams");
        teamAbbreviations.put("3TM", "3 Different Teams");
        teamAbbreviations.put("4TM", "4 Different Teams");
    }

    // create a reverse hashmap to convert between team names and abbreviations
    public static void teamNametoAbbreviation(HashMap<String, String> teamAbbreviations) {
        teamAbbreviations.put("Arizona Cardinals", "ARI");
        teamAbbreviations.put("Atlanta Falcons", "ATL");
        teamAbbreviations.put("Baltimore Ravens", "BAL");
        teamAbbreviations.put("Buffalo Bills", "BUF");
        teamAbbreviations.put("Carolina Panthers", "CAR");
        teamAbbreviations.put("Chicago Bears", "CHI");
        teamAbbreviations.put("Cincinnati Bengals", "CIN");
        teamAbbreviations.put("Cleveland Browns", "CLE");
        teamAbbreviations.put("Dallas Cowboys", "DAL");
        teamAbbreviations.put("Denver Broncos", "DEN");
        teamAbbreviations.put("Detroit Lions", "DET");
        teamAbbreviations.put("Green Bay Packers", "GNB");
        teamAbbreviations.put("Houston Texans", "HOU");
        teamAbbreviations.put("Indianapolis Colts", "IND");
        teamAbbreviations.put("Jacksonville Jaguars", "JAX");
        teamAbbreviations.put("Kansas City Chiefs", "KAN");
        teamAbbreviations.put("Los Angeles Chargers", "LAC");
        teamAbbreviations.put("Los Angeles Rams", "LAR");
        teamAbbreviations.put("Miami Dolphins", "MIA");
        teamAbbreviations.put("Minnesota Vikings", "MIN");
        teamAbbreviations.put("New England Patriots", "NWE");
        teamAbbreviations.put("New Orleans Saints", "NOR");
        teamAbbreviations.put("New York Giants", "NYG");
        teamAbbreviations.put("New York Jets", "NYJ");
        teamAbbreviations.put("Las Vegas Raiders", "LVR");
        teamAbbreviations.put("Philadelphia Eagles", "PHI");
        teamAbbreviations.put("Pittsburgh Steelers", "PIT");
        teamAbbreviations.put("San Francisco 49ers", "SFO");
        teamAbbreviations.put("Seattle Seahawks", "SEA");
        teamAbbreviations.put("Tampa Bay Buccaneers", "TAM");
        teamAbbreviations.put("Tennessee Titans", "TEN");
        teamAbbreviations.put("Washington Football Team", "WAS");
        teamAbbreviations.put("Washington Commanders", "WAC");
        teamAbbreviations.put("St. Louis Rams", "STL");
        teamAbbreviations.put("Oakland Raiders", "OAK");
        teamAbbreviations.put("Los Angeles Raiders", "RAI");
        teamAbbreviations.put("Phoenix Cardinals", "PHO");
        teamAbbreviations.put("San Diego Chargers", "SDG");
        teamAbbreviations.put("2 Different Teams", "2TM");
        teamAbbreviations.put("3 Different Teams", "3TM");
        teamAbbreviations.put("4 Different Teams", "4TM");
    }

    // create an exception class
    public static class InvalidYearException extends RuntimeException {
        public InvalidYearException(String message) {
            super(message);
        }
    }
}  

