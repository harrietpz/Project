package grinnell.edu.project;

import java.time.LocalDate;
/**
 * A class describing a set of School objects
 * 
 * @author Ajuna Kyaruzi
 * @author Leah Greenberg
 * @author Eileen Fordham
 * @author Hattie Zucker
 *
 */
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
}//class SchoolSet
