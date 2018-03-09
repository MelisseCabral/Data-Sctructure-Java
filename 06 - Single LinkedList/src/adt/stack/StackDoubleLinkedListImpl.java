package adt.stack;

import adt.linkedList.DoubleLinkedList;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {
	
	protected DoubleLinkedList<T> list;
	protected int size;
	
	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
	}
	
	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()){
            throw new StackOverflowException();
		}
		if (element == null){
			return;
		}
		list.insertFirst(element);

	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
		T[] array = list.toArray();
		T remover = array[list.size() - 1];
		list.removeLast();
		return remover;
	}

	@Override
	public T top() {
		T elemento = null;
		if (!(list.isEmpty())) {
			T[] array = list.toArray();
			elemento = array[array.length - 1];
		}
		return elemento;
	}


	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return list.size() == size;
	}

}
