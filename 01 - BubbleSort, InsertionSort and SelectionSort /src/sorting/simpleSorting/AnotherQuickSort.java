package sorting.simpleSorting;

/**
 * Quicksort algorithm.
 * @param a an array of Comparable items.
 */
public class AnotherQuickSort {
	  private long[] data;

	  private int len;

	  public AnotherQuickSort(int max) {
	    data = new long[max];
	    len = 0;
	  }

	  public void insert(long value) {
	    data[len] = value; // insert and increment size
	    len++;
	  }

	  public void display() {
	    System.out.print("Data:");
	    for (int j = 0; j < len; j++)
	      System.out.print(data[j] + " ");
	    System.out.println("");
	  }

	  public void quickSort() {
	    recQuickSort(0, len - 1);
	  }

	  public void recQuickSort(int left, int right) {
	    int size = right - left + 1;
	    if (size <= 3) // manual sort if small
	      manualSort(left, right);
	    else // quicksort if large
	    {
	      long median = medianOf3(left, right);
	      int partition = partitionIt(left, right, median);
	      recQuickSort(left, partition - 1);
	      recQuickSort(partition + 1, right);
	    }
	  }

	  public long medianOf3(int left, int right) {
	    int center = (left + right) / 2;
	    // order left & center
	    if (data[left] > data[center])
	      swap(left, center);
	    // order left & right
	    if (data[left] > data[right])
	      swap(left, right);
	    // order center & right
	    if (data[center] > data[right])
	      swap(center, right);

	    swap(center, right - 1); // put pivot on right
	    return data[right - 1]; // return median value
	  }

	  public void swap(int dex1, int dex2) {
	    long temp = data[dex1];
	    data[dex1] = data[dex2];
	    data[dex2] = temp;
	  }

	  public int partitionIt(int left, int right, long pivot) {
	    int leftPtr = left; // right of first elem
	    int rightPtr = right - 1; // left of pivot

	    while (true) {
	      //       find bigger
	      while (data[++leftPtr] < pivot)
	        ;
	      //       find smaller
	      while (data[--rightPtr] > pivot)

	        ;
	      if (leftPtr >= rightPtr) // if pointers cross, partition done
	        break;    
	      else
	        // not crossed, so
	        swap(leftPtr, rightPtr); // swap elements
	    }
	    swap(leftPtr, right - 1); // restore pivot
	    return leftPtr; // return pivot location
	  }

	  public void manualSort(int left, int right) {
	    int size = right - left + 1;
	    if (size <= 1)
	      return; // no sort necessary
	    if (size == 2) { // 2-sort left and right
	      if (data[left] > data[right])
	        swap(left, right);
	      return;
	    } else // size is 3
	    { // 3-sort left, center, & right
	      if (data[left] > data[right - 1])
	        swap(left, right - 1); // left, center
	      if (data[left] > data[right])
	        swap(left, right); // left, right
	      if (data[right - 1] > data[right])
	        swap(right - 1, right); // center, right
	    }
	  }

	  
	}
