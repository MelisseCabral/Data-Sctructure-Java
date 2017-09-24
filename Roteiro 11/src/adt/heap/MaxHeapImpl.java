package adt.heap;

public class MaxHeapImpl<T extends Comparable<T>> implements MaxHeap<T> {

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	private int index;
	private int size;
	private T[] array;

	public MaxHeapImpl() {
		array = (T[]) new Comparable[INITIAL_SIZE];
		index = 0;
		size = INITIAL_SIZE;
	}

	private int parent(int i) {
		int pai = (i - 1) / 2;
		return pai;
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return 2 * i + 2;
	}

	@Override
	public void buildHeap(T[] array) {
		this.array = (T[]) new Comparable[array.length];
		int indice = 0;

		for (int i = 0; i < array.length; i++) {

			this.array[i] = array[i];
			indice++;
		}
		for (int i = (int) (Math.floor(indice / 2) - 1); i >= 0; i--) {
			heapify(i);
		}
	}

	private void heapify(int position) {
		int left = left(position);
		int right = right(position);
		int max = position;

		if (left <= index && array[left].compareTo(array[position]) > 0)
			max = left;

		if (right <= index && array[right].compareTo(array[max]) > 0)
			max = right;

		if (max != position) {
			Util.swap(array, max, position);
			;
			heapify(max);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private void increaseHeap() {
		 
		@SuppressWarnings("unchecked")
		T[] arrayTemp = (T[]) new Comparable[array.length];
 
		for(int i = 0; i < arrayTemp.length; i++) {
 
			arrayTemp[i] = array[i];
		}
 
		size = array.length + INCREASING_FACTOR;
 
		array = (T[]) new Comparable[array.length + INCREASING_FACTOR];
 
		for(int i = 0; i < arrayTemp.length; i++) {
 
			array[i] = arrayTemp[i];
		}
 
	}
	
	@Override
	public void insert(T element) {
 
		if(element != null) {
 
			if(index == size) {
 
				increaseHeap();
			}
 
			int i = index;
			while(i > 0 && array[parent(i)].compareTo(element) < 0) {
 
				array[i] = array[parent(i)];
				i = parent(i);
			}
			array[i] = element;
			index++;
		}
	}

	@Override
	public T extractRootElement() {
 
		if (isEmpty()) {
 
			return null;
		}
 
		T temp = array[0];
 
		array[0] = array[index - 1];
		array[index-1] = null;
		heapify(0);
		index--;
 
		return temp;
	}

	@Override
	public T rootElement() {
		if (isEmpty())
			return null;
		return array[0];
	}

	@Override
	public T[] heapsort(T[] array) {

		T[] arrayTemp = this.array;
		
		this.array = array;
		index = 0;
		buildHeap(array);
		
		int i = index;
		
		for(int j = index-1; j > 0; j--){
		
			Util.swap(this.array, 0, j);
			index--;
			heapify(0);
		}
		
		index = i;
		T[] arrayRetorno = toArray();
		this.array = arrayTemp;
		
		return  arrayRetorno;
	}

	@Override
	public T[] toArray() {
		
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Comparable[index];
		for(int i = 0; i < result.length; i++){
	
			result[i] = this.array[i];
		}
		
		return result;
	}

}