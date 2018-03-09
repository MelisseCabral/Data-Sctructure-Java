package sorting.simpleSorting;

import sorting.SortingImpl;

public class Mergesort<T extends Comparable<T>> extends SortingImpl<T> {

	@Override
	protected void sort(T[] array,int leftIndex, int rightIndex) {
		if(rightIndex <= leftIndex)
			return;
		int meio = (leftIndex + rightIndex)/2;
		sort(array,leftIndex,meio);
		sort(array,meio+1,rightIndex);
		merge(array, leftIndex,meio,rightIndex);
	}
	
	private void merge(T[] array, int leftIndex, int mid, int rightIndex) {
		T[] helper = (T[]) new Comparable[array.length];
		int i = leftIndex, j = mid + 1, k = leftIndex;
		
		for (int k2 = 0; k2 < helper.length; k2++) {
			helper[k2] = array[k2];
		}// for
		
		while (i <= mid && j <= rightIndex) {
			if(helper[i].compareTo(helper[j]) > 0) {
				array[k] = helper[j];
				j++;
			} else {
				array[k] = helper[i];
				i++;
			}// if-else
			k++;
		}// while
		
		while(i <= mid) {
			array[k] = helper[i];
			k++;
			i++;
		}// while
		
		while(j <= rightIndex) {
			array[k] = helper[j];
			k++;
			j++;
		}// while
		
	}// merge
	
}
