package grinnell.edu.project;
/**
 * A class containing information about the distance between two schools
 * 
 * @author Ajuna Kyaruzi
 * @author Leah Greenberg
 * @author Eileen Fordham
 * @author Hattie Zucker
 *
 */
public class Distance
{
  //+--------+----------------------------------------------------------
  //| Fields |
  //+--------+
  /**
   * holds the abbreviation for the first school
   */
  String school1;
  /**
   * holds the abbreviation for the second school
   */
  String school2;
  /**
   * holds the distance between these two schools
   */
  int distance;

  //+--------------+---------------------------------------------------
  //| Constructors |
  //+--------------+
  public Distance(String school1, String school2, int distance)
  {
    this.school1 = school1;
    this.school2 = school2;
    this.distance = distance;
  }//Distance(String, String, int)
 }// class Distance
