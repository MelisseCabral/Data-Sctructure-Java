package sorting.variationsOfSelectionsort;

import sorting.SortingImpl;
import sorting.Util;

/**
 * This selectionsort variation pushes the greatest element to the right (walking from left to right). 
 * After that, it pushes the smallest element to the left (walking from right to left). This happens 
 * in a same iteration; that is, in a same (external iteration) an internal iteration pushes the 
 * greatest element and another internal iteration brings the smallest element.
 * At the end of the first (external) iteration the greatest element is at the rightmost position,
 * whereas the smallest element is at the leftmost position. Then, the next iteration 
 * excludes that positions and continues the ordering procedure using the same idea. 
 * The algorithm must sort the elements from leftIndex to rightIndex (inclusive).  
 */

public class BidirectionalSelectionsort<T extends Comparable<T>> extends SortingImpl<T>{

	@Override
	protected void sort(T[] array,int leftIndex, int rightIndex) {
		while (leftIndex <= rightIndex){
			int min = leftIndex;
			int max = rightIndex;
			
			for (int i = leftIndex; i <= rightIndex ; i++) {
				if(array[i].compareTo(array[min]) < 0)
					min = i;
			}
			Util.swap(array, leftIndex++, min);
			
			for (int j = rightIndex ; j >= leftIndex ; j--) {
				if(array[j].compareTo(array[max]) > 0)
					max = j;
			}
			Util.swap(array, rightIndex--, max);
		} 
		
	}

}
