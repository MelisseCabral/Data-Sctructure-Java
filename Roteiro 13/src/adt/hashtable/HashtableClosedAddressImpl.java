package adt.hashtable;

import java.util.LinkedList;

public class HashtableClosedAddressImpl<T> extends AbstractHashtable<T, LinkedList<T>> {
	
	private HashFunctionClosedAddress hash_function;  // auxiliary hash_function post cast

	// DO NOT DELETE THIS CONSTRUCTOR. ADJUST IT.
	@SuppressWarnings("unchecked")
	public HashtableClosedAddressImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		//TODO Adjust your constructor here
		// The length of the internal table must be the immediate prime number greater than 
		// the given size. For example, if size=10 then the length must be 11. If size=20, the length 
		// must be 23. You may use the method getPrimeAbove(int size) but you must implement it.
		super.table = new LinkedList[getPrimeAbove(size)];
		
		super.hashFunction = new HashFunctionDivisionMethodImpl<T>(this); 
		this.hash_function = (HashFunctionDivisionMethodImpl<T>) super.hashFunction;
	}
	
	//AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given number.  
	 */
	int getPrimeAbove(int number){
		int result = number;
		while(!Util.isPrime(result)){
			result = result + 1;
		}
		return result;
	}
			
	@Override
	public void insert(T element) {
		if (element != null && search(element) == null) {
			int key = hash_function.hash(element);
			if (table[key] == null)
				super.table[key] = new LinkedList<T>();
			
			super.table[key].add(element);
			super.elements++;
		}
		
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int key = hash_function.hash(element);
			if (table[key] != null) {
				if (super.table[key].remove(element)){
					super.elements--;
				}
			}
		}
		
	}

	@Override
	public T search(T element) {
		if (element != null){
			int key = hash_function.hash(element);  // acha a chave
			LinkedList<T> linked_list = super.table[key]; // pega a linked list associada aquela chave
			if (linked_list == null){
				return null;            // nao há ninguem ali
			}
			int index = linked_list.indexOf(element);  // procura o indice daquele elemento na linked list
			if (index == -1){  // nao achou o elemento
				return null;
			} else { // achou o elemento
				return (T)linked_list.get(index);
			}
			
			
		}
		return null;
	}

	@Override
	public int indexOf(T element) {
		if (element == null) return -1;
		
		int key = hash_function.hash(element);
		LinkedList<T> linked_list = super.table[key];
		if (linked_list == null){ // se não há ninguém ali
			return -1;
		}
		// se há:
		int index = super.table[key].indexOf(element);
		if (index == -1){ // nao achou na linked list
			return -1;
		} else {
			return key;
		}
	}
	
	@Override
	public String toString(){
		String string = "{";
		for (int i = 0; i < table.length; i++) {
			String substring = "[";
			if (table[i] == null){
				substring += "VAZIO";
			} else  {
				for (int j = 0; j < table[i].size(); j++) {
					substring += table[i].get(j) + ",";	
				}
			}
			substring += "]" + ",";
			string += substring;
		}
		string += "}";
		
		return string;
	}

}
