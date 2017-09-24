package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;
	
	@Override
	public boolean isEmpty() {
		return data == null;
	}

	@Override
	public int size() {
		return (data == null) ? 0 : 1 + next.size();
	}

	@Override
	public T search(T element) {
		T result = null;
		if(!isEmpty()){
			if(data.equals(element)) {
				result = data;
			}
			else {
				result = next.search(element);
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if(data != null) {
			next.insert(element);
		}
		else {
			data = element;
			next = new RecursiveSingleLinkedListImpl<T>();
		}
	}

	@Override
	public void remove(T element) {
		if(data != null) {
			if(data.equals(element)) {
				setData(next.getData());
				setNext(next.getNext());
			}
			else if(next.getData() != null) {
				next.remove(element);
			}
		}
	}
	
	private T[] toArray(int index, T[] array) {
		if(data != null) {
			array[index] = data;
			next.toArray(index + 1, array);
		}
		return array;
	}
	
	@Override
	public T[] toArray(){
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size()];
		return toArray(0, array);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
