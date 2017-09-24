package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;
	
	
	public SingleLinkedListImpl(){
		head = new SingleLinkedListNode<T>();
	}
	
	
	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		
		SingleLinkedListNode<T> auxNode = head;
		
		while(!auxNode.isNIL()){
			size++;
			auxNode = auxNode.next;
			
		}
		
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> auxNode = head;
		
		while(!auxNode.isNIL() && !auxNode.data.equals(element)){
			auxNode = auxNode.next;
		}
		
		
			return auxNode.data;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> auxHead = head;

		if(head.isNIL()){
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode(element, new SingleLinkedListNode<T>());
			head = newHead;
		}
		else{
			while(!auxHead.next.isNIL()){
				auxHead = auxHead.next;
			}
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode(element, new SingleLinkedListNode<T>());
			newNode.next = auxHead.next;
			auxHead.next = newNode;
		}

	}

	@Override
	public void remove(T element) {
		if (!(head.isNIL())) {
 
			if (head.getData().equals(element)) {
				head = head.next;
			} else {
				SingleLinkedListNode<T> newHead = head;
				SingleLinkedListNode<T> elementoAnterior = new SingleLinkedListNode<T>();
				while (!(newHead.isNIL())
						&& !(newHead.getData().equals(element))) {
					elementoAnterior = newHead;
					newHead = newHead.next;
				}
				if (!(newHead.isNIL())) {
					elementoAnterior.next = newHead.next;
				}
			}
		}
	}
	@Override
	public T[] toArray(){
		T[] array = (T[])new Object[this.size()];
		
		int count = 0;
		
		SingleLinkedListNode<T> auxNode = head;
		
		while(!auxNode.isNIL()){
			array[count++] = auxNode.getData();
			auxNode = auxNode.next;
		}
		
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

	
}
