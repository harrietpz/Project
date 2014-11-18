package grinnell.edu.project;

public class SchoolSet
{
  //+--------+----------------------------------------------------------
  //| Fields |
  //+--------+
  //holds the names of schools
  School[] schools;
  //holds the possible dates of play
  int[] season;
  //holds the second school, the location of the game
  School home;

  //+--------------+---------------------------------------------------
  //| Constructors |
  //+--------------+

  public Game(int date, School school1, School school2)
  {
    this.date = date;
    this.away = school1;
    this.home = school2;
  }//Game()

  //+---------+-----------------------------------------------------
  //| Methods |
  //+---------+
}//class SchoolSet
