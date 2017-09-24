/**
 * 
 */
package sorting.simpleSorting;
import sorting.SortingImpl;
import sorting.Util;

public class Quicksort<T extends Comparable<T>> extends SortingImpl<T> {

	
	@Override
	protected void sort(T[] array,int leftIndex, int rightIndex) {
	    if (leftIndex < rightIndex){
	         int posicaoPivo = separar(array, leftIndex, rightIndex);
	         sort(array, leftIndex, posicaoPivo - 1);
	         sort(array, posicaoPivo + 1, rightIndex);
	      }
	}
	
	private int separar(T[] array, int inicio, int fim)
	   {
	      T pivo = array[inicio];
	      int i = inicio + 1, f = fim;
	      while (i <= f)
	      {
	         if (array[i].compareTo(pivo) <= 0)
	            i++;
	         else if (pivo.compareTo(array[f]) < 0)
	            f--;
	         else
	         {
	            Util.swap(array, i, f);
	         }
	      }
	      Util.swap(array, inicio, f);
	      return f;
	   }
	

}
