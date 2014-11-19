package grinnell.edu.project;

public class School
{
  //+--------+----------------------------------------------------------
  //| Fields |
  //+--------+
  //holds necessary dates of play
  int[] yesDates;
  //holds restrictions of play
  int[] noDates;
  //holds counter of scheduled games
  int gameCount;
  //holds the name of the school
  String name;
  //Holds the abbreviation for the school name
  String abrev;

  //+--------------+---------------------------------------------------
  //| Constructors |
  //+--------------+

  public School(int[] dates, int[] dates2, String name, String abrev)
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

  public boolean canPlay(int date)
  {
    //STUB
    return true;
  }//canPlay(int)

  public boolean mustPlay(int date)
  {
    //STUB
    return true;
  }//mustPlay(int)
  
  public boolean maxGames()
  {
    return this.gameCount==16;
  }//maxGames()

}//class School
