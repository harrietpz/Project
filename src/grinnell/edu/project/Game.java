package grinnell.edu.project;

public class Game
{
  //+--------+----------------------------------------------------------
  //| Fields |
  //+--------+
  //holds the date of a game
  int date;
  //holds the first school
  School away;
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
  public int distance()
  {
    //STUB
    return 0;
  }//distance()

}
//class Game