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
   * @return a linked list 
   * 
   */
  public static List<String> readFile(PrintWriter pen,
                                      BufferedReader inputReader)
    throws IOException
  {
    //make a String called userResponse that is the next line from user
    String userResponse = inputReader.readLine();
    pen.flush();
    Path path = FileSystems.getDefault().getPath(userResponse);

    while (Files.exists(path, LinkOption.NOFOLLOW_LINKS) == false)
      {
        //print an error message and restart
        pen.println("File doesn't exist");
        pen.println("Enter filename please: ");
        userResponse = inputReader.readLine();
        pen.flush();
        path = FileSystems.getDefault().getPath(userResponse);
      }//while the file doesn't exist
    //read the file into a linked list
    List<String> answer = Files.readAllLines(path);
    return answer;
  }// readFile(PrintWriter, BufferedReader)

  /**
   * Method that turns input into a SchoolSet
   * @param input, a linked list
   * @return a SchoolSet
   * @pre input must be in the correct format specified in the wiki
   */
  public static SchoolSet schoolReader(List<String> input)
  {
    //make a list iterator called cursor
    ListIterator<String> cursor = input.listIterator();
    if (input.isEmpty())
      return null;
    //declare and initialize numberOfSchools to be the value from the file
    int numberOfSchools = Integer.valueOf(cursor.next());
    //declare and initialize season to be the dates in the file, split at commas 
    ArrayList<LocalDate> season = stringToDate((cursor.next()).split(", "));
    //make an array of Schools to hold the schools
    School[] schoolArray = new School[numberOfSchools];
    int i = 0;
    while (cursor.hasNext() && i < numberOfSchools)
      {
        //advance the cursor
        cursor.next();
        //get the name, abrev, and yes dates
        String name = cursor.next();
        String abrev = cursor.next();
        String yes = (cursor.next());
        ArrayList<LocalDate> yesDates = null;
        if (!(yes.isEmpty()))
          {
            yesDates = stringToDate(yes.split(", "));
          }//if yesDates is not null
        String no = (cursor.next());
        ArrayList<LocalDate> noDates = null;
        if (!(no.isEmpty()))
          {
            noDates = stringToDate(no.split(", "));
          }//if noDates is not null
        //make an ArrayList to hold plays
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
      }//while there is another element and we haven't progressed through all schools

    SchoolSet answer = new SchoolSet(schoolArray, season);

    return answer;
  }//schoolReader(List<String>)

  /**
   * Turn input into an array of distances
   * @param input, a linked list
   * @return an array of Distances
   * @pre input must be a file in the proper distance format
   */
  public static Distance[] distanceReader(List<String> input)
  {
    ListIterator<String> cursor = input.listIterator();
    int size = input.size();
    //if the file is empty return null
    if (size == 0)
      return null;
    Distance[] vals = new Distance[size];
    for (int i = 0; i < size; i++)
      {
        //split the inpup into tmp and fill vals with Distances
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
    //declare a new ArrayList to hold the dates
    ArrayList<LocalDate> vals = new ArrayList<LocalDate>(count);
    for (int i = 0; i < count; i++)
      {
        if (!(input[i].isEmpty()))
          {
            //add to vals
            CharSequence tmp = input[i].subSequence(0, input[i].length());
            vals.add(i, LocalDate.parse(tmp));
          }//if input is not null
      }//for

    return vals;
  }//stringToDate(String[])

  /**
   * Print out our entire schedule of games and dates
   * @param pen, a PrintWriter
   * @param schools, a SchoolSet
   */
  public static void schedPrint(PrintWriter pen, SchoolSet schools)
  {
    Game[] games = schools.sortByGameDate();
    pen.println("Schedule of Games");
    //loop through games
    for (int i = 0; i < games.length; i++)
      {
        //print desired info in desired format 
        pen.print(" " + (i + 1) + ".");
        printDate(games[i].date, pen);
        pen.println("  " + " " + games[i].home.abrev + " vs. "
                    + games[i].away.abrev + " at " + games[i].away.name);
      }//for 
  }//schedPrint(PrintWriter, SchoolSet)

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
  }//printDate(LocalDate, PrintWriter)

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
  }// titleCase(String)

}//class Utils
