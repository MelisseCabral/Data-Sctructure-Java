package sorting.simpleSorting;

import java.util.Arrays;

import sorting.SortingImpl;

public class Radixsort<T extends Comparable<T>> extends SortingImpl<T> {
	 
	
	protected void sort(T[] array, int leftIndex, int rightIndex) {
		for ( int i = 9; i >= 0; i-- ) {
			countingsort(array, leftIndex, rightIndex, i);
		}
 
	}
 	/**
 	 * Metodo auxiliar stable para ordenar cada digito do array recebido como parametro
 	 * 
 	 * @param 
 	 * 		T[] array vetor generico
 	 * 
 	 * @param 
 	 * 		int leftIndex
 	 * 
 	 * @param 
 	 * 		int rightIndex
 	 * 
 	 * @param 
 	 * 		int indice
 	 * 
 	 *  */
	private void countingsort(T[] array, int leftIndex, int rightIndex, int indice ) {
		
		if ( array.length == 0 ) {
			return;
		}
		
		String numero;
		Integer[] ocorrencia = new Integer[10];
		T[] sorted = (T[]) new Comparable[(rightIndex + 1) - leftIndex];
 
		/**
		 *  Preencher o vetor ocorrencia com zero 
		 * 
		 * */
		for ( int i = 0; i < 10; i++ ) {
			ocorrencia[i] = 0;
		}
		
		/**
		 *  Contar ocorrencia de cada elemento 
		 * 
		 * */
		for ( int j = leftIndex; j <= rightIndex ; j++ ) {
			numero = array[j].toString();
			ocorrencia[ numero.charAt(indice) - '0' ]++;
		}
 
		/**
		 *  Efetua a soma no array ocorrencia : cada termo, exceto o primeiro, resulta da soma anterior 
		 * 
		 * */
		for ( int k = 1; k < 10; k++ ) {
			ocorrencia[k] += ocorrencia[k - 1];
		}
 
		/**
		 * O que era posicao no array ocorrencia vira elemento no array sorted e o que era elemento vira posicao
		 * 
		 */
		for ( int l = rightIndex; l >= leftIndex; l-- ) {
			numero = array[l].toString();
			int valor_real = (numero.charAt(indice) - 48);
			sorted[(ocorrencia[valor_real]) - 1] = array[l];
			ocorrencia[valor_real]--;
		}
 
		/**
		 * Modifica o array original levando em conta o limite inferior(leftIndex) e o superior(rightIndex)
		 * como restritores de indices
		 * 
		 */
		int k = 0;
		for ( int i = leftIndex; i <= rightIndex; i++ ) {
			array[i] = (T) sorted[k];
			k++;
		}
	}
 
	public static void main(String[] args) {
		Radixsort<String> r = new Radixsort<String>();
		String[] i = {"0000000005", "0000000003", "0000000004", "0000000001" ,"0000000002"};
		String[] u = {"0000000005"};
		String[] g = {"0000000005", "0000000005", "0000000005", "0000000005" ,"0000000005"};
		r.sort(i);
		r.sort(g);
		r.sort(u);
		System.out.println(Arrays.toString(i));
		System.out.println(Arrays.toString(g));
		System.out.println(Arrays.toString(u));
	}
	
}