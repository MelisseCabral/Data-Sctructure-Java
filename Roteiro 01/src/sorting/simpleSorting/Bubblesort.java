package sorting.simpleSorting;

import java.util.Arrays;

import sorting.SortingImpl;
import sorting.Util;

/**
 * The bubble sort algorithm pushes big elements to the right or small elements to 
 * the left by exchanging adjacent elements. The algorithm must sort the elements from 
 * leftIndex to rightIndex (inclusive). 
 */
public class Bubblesort<T extends Comparable<T>> extends SortingImpl<T> {

	@Override
	protected void sort(T[] array, int leftIndex, int rightIndex) {
		for (int i = 0; i <= rightIndex - leftIndex; i++) {
			for (int j = leftIndex; j <= rightIndex-i-1; j++) {
				if(array[j].compareTo(array[j + 1]) > 0 ) {
					Util.swap(array, j+1, j);
				}
			}
		}
	}
	
	
}
