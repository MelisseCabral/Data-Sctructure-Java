package adt.linkedList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RecursiveDoubleLinkedListImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test0() {
		LinkedList<Integer> l = new RecursiveDoubleLinkedListImpl<Integer>();
		assertTrue(l.isEmpty());
		assertEquals(l.size(), 0);

		l.insert(5);
		assertFalse(l.isEmpty());
		assertEquals(l.size(), 1);
	}

	@Test
	public void test1() {
		LinkedList<Integer> l = new RecursiveDoubleLinkedListImpl<Integer>();
		Integer[] array = new Integer[]{5, 3, 9};
		l.insert(5);
		l.insert(3);
		l.insert(9);
		assertArrayEquals(array, l.toArray());
	}

	@Test
	public void test2() {
		LinkedList<Integer> l = new RecursiveDoubleLinkedListImpl<Integer>();
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
		DoubleLinkedList<String> l = new RecursiveDoubleLinkedListImpl<String>();
		l.insert("rafael");
		l.insert("lapis");
		l.insert("borracha");
		l.insert("papel");
		l.insert("regua");
		assertEquals(l.search("rafael"), "rafael");
		assertEquals(l.search("lapis"), "lapis");
		assertEquals(l.search("borracha"), "borracha");
		assertEquals(l.search("papel"), "papel");
		assertEquals(l.search("regua"), "regua");
		assertEquals(l.search("caneta"), null);

		l.insert("pincel");
		assertEquals(l.size(), 6);
		assertEquals(l.search("rafael"), "rafael");
		assertEquals(l.search("lapis"), "lapis");
		assertEquals(l.search("borracha"), "borracha");
		assertEquals(l.search("papel"), "papel");
		assertEquals(l.search("regua"), "regua");
		assertEquals(l.search("pincel"), "pincel");
		assertEquals(l.search("caneta"), null);

		l.remove("pincel");
		assertEquals(l.size(), 5);
		assertEquals(l.search("pincel"), null);

		l.removeFirst();
		assertEquals(l.search("rafael"), null);
		assertEquals(l.search("lapis"), "lapis");
		assertEquals(l.search("borracha"), "borracha");
		assertEquals(l.search("papel"), "papel");
		assertEquals(l.search("regua"), "regua");
		assertEquals(l.search("pincel"), null);
		assertEquals(l.search("caneta"), null);

		l.removeFirst();
		assertEquals(l.search("rafael"), null);
		assertEquals(l.search("lapis"), null);
		assertEquals(l.search("borracha"), "borracha");
		assertEquals(l.search("papel"), "papel");
		assertEquals(l.search("regua"), "regua");
		assertEquals(l.search("pincel"), null);
		assertEquals(l.search("caneta"), null);

		l.removeFirst();
		assertEquals(l.search("rafael"), null);
		assertEquals(l.search("lapis"), null);
		assertEquals(l.search("borracha"), null);
		assertEquals(l.search("papel"), "papel");
		assertEquals(l.search("regua"), "regua");
		assertEquals(l.search("pincel"), null);
		assertEquals(l.search("caneta"), null);

		l.removeLast();
		assertEquals(l.search("rafael"), null);
		assertEquals(l.search("lapis"), null);
		assertEquals(l.search("borracha"), null);
		assertEquals(l.search("papel"), "papel");
		assertEquals(l.search("regua"), null);
		assertEquals(l.search("pincel"), null);
		assertEquals(l.search("caneta"), null);

		l.removeLast();
		assertEquals(l.search("rafael"), null);
		assertEquals(l.search("lapis"), null);
		assertEquals(l.search("borracha"), null);
		assertEquals(l.search("papel"), null);
		assertEquals(l.search("regua"), null);
		assertEquals(l.search("pincel"), null);
		assertEquals(l.search("caneta"), null);

		l.removeFirst();
		l.removeLast();
		assertEquals(l.search("rafael"), null);
		assertEquals(l.search("lapis"), null);
		assertEquals(l.search("borracha"), null);
		assertEquals(l.search("papel"), null);
		assertEquals(l.search("regua"), null);
		assertEquals(l.search("pincel"), null);
		assertEquals(l.search("caneta"), null);
		assertArrayEquals(l.toArray(), new String[]{});

		l.insertFirst("rafael");
		assertEquals(l.search("rafael"), "rafael");
		assertEquals(l.search("lapis"), null);
		assertEquals(l.search("borracha"), null);
		assertEquals(l.search("papel"), null);
		assertEquals(l.search("regua"), null);
		assertEquals(l.search("pincel"), null);
		assertEquals(l.search("caneta"), null);
		assertArrayEquals(l.toArray(), new String[]{"rafael"});

		l.insertFirst("lapis");
		assertEquals(l.search("rafael"), "rafael");
		assertEquals(l.search("lapis"), "lapis");
		assertEquals(l.search("borracha"), null);
		assertEquals(l.search("papel"), null);
		assertEquals(l.search("regua"), null);
		assertEquals(l.search("pincel"), null);
		assertEquals(l.search("caneta"), null);
		assertArrayEquals(l.toArray(), new String[]{"lapis", "rafael"});

		l.insertFirst("regua");
		assertEquals(l.search("rafael"), "rafael");
		assertEquals(l.search("lapis"), "lapis");
		assertEquals(l.search("borracha"), null);
		assertEquals(l.search("papel"), null);
		assertEquals(l.search("regua"), "regua");
		assertEquals(l.search("pincel"), null);
		assertEquals(l.search("caneta"), null);
		assertArrayEquals(l.toArray(), new String[]{"regua", "lapis", "rafael"});

		l.insertFirst("borracha");
		assertEquals(l.size(), 4);
		assertEquals(l.search("rafael"), "rafael");
		assertEquals(l.search("lapis"), "lapis");
		assertEquals(l.search("borracha"), "borracha");
		assertEquals(l.search("papel"), null);
		assertEquals(l.search("regua"), "regua");
		assertEquals(l.search("pincel"), null);
		assertEquals(l.search("caneta"), null);
		assertArrayEquals(l.toArray(), new String[]{"borracha", "regua", "lapis", "rafael"});

		l.insert("papel");
		assertEquals(l.search("rafael"), "rafael");
		assertEquals(l.search("lapis"), "lapis");
		assertEquals(l.search("borracha"), "borracha");
		assertEquals(l.search("papel"), "papel");
		assertEquals(l.search("regua"), "regua");
		assertEquals(l.search("pincel"), null);
		assertEquals(l.search("caneta"), null);
		assertArrayEquals(l.toArray(), new String[]{"borracha", "regua", "lapis", "rafael", "papel"});
	}
}