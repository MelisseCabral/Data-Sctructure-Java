package adt.hashtable;

/**
 * This class is an implementation of the hash function using linear probing. 
 */
public class HashFunctionLinearProbingImpl<T> implements HashFunctionOpenAddress {

	protected Hashtable<T> hashtable;
	private int mod;
	
	// The auxiliary hash function used by linear probing.
	protected HashFunctionClosedAddress originalHashFunction;
	
	public HashFunctionLinearProbingImpl(Hashtable<T> hashtable, HashFunctionClosedAddressMethod method) {
		super();
		this.hashtable = hashtable;
		this.originalHashFunction = new HashFunctionDivisionMethodImpl<T>(hashtable);
		mod = hashtable.capacity();
		//Adjust your constructor to initialize the attribute originalHashFunction with the correct hash function 
	}
	
	/**
	 * The hash function might use the length of the hashtable. This can be captured through the method
	 * capacity of the hashtable.
	 */
	@Override
	public int hash(Object element, int probe) {
		return (originalHashFunction.hash(element) + probe) % mod;
	}

}
