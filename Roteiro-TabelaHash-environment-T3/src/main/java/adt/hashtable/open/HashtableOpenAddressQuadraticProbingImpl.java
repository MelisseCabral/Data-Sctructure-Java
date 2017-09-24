package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> 
	extends AbstractHashtableOpenAddress<T> {
	
	public HashtableOpenAddressQuadraticProbingImpl(int size, 
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
	}
	
	@Override
	public void insert(T element) {
		boolean flag = false;
		if (!isFull()) {
			for(int i = 0; i < capacity() && flag == false; i++) {
				@SuppressWarnings("unchecked")
				int indice = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, i);
				if (table[indice] == null || table[indice].getClass() == DELETED.class) {
					table[indice] = element;
					elements++;
				}
				else {
					COLLISIONS++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		int indice = indexOf(element);
		if (indice != -1) {
			table[indice] = new DELETED();
			elements--;
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		int indice = indexOf(element);
		if (indice != -1) {
			T t = (T) table[indice];
			result = t;
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		int indice = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, 0);
		for(int i = 1; i < capacity() && table[indice] != null; i++) {
			indice = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, i);
			if(table[indice].equals(element)) {
				index = indice;
			}
		}
		return index;
	}
}
