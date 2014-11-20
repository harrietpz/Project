package grinnell.edu.project;

import java.time.LocalDate;

public class School
{
  //+--------+----------------------------------------------------------
  //| Fields |
  //+--------+
  //holds necessary dates of play
  LocalDate[] yesDates;
  //holds restrictions of play
  LocalDate[] noDates;
  //holds counter of scheduled games
  int gameCount;
  //holds the name of the school
  String name;
  //Holds the abbreviation for the school name
  String abrev;

  //+--------------+---------------------------------------------------
  //| Constructors |
  //+--------------+

  public School(LocalDate[] dates, LocalDate[] dates2, String name, String abrev)
  {
    this.yesDates = dates;
    this.noDates = dates2;
    this.name = name;
    this.abrev = abrev;
    this.gameCount = 0;
  }//School(int[], int[], String, String)
  
  public School()
  {
    this.gameCount = 0;
  }//School()

  //+---------+-----------------------------------------------------
  //| Methods |
  //+---------+

  public boolean canPlay(LocalDate date)
  {
    //STUB
    return true;
  }//canPlay(int)

  public boolean mustPlay(LocalDate date)
  {
    //STUB
    return true;
  }//mustPlay(int)
  
  public boolean maxGames()
  {
    return this.gameCount==16;
  }//maxGames()

}//class School
