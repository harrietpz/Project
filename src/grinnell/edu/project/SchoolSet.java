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

  //+--------------+---------------------------------------------------
  //| Constructors |
  //+--------------+

  public SchoolSet(School[] schools, int[] season)
  {
    this.schools = schools ;
    this.season = season ;
  }//SchoolSet()

  //+---------+-----------------------------------------------------
  //| Methods |
  //+---------+
}//class SchoolSet
