package grinnell.edu.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
/**
 * A user interface to make game schedules
 * 
 * @author Ajuna Kyaruzi
 * @author Leah Greenberg
 * @author Eileen Fordham
 * @author Hattie Zucker
 *
 */
public class UserInterface
{
  public static void main(String[] args)
    throws IOException
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader inputReader =
        new BufferedReader(new InputStreamReader(System.in));
    //enter the file that holds schools/info
    pen.println("Enter name of school file: ");
    List<String> schoolFile = Utils.readFile(pen, inputReader);
    SchoolSet schools = Utils.schoolReader(schoolFile);
    //enter the file that holds distances
    pen.println("Enter name of distance file: ");
    List<String> distanceFile = Utils.readFile(pen, inputReader);
    Distance[] distances = Utils.distanceReader(distanceFile);
    //make and print the schedule
    Utils.schedPrint(pen, ScheduleMaker.makeSchedule(schools, distances));
  }//main
}// class UserInterface
