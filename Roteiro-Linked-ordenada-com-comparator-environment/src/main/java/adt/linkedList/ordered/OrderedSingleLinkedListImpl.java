package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.SingleLinkedListNode;

/**
 * Para testar essa classe voce deve implementar seu comparador. 
 * Primeiro implemente todos os métodos requeridos. Depois implemente dois comparadores (com idéias opostas)
 * e teste sua classe com eles. Dependendo do comparador que você utilizar a lista funcionar como ascendente
 * ou descendente, mas a implemntação dos métodos é a mesma.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class OrderedSingleLinkedListImpl<T> implements
		OrderedSingleLinkedList<T> {

	private SingleLinkedListNode<T> head;
	private Comparator<T> comparator;
	
	public OrderedSingleLinkedListImpl() {
		// TODO Auto-generated constructor stub
		throw new RuntimeException("Default constructor is not working yet!");
	}
	
	public OrderedSingleLinkedListImpl(Comparator<T> comparator) {
		this.comparator = comparator;
	}


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public T search(T element) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public void insert(T element) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public T[] toArray() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public T minimum() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public T maximum() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
	
}
