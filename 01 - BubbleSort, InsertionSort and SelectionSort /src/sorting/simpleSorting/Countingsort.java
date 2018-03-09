package sorting.simpleSorting;

import java.util.Arrays;

import sorting.SortingImpl;

public class Countingsort extends SortingImpl<Integer> {
	/**
	 *  
	 criar lista zerada(C) com o tamanho do maior elemento da lista
	 criar outra lista(B) do tamanho da lista a ser ordenada
	 iterar sobre a lista original, incrementar um para ocorrência de um
	 número na posição dele em C
	 itero e somo as ocorrencia de C a medida que forem aparecendo de modo a
	 atualizar cada posição ( C[i] += C[i-1])
	 teremos um C um vetor de somas
	 percorro de trás para frente o vetor B
	 O que era posicao em C vira elemento em B e o que era elemento em C vira
	 posicao em B e decremento um do respectivo elemento em C
	
	 ex:
	 Array = [7,2,3,5,4,1]
	 A = [0,0,0,0,0,0,0] - lista zerada do tamanho do elemento máximo
	 B = [0,0,0,0,0,0] - lista com o tamanho da original
	
	 A = [1,1,1,1,1,0,1]
	 B = [0,0,0,0,0,0]
	
	 A = [1,2,3,4,5,5,6]
	 B = [0,0,0,0,0,0]
	
	 A = [1,2,3,4,5,5,5]
	 B = [0,0,0,0,0,7]
	
	 A = [1,2,3,4,4,5,5]
	 B = [0,0,0,0,5,7] ...
	 	
	*/
	
	@Override
	protected void sort(Integer[] array,int leftIndex, int rightIndex) {
		
		if (array == null) {
			throw new RuntimeException("Array não pode ser null.");
		}

		if (leftIndex < 0 || rightIndex >= array.length) {
			throw new RuntimeException(
					"Left index deve ser maior ou igual a zero "
							+ "e rightIndex deve ser menor que " + array.length);
		}

		Integer B[] = new Integer[rightIndex - leftIndex + 1];
		Integer C[] = new Integer[101];

		/** 
		 * Zera o array C(das ocorrencias) jah que o tipo eh Integer
		 *  
		 *  */
		for (int i = 0; i <= 100; i++) {
			C[i] = 0;
		}

		/** 
		 * Contabiliza ocorrencia de cada elemento do array 
		 *  
		 *  */
		for (int j = leftIndex; j <= rightIndex; j++) {
			C[array[j]] ++;
		}

		/**
		 * Realiza a soma acumulada no array
		 * 
		 * Cada elemento do array eh a soma de todos elementos ate a posicao anterior
		 * 
		 * exemplo : array = [1,2,3,4,5] --> [1,3,6,10,15]
		 * 
		 * */
		for (int j = 1; j <= 100; j++) {
			C[j] += C[j - 1];
		}

		/**
		 * Percorrer de trás para frente
		 * Posicao de C vira elemento em B e elemento de C vira posicao em B 
		 * Decremento um do respectivo elemento em C
		 * 
		 * */
		for (int i = rightIndex; i >= leftIndex; i--) {
			B[C[array[i]] - 1] = array[i];
			C[array[i]] --;
		}

		/**
		 * Copio os elementos de B para o array original
		 * 
		 *  */
		int x = 0;
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = B[x];
			x++;
		}
			
		}
	

	public static void main(String[] args) {
		Countingsort cs = new Countingsort();
		Integer v[] = {7,2,3,8,9,9,5,4,1};
		Integer[] p = {10, 12, 11, 33, 90, 51, 81, 24, 7, 56};
		Integer[] equalArray = {10, 10, 10};
		Integer[] sampleArray = {10,2,33,70};
		Integer[] unitArray = {10};
		Integer[] emptyArray = {};
		cs.sort(v);
		cs.sort(p);
		cs.sort(equalArray);
		cs.sort(sampleArray);
		cs.sort(unitArray);
		cs.sort(emptyArray);
		System.out.println(Arrays.toString(v));
		System.out.println(Arrays.toString(p));
		System.out.println(Arrays.toString(equalArray));
		System.out.println(Arrays.toString(sampleArray));
		System.out.println(Arrays.toString(unitArray));
		System.out.println(Arrays.toString(emptyArray));
		
	}
}
