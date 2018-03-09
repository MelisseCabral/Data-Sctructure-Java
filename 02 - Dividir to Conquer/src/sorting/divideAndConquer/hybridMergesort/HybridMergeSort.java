package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do MergeSort 
 * que pode fazer uso do InsertionSort (um algoritmo híbrido) da seguinte forma: 
 * o MergeSort é aplicado a entradas maiores a um determinado limite. Caso a entrada 
 * tenha tamanho menor ou igual ao limite o algoritmo usa o InsertionSort. 
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de 
 *   forma que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada
 *   chamada interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e 
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 *  - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends AbstractSorting<T> {
    
	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
public static final int SIZE_LIMIT = 4;
	
	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;
	
	private void merge(int leftIndex, int rightIndex, T[] array){
		
		int i , j;
		T chave;
		
		for(i = (leftIndex + rightIndex) / 2 + 1; i <= rightIndex; i++){
			chave = array[i];
			j = i-1;
			while(j >= leftIndex && chave.compareTo(array[j]) < 0){
				array[j+1] = array[j];
				j--;
			}
			
			array[j+1] = chave;
		}
	}
	private void mergesort(T[] array, int leftIndex, int rightIndex){
		chooseSortType(array, leftIndex, (leftIndex + rightIndex) / 2);
		chooseSortType(array, (leftIndex  + rightIndex) / 2 + 1, rightIndex);
		
		merge(leftIndex, rightIndex, array);
	}
	
	private void insertionsort(T[] array, int leftIndex, int rightIndex){
		
		int i, j;
		T chave;
		
		for(i = leftIndex+1; i <= rightIndex; i++){
			chave = array[i];
			j = i-1;
			while(j >= leftIndex && chave.compareTo(array[j]) < 0){
				array[j+1] = array[j];
				j--;
			}
			
			array[j+1] = chave;
		}
	}
	private void chooseSortType(T[] array,int leftIndex, int rightIndex){
		
		if(rightIndex - leftIndex + 1 <= SIZE_LIMIT){
			INSERTIONSORT_APPLICATIONS++;
			insertionsort(array, leftIndex, rightIndex);
		}
		else{
			MERGESORT_APPLICATIONS++;
			mergesort(array, leftIndex, rightIndex);
		}
	}
	
	
	public void sort(T[] array, int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		
		if(leftIndex > rightIndex || leftIndex < 0 || rightIndex >= array.length){
			return;
		}
		
		chooseSortType(array, leftIndex, rightIndex);
	}
}
