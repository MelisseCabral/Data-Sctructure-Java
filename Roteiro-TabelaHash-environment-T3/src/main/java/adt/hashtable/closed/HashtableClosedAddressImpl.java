package adt.hashtable.closed;

import java.util.LinkedList;

import adt.hashtable.AbstractHashtable;
import adt.hashtable.hashfunction.*;
import adt.hashtable.Util;
import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed address.
	 * Such a function can follow one of these methods: DIVISION or MULTIPLICATION.
	 * In the DIVISION method, it is useful to change the size of the table to an integer 
	 * that is prime. This can be achieved by producing such a prime number that is bigger and 
	 * close to the desired size. 
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and getPrimeAbove as documented 
	 * bellow. 
	 * 
	 * The length of the internal table must be the immediate prime number greater than the 
	 * given size. For example, if size=10 then the length must be 11. If size=20, 
	 * the length must be 23. You must implement this idea in the auxiliary method 
	 * getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize  = desiredSize;
		
		if(method == HashFunctionClosedAddressMethod.DIVISION){
			realSize = this.getPrimeAbove(desiredSize); //real size must the the immediate prime above
		} 
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}
	
	//AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given number.  
	 * You can use the method Util.isPrime to check if a number is prime.
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
		if(search(element) == null) {
			@SuppressWarnings("unchecked")
			int indice = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element);
			if(table[indice] == null) {
				table[indice] = new LinkedList<T>();
			}
			else {
				COLLISIONS++;
			}
			((LinkedList<T>) table[indice]).add(element);
			elements++;
		}
	}

	@Override
	public void remove(T element) {
		if(search(element) != null) {
			@SuppressWarnings("unchecked")
			int indice = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element);
			boolean remove = ((LinkedList<T>) table[indice]).remove(element);
			elements--;
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		@SuppressWarnings("unchecked")
		int indice = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element);
		LinkedList<T> linkedList1 = (LinkedList<T>) table[indice];
		if(table[indice] != null && linkedList1.indexOf(element) != -1) {
			result = linkedList1.get(linkedList1.indexOf(element));
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		int result = -1;
		if (search(element) != null) {
			result = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element);
		}
		return result;
	}

}
