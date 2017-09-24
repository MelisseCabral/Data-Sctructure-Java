package adt.hashtable;

public class HashtableOpenAddressLinearProbingImpl<T> extends
		AbstractHashtable<T, Object> {
	
	private static DELETED deleted_item = new DELETED();
	private HashFunctionOpenAddress hash_function;    // auxiliary hash_function post cast

	// DO NOT DELET THIS CONSTRUCTOR. ADJUST IT.
	@SuppressWarnings("unchecked")
	public HashtableOpenAddressLinearProbingImpl(int size,HashFunctionClosedAddressMethod method) {
		super(size);
		super.table = new Object[size];
		//TODO Adjust your constructor here
		// The length of the internal table must be given size
		// the hash function must be an implementation of linear probing. 
		super.hashFunction = new HashFunctionLinearProbingImpl<T>(this, method); 
		this.hash_function = (HashFunctionLinearProbingImpl<T>) hashFunction;
	}
	
	@Override
	public void insert(T element) {
		if (element != null && search(element) == null) {
			if (isFull()){
				throw new HashtableOverflowException();
			} else {
				int probe = 0;
				int key = hash_function.hash(element, probe);
				while (super.table[key] != null && !deleted_item.equals(table[key])){
					COLLISIONS++;
					probe++;
					key = hash_function.hash(element, probe);
				}
				super.table[key] = element;
				super.elements++;
			}
		}
		
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()){
			int probe = 0;
			int key = hash_function.hash(element, probe);
			while ((super.table[key] == null || !table[key].equals(element)) && probe < capacity()){
				probe++;
				key = hash_function.hash(element, probe);
			}
			
			if (table[key].equals(element)) {
				super.table[key] = deleted_item;
				super.elements--;
			}
		}

	}

	@Override
	public T search(T element) {
		if (element == null || isEmpty())
			return null;
		
		int probe = 0;
		int key = hash_function.hash(element, probe);
		while ((table[key] == null || !table[key].equals(element)) && probe < capacity()){
			probe++;
			key = hash_function.hash(element, probe);
		}
			
		if (table[key] == null || !table[key].equals(element)){ // cheguei a ultima posicao a nao achou
			return null;
		} else { // achou o elemento
			return element;
		}
			
	}

	@Override
	public int indexOf(T element) {
		if (element == null) return -1;
		
		int probe = 0;
		int key = hash_function.hash(element, probe);
		while ((table[key] == null || !table[key].equals(element)) && probe < capacity()){
			probe++;
			key = hash_function.hash(element, probe);
		}
			
		if (table[key] == null || !table[key].equals(element)){ // cheguei a ultima posicao a nao achou
			return -1;
		} else { // achou o elemento
			return key;
		}
	}
	
	@Override
	public String toString(){
		String string = "[";
		for (int i = 0; i < table.length; i++) {
			string += table[i] + ",";
		}
		
		string += "]";
		return string;
	}

}
