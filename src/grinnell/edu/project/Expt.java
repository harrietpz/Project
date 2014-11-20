package grinnell.edu.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class Expt
{

  public static void main(String[] args)
    throws IOException
  {
    /* variables for UI */
    //pen and reader
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader inputReader =
        new BufferedReader(new InputStreamReader(System.in));
  // pen.println(Utils.readFile(pen, inputReader));

    String[] vals = { "1955-12-04", "1995-09-15", "1994-09-13", "1994-09-17" };

    ArrayList<LocalDate> dating = Utils.stringToDate(vals);

    School school1 = new School(dating, dating, "First School", "FS");
    School school2 = new School(dating, dating, "Second School", "SS");
    School school3 = new School(dating, dating, "Third School", "TS");
    String date1 = "2014-11-19";
    Game game1 =
        new Game(LocalDate.parse(date1.subSequence(0, date1.length())),
                 school1, school2);
    Game game2 =
        new Game(LocalDate.parse("2014-11-20".subSequence(0, 10)), school2,
                 school1);
    Game game3 =
        new Game(LocalDate.parse("2014-11-21".subSequence(0, 10)), school3,
                 school2);
    Game game4 =
        new Game(LocalDate.parse("2014-11-22".subSequence(0, 10)), school2,
                 school3);
    Game game5 =
        new Game(LocalDate.parse("2014-11-23".subSequence(0, 10)), school1,
                 school3);
    ArrayList<Game> games = new ArrayList<Game>(5) ;
    games.add(game5);
    games.add(game4);
    games.add(game3);
    games.add(game2);
    games.add(game1);

    School[] shules = {school1, school2, school3} ;
    SchoolSet schools = new SchoolSet(shules, dating) ;
    schools.setGames(games);
    
    Utils.schedPrint(pen, schools);
    
    
  }//main

}// class Expt
