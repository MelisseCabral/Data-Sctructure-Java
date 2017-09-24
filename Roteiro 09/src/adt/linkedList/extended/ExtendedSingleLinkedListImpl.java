package adt.linkedList.extended;

import java.util.Comparator;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

/**
 * The ExtendedSingleList class is a linked list that works with elements that can be compared.
 * To achieve this, the class uses a comparator. You must think about what methods of the super 
 * class must be rewritten to use the comparator. Furthermore, you must implement the new methods 
 * of this list according to their comments.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class ExtendedSingleLinkedListImpl<T> extends SingleLinkedListImpl<T> {
	
	private Comparator<T> comparator;
	private SingleLinkedListNode<T> auxNode, tempNode;
	
	public ExtendedSingleLinkedListImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
		auxNode = head;
	}

	/**
	 * It returns the least (minimum) element of the list or null if the list is empty.
	 * @return
	 */
	public T minimum(){
		T temp =  head.getData();
		if(!isEmpty()){
					
			while(!auxNode.isNIL()){
				auxNode = auxNode.getNext();
				if (comparator.compare(auxNode.getData(),temp) < 0){
					temp = auxNode.getData();
				}
			}
		}
		return temp;
	} 
	
	
	/**
	 * It puts all elements of otherList in this list. The elements are put in the same order 
	 * they appear in otherList. Try to make this methods as fast as possible, that is, with O(1). 
	 * @param otherList
	 */
	public void concatenate(ExtendedSingleLinkedListImpl<T> otherList){
		tempNode = otherList.getHead();
		while (!tempNode.isNIL()) {
			insert(tempNode.getData());
			tempNode = tempNode.getNext();
		}
	}

	/**
	 * As linked lists accept repeated elements, this methods remove all occurrences 
	 * of a same element in this list.
	 * @param element
	 */
	public void removeAll(T element){

		if(!isEmpty()){
			while(!auxNode.isNIL()){
				auxNode = auxNode.getNext();
				if (comparator.compare(auxNode.getData(),element) == 0)
					super.remove(element);
			}
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
