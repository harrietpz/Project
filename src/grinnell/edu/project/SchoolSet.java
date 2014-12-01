package grinnell.edu.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

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
  ArrayList<LocalDate> season;
  //holds all the games in the schedule
  ArrayList<Game> games;

  //+--------------+---------------------------------------------------
  //| Constructors |
  //+--------------+

  public SchoolSet(School[] schools, ArrayList<LocalDate> season)
  {
    this.schools = schools;
    this.season = season;
    this.games = new ArrayList<Game>();
  }//SchoolSet()

  // +-----------+-------------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * A comparator for integers.
   */
  public static final Comparator<School> standardSchoolComparator =
      (left, right) -> left.name.compareTo(right.name);

  /**
   * A comparator for integers.
   */
  public static final Comparator<Game> standardGameComparator =
      (left, right) -> left.date.compareTo(right.date);

  //+---------+-----------------------------------------------------
  //| Methods |
  //+---------+

  /**
   * Setting the ArrayList<Games> for the school as null
   * @param games
   */
  public void setGames(ArrayList<Game> games)
  {
    this.games = games;
  }//setGames(ArrayList<Game>

  public School getSchool(String abbrev)
  {
    int size = this.schools.length;

    for (int i = 0; i < size; i++)
      {
        //System.err.println(i + ". " + schools[i].abrev);
        if (schools[i].abrev.equals(abbrev))
          return schools[i];
      }//for
    return null;
  }//getSchool(String)

  /**
   * Shuffle the elements of an array.
   * @citation Based off code given on Problem 4 of Fall 2014 CSC207 Exam 1
   */
  public void permute()
  {
    Random rand = new Random();
    int size = this.schools.length;
    for (int i = 0; i < size; i++)
      {
        School tmp = this.schools[i];
        int j = Math.abs(rand.nextInt()) % size;
        this.schools[i] = this.schools[j];
        this.schools[j] = tmp;
      } // for
  } // shuffle()

  public Game[] sortByGameDate()
  { 
    Game[] ans = new Game[this.games.size()];
    this.games.toArray(ans) ;
    Arrays.sort(ans, standardGameComparator);
    return ans;
  } //sortByGameDate()

  public void sortBySchool()
  {
    Arrays.sort(this.schools, standardSchoolComparator);
  } //sortBySchool()

}//class SchoolSet
