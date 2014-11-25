package grinnell.edu.project;

import java.util.Comparator;

/**
 * A standard comparator for games. 
 *
 * @author Samuel A. Rebelsky
 * @author Ajuna S. Kyaruzi
 */
public class StandardGameComparator
    implements Comparator<Game>
{
  /**
   * The one copy.
   */
  public static StandardGameComparator COMPARATOR =
      new StandardGameComparator();

  /**
   * Build a new comparator.  We make this private to keep the class
   * a singleton.
   */
  private StandardGameComparator()
  {
  } // StandardGameComparator()

  @Override
  public int compare(Game x, Game y)
  {
    return x.date.compareTo(y.date);
  } // compare(Game, Game)

} // StandardGameComparator
