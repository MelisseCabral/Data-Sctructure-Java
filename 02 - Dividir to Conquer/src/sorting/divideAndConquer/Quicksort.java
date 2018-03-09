package sorting.divideAndConquer;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm.
 * The algorithm chooses a pivot element and rearranges the elements of the
 * interval in such a way that all elements lesser than the pivot go to the
 * left part of the array and all elements greater than the pivot, go to the
 * right part of the array. Then it recursively sorts the left and the right parts.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {
	
private int partition(T[] array, int leftIndex, int rightIndex){
		
		int i = leftIndex + 1, j = rightIndex;
		T pivot = array[leftIndex];
		
		while(i <= j){
			while(i <= j && array[i].compareTo(pivot) <= 0)
				i++;
			
			while(i <= j && array[j].compareTo(pivot) > 0)
				j--;
			
			if(i<j)
				Util.swap(array, i, j);
		}
		
		Util.swap(array, leftIndex, j);
		return j;
	}
	
	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if(leftIndex >= rightIndex || leftIndex < 0 || rightIndex >= array.length){
			return;
		}
		
		int pivotIndex = partition(array, leftIndex, rightIndex);
		
		sort(array, leftIndex, pivotIndex - 1);
		sort(array, pivotIndex + 1, rightIndex);
	}
}
