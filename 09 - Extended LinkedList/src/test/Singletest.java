package test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class Singletest {
	private SingleLinkedListImpl<Integer> lista;
	
	@Before
	public void setUp(){
		lista = new SingleLinkedListImpl<Integer>();
	}
	
	@Test
	public void testIsEmptyAndSize(){
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(lista.size() == 0);
		
		lista.insert(8);
		Assert.assertFalse(lista.isEmpty());
		Assert.assertTrue(lista.size() == 1);
	
		Assert.assertTrue(lista.getHead().equals(new SingleLinkedListNode<Integer>(new Integer(8),null)));
		
		lista.remove(8);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertTrue(lista.size() == 0);
		Assert.assertTrue(lista.getHead().getData() == null);
		
		lista.insert(9);
		lista.insert(10);
		lista.insert(11);
		lista.insert(12);
		Assert.assertFalse(lista.isEmpty());
		Assert.assertTrue(lista.size() == 4);
		Assert.assertTrue(lista.getHead().equals(new SingleLinkedListNode<Integer>(new Integer(9),null)));
		
		lista.remove(9);
		Assert.assertTrue(lista.size() == 3);
		Assert.assertTrue(lista.getHead().equals(new SingleLinkedListNode<Integer>(new Integer(10),null)));
		
		lista.remove(12);
		Assert.assertTrue(lista.size() == 2);
		Assert.assertTrue(lista.getHead().equals(new SingleLinkedListNode<Integer>(new Integer(10),null)));
		
		lista.insert(9);
		lista.insert(10);
		lista.insert(11);
		lista.insert(12);
		Assert.assertTrue(lista.size() == 6);

	}
	
	@Test
	public void testesInsertAndRemove(){
		Assert.assertTrue(lista.isEmpty());
		Assert.assertArrayEquals(new Integer[] {}, lista.toArray());
		lista.insert(1);
		Assert.assertArrayEquals(new Integer[] {1}, lista.toArray());
		lista.insert(2);
		Assert.assertArrayEquals(new Integer[] {1, 2}, lista.toArray());
		lista.insert(3);
		Assert.assertArrayEquals(new Integer[] {1, 2, 3}, lista.toArray());
		lista.insert(4);
		Assert.assertArrayEquals(new Integer[] {1, 2, 3, 4}, lista.toArray());
		lista.insert(5);
		Assert.assertTrue(lista.size() == 5);
		Assert.assertFalse(lista.isEmpty());
		Assert.assertArrayEquals(new Integer[] {1, 2, 3, 4, 5}, lista.toArray());
		
		lista.remove(6);
		Assert.assertTrue(lista.size() == 5);
		Assert.assertArrayEquals(new Integer[] {1, 2, 3, 4, 5}, lista.toArray());
		
		lista.remove(1);
		Assert.assertTrue(lista.size() == 4);
		Assert.assertArrayEquals(new Integer[] {2, 3, 4, 5}, lista.toArray());
		
		lista.remove(5);
		Assert.assertTrue(lista.size() == 3);
		Assert.assertArrayEquals(new Integer[] {2, 3, 4}, lista.toArray());
		
		lista.remove(3);
		Assert.assertTrue(lista.size() == 2);
		Assert.assertArrayEquals(new Integer[] {2, 4}, lista.toArray());
		
		lista.remove(null);
		Assert.assertTrue(lista.size() == 2);
		Assert.assertArrayEquals(new Integer[] {2, 4}, lista.toArray());
		
		lista.insert(null);
		Assert.assertTrue(lista.size() == 2);
		Assert.assertArrayEquals(new Integer[] {2, 4}, lista.toArray());
		
		lista.remove(2);
		lista.remove(4);
		Assert.assertTrue(lista.size() == 0);
		Assert.assertTrue(lista.isEmpty());
		Assert.assertArrayEquals(new Integer[] {}, lista.toArray());
		
		lista.insert(45);
		Assert.assertTrue(lista.size() == 1);
		Assert.assertFalse(lista.isEmpty());
		Assert.assertArrayEquals(new Integer[] {45}, lista.toArray());
	}
	
	@Test
	public void testSearch(){
		
		lista.insert(1);
		lista.insert(1);
		lista.insert(2);
		lista.insert(3);
		lista.insert(null);
		lista.insert(4);
		lista.insert(4);
		Assert.assertEquals(null, lista.search(null));
	
		Assert.assertEquals(new Integer(1), lista.search(1));
		Assert.assertEquals(new Integer(2), lista.search(2));
		Assert.assertEquals(new Integer(3), lista.search(3));
		Assert.assertEquals(new Integer(4), lista.search(4));
		Assert.assertEquals(null, lista.search(5));
		Assert.assertEquals(null, lista.search(null));
		
		lista.remove(null);
		lista.remove(1);
		Assert.assertEquals(new Integer(1), lista.search(1));
		Assert.assertArrayEquals(new Integer[] {1,2,3,4,4}, lista.toArray());
		lista.remove(1);
		Assert.assertEquals(null, lista.search(1));
		Assert.assertArrayEquals(new Integer[] {2,3,4,4}, lista.toArray());
		Assert.assertEquals(null, lista.search(null));
		
		lista.remove(4);
		Assert.assertEquals(new Integer(4), lista.search(4));
		Assert.assertArrayEquals(new Integer[] {2,3,4}, lista.toArray());
		
		lista.remove(3);
		Assert.assertEquals(null, lista.search(3));
		Assert.assertArrayEquals(new Integer[] {2,4}, lista.toArray());
		
		Assert.assertEquals(null, lista.search(null));
		
	}

}