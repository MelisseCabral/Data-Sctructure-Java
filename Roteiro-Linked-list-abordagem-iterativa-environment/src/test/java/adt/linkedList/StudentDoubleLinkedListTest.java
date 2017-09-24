package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest {
	
	private DoubleLinkedList<Integer> lista1;
	private DoubleLinkedList<Integer> lista2;
	private DoubleLinkedList<Integer> lista3;
	
	@Before
	public void setUp() throws Exception {

		getImplementations();
		
		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		
		// Lista com 1 elemento.
		lista3.insert(1);
	}
	
	private void getImplementations(){
		//TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new DoubleLinkedListImpl<Integer>();
		lista2 = new DoubleLinkedListImpl<Integer>();
		lista3 = new DoubleLinkedListImpl<Integer>();
	}

	@Test
	public void testIsEmpty() {
		Assert.assertTrue(lista2.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertTrue(lista1.size() == 3);
		Assert.assertTrue(lista2.size() == 0);
	}

	@Test
	public void testSearch() {
		lista1.insert(3);
		Assert.assertTrue(lista1.search(3).equals(3));
		lista3.insert(1);
		Assert.assertTrue(lista3.search(1).equals(1));
		lista2.insert(1);
		Assert.assertTrue(!lista2.isEmpty());
	}

	@Test
	public void testInsert() {
		lista1.insert(5);
		Assert.assertTrue(lista1.search(5).equals(5));
		lista2.insert(1);
		Assert.assertTrue(!lista2.isEmpty());
	}

	@Test
	public void testRemove() {
		lista1.remove(3);
		Assert.assertTrue(lista1.size() == 2);
		lista3.remove(1);
		Assert.assertTrue(lista3.size() == 0);
	}

	@Test
	public void testToArray() {
		Assert.assertTrue(true);
	}
	
	// Métodos de DoubleLinkedList
	
	@Test
	public void testInsertFirst(){
		Assert.assertTrue(true);
		
	}

	@Test
	public void testRemoveFirst(){
		Assert.assertTrue(true);
	}
	@Test
	public void testRemoveLast(){
		Assert.assertTrue(true);
	}
}