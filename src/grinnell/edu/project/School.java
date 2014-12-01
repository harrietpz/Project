package grinnell.edu.project;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class containing information about a school
 * 
 * @author Ajuna Kyaruzi
 * @author Leah Greenberg
 * @author Eileen Fordham
 * @author Hattie Zucker
 *
 */

public class School
{
  //+--------+----------------------------------------------------------
  //| Fields |
  //+--------+
  /**
   * holds necessary dates of play
   */
  ArrayList<LocalDate> yesDates;
  /**
   * holds restrictions of play
   */
  ArrayList<LocalDate> noDates;
  /**
   * holds an ArrayList of schools it has to play
   */
  ArrayList<String> plays;
  /**
   * holds the name of the school
   */
  String name;
  /**
   * Holds the abbreviation for the school name
   */
  String abrev;

  /**
   * Number of away games they need to play
   */
  int awayGamesLeft;

  /**
   * Number of home games they need to play
   */
  int homeGamesLeft;

  //+--------------+---------------------------------------------------
  //| Constructors |
  //+--------------+

/**
 * @param name, a String
 * @param abrev, a String
 * @param dates, ArrayList of LocalDates
 * @param dates2, ArrayList of LocalDates
 * @param plays, ArrayList of Strings
 */
  public School(String name, String abrev, ArrayList<LocalDate> dates, ArrayList<LocalDate> dates2, ArrayList<String> plays)
  {
    this.yesDates = dates;
    this.noDates = dates2;
    this.name = name;
    this.abrev = abrev;
    this.plays = plays;
    this.awayGamesLeft = 8;
    this.homeGamesLeft = 8;
  }//School(String, String, ArrayList, ArrayList, ArrayList)


  //+---------+-----------------------------------------------------
  //| Methods |
  //+---------+
  
  /**
   * Checks if the school needs more away games than home games
   * @return a boolean
   * @post returns true if awayGamesLeft is greater than or equal to 
   * homeGamesLeft, false otherwise.
   */
  public Boolean needsAway()
  {
    return (this.homeGamesLeft <= this.awayGamesLeft) ;
  }//needsAway()

}//class School
