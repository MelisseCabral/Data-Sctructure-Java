package sorting.divideAndConquer;

import java.lang.reflect.Array;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm.  
 * The algorithm consists of recursively dividing the unsorted list in the middle,
 * sorting each sublist, and then merging them into one single sorted list.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	
private void merge(int leftIndex, int rightIndex, T[] array){
		
		@SuppressWarnings("unchecked")
		T[] aux = (T[])(Array.newInstance(array[0].getClass(), rightIndex - leftIndex + 1));
		int i = leftIndex;
		int j = (leftIndex + rightIndex) / 2 + 1;
		int auxIndex = 0;
		
		while(i <= (leftIndex + rightIndex) / 2 || j <= rightIndex){
			if(j > rightIndex || (i <= (leftIndex + rightIndex) / 2 && array[i].compareTo(array[j]) < 0)){
				aux[auxIndex++] = array[i];
				i++;
			}
			
			else{
				aux[auxIndex++] = array[j];
				j++;
			}
			
		}
		
		for(i = 0; i < aux.length; i++){
			array[leftIndex + i] = aux[i];
		}
	}
	
	
	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if(leftIndex >= rightIndex || leftIndex < 0 || rightIndex >= array.length){
			return;
		}
		
		sort(array, leftIndex, (leftIndex + rightIndex) / 2);
		sort(array, (leftIndex  + rightIndex) / 2 + 1, rightIndex);
		
		merge(leftIndex, rightIndex, array);
	}
}
