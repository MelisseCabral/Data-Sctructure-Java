package sorting.simpleSorting;

import sorting.SortingImpl;

/**
 * The insertion sort algorithm consumes the array (element by element) and inserts the elements 
 * into an ordered list. The algorithm must sort the elements from 
 * leftIndex to rightIndex (inclusive). 
 */
public class Insertionsort<T extends Comparable<T>> extends SortingImpl<T> {

	@Override
	protected void sort(T[] array, int leftIndex, int rightIndex) {
		for (int j = leftIndex + 1 ; j < rightIndex+1; j++) {
			T key = array[j];
			int i = j -1 ;
			while ((i >= leftIndex) && (array[i].compareTo(key)) > 0){
				array[i+1] = array[i];
				i--;
			}
			array[i+1] = key;
		}		
	}

}
