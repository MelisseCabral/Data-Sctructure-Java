package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentLinkedListTest {
	
	private LinkedList<Integer> lista1;
	private LinkedList<Integer> lista2;
	
	@Before
	public void setUp() throws Exception {
		
		getImplementations();
		
		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);
		
	}
	
	private void getImplementations(){
		//TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new SingleLinkedListImpl<Integer>();
		lista2 = new SingleLinkedListImpl<Integer>();
	}

	@Test
	public void testIsEmpty() {
		Assert.assertTrue(lista2.isEmpty() == true);
		Assert.assertTrue(lista1.isEmpty() == false);
	}

	@Test
	public void testSize() {
		Assert.assertTrue(lista1.size() == 3);
		Assert.assertTrue(lista2.size() == 0);
	}

	@Test
	public void testSearch() {
		Assert.assertTrue(lista2.search(6) == null);
		Assert.assertTrue(lista1.search(2) == 2);
	}

	@Test
	public void testInsert() {
		lista2.insert(66);
		Assert.assertTrue(!lista2.isEmpty());
	}

	@Test
	public void testRemove() {
		lista1.remove(1);
		lista1.remove(2);
		lista1.remove(3);
		Assert.assertTrue(lista1.isEmpty());
	}

	@Test
	public void testToArray() {
		//
	}
}