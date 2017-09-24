package orderStatistic;

import sorting.Util;

/**
 * O quickselect eh um algoritmo baseado no quicksort para descobrir/selectionar, 
 * em tempo linear, a k-esima estatistica de ordem (k-esimo menor elemento) de um 
 * conjunto de dados.
 * 
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array inicial
 * em dois subarrays da mesma forma que o quicksort (elementos menores que o pivot
 * a esquerda do pivot e elementos maiores que o pivot a direita dele). Entretanto, 
 * ao inves de chamar o quicksort recursivo nas duas metades, o quickselect 
 * eh executado (recursivamente) apenas na metade que contem o elemento que ele 
 * procura (o k-esimo menor elemento). Isso reduz a complexidade de O(n.log n)
 * para O(n)
 * 
 * @author Adalberto
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calclar o k-esimo 
	 * menor elemento (k-esima estatistica de ordem) de um determinado array de dados 
	 * comparaveis. Primeiro ele escolhe um elemento como o pivot e particiona os daods
	 * em duas partes baseado no pivot (exatemente da mesma forma que o quicksort).
	 * Depois disso, ele chama recursivamente o mesmo algoritmo em apenas uma das metades
	 * (a que contem o k-esimo menor elemento). Isso redua a completixade de O(n.log n) para
	 * O(n). 
	 * 
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja fora do 
	 * tamanho do array, o metodo deve retornar null.
	 * 
	 * 
	 * @param array o array de dados a procurar o k-esimo menor elemento.
	 * @param k a ordem do elemento desejado. 1 significa primeiro menor elemento, 
	 * 2 significa segundo menor elemento e assim por diante
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T quickSelect(T[] array, int k){
		
		T pivot;
		if(array.length/2 != k)
			pivot = array[array.length/2];
		else 
			pivot = array[array.length/2 + 1];
		
		T[] array1 = (T[]) new Object[array.length/2];
		T[] array2 = (T[]) new Object[array.length/2];
		
		if(array.length < k || array[k] == null) 
		{
			 return null;
		}

		for(int i = 0; i < array.length / 2; i++)
		{
			if((int) array[i] > (int) pivot)
			{
				Util.swap(array,(int) array[i],(int) pivot);
				array2[i] = array[i];
			} 
		}
		
		for(int j = array.length / 2; j < array.length; j++)
		{
			if((int) array[j] < (int) pivot)
			{
				Util.swap(array,(int) array[j],(int) pivot);
				array1[j] = array[j];
			} 
		}
		if((int) array1.length < k)
		{
			quickSelect(array1, k);
			return array1[k];
		} 
		else
		{
			quickSelect(array2, k);
			return array2[k];
		}	
	}
}
