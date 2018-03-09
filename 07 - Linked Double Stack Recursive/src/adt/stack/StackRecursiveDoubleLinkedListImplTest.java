package adt.stack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackRecursiveDoubleLinkedListImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Stack<Integer> s = new StackRecursiveDoubleLinkedListImpl<Integer>(3);
		try {
			assertTrue(s.isEmpty());
			assertFalse(s.isFull());
			s.push(5);
			assertFalse(s.isEmpty());
			assertFalse(s.isFull());
			s.push(4);
			assertFalse(s.isEmpty());
			assertFalse(s.isFull());
			s.push(9);
		} catch (Exception e) {
			fail("full???");
		}
		assertTrue(s.isFull());
		try {
			s.push(10);
			fail("not full???");
		} catch (Exception e) {
		}
		try {
			assertFalse(s.isEmpty());
			assertEquals(9, (int)s.top());
			assertEquals(9, (int)s.pop());
			assertFalse(s.isEmpty());
			assertFalse(s.isFull());
			assertEquals(4, (int)s.top());
			assertEquals(4, (int)s.pop());
			assertFalse(s.isEmpty());
			assertFalse(s.isFull());
			assertEquals(5, (int)s.top());
			assertEquals(5, (int)s.pop());
			assertFalse(s.isFull());
			assertTrue(s.isEmpty());
		} catch (Exception e) {
			fail("empty???");
		}
		try {
			assertEquals(null, s.top());
			s.pop();
			fail("not empty???");
		} catch (Exception e) {
		}
	}

}