import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Luis Juarez on 11/29/2016.
 *
 */
public class JavaFinal {
    public static ArrayList<TeamScores> teamStats = new ArrayList<TeamScores>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Display");
        frame.setContentPane(new Display().Panel1);
        frame.setTitle("Basketball Stats");
        frame.setSize(700, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        int seconds = 60;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),0,seconds*1000);


           // printSomeData(connection);
            System.out.println("Totally Done");


    } // end main

    public static void readURL()
    {
        teamStats.clear();

        String url = "http://www.basketball-reference.com";
        String databaseName = "BasketBallStats.db";
            //creates connection to URL and reads in information
        try {
            Document doc = Jsoup.connect(url).get();
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);

            Elements winsList = doc.getElementsByAttributeValue("data-stat", "wins");
            Elements lossesList = doc.getElementsByAttributeValue("data-stat", "losses");
            Elements namesList = doc.getElementsByAttributeValue("data-stat", "team_name");

                for (int x =0; x < namesList.size(); x++)
                {
                 System.out.println(namesList.get(x).text() + " wins: " + winsList.get(x).text() + " losses: " + lossesList.get(x).text());

                 teamStats.add(new TeamScores(namesList.get(x).text(), winsList.get(x).text(), lossesList.get(x).text()));
                }
            storeDB(connection);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void storeDB(Connection connection)
    {
        //check for database file
        //if it doesn't exist create it
        try{
        checkDataBase(connection);

        Statement statement = connection.createStatement();

         for (int count = 0; count < teamStats.size(); count++)
         {
             String currentTeam = teamStats.get(count).getTeamName();
             String currentWin = teamStats.get(count).getWins();
             String currentLoss = teamStats.get(count).getLosses();
             String sql = "INSERT INTO basketball_stats(team_name, wins, losses)" + " VALUES('" + currentTeam + "', '" + currentWin + "', '" + currentLoss + "');";
             statement.executeUpdate(sql);
         }
        /*for(TeamScores x:teamStats){
            String sql = "INSERT INTO basketball_stats(team_name, wins, losses)"
                    + " VALUES('" + x.teamName + "'," + x.wins + ","
                    + x.losses + ");";
            statement.executeUpdate(sql);
        }*/
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public static void checkDataBase(Connection connection)
    {
        //create table if not already present
        Statement statement = null;
        try {
            statement = connection.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS basketball_stats(id INTEGER, team_name TEXT,"
                + " wins TEXT , losses TEXT, date_time DATETIME DEFAULT CURRENT_TIMESTAMP"
                + " ,PRIMARY KEY(id));";
            statement.execute(sql);

            //these two lines clear out the table
            // sql = "DELETE FROM basketball_stats;";
          //  statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printSomeData(Connection connection){
        Statement statement;
        try {
            statement = connection.createStatement();
            //some info that you can query
            //this will give you the sum of all participants
            ResultSet resultSet = statement.executeQuery("SELECT SUM(total) FROM educational_groups");
            System.out.printf("The sum of participants: %,d\n", resultSet.getInt(1));
            //this will give you age_group of highest highschool number
            resultSet = statement.executeQuery("SELECT age_group, highschool FROM educational_groups ORDER BY highschool DESC;");
            System.out.printf("The group with most highschool grads: %s\n", resultSet.getString(1));
            //this will give you age_group of lowest highschool number
            resultSet = statement.executeQuery("SELECT age_group, highschool FROM educational_groups ORDER BY highschool;");
            System.out.printf("The group with least highschool grads: %s\n", resultSet.getString(1));
            //to get a ratio of 25+ who graduated from highschool
            resultSet = statement.executeQuery("SELECT total, age_group, highschool FROM educational_groups WHERE age_group = '25 years and over' ;");
            Double ratio = (double)resultSet.getInt(3)/ (double)resultSet.getInt(1);
            System.out.printf("The %% of participants in 25 year and older: %.3f%%\n", ratio);
            //to get highest number group name, you have to create a nested query
            resultSet = statement.executeQuery("SELECT age_group, highschool FROM educational_groups WHERE educational_groups.highschool=(SELECT Max(educational_groups.highschool) FROM educational_groups);");
            System.out.printf("The the highest number of highschool grads comes from: %s\n", resultSet.getString(1));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}//end FinalClass

class MyTimerTask extends TimerTask {
    public void run() {
        // TODO    Hummm what do I want to do when this timer clicks?
        JavaFinal.readURL();

        System.out.println("Timer task executed.");
    }
}

class TeamScores {
    String teamName;
    String wins;
    String losses;

    public TeamScores() {
        teamName = "";
        wins = "";
        losses = "";
    }

    public TeamScores(String teamName, String wins, String losses) {
        this.teamName = teamName;
        this.wins = wins;
        this.losses = losses;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getWins() {
        return wins;
    }

    public String getLosses() {
        return losses;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }
}

