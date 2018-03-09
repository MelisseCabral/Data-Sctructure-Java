package sorting.divideAndConquer.quicksort3;


import sorting.SortingImpl;
import sorting.Util;

/**
 * The interface describing the algorithm of quicksort median of 3. 
 * The implementation of the algorithm must be in-place!
 */
public class QuicksortMedianOfThree<T extends Comparable<T>> extends SortingImpl<T>{
	
	public void sort(T[] array, int leftIndex, int rightIndex){
		if (leftIndex < rightIndex && leftIndex >= 0){
			medianaDeTres(array, leftIndex, rightIndex);
			Util.swap(array, (leftIndex + rightIndex) / 2, rightIndex - 1);
			int pivotPosition = partition(array, leftIndex + 1, rightIndex - 1);
			sort(array, leftIndex, pivotPosition - 1);
			sort(array, pivotPosition + 1, rightIndex);

		}
	}
	
	private void medianaDeTres(T[] array, int left, int right){
		int middle = (right + left) / 2;
		int menor_indice = left;
		
		if (array[right].compareTo(array[menor_indice]) < 0)
			menor_indice = right;
		if (array[middle].compareTo(array[menor_indice]) < 0)
			menor_indice = middle;
		
		Util.swap(array, left, menor_indice);
		
		if(array[middle].compareTo(array[right]) > 0)
			Util.swap(array, middle, right);
	}
		
	
	
	private int partition(T[] array, int left, int right){
		int pivotPosition = right;
		T pivo = array[right];
		
		for (int j = right - 1; j >= left; j--) {
			if (array[j].compareTo(pivo) > 0){
				pivotPosition--;
				Util.swap(array, pivotPosition, j);
			}
		}
		Util.swap(array, right, pivotPosition);
		
		
		return pivotPosition;
	}
	
	
	
	
	
}
