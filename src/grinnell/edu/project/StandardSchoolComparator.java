package grinnell.edu.project;

import java.util.Comparator;

/**
 * A standard comparator for schools. 
 *
 * @author Samuel A. Rebelsky
 * @author Ajuna S. Kyaruzi
 */
public class StandardSchoolComparator
    implements Comparator<School>
{
  /**
   * The one copy.
   */
  public static StandardSchoolComparator COMPARATOR =
      new StandardSchoolComparator();

  /**
   * Build a new comparator.  We make this private to keep the class
   * a singleton.
   */
  private StandardSchoolComparator()
  {
  } // StandardGameComparator()

  @Override
  public int compare(School x, School y)
  {
    return x.name.compareTo(y.name);
  } // compare(School, School)

} // StandardSchoolComparator
