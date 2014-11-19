package grinnell.edu.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;
import java.util.ListIterator;

//@Citation We used code that Ajuna and Albert wrote for HW 7
//@Citation http://stackoverflow.com/questions/4716503/best-way-to-read-a-text-file
//@Citation https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html#read--

public class Utils
{
  /**
   * Method to convert a string to an array of ints
   */
  public static int[] arrayParser(String input)
  {
    int count=((input.length()-6) / 8) + 1;
    int[] vals = new int[count];
    int index=0;
    for(int i=0; i<count; i++)
      {
        //set vals to be the substring of length 6
        vals[i]=Integer.valueOf(input.substring(index, index+6));
        //increment index by 8
        index+=8; 
      }//for loop
    return vals;
  }//arrayParser(String)
  /**
   * Method that reads the file that we entered and returns a string linked list
   * @throws IOException
   */
  public static List<String> readFile()
    throws IOException
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader inputReader =
        new BufferedReader(new InputStreamReader(System.in));

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
    int[] season = arrayParser(cursor.next());
        
    School[] schoolArray = new School[numberOfSchools];
    int i = 0;
    while (cursor.hasNext() && i < numberOfSchools)
      {
        School tmp = new School();
        tmp.name = cursor.next();
        tmp.abrev = cursor.next();
        tmp.yesDates = arrayParser(cursor.next());
        tmp.noDates = arrayParser(cursor.next());
        cursor.next();
        i++;
      }//while

    SchoolSet answer = new SchoolSet(schoolArray, season);
    
    return answer;
  }
}//class Utils
