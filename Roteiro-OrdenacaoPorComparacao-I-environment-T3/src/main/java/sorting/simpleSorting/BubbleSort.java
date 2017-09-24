package sorting.simpleSorting;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

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
