package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	@Override
	public void insert(T element) {
		if(data != null) {
			next.insert(element);
		}
		else {
			setData(element);
			setNext(new RecursiveDoubleLinkedListImpl<T>());
			((RecursiveDoubleLinkedListImpl<T>) next).setPrevious(this);
			if(previous == null) {
				setPrevious(new RecursiveDoubleLinkedListImpl<T>());
				previous.setNext(this);
			}
		}
	}
	
	@Override
	public void insertFirst(T element) {
		if(data == null) {
			if(next != null) {
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<T>();
				newNode.setData(next.getData());
				newNode.setNext(next.getNext());
				newNode.setPrevious((RecursiveDoubleLinkedListImpl<T>) next);
				((RecursiveDoubleLinkedListImpl<T>) next.getNext()).setPrevious(newNode);
				next.setNext(newNode);
				next.setData(element);
			}
			else {
				setData(element);
				setNext(new RecursiveDoubleLinkedListImpl<T>());
				setPrevious(new RecursiveDoubleLinkedListImpl<T>());
				((RecursiveDoubleLinkedListImpl<T>) next).setPrevious(this);
				previous.setNext(this);
			}
		}
		else {
			previous.insertFirst(element);
		}
	}
	
	@Override
	public void remove(T element) {
		if(data != null) {
			if(data.equals(element)) {
				if(next.getData() == null) {
					setPrevious(null);
					setNext(null);
					setData(null);
				}
				else {
					setData(next.getData());
					setNext(next.getNext());
					((RecursiveDoubleLinkedListImpl<T>) next).setPrevious(this);
				}
			}
			else if(next.getData() != null) {
				next.remove(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if(data != null) {
			if(previous.getData() == null) {
				if(next.getData() == null) {
					setPrevious(null);
					setNext(null);
					setData(null);
				} 
				else {
					setData(next.getData());
					setNext(next.getNext());
					((RecursiveDoubleLinkedListImpl<T>) next).setPrevious(this);
				}
			}
			else {
				previous.removeFirst();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeLast() {
		if(data != null) {
			if(next.getData() == null) {
				if(previous.getData() == null) {
					setPrevious(null);
					setNext(null);
					setData(null);
				} 
				else {
					((RecursiveDoubleLinkedListImpl<T>) next).setPrevious(previous);
					previous.setNext(next);
				}
			}
			else {
				((DoubleLinkedList<T>) next).removeLast();
			}
		}
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
	
	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}
}
