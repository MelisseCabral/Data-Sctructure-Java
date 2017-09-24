package sorting.variationsOfBubblesort;

import sorting.SortingImpl;
import sorting.Util;

/**
 * The combsort algoritm. 
 */
public class Combsort<T extends Comparable<T>> extends SortingImpl<T> {
	@Override
	protected void sort(T[] array,int leftIndex, int rightIndex) {
		if(leftIndex >= rightIndex || leftIndex < 0 || rightIndex >= array.length){
			return;
		}
		
		int gap = rightIndex - leftIndex + 1;
		boolean sorted = false;
		
		while(gap > 1 || !sorted){
			gap /= 1.25;
			sorted = true;
			
			if(gap == 0)
				gap = 1;
			
			for(int i = leftIndex; i + gap <= rightIndex; i++){
				if(array[i].compareTo(array[i + gap]) > 0){
					Util.swap(array, i, i + gap);
					sorted = false;
				}
			}
		}
	}
}
