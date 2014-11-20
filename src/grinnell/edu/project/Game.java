package grinnell.edu.project;

import java.time.LocalDate;
import java.util.Date;

public class Game
{
  //+--------+----------------------------------------------------------
  //| Fields |
  //+--------+
  //holds the date of a game
  LocalDate date;
  //holds the first school
  School away;
  //holds the second school, the location of the game
  School home;
 
  //+--------------+---------------------------------------------------
  //| Constructors |
  //+--------------+
  
  public Game(LocalDate date, School school1, School school2)
  {
    this.date = date;
    this.away = school1;
    this.home = school2;
  }//Game()

  //+---------+-----------------------------------------------------
  //| Methods |
  //+---------+
  public int distance()
  {
    //STUB
    return 0;
  }//distance()

}
//class Game