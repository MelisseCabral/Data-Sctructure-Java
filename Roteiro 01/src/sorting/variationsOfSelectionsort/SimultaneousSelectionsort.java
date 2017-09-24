package sorting.variationsOfSelectionsort;

import sorting.SortingImpl;
import sorting.Util;

/**
 * This algorithm applies two selectionsorts simultaneously. In a same iteration, one selectionsort puts
 * the greatest element into the right position and another selectionsort puts the smallest element into 
 * the left position. At the end of the first iteration, the smalles element is in the first position 
 * (index 0) and the greatest element is the last position (index N-1). The next iteration does the same 
 * from index 1 to index N-2. And so on. The execution continues until the array is completely 
 * ordered. 
 */
public class SimultaneousSelectionsort<T extends Comparable<T>> extends SortingImpl<T>{

	@Override
	protected void sort(T[] array,int leftIndex, int rightIndex) {
		

	}
}
