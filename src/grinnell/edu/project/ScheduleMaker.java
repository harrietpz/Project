package grinnell.edu.project;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

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
        Game game = null;
        while (game == null)
          {
            School[] pair = getCompatibleSchools(schools);
            game =
                findGame(schools, pair[0], pair[1], schools.season, distances);
            if (game != null)
              {
                if (!checkIfPlayed(schools, game))
                  {
                    schools.games.add(game);
                    System.out.println(game.home.name + " vs. " + game.away.name + " on " + game.date);
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
  public static School[] getCompatibleSchools(SchoolSet set)
  {
    Random rand = new Random();
    School school1 = set.schools[rand.nextInt(set.schools.length)];
    int playSize = school1.plays.size();
    while (playSize < 1)
      {
        school1 = set.schools[rand.nextInt(set.schools.length)];
      }//while
    String abbrev = school1.plays.get(rand.nextInt(playSize));
    School school2 = set.getSchool(abbrev);
    boolean needsAway1 = school1.needsAway();
    int i = 0;
    while (school2.needsAway() != needsAway1 && i < (playSize * 2))
      {
        abbrev = school1.plays.get(rand.nextInt(playSize));
        school2 = set.getSchool(abbrev);
        i++;
      }//while
    School[] pair;
    if (needsAway1)
      {
        pair = new School[] { school1, school2 };
      }//if
    else
      {
        pair = new School[] { school2, school1 };
      }//else
    return pair;
  }//getCompatibleSchool(SchoolSet)

  /**
   * Finds a working game or null if no date is possible between those schools.
   * @param set
   * @param school1
   * @param school2
   * @param season
   * @param distance
   * @return game
   */
  public static Game findGame(SchoolSet set, School school1, School school2,
                              ArrayList<LocalDate> season, Distance[] distance)
  {
    //Pick random date
    Random rand = new Random();
    LocalDate date = season.get(rand.nextInt(season.size()));
    Game game = new Game(date, school1, school2);
    //check date
    int checkValue = checkDate(set, game, distance);
    int oldValue = checkValue ;
    Game oldGame = game ;
    
    int i = 0;
    while (checkValue < 1 && i < season.size())
      {
        date = season.get(rand.nextInt(season.size()));
        game = new Game(date, school1, school2);
        
        checkValue = checkDate(set, game,distance);
        if (checkValue > oldValue)
          {
            //make the oldvalue, the checkvalue
            oldValue = checkValue ;
            oldGame = game ;
          }//if found a better date, update 
        
        i++;
      }//while
    
    //if  no valid dates, return null
    if (checkValue == -1)
      {
        return null;
      }
    //if succeeds, return best game
    return oldGame;
  }//findGame(SchoolSet, School, School, ArrayList<LocalDate>, Distance)

  /**
   * Checks if the date of a game works for both schools
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
    if (!checkIfPlayed(schools, game))
      {
        if (game.date.getDayOfWeek().equals(DayOfWeek.TUESDAY)
            || game.date.getDayOfWeek().equals(DayOfWeek.WEDNESDAY))
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
      }//if
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
    ArrayList<Game> schoolGames = schools.games;

    //If the exact game is in schoolGames, no need to iterate, return it
    Boolean boole = false ; 
    if (schoolGames.size() == 0)
      {
        return false ;
      }//if
    else
      {
        boole = schoolGames.contains(game);
      }//else
    
    if (!boole)
      {
        int size = schoolGames.size();
        for (int i = 0; i < size; i++)
          {
            Game current = schoolGames.get(i);
            if (current.date.equals(game.date))
              {
                if (current.away.equals(game.away) || current.home.equals(game.away)
                    || current.away.equals(game.home) || current.home.equals(game.home))
                  {
                    boole = true;
                  }//if
              }//if
          }//for
      }//if

    return boole;
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
        if ((dist.school1.equals(ab1) && dist.school2.equals(ab2))
            || (dist.school1.equals(ab2) && dist.school2.equals(ab1)))
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
  public static void updateSchools(Game game)
  {
    School school1 = game.away;
    School school2 = game.home;
    LocalDate day = game.date;
    if (school1.plays.contains(school2.abrev))
      {
        school1.plays.remove(school2.abrev);
        school2.plays.remove(school1.abrev);
      } //if
    if (school1.yesDates.contains(day))
      {
        school1.yesDates.remove(day);
      } //if
    if (school2.yesDates.contains(day))
      {
        school2.yesDates.remove(day);
      } //if
    school2.homeGamesLeft--;
    school1.awayGamesLeft--;
  }//updateSchools(Game)

  /**
   * Checks  if all the schools within the SchoolSet 
   * Have empty play lists (fulfilling the playing requirements). 
   * @param set
   * @return
   */
  public static boolean fulfilled(SchoolSet set)
  {
    boolean fulfilled = true;
    for(int i = 0; i < set.schools.length; i++)
      {
        if(!set.schools[i].plays.isEmpty())
          {
            fulfilled = false;
          } // if school needs games
      } // for all schools
    return fulfilled;
  }//fulfilled(SchoolSet)

}//class ScheduleMaker
