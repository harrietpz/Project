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
  // +-----------+-------------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * A random number generator to use to find random schools, dates and games
   */
  static Random rand = new Random();

  //+---------+-----------------------------------------------------
  //| Methods |
  //+---------+

  /**
   * Make the schedule of games
   * @param set, a SchoolSet
   * @param distances, an array of distances
   * @return resultSet, a SchoolSet
   * @post resultSet.games is full with all games needed to be played
   * @post resultSet.schools = set.schools
   * @post resultSet.season = set.season
   */
  public static SchoolSet makeSchedule(SchoolSet set, Distance[] distances)
  {
    while (!fulfilled(set))
      {
        School[] pair = getCompatibleSchools(set);
        Game game = findGame(set, pair[0], pair[1], set.season, distances);
        //If the game is null, add it to the schoolSet
        if (game != null)
          {
            set.games.add(game);
            updateSchools(game);
          } //if
        //Otherwise, there is no possible game between the two schools,
        //remove some games, and try again
        else if (game == null)
          {
            for (int i = 0; i < (set.games.size() * .05); i++)
              {
                int index = rand.nextInt(set.games.size());
                // get random game
                Game temp = set.games.get(index);
                // update game's schools' plays and home/away games and dates
                reverseUpdateSchools(temp);
                // remove random game
                set.games.remove(index);
              }//for
          }//else
      } //while
    return set;
  }//makeSchedule(SchoolSet, Distance[])

  /**
   * Picks a random school and a second school from the first's necessary plays. 
   * If the first's plays list is empty, then pick a new first school again 
   * Until successful.
   * @param set, a SchoolSet
   * @return arr, an array of Schools
   * @post arr.length = 2
   * @post arr[0] is home
   * @post arr[1] is away
   * @pre set is unfulfilled
   */
  public static School[] getCompatibleSchools(SchoolSet set)
  {
    //Pick a random first school
    School school1 = set.schools[rand.nextInt(set.schools.length)];
    int playSize = school1.plays.size();
    // If the school's playSize is less than 1, get a new school
    while (playSize < 1)
      {
        school1 = set.schools[rand.nextInt(set.schools.length)];
        playSize = school1.plays.size();
      }//while
    //Get a second school from the first one's plays
    String abbrev = school1.plays.get(rand.nextInt(playSize));
    School school2 = set.getSchool(abbrev);
    boolean needsAway1 = school1.needsAway();
    int i = 0;
    //if both schools need an away game, find a new working school
    //iterate (school1.plays.size * 2) times, if still hasn't found it
    //then continue with the last "bad" match.
    while (school2.needsAway() == needsAway1 && i < (playSize * 2))
      {
        abbrev = school1.plays.get(rand.nextInt(playSize));
        school2 = set.getSchool(abbrev);
        i++;
      }//while
    //Make a pair of the two schools playing together, in the order
    //{home, away} determined by which one needs an away game more. 
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
   * @param set, a SchoolSet
   * @param school1, a School
   * @param school2, a School
   * @param season, an ArrayList of LocalDates
   * @param distances, an array of Distances
   * @return game, a Game
   * @post game is null if there is no date possible between the schools
   * @post game.home = school1
   * @post game.away = school2
   * @post season.contains(game.date) is true
   */
  public static Game
    findGame(SchoolSet set, School school1, School school2,
             ArrayList<LocalDate> season, Distance[] distances)
  {
    int i = 0;
    //Pick random date
    LocalDate date = season.get(rand.nextInt(season.size()));
    Game game = new Game(date, school1, school2);
    //check date
    int checkValue = checkDate(set, game, distances);
    int oldValue = checkValue;
    Game oldGame = game;
    while (checkValue < 1 && i < season.size())
      {
        date = season.get(rand.nextInt(season.size()));
        game = new Game(date, school1, school2);
        checkValue = checkDate(set, game, distances);
        if (checkValue > oldValue)
          {
            //make the oldvalue, the checkvalue
            oldValue = checkValue;
            oldGame = game;
          }//if found a better date, update 
        i++;
      }//while   
    if (checkValue == -1)
      {
        return null;
      }//if  no valid dates, return null
    //otherwise it succeeds so return best game
    return oldGame;
  }//findGame(SchoolSet, School, School, ArrayList<LocalDate>, Distance[])

  /**
   * Checks if the date of a game works for both schools
   * @param set, a SchoolSet
   * @param game, a Game
   * @param distances, an array of Distances
   * @return result, an int
   * @post returns -1 for failure, 0 for acceptance and 1 for preference
   */
  public static int checkDate(SchoolSet set, Game game, Distance[] distances)
  {
    int result = -1;
    if (game.away.noDates != null
        && game.home.noDates != null
        && (game.away.noDates.contains(game.date) 
            || game.home.noDates.contains(game.date)))
      {
        return -1;
      }//if
    if (!checkIfPlayed(set, game))
      {
        //if the game is a Tuesday or Wednesday 
        //and the distance is greater than 270
        //then return -1
        if (((game.date.getDayOfWeek().equals(DayOfWeek.TUESDAY)) 
            || (game.date.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)))
            && (findDistance(game.home, game.away, distances) > 270))
          {
            return -1;
          }//if
        if ((game.away.yesDates != null)
            && (game.home.yesDates != null)
            && ((game.away.yesDates.contains(game.date)) 
                || game.home.yesDates.contains(game.date)))
          {
            result = 1;
          }//if
        else
          {
            result = 0;
          }// else
      }//if
    return result;
  }//checkDate(SchoolSet, Game, Distance[])

  /**
   * Checks if either school has played on the specified date
   * @param set, a SchoolSet
   * @param game, a Game
   * @return boole, a boolean
   * @post boole is true if either school has played on that date
   * @post boole is false if neither school has played on that date
   */
  public static boolean checkIfPlayed(SchoolSet set, Game game)
  {
    //Go through SchoolSet's games field, check all the games for either schools
    //and that date
    ArrayList<Game> schoolGames = set.games;
    Boolean boole = false;
    //If the exact game is in schoolGames, no need to iterate, return it
    if (schoolGames.size() == 0)
      {
        return false;
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
                if (current.away.equals(game.away)
                    || current.home.equals(game.away)
                    || current.away.equals(game.home)
                    || current.home.equals(game.home))
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
   * @param school1, a School
   * @param school2, a School
   * @param distances, an array of Distances
   * @return an int
   * @post returns -1 if school1 and school2 are not in distances
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
   * Decrements the Home and Away Games of respective schools.
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
   * Adds each school to its partner's plays list 
   * Increments the Home and Away Games of respective schools.
   * @param game, a Game
   */
  public static void reverseUpdateSchools(Game game)
  {
    School school1 = game.away;
    School school2 = game.home;
    school1.plays.add(school2.abrev);
    school2.plays.add(school1.abrev);
    school2.homeGamesLeft++;
    school1.awayGamesLeft++;
  }//reverseUpdateSchools(Game)

  /**
   * Checks  if all the schools within the SchoolSet 
   * Have empty play lists (fulfilling the playing requirements). 
   * @param set
   * @return a boolean
   * @post returns true if all the school's in set have empty plays
   * @post returns false if any school's in set does not have an empty plays
   */
  public static boolean fulfilled(SchoolSet set)
  {
    boolean fulfilled = true;
    for (int i = 0; i < set.schools.length; i++)
      {
        if (!set.schools[i].plays.isEmpty())
          {
            fulfilled = false;
          } // if school needs games
      } // for all schools
    return fulfilled;
  }//fulfilled(SchoolSet)
}//class ScheduleMaker
