package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	private DoubleLinkedListNode<T> last , NIL;
	
	public DoubleLinkedListImpl() {
		head = new DoubleLinkedListNode<T>();
		last = new DoubleLinkedListNode<T>();
	}
	
	@Override
	public void insertFirst(T element) {
		NIL = new DoubleLinkedListNode<T>(); 
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element,(DoubleLinkedListNode<T>) head, NIL);
		
		if (head.isNIL()) {
			last = newHead;
		}
		head = newHead;
	}

	@Override
	public void removeFirst() {
		NIL = new DoubleLinkedListNode<T>();
		if (!head.isNIL()) {
			((DoubleLinkedListNode<T>) head.next).previous = NIL;
			head = (DoubleLinkedListNode<T>) head.next;
		}
	}

	@Override
	public void removeLast() {
		if (!last.isNIL()) {
			last = (DoubleLinkedListNode<T>) last.previous;
			if (head.isNIL()) {
				head = last;
			}

			last.next = new DoubleLinkedListNode<T>();
		}
	}

}
