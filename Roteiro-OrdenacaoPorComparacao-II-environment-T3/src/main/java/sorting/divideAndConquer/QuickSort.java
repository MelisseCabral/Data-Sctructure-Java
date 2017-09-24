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

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
            int posicaoPivo = this.separar(array, leftIndex, rightIndex);
            this.sort(array, leftIndex, posicaoPivo - 1);
            this.sort(array, posicaoPivo + 1, rightIndex);
        }
	}
	private int separar(T[] array, int inicio, int fim) {
        T pivo = array[inicio];
        int i = inicio + 1;
        int f = fim;
        while (i <= f) {
            if (array[i].compareTo(pivo) <= 0) {
                ++i;
                continue;
            }
            if (pivo.compareTo(array[f]) < 0) {
                --f;
                continue;
            }
            Util.swap((Object[])array, (int)i, (int)f);
        }
        Util.swap((Object[])array, (int)inicio, (int)f);
        return f;
    }
}
