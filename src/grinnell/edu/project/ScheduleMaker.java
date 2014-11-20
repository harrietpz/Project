package grinnell.edu.project;

import java.time.DayOfWeek;
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
  public static SchoolSet makeSchedule(SchoolSet schools, Distance[] distances)
  {
    while (!fulfilled(schools))
      {
        LocalDate date = null;
        while (date == null)
          {
            School[] pair = getCompatibleSchools(schools);
            date =
                findDate(schools, pair[0], pair[1], schools.season, distances);
            if (date != null)
              {
                Game game = new Game(date, pair[0], pair[1]);
                if (!checkIfPlayed(schools, game))
                  {
                    schools.games.add(game);
                    updateSchools(game);
                  }//if
              } //if
          } //while
      } //while
    return schools;
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
  public static LocalDate findDate(SchoolSet schools, School school1,
                                   School school2, ArrayList<LocalDate> season,
                                   Distance[] distance)
  {
    //Pick random date
    //check if date value is null
    //if fails, return null
    //if succeeds, return date

    //Try multiple times until we get the best date.
    return null;
  }//findDate(SchoolSet, School, School, ArrayList<LocalDate>, Distance)

  /**
   * Checks if the Date works for both schools
   * @param schools
   * @param game
   * @param distance
   * @return result, an int
   * @post returns -1 for failure, 0 for acceptance and 1 for preference
   */
  public static int
    checkDate(SchoolSet schools, Game game, Distance[] distance)
  {
    int result = -1;
    if (game.away.noDates.contains(game.date) 
        || game.home.noDates.contains(game.date))
      {
        return result;
      }//if
    if (!checkIfPlayed(schools,game))
      {
        if (game.date.getDayOfWeek() == DayOfWeek.TUESDAY
            || game.date.getDayOfWeek() == DayOfWeek.WEDNESDAY)
          {
            if (findDistance(game.home, game.away, distance) <= 270)
              {
                result = 0;
              }//if
          }//if
        if (game.away.yesDates.contains(game.date) 
            || game.home.yesDates.contains(game.date))
          {
            result = 1;
          }//if
        return result;
      }//if
    result = -1;
    return result;
  }//checkDate(SchoolSet, Game, Distance)

  /**
   * Checks if either school has played on the specified date
   * @param schools
   * @param game
   * @return boole, a boolean
   */
  public static boolean checkIfPlayed(SchoolSet schools, Game game)
  {
    //Go through SchoolSet's games field, check all the games for either schools
    //and that date
    return false;
  }//checkIfPlayed(SchoolSet, Game)

  /**
   * Finds the distance between the two specified schools 
   * @param school1
   * @param school2
   * @param distances
   * @return howFar, an int
   */
  public static int findDistance(School school1, School school2,
                                 Distance[] distances)
  {
    String ab1 = school1.abrev;
    String ab2 = school2.abrev;
    for (int i = 0; i < distances.length; i++)
      {
        Distance dist = distances[i];
        if ((dist.school1 == ab1 && dist.school2 == ab2)
            || (dist.school1 == ab2 && dist.school2 == ab1))
          {
            return dist.distance;
          }//if
      } //for
    return -1;
  }//findDistance(School, School, Distance[])

  /**
   * Removing each school from its partner's plays list 
   * Removes the date from each schools' yesDates list if it exists. 
   * @param game, a Game
   */
  public static void
    updateSchools(Game game)
  {
    School school1 = game.home ;
    School school2 = game.away ;
    LocalDate day = game.date ;
    if (school1.plays.contains(school2.abrev))
      {
        school1.plays.remove(school1.abrev);
        school2.plays.remove(school2.abrev);
      } //if
    if (school1.yesDates.contains(day))
      {
        school1.yesDates.remove(day);
        school2.yesDates.remove(day);
      } //if
  }//updateSchools(Game)

  /**
   * Checks  if all the schools within the SchoolSet 
   * Have empty play lists (fulfilling the playing requirements). 
   * @param schools
   * @return
   */
  public static boolean fulfilled(SchoolSet schools)
  {
    //STUB
    return false;
  }//fulfilled(SchoolSet)

}//class ScheduleMaker
