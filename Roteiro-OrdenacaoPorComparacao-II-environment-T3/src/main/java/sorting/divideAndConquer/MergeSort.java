package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm.  
 * The algorithm consists of recursively dividing the unsorted list in the middle,
 * sorting each sublist, and then merging them into one single sorted list.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if (rightIndex <= leftIndex) {
            return;
        }
        int meio = (leftIndex + rightIndex) / 2;
        this.sort(array, leftIndex, meio);
        this.sort(array, meio + 1, rightIndex);
        this.merge(array, leftIndex, meio, rightIndex);
	}
	
	 private void merge(T[] array, int leftIndex, int mid, int rightIndex) {
	        Comparable[] helper = new Comparable[array.length];
	        int i = leftIndex;
	        int j = mid + 1;
	        int k = leftIndex;
	        int k2 = 0;
	        while (k2 < helper.length) {
	            helper[k2] = array[k2];
	            ++k2;
	        }
	        while (i <= mid && j <= rightIndex) {
	            if (helper[i].compareTo(helper[j]) > 0) {
	                array[k] = (T) helper[j];
	                ++j;
	            } else {
	                array[k] = (T) helper[i];
	                ++i;
	            }
	            ++k;
	        }
	        while (i <= mid) {
	            array[k] = (T) helper[i];
	            ++k;
	            ++i;
	        }
	        while (j <= rightIndex) {
	            array[k] = (T) helper[j];
	            ++k;
	            ++j;
	        }
	    }
}
