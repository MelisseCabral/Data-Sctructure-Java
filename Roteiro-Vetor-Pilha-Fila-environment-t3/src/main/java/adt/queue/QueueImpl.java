package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
		
	
	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[])new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		 if (!this.isEmpty()) {
	            return this.array[0];
	        }
	        return null;
		
	}

	@Override
	public boolean isEmpty() {
		if (this.tail == -1) {
            return true;
        }
        return false;
	}

	@Override
	public boolean isFull() {
		if (this.tail == this.array.length - 1) {
            return true;
        }
        return false;
	}
	
	private void shiftLeft(){
		int i = 0;
        while (i < this.tail) {
            this.array[i] = this.array[i + 1];
            ++i;
        }
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
            throw new QueueOverflowException();
        }
        if (element != null) {
            ++this.tail;
            this.array[this.tail] = element;
        }
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
            throw new QueueUnderflowException();
        }
        T removed_element = this.array[0];
        this.shiftLeft();
        --this.tail;
        return removed_element;
	}


}
