package grinnell.edu.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;

//@Citation We used code that Ajuna and Albert wrote for HW 7
//@Citation http://stackoverflow.com/questions/4716503/best-way-to-read-a-text-file
//@Citation https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html#read--

public class Utils
{
  /**
   * Method to convert a string to an array of LocalDates
   */
  public static LocalDate[] StringToDate(String[] input)
  {
    int count = input.length;

    LocalDate[] vals = new LocalDate[count];

    for (int i = 0; i < count; i++)
      {
        CharSequence tmp = input[i].subSequence(0, input[i].length()) ;
        vals[i] = LocalDate.parse(tmp);
        System.out.println(vals[i].toString());
      }//for

    return vals;
  }//StringToDate(String)

  /**
   * Method that reads the file that we entered and returns a string linked list
   * @throws IOException
   */
  public static List<String> readFile(PrintWriter pen,
                                      BufferedReader inputReader)
    throws IOException
  {
    pen.println("Enter filename please: ");
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

  public static SchoolSet schoolReader(List<String> input)
  {
    ListIterator<String> cursor = input.listIterator();
    if (input.isEmpty())
      return null;

    int numberOfSchools = Integer.valueOf(cursor.next());
    LocalDate[] season = StringToDate((cursor.next()).split(", "));

    School[] schoolArray = new School[numberOfSchools];
    int i = 0;
    while (cursor.hasNext() && i < numberOfSchools)
      {
        School tmp = new School();
        tmp.name = cursor.next();
        tmp.abrev = cursor.next();
        tmp.yesDates = StringToDate((cursor.next()).split(", "));
        tmp.noDates = StringToDate((cursor.next()).split(", "));
        cursor.next();
        i++;
      }//while

    SchoolSet answer = new SchoolSet(schoolArray, season);

    return answer;
  }
}//class Utils
