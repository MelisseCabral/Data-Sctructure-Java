package sorting.linearSorting;

import sorting.SortingImpl;

/**
 * 
 * @author Rémy De Fru
 * Matrícula: 112110094
 *
 */
public class Countingsort extends SortingImpl<Integer> {

	final int VALOR_MAX = 100;
	
	@Override
	protected void sort(Integer[] array,int leftIndex, int rightIndex) {
		if(leftIndex >= rightIndex || leftIndex < 0 || rightIndex >= array.length){
			return;
		}
		
		for(int i = leftIndex; i <= rightIndex; i++){
			if(array[i].intValue() > 100)
				return;
		}

		int[] counting = new int[VALOR_MAX];
		Integer[] arraySorted = new Integer[rightIndex - leftIndex + 1];

		for(int i = leftIndex; i <= rightIndex; i++){
			counting[array[i] - 1]++;
		}

		int soma = 0;
		for(int i = 0; i < VALOR_MAX; i++){
			soma += counting[i];
			counting[i] = soma;
		}
		
		for(int i = rightIndex; i >= leftIndex; i--){
			counting[array[i] - 1]--;
			arraySorted[counting[array[i] - 1]] = array[i];
		}
		
		for(int i = 0; i < rightIndex - leftIndex + 1; i++){
			array[leftIndex + i] = arraySorted[i];
		}
	}

}
