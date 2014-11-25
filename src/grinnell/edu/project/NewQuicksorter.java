package grinnell.edu.project;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Sort using a slightly different version of Quicksort.
 *
 * @author Samuel A. Rebelsky
 * @author Ajuna S. Kyaruzi
 * @author Ameer Shujjah
 * Citation Based off CSC207 HW8
 */
public class NewQuicksorter<T>
    extends SorterBridge<T>
{
  /**
   * Sort vals using Quicksort.  See the Sorter<T> interface
   * for additional details.
   */
  @Override
  public T[] sorti(T[] vals, Comparator<T> order)
  {
    qsort(vals, order, 0, vals.length);
    return vals;
  } // sorti(T[], Comparator<T>)

  /**
   * Sort the elements in positions [lb..ub) using Quicksort.
   */
  public void qsort(T[] vals, Comparator<T> order, int lb, int ub)
  {
    // One element arrays are sorted.
    if (lb >= ub - 1)
      {
        return;
      }
    else
      {
        T pivot = selectPivot(vals, order, lb, ub);
        int[] bounds = partition(pivot, vals, order, lb, ub);
        qsort(vals, order, lb, bounds[0]);
        qsort(vals, order, bounds[1], ub);
      } // More than one element
  } // qsort(T[], Comparator<T>, int, int)

  /**
   * Select the median of three randomly selected select
   */
  public T selectPivot(T[] vals, Comparator<T> order, int lb, int ub)
  {
    Random random = new Random();
    
    //Generate three random indexes
    int index1 = lb + random.nextInt(ub - lb);
    int index2 = lb + random.nextInt(ub - lb);
    int index3 = lb + random.nextInt(ub - lb);

    //If the median val
    if (order.compare(vals[index1], vals[index2]) > 0)
      {
        if (order.compare(vals[index2], vals[index3]) > 0)
          return vals[index2];
        else if (order.compare(vals[index1], vals[index3]) > 0)
          return vals[index3];
        else
          return vals[index1];
      }//if
    else
      {
        if (order.compare(vals[index1], vals[index3]) > 0)
          return vals[index1];
        else if (order.compare(vals[index2], vals[index3]) > 0)
          return vals[index3];
        else
          return vals[index2];
      }//else  
  }//selectPivot(T[], Comparator<T>, int, int)
  
  /**
   * Reorganize the elements in positions [lb..ub) of vals such that
   * elements smaller than the pivot appear to the left, elements
   * bigger than the pivot appear to the right of the pivot, and
   * copies of the pivot are in the middle.  Return a two-element
   * array that gives the lower bound (inclusive) and upper bound
   * (exclusive) of the section of the array that contains the equal
   * values.
   *
   * @param
   *    pivot, a value
   * @param
   *    values, an array.
   * @param
   *    order, a comparator.
   * @param
   *    lb, an integer.
   * @param
   *    ub, an integer.
   * @return
   *    mid, the index of the pivot.
   *
   * @pre
   *    order can be applied to any pair of elements in vals.
   * @pre
   *    0 <= lb < ub < values.length.
   * @post
   *    lb <= mid < ub
   * @post
   *    values[mid] == pivot, for some value pivot
   * @post
   *    For all i, lb <= i < mid, order.compare(values[i],pivot) <= 0
   *    For all i, mid < i < ub, order.compare(pivot, values[i]) < 0
   */
  int[] partition(T pivot, T[] vals, Comparator<T> order, int lb, int ub)
  {
    //Based off the solution Sam wrote for the notes on Exam1

    int small = lb;
    int equal = lb;
    int large = ub;

    while (equal < large)
      {
        int comp = order.compare(vals[equal], pivot);

        if (comp < 0)
          {
            //Swap the current val with the val at the end of
            //the equal boundary. Increment both boundaries
            swap(vals, small++, equal++);
          }//if current val < pivot

        else if (comp == 0)
          {
            //Just increment the equal boundary
            ++equal;
          }//if the current val == pivot

        else
          {
            //Swap the current val with the val at the end of
            //the large boundary. Decrement the large boundary
           swap(vals, equal, --large);
          }//if the current val > pivot
      }//while

    return new int[] { small, large };

  } // partition
  
  /**
   * Swap two elements in an array.
   *
   * @param values, the array
   * @param i, one of the indices
   * @param j, another index
   * @pre 0 <= i,j < values.length
   * @pre a = values[i]
   * @pre b = values[j]
   * @post values[i] = b
   * @post values[j] = a
   */
  public static <T> void swap(T[] values, int i, int j)
  {
    T tmp = values[i];
    values[i] = values[j];
    values[j] = tmp;
    //incrementCounter() ;
  } // swap(T[], int, int)
} // NewQuicksorter<T>
