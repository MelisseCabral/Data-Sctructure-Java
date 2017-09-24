package adt.hashtable;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Testhash {

	
	private Hashtable<String> hashString;
	private Hashtable<Integer> hashInteger;
	
	@Before
	public void before() {
		hashString = new HashtableClosedAddressImpl<>(3, null);
		hashInteger = new HashtableOpenAddressLinearProbingImpl<>(5, null);
	}
	
	@Test
	public void testBasic() {
		
		Assert.assertEquals(3, hashString.capacity());
		Assert.assertEquals(5, hashInteger.capacity());
		
		Assert.assertEquals(0, hashString.size());
		Assert.assertEquals(0, hashInteger.size());
		
		Assert.assertEquals(true, hashString.isEmpty());
		Assert.assertEquals(true, hashInteger.isEmpty());
		
		Assert.assertEquals(false, hashString.isFull());
		Assert.assertEquals(false, hashInteger.isFull());
	
		Assert.assertEquals(-1, hashString.indexOf(null));
		Assert.assertEquals(-1, hashInteger.indexOf(null));
		
		Assert.assertEquals(-1, hashString.indexOf("100"));
		Assert.assertEquals(-1, hashInteger.indexOf(100));
		
		Assert.assertEquals(null, hashString.search(null));
		Assert.assertEquals(null, hashInteger.search(null));
		
		Assert.assertEquals(null, hashString.search("100"));
		Assert.assertEquals(null, hashInteger.search(100));
	
		hashString.insert(null);
		hashInteger.insert(null);
		
		Assert.assertEquals(true, hashString.isEmpty());
		Assert.assertEquals(true, hashInteger.isEmpty());
		
		Assert.assertEquals(null, hashString.search(null));
		Assert.assertEquals(null, hashInteger.search(null));
		
		hashString.remove(null);
		hashInteger.remove(null);
		
		Assert.assertEquals(true, hashString.isEmpty());
		Assert.assertEquals(true, hashInteger.isEmpty());
		
		Assert.assertEquals(null, hashString.search(null));
		Assert.assertEquals(null, hashInteger.search(null));
		
		hashString.remove("100");
		hashInteger.remove(100);
		
		Assert.assertEquals(true, hashString.isEmpty());
		Assert.assertEquals(true, hashInteger.isEmpty());
		
		Assert.assertEquals(null, hashString.search(null));
		Assert.assertEquals(null, hashInteger.search(null));
		
		
		hashString.insert("100");
		hashInteger.insert(100);

//		System.out.println(hashString.toString());
//		System.out.println(hashInteger.toString());
		
		Assert.assertEquals(false, hashString.isEmpty());
		Assert.assertEquals(false, hashInteger.isEmpty());

		Assert.assertEquals(1, hashString.size());
		Assert.assertEquals(1, hashInteger.size());
		
		Assert.assertEquals(false, hashString.isFull());
		Assert.assertEquals(false, hashInteger.isFull());
		
		Assert.assertEquals((new String("100").hashCode() % 3), hashString.indexOf("100"));
		Assert.assertEquals((new Integer(100).hashCode() % 5), hashInteger.indexOf(100));
		
		Assert.assertEquals(new String("100"), hashString.search("100"));
		Assert.assertEquals(new Integer(100), hashInteger.search(100));
		
		hashInteger.insert(100);
		hashString.insert("100");
		
		Assert.assertEquals(false, hashString.isEmpty());
		Assert.assertEquals(false, hashInteger.isEmpty());
		
		Assert.assertEquals(1, hashString.size());
		Assert.assertEquals(1, hashInteger.size());
		
		Assert.assertEquals(false, hashString.isFull());
		Assert.assertEquals(false, hashInteger.isFull());
		
//		System.out.println(hashString.toString());
//		System.out.println(hashInteger.toString());
		
		hashString.insert("105");
		hashInteger.insert(105);
		//TODO VAI TER COLISAO AQUIIIII!!! RUN TO THE HILLS!

		
		Assert.assertEquals(2, hashString.size());
		Assert.assertEquals(2, hashInteger.size());
		
//		System.out.println(hashString.toString());
//		System.out.println(hashInteger.toString());
		
		Assert.assertEquals(false, hashString.isEmpty());
		Assert.assertEquals(false, hashInteger.isEmpty());
		
		Assert.assertEquals(false, hashString.isFull());
		Assert.assertEquals(false, hashInteger.isFull());
		
		Assert.assertEquals((new String("105").hashCode() % 3), hashString.indexOf("105"));
		Assert.assertEquals((new Integer(105).hashCode() % 5) + 1, hashInteger.indexOf(105));
		
		Assert.assertEquals(new String("105"), hashString.search("105"));
		Assert.assertEquals(new Integer(105), hashInteger.search(105));
		
		hashString.insert("102");
		hashInteger.insert(102);

//		System.out.println(hashInteger.toString());
		
		Assert.assertEquals(false, hashString.isEmpty());
		Assert.assertEquals(false, hashInteger.isEmpty());

		Assert.assertEquals(3, hashString.size());
		Assert.assertEquals(3, hashInteger.size());
		
		Assert.assertEquals(true, hashString.isFull());
		Assert.assertEquals(false, hashInteger.isFull());
		
		Assert.assertEquals((new String("102").hashCode() % 3), hashString.indexOf("102"));
		Assert.assertEquals((new Integer(102).hashCode() % 5), hashInteger.indexOf(102));
		
		Assert.assertEquals(new String("102"), hashString.search("102"));
		Assert.assertEquals(new Integer(102), hashInteger.search(102));
		
		hashInteger.insert(103);
//		System.out.println(hashInteger.toString());
		hashString.insert("103");
		hashInteger.insert(104);

//		System.out.println(hashInteger.toString());
		hashString.insert("104");
		
		try {
			hashInteger.insert(101);
			Assert.fail();
		} catch(Exception exc) {
			Assert.assertEquals(exc.getMessage(), "Hashtable overflow!");
		}
		
		Assert.assertEquals(false, hashString.isEmpty());
		Assert.assertEquals(false, hashInteger.isEmpty());
		
		Assert.assertEquals(true, hashString.isFull());
		Assert.assertEquals(true, hashInteger.isFull());
		
		Assert.assertEquals(new String("105"), hashString.search("105"));
		Assert.assertEquals(new String("102"), hashString.search("102"));
		Assert.assertEquals(new String("103"), hashString.search("103"));
		Assert.assertEquals(new String("104"), hashString.search("104"));
		

		Assert.assertEquals(new Integer(105), hashInteger.search(105));
		Assert.assertEquals(new Integer(102), hashInteger.search(102));
		Assert.assertEquals(new Integer(103), hashInteger.search(103));
		Assert.assertEquals(new Integer(104), hashInteger.search(104));
		
//		System.out.println(hashString.toString());
//		System.out.println(hashInteger.toString());
		
		hashString.remove("100");
		hashInteger.remove(100);
		
		System.out.println(hashString.toString());
		System.out.println(hashInteger.toString());
		
		Assert.assertEquals(false, hashString.isEmpty());
		Assert.assertEquals(false, hashInteger.isEmpty());
		
		Assert.assertEquals(true, hashString.isFull());
		Assert.assertEquals(false, hashInteger.isFull());
		
		Assert.assertEquals(-1, hashString.indexOf("100"));
		Assert.assertEquals(-1, hashInteger.indexOf(100));
		
		Assert.assertEquals(4, hashString.size());
		Assert.assertEquals(4, hashInteger.size());
		
		Assert.assertEquals(null, hashString.search("100"));
		Assert.assertEquals(null, hashInteger.search(100));
		

		hashInteger.insert(100);
		hashString.insert("100");
		
		Assert.assertEquals(false, hashString.isEmpty());
		Assert.assertEquals(false, hashInteger.isEmpty());

		Assert.assertEquals(5, hashString.size());
		Assert.assertEquals(5, hashInteger.size());
		
		Assert.assertEquals(true, hashString.isFull());
		Assert.assertEquals(true, hashInteger.isFull());
		
		Assert.assertEquals((new String("100").hashCode() % 3), hashString.indexOf("100"));
		Assert.assertEquals((new Integer(100).hashCode() % 5), hashInteger.indexOf(100));
		
		Assert.assertEquals(new String("100"), hashString.search("100"));
		Assert.assertEquals(new Integer(100), hashInteger.search(100));
		
		System.out.println(hashString.toString());
		System.out.println(hashInteger.toString());
		
		
	}

}
