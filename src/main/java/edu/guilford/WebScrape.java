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
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;




public class WebScrape{

    static int fileRefreshTime = 5; //hours

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

    static ArrayList<Player> careerTotals(int startYear, int endYear) throws IOException {
        List<Integer> yearsArray = IntStream.rangeClosed(startYear, endYear).boxed().toList();
        Random rand = new Random();
        Boolean delay = false;

        int refreshFileQuant = 0;
        for (int year : yearsArray) {
            String yearListFileLoc = "src/main/java/edu/guilford/yearLists/"+year+"List.ser";
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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                readStream.close();
                
                //System.out.println(hoursElapsed);
                if (hoursElapsed >= fileRefreshTime) {
                    refreshFileQuant++;
                }
            }    
        }
        if (refreshFileQuant > 3) {
            delay = true;   
        }
        //System.out.println(yearsArray);
        ArrayList<Player> careerList = new ArrayList<Player>();
        ArrayList<String> IDList = new ArrayList<String>();
        ArrayList<Player> yearList = new ArrayList<Player>(); 
        for (int year : yearsArray) {
            for (Player person : createPlayerList(year)) {
                yearList.add(person);
            }
            if (delay) {
                try {
                    Thread.sleep(500+rand.nextInt(200));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
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

                p.setGames_Played(p.getGames_Played()+player.getGames_Played());
                p.setGames_Started(p.getGames_Started()+player.getGames_Started());
                p.setPassing_Attempts(p.getPassing_Attempts()+player.getPassing_Attempts());
                p.setPassing_Completions(p.getPassing_Completions()+player.getPassing_Completions());
                p.setPassing_Yards(p.getPassing_Yards()+player.getPassing_Yards());
                p.setPassing_Touchdowns(p.getPassing_Touchdowns()+player.getPassing_Touchdowns());
                p.setInterceptions(p.getInterceptions()+player.getInterceptions());
                p.setRushing_Attempts(p.getRushing_Attempts()+player.getRushing_Attempts());
                p.setRushing_Yards(p.getRushing_Yards()+player.getRushing_Yards());
                try {p.setRushing_Yards_Per_Attempt(p.getRushing_Yards()/p.getRushing_Attempts());} catch (Exception e) {p.setRushing_Yards_Per_Attempt(0);}
                p.setRushing_Touchdowns(p.getRushing_Touchdowns()+player.getRushing_Touchdowns());
                p.setReceiving_Targets(p.getReceiving_Targets()+player.getReceiving_Targets());
                p.setReceptions(p.getReceptions()+player.getReceptions());
                p.setReceiving_Yards(p.getReceiving_Yards()+player.getReceiving_Yards());
                try {p.setYards_Per_Reception(p.getReceiving_Yards()/p.getReceptions());} catch (Exception e) {p.setYards_Per_Reception(0);}
                p.setReceiving_Touchdowns(p.getReceiving_Touchdowns()+player.getReceiving_Touchdowns());
                p.setFumbles(p.getFumbles()+player.getFumbles());
                p.setFumbles_Lost(p.getFumbles_Lost()+player.getFumbles_Lost());
                p.setTotal_Touchdowns(p.getTotal_Touchdowns()+player.getTotal_Touchdowns());
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

    static ImageIcon playerProfileImage(String playerCode) throws IOException {
        String imgFilePath = "src/main/java/edu/guilford/PlayerImages/"+playerCode+".jpeg";
        File tempDirectory = new File(imgFilePath);
        ImageIcon img = new ImageIcon("src/main/java/edu/guilford/imageUnavailable.png");

        if (tempDirectory.exists()) {
            img = new ImageIcon(imgFilePath);
            System.out.println("Player pulled from files");
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

                    img = new ImageIcon(imgFilePath);
                    System.out.println("Pulling Image from Website...");
                }
                }   
            } catch (HttpStatusException e) {
                img = new ImageIcon("finalproject/src/main/java/edu/guilford/imageUnavailable.png");
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

    static ArrayList<Player> createPlayerList(int year) throws IOException {
        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<Player> filePlayers = new ArrayList<Player>();

        String yearListFileLoc = "src/main/java/edu/guilford/yearLists/"+year+"List.ser";
        File tmpDir = new File(yearListFileLoc);

        Boolean createNewList = true;
        try{
            if (tmpDir.exists()) {
                FileInputStream readData = new FileInputStream(yearListFileLoc);
                ObjectInputStream readStream = new ObjectInputStream(readData);
                LocalDateTime fileDate = (LocalDateTime) readStream.readObject();
                filePlayers = (ArrayList<Player>) readStream.readObject();
                long hoursElapsed = fileDate.until(LocalDateTime.now(), ChronoUnit.HOURS);
                //System.out.println(hoursElapsed);
                if (hoursElapsed <= 5) {
                    //System.out.println("pulled from files");
                    players = filePlayers;
                    createNewList = false;
                }
                readStream.close();
            }

            //createNewList = true; //Comment out this line to remove file bypass

            if (createNewList) {
                //System.out.println("beginning web scrape");
                    players = new ArrayList<Player>();
                    String url = "https://www.pro-football-reference.com/years/"+year+"/fantasy.htm";
                    Document doc = Jsoup.connect(url).get();
                    
                    //Initializes Hashmap to convert team abbreivations to full team names
                    HashMap<String, String> teamAbbreviations = new HashMap<>();
                    initializeTeamAbbreviations(teamAbbreviations);

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
                        p.setPosition(positions.get(playerIndex));
                        p.setAge(ages.get(playerIndex));
                        p.setGames_Played(GP.get(playerIndex));
                        p.setGames_Started(G.get(playerIndex));
                        p.setPassing_Attempts(pass_att.get(playerIndex));
                        p.setPassing_Completions(pass_cmp.get(playerIndex));
                        p.setPassing_Yards(pass_yds.get(playerIndex));
                        p.setPassing_Touchdowns(pass_tds.get(playerIndex));
                        p.setInterceptions(ints.get(playerIndex));
                        p.setRushing_Attempts(rush_att.get(playerIndex));
                        p.setRushing_Yards(rush_yds.get(playerIndex));
                        p.setRushing_Yards_Per_Attempt(rush_ya.get(playerIndex));
                        p.setRushing_Touchdowns(rush_tds.get(playerIndex));
                        p.setReceiving_Targets(rec_tgts.get(playerIndex));
                        p.setReceptions(rec.get(playerIndex));
                        p.setReceiving_Yards(rec_yds.get(playerIndex));
                        p.setYards_Per_Reception(rec_yr.get(playerIndex));
                        p.setReceiving_Touchdowns(rec_tds.get(playerIndex));
                        p.setFumbles(fum.get(playerIndex));
                        p.setFumbles_Lost(FL.get(playerIndex));
                        p.setPosition_Rank(pos_rank.get(playerIndex));
                        p.setTotal_Touchdowns(total_tds.get(playerIndex));
                        p.setOverall_Rank(ovr_rank.get(playerIndex));

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
    private static void initializeTeamAbbreviations(HashMap<String, String> teamAbbreviations) {
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
        teamAbbreviations.put("STL", "St. Louis Rams");
        teamAbbreviations.put("OAK", "Oakland Raiders");
        teamAbbreviations.put("SDG", "San Diego Chargers");
        teamAbbreviations.put("2TM", "2 Different Teams");
        teamAbbreviations.put("3TM", "3 Different Teams");
        teamAbbreviations.put("4TM", "4 Different Teams");
    }
}  

