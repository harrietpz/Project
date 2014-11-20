package grinnell.edu.project;

import java.time.LocalDate;

public class SchoolSet
{
  //+--------+----------------------------------------------------------
  //| Fields |
  //+--------+
  //holds the names of schools
  School[] schools;
  //holds the possible dates of play
  LocalDate[] season;

  //+--------------+---------------------------------------------------
  //| Constructors |
  //+--------------+

  public SchoolSet(School[] schools, LocalDate[] season)
  {
    this.schools = schools ;
    this.season = season ;
  }//SchoolSet()

  //+---------+-----------------------------------------------------
  //| Methods |
  //+---------+
}//class SchoolSet
