package sorting.simpleSorting;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T>{

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int i = 0;
        while (i <= rightIndex - leftIndex) {
            int j = leftIndex;
            while (j <= rightIndex - i - 1) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    Util.swap((Object[])array, (int)(j + 1), (int)j);
                }
                ++j;
            }
            ++i;
        }
   }
}

