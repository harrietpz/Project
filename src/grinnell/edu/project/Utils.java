package grinnell.edu.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Methods for reading files to make schedules and printing them
 * 
 * @author Ajuna Kyaruzi
 * @author Leah Greenberg
 * @author Eileen Fordham
 * @author Hattie Zucker
 * @Citation We used code that Ajuna and Albert wrote for HW 7
 * @Citation http://stackoverflow.com/questions/4716503/best-way-to-read-a-text-file
 * @Citation https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html#read--
*/
public class Utils
{
  /**
   * Method that reads the file that we entered and returns a string linked list
   * @throws IOException
   * @param pen, a PrintWriter
   * @param inputReader, a BufferedReader
   */
  public static List<String> readFile(PrintWriter pen,
                                      BufferedReader inputReader)
    throws IOException
  {
    String userResponse = inputReader.readLine();
    pen.flush();
    Path path = FileSystems.getDefault().getPath(userResponse);

    while (Files.exists(path, LinkOption.NOFOLLOW_LINKS) == false)
      {
        pen.println("File doesn't exist");
        pen.println("Enter filename please: ");
        userResponse = inputReader.readLine();
        pen.flush();
        path = FileSystems.getDefault().getPath(userResponse);
      }//while
    List<String> answer = Files.readAllLines(path);
    return answer;
  }// readFile()

  /**
   * Method that turns input into a SchoolSet
   * @param input, a linked list
   * @return a SchoolSet
   */
  public static SchoolSet schoolReader(List<String> input)
  {
    ListIterator<String> cursor = input.listIterator();
    if (input.isEmpty())
      return null;

    int numberOfSchools = Integer.valueOf(cursor.next());
    ArrayList<LocalDate> season = stringToDate((cursor.next()).split(", "));

    School[] schoolArray = new School[numberOfSchools];
    int i = 0;
    while (cursor.hasNext() && i < numberOfSchools)
      {
        cursor.next();
        String name = cursor.next();
        String abrev = cursor.next();
        String yes = (cursor.next());
        ArrayList<LocalDate> yesDates = null;
        if (yes != "")
          {
            yesDates = stringToDate(yes.split(", "));
          }//if
        String no = (cursor.next());
        ArrayList<LocalDate> noDates = null;
        if (no != "")
          {
            noDates = stringToDate(no.split(", "));
          }//if

        ArrayList<String> plays = new ArrayList<String>(16);
        String[] twice = cursor.next().split(" ");
        String[] once = cursor.next().split(" ");
        for (int j = 0; j < twice.length; j++)
          {
            plays.add(twice[j]);
            plays.add(twice[j]);
          }//for
        for (int j = 0; j < once.length; j++)
          {
            plays.add(once[j]);
          }//for

        schoolArray[i] = new School(name, abrev, yesDates, noDates, plays);
        i++;
      }//while

    SchoolSet answer = new SchoolSet(schoolArray, season);

    return answer;
  }//schoolReader(List<String>)

  /**
   * Turn input into an array of distances
   * @param input, a linked list
   * @return an array of Distances
   */
  public static Distance[] distanceReader(List<String> input)
  {
    ListIterator<String> cursor = input.listIterator();
    int size = input.size();
    if (size == 0)
      return null;
    Distance[] vals = new Distance[size];
    for (int i = 0; i < size; i++)
      {
        String[] tmp = cursor.next().split(" ");
        vals[i] = new Distance(tmp[0], tmp[1], Integer.valueOf(tmp[2]));
      }//for loop

    return vals;

  }//distanceReader(List<String>)

  /**
   * Method to convert a string to an array list of LocalDates
   * @param input, a String[]
   * @return an ArrayList of LocalDates
   */
  public static ArrayList<LocalDate> stringToDate(String[] input)
  {
    int count = input.length;

    ArrayList<LocalDate> vals = new ArrayList<LocalDate>(count);
    for (int i = 0; i < count; i++)
      {
        if (input[i] != " ")
          {
            CharSequence tmp = input[i].subSequence(0, input[i].length());
            vals.add(i, LocalDate.parse(tmp));
          }
      }//for

    return vals;
  }//stringToDate(String)

  /**
   * Print out our entire schedule of games and dates
   * @param pen
   * @param games
   */
  public static void schedPrint(PrintWriter pen, SchoolSet schools)
  {
    ArrayList<Game> games = schools.games;
    pen.println("Schedule of Games");
    for (int i = 0; i < games.size(); i++)
      {
        pen.print(" " + (i + 1) + ".");
        printDate(games.get(i).date, pen);
        pen.println("  " + " " + games.get(i).home.abrev + " vs. "
                    + games.get(i).away.abrev + " at " + games.get(i).away.name);
      }//for loop
  }//schedPrint(PrintWriter, Game[])

  /**
   * Print out a specific date in our format
   * @param date
   * @param pen
   */
  public static void printDate(LocalDate date, PrintWriter pen)
  {
    int day = date.getDayOfMonth();
    String month = titleCase(date.getMonth().toString());
    int year = date.getYear();
    String weekday = titleCase(date.getDayOfWeek().toString());
    pen.println(weekday + " " + month + ", " + day + "th " + year);
  }//void (LocalDate, PrintWriter)

  /**
   * Turn a string into TitleCase
   * @param input, a String
   * @return a String
   */
  public static String titleCase(String input)
  {
    input = input.toLowerCase();
    Character first = input.charAt(0);
    first = Character.toUpperCase(first);
    return input.replace(input.charAt(0), first);
  }// camelCase

}//class Utils
