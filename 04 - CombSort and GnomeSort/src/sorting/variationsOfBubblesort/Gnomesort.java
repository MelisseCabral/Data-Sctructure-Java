package sorting.variationsOfBubblesort;

import sorting.SortingImpl;
import sorting.Util;

/**
 * The implementation of the algorithm must be in-place! 
 */
public class Gnomesort<T extends Comparable<T>> extends SortingImpl<T>{
	protected void sort(T[] array,int leftIndex, int rightIndex){
		if(leftIndex >= rightIndex || leftIndex < 0 || rightIndex >= array.length){
			return;
		}
		
		int pivot = leftIndex + 1;
		
		while(pivot <= rightIndex){
			if(array[pivot].compareTo(array[pivot-1]) >= 0)
				pivot++;
			else{
				Util.swap(array, pivot, pivot - 1);
				if(pivot > leftIndex + 1)
					pivot--;
				else
					pivot++;
			}
		}
	}
}
