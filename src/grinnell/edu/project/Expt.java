package grinnell.edu.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;


public class Expt
{

  public static void main(String[] args)
    throws IOException
  {
    /* variables for UI */
    //pen and reader
    PrintWriter pen = new PrintWriter(System.out, true);
    BufferedReader inputReader =
        new BufferedReader(new InputStreamReader(System.in));
   // System.out.println(Utils.readFile(pen, inputReader));
    
    String[] vals = { "1955-12-04", "1995-09-15", "1994-09-13", "1994-09-17"};
    
    LocalDate[] dating = Utils.StringToDate(vals) ;
    
    
  }//main

}// class Expt
