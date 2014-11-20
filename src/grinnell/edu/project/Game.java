package grinnell.edu.project;

import java.time.LocalDate;
/**
 * A class describing a particular game
 * 
 * @author Ajuna Kyaruzi
 * @author Leah Greenberg
 * @author Eileen Fordham
 * @author Hattie Zucker
 *
 */
public class Game
{
  //+--------+----------------------------------------------------------
  //| Fields |
  //+--------+
  /**
   * holds the date of a game
   */
  LocalDate date;
  /**
   * holds the first school
   */
  School away;
  /**
   * holds the second school, the location of the game
   */
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
  /**
   * Finds the distance between the two schools for a given game
   * @return int, representing distance in whatever unit given
   */
  public int distance()
  {
    //STUB
    return 0;
  }//distance()

}
//class Game