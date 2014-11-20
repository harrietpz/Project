package grinnell.edu.project;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Methods for making a schedule of games
 * 
 * @author Ajuna Kyaruzi
 * @author Leah Greenberg
 * @author Eileen Fordham
 * @author Hattie Zucker
 * */
public class ScheduleMaker
{
  /**
   * Make the schedule of games
   * @param schools, a SchoolSet
   * @param distances, an array of distances
   * @return an array of Games
   */
  public static Game[] makeSchedule(SchoolSet schools, Distance[] distances)
  {
    //STUB
    return null;
  }//makeSchedule(SchoolSet)
  
  /**
   * Picks a random school and a second school from the first's necessary plays. 
   * If the first's plays list is empty, then pick a new first school again until 
   * Successful.
   * @param schools
   * @return 
   */
  public static School[] getCompatibleSchools(SchoolSet schools)
  {
    //STUB
    return null;
  }//getCompatibleSchool(SchoolSet)
  
  /**
   * Finds a working date or null if no date is possible between those schools.
   * @param schools
   * @param school1
   * @param school2
   * @param season
   * @param distance
   * @return playDate, a LocalDate
   */
  public static LocalDate findDate(SchoolSet schools, School school1, School school2, ArrayList<LocalDate> season, Distance[] distance )
  {
    //STUB
    return null ;
  }//findDate(SchoolSet, School, School, ArrayList<LocalDate>, Distance)
  
  /**
   * Checks if the Date works for both schools
   * @param schools
   * @param school1
   * @param school2
   * @param gameDay
   * @param distance
   * @return result, an int
   * @post returns -1 for failure, 0 for acceptance and 1 for preference
   */
  public static int checkDate(SchoolSet schools, School school1, School school2, LocalDate gameDay, Distance[] distance)
  {
    //STUB
    return 0 ;
  }//checkDate(SchoolSet, School, School, LocalDate, Distance)
  
  /**
   * Checks if either school has played on the specified date
   * @param schools
   * @param school1
   * @param school2
   * @param gameDay
   * @return boole, a boolean
   */
  public static boolean checkIfPlayed(SchoolSet schools, School school1, School school2, LocalDate gameDay)
  {
    //STUB
    return false ;
  }//checkIfPlayed(SchoolSet, School, School, LocalDate)
  
  /**
   * Finds the distance between the two specified schools 
   * @param school1
   * @param school2
   * @param distances
   * @return howFar, a distance
   */
  public static Distance findDistance (School school1, School school2, Distance[] distances)
  {
    //STUB
    return null ;
  }//findDistance(School, School, Distance[])
  
  /**
   * Removing each school from its partner's plays list 
   * Removes the date from each schools' yesDates list if it exists. 
   * @param school1
   * @param school2
   * @param day
   */
  public static void updateSchools(School school1, School school2, LocalDate day)
  {
    //STUB
  }//updateSchools(School, School, LocalDate)
  
  /**
   * Checks  if all the schools within the SchoolSet 
   * Have empty play lists (fulfilling the playing requirements). 
   * @param schools
   * @return
   */
  public static boolean fulfilled(SchoolSet schools)
  {
    //STUB
    return false ;
  }//fulfilled(SchoolSet)
  
  
}//class ScheduleMaker
