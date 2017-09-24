package adt.skipList;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SkipTest {
	private SkipList<Integer> lista;
	private SkipList<Integer> lista2;
	private SkipNode<Integer> root, NIL;
	
	@Before
	public void setUp(){
		lista = new SkipListImpl<Integer>(3);
		lista2 = new SkipListImpl<Integer>(6);
		root = new SkipNode(Integer.MIN_VALUE, 6, new Integer(Integer.MIN_VALUE));
		NIL = new SkipNode(Integer.MAX_VALUE, 6, new Integer(Integer.MAX_VALUE));
	}
	
	@Test
	public void basicTest() {
		Assert.assertTrue(lista.size() == 0);
		Assert.assertTrue(lista.height() == 0);
		
		lista.insert(4, 4, 1);
		Assert.assertTrue(lista.size() == 1);
		Assert.assertTrue(lista.height() == 1);
		
		lista.insert(5, 5, 1);
		Assert.assertTrue(lista.size() == 2);
		Assert.assertTrue(lista.height() == 1);
		
		lista.insert(1, 1, 2);
		Assert.assertTrue(lista.size() == 3);
		Assert.assertTrue(lista.height() == 2);
		

		Assert.assertTrue(lista.search(1).key == 1);
		Assert.assertTrue(lista.search(5).key == 5);
		Assert.assertTrue(lista.search(9) == null);
		
		lista.insert(9, 9, 4);
		Assert.assertTrue(lista.size() == 3);
		
		lista.insert(9, 9, 3);
		Assert.assertTrue(lista.size() == 4);
		Assert.assertTrue(lista.search(9).key == 9);
		
		System.out.println(Arrays.toString(lista.toArray()));
		System.out.println("*Deve conter 1 com 2 niveis, 4 com 1 nível, 5 com um nível, 9 com 3 niveis, além de ROOT e NIL");
		
		lista.remove(10);
		Assert.assertTrue(lista.size() == 4);
		Assert.assertTrue(lista.height() == 3);
		
		
		lista.remove(9);
		Assert.assertTrue(lista.size() == 3);
		Assert.assertTrue(lista.height() == 2);
		
		lista.remove(1);
		Assert.assertTrue(lista.size() == 2);
		Assert.assertTrue(lista.height() == 1);
		
		lista.remove(4);
		Assert.assertTrue(lista.height() == 1);
		lista.remove(5);
		Assert.assertTrue(lista.size() == 0);
		Assert.assertTrue(lista.height() == 0);
		
		System.out.println(Arrays.toString(lista.toArray()));
		System.out.println("**Deve conter ROOT e NIL apenas");
	}
	
	@Test
	public void testeMoreHard(){
		lista2.insert(5, 5, 1);
		lista2.insert(3, 3, 2);
		lista2.insert(0, 0, 5);
		lista2.insert(4, 4, 3);
		lista2.insert(4, 4, 6);
		Assert.assertTrue(lista2.size() == 4);
		Assert.assertTrue(lista2.height() == 5);
		lista2.insert(4, 4, 7);
		Assert.assertTrue(lista2.size() == 4);
		Assert.assertTrue(lista2.height() == 5);
		lista2.insert(7, null, 5);
		Assert.assertTrue(lista2.size() == 4);
		Assert.assertTrue(lista2.height() == 5);
		lista2.insert(7, 7, -1);
		Assert.assertTrue(lista2.size() == 4);
		Assert.assertTrue(lista2.height() == 5);
		lista2.insert(10, 10, 6);
		Assert.assertTrue(lista2.size() == 5);
		Assert.assertTrue(lista2.height() == 6);
		lista2.remove(5);
		Assert.assertTrue(lista2.size() == 4);
		Assert.assertTrue(lista2.height() == 6);
		lista2.remove(10);
		Assert.assertTrue(lista2.size() == 3);
		Assert.assertTrue(lista2.height() == 5);
		lista2.remove(Integer.MAX_VALUE);
		lista2.remove(Integer.MIN_VALUE);
		Assert.assertTrue(lista2.size() == 3);
		Assert.assertTrue(lista2.height() == 5);
		
		lista2.remove(0);
		Assert.assertTrue(lista2.size() == 2);
		Assert.assertTrue(lista2.height() == 3);
		
		lista2.remove(-27);
		Assert.assertTrue(lista2.size() == 2);
		Assert.assertTrue(lista2.height() == 3);
		
		Assert.assertTrue(lista2.search(Integer.MAX_VALUE).equals(NIL));
		Assert.assertTrue(lista2.search(Integer.MIN_VALUE).getKey() == root.getKey());
		
		System.out.println(Arrays.toString(lista2.toArray()));
		System.out.println("***Deve conter 3, com dois niveis, 4, com três niveis, além de ROOT e NIL");
		
	}

}
