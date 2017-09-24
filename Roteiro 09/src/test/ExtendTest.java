package test;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.linkedList.SingleLinkedListNode;
import adt.linkedList.extended.ExtendedSingleLinkedListImpl;

public class ExtendTest {
	
	private ExtendedSingleLinkedListImpl<Integer> lista;
	private ExtendedSingleLinkedListImpl<Integer> lista2;
	private Comparator<Integer> comp;
	@Before
	public void before(){
		comp = new Comp1();
		lista = new ExtendedSingleLinkedListImpl<Integer>(comp);
		lista2 = new ExtendedSingleLinkedListImpl<Integer>(comp);
	}
/*	
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
	
	@Test
	public void testMinimun(){
		
		lista.insert(1);
		lista.insert(7);
		lista.insert(5);
		lista.insert(0);
		lista.insert(-1);
		lista.insert(-1);
		lista.insert(10);
		
		Assert.assertEquals(lista.minimum(), new Integer(-1));
		
		lista.remove(-1);
		
		Assert.assertEquals(lista.minimum(), new Integer(-1));
		
		lista.remove(-1);
		Assert.assertEquals(lista.minimum(), new Integer(0));
		lista.remove(0);
		Assert.assertEquals(lista.minimum(), new Integer(1));
		lista.remove(7);
		Assert.assertEquals(lista.minimum(), new Integer(1));
		lista.remove(1);
		Assert.assertEquals(lista.minimum(), new Integer(5));
	}
	
	@Test
	public void testRemoveAll() {

		lista.insert(-1);
		lista.insert(-1);
		lista.insert(-1);
		lista.insert(0);
		Assert.assertEquals(lista.minimum(), new Integer(-1));
		
		lista.removeAll(-1);
		Assert.assertEquals(lista.minimum(), new Integer(0));
		
		lista.removeAll(0);
		Assert.assertTrue(lista.minimum() == null);
		
		lista.insert(2);
		for(int i = 0 ; i < 100; i++)
			lista.insert(1);
		Assert.assertEquals(lista.minimum(), new Integer(1));
		
		lista.removeAll(1);
		Assert.assertEquals(lista.minimum(), new Integer(2));;
			
	}
*/	
	@Test
	public void testConcatenate() {

		lista.insert(10);
		
		lista2.insert(1);
		lista2.insert(2);
		lista2.insert(3);
	
		lista.concatenate(lista2);
		Assert.assertArrayEquals(lista.toArray(), new Integer[] {10,1,2,3});
		Assert.assertTrue(lista.size() == 4);
		
		lista.insert(10);
		lista.insert(100);
		Assert.assertTrue(lista.size() == 6);
		Assert.assertArrayEquals(lista.toArray(), new Integer[] {10,1,2,3,10,100});
		lista.concatenate(lista2);
		System.out.println(lista2.size());
		//System.out.println(Arrays.toString(lista2.toArray()));
		//Assert.assertTrue(lista.size() == 9);
	//	Assert.assertArrayEquals(lista.toArray(), new Integer[] {1,2,3,1,2,3});
			
	}
	
	
}
