package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	
	protected DoubleLinkedList<T> list;
	protected int size;
	
	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
	}
	
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		list.insert(element);

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T[] temp = list.toArray();
		T result = temp[0];
		list.removeFirst();
		return result;
		
	}

	@Override
	public T head() {
		DoubleLinkedListImpl<T> list2 = (DoubleLinkedListImpl<T>) list;
		return list2.getHead().getData();
	
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return size == list.size();
	}

}
