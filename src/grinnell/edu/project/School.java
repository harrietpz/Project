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
  ArrayList<String> plays ;
  /**
   * holds the name of the school
   */
  String name;
  /**
   * Holds the abbreviation for the school name
   */
  String abrev;

  //+--------------+---------------------------------------------------
  //| Constructors |
  //+--------------+

  public School(ArrayList<LocalDate>dates, ArrayList<LocalDate> dates2, String name, String abrev)
  {
    this.yesDates = dates;
    this.noDates = dates2;
    this.name = name;
    this.abrev = abrev;
    this.plays = null ;
  }//School(int[], int[], String, String)
  
  public School()
  {
  }//School()

  //+---------+-----------------------------------------------------
  //| Methods |
  //+---------+


}//class School
