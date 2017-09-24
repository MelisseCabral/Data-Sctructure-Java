package adt.linkedList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RecursiveSingleLinkedListImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test0() {
		LinkedList<Integer> l = new RecursiveSingleLinkedListImpl<Integer>();
		assertTrue(l.isEmpty());
		assertEquals(l.size(), 0);
		l.insert(5);
		assertFalse(l.isEmpty());
		assertEquals(l.size(), 1);
	}

	@Test
	public void test1() {
		LinkedList<Integer> l = new RecursiveSingleLinkedListImpl<Integer>();
		Integer[] array = new Integer[]{5, 3, 9};
		l.insert(5);
		l.insert(3);
		l.insert(9);
		assertArrayEquals(array, l.toArray());
	}

	@Test
	public void test2() {
		LinkedList<Integer> l = new RecursiveSingleLinkedListImpl<Integer>();
		l.insert(5);
		l.insert(3);
		l.insert(9);
		l.insert(12);
		l.remove(3);
		assertArrayEquals(new Integer[]{5, 9, 12}, l.toArray());
		assertFalse(l.isEmpty());
		assertEquals(l.size(), 3);
		l.remove(3);
		assertArrayEquals(new Integer[]{5, 9, 12}, l.toArray());
		assertEquals(l.size(), 3);
		l.remove(5);
		assertArrayEquals(new Integer[]{9, 12}, l.toArray());
		assertEquals(l.size(), 2);
		l.remove(9);
		assertArrayEquals(new Integer[]{12}, l.toArray());
		assertEquals(l.size(), 1);
		l.remove(12);
		assertEquals(l.size(), 0);
		assertTrue(l.isEmpty());
		assertArrayEquals(new Integer[]{}, l.toArray());
		l.remove(17);
		assertArrayEquals(new Integer[]{}, l.toArray());
	}

	@Test
	public void test3() {
		LinkedList<String> l = new RecursiveSingleLinkedListImpl<String>();
		l.insert("rafael");
		l.insert("lapis");
		l.insert("borracha");
		l.insert("papel");
		l.insert("regua");
		assertEquals(l.search("rafael"), "rafael");
		assertEquals(l.search("regua"), "regua");
		assertEquals(l.search("caneta"), null);
	}
}