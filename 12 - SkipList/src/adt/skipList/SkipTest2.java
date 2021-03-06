package adt.skipList;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SkipTest2 {

	private SkipListImpl<Integer> skip;
	private int maxLevel = 10;
	private SkipNode<Integer> root = new SkipNode(Integer.MIN_VALUE, maxLevel, new Integer(Integer.MIN_VALUE));
	private SkipNode<Integer> NIL = new SkipNode(Integer.MAX_VALUE, maxLevel, new Integer(Integer.MAX_VALUE));
	@Before
	public void before() {
		skip = new SkipListImpl<>(maxLevel);
	}
	
	@Test
	public void testBasicsAndBorderCases() {
		
		SkipNode<Integer>[] emptyArray = new SkipNode[] {root, NIL};
		Assert.assertTrue(Arrays.equals(emptyArray, skip.toArray()));
		
		Assert.assertEquals(0, skip.size());
		Assert.assertEquals(0, skip.height());
		
		System.out.println("EMPTY SKIPLIST: " + Arrays.toString(skip.toArray()));
		
		Assert.assertEquals(null, skip.search(1));
		Assert.assertEquals(null, skip.search(10000000));
		
		Assert.assertEquals(NIL, skip.search(Integer.MAX_VALUE));
		System.out.println(skip.search(Integer.MIN_VALUE));
		Assert.assertEquals(Integer.MIN_VALUE, skip.search(Integer.MIN_VALUE).key);
		
		skip.insert(1, 1, 1);
		
		Assert.assertEquals(1, skip.search(1).key);
		Assert.assertTrue(skip.search(1).satteliteData.equals(1));
		Assert.assertEquals(1, skip.height());
		Assert.assertEquals(1, skip.size());
		System.out.println("ADDED 1 TO SKIPLIST: " + Arrays.toString(skip.toArray()));
		Assert.assertTrue(Arrays.equals(new SkipNode[] { root, new SkipNode(1,1,1), NIL}, skip.toArray()));

		skip.insert(2, 2, 2);
		
		Assert.assertEquals(2, skip.search(2).key);
		Assert.assertTrue(skip.search(2).satteliteData.equals(2));
		Assert.assertEquals(2, skip.height());
		Assert.assertEquals(2, skip.size());
		System.out.println("ADDED 2 TO SKIPLIST: " + Arrays.toString(skip.toArray()));
		
		skip.insert(0, 0, 1);
		Assert.assertEquals(0, skip.search(0).key);
		Assert.assertTrue(skip.search(0).satteliteData.equals(0));
		Assert.assertEquals(2, skip.height());
		Assert.assertEquals(3, skip.size());
		System.out.println("ADDED 0 TO SKIPLIST: " + Arrays.toString(skip.toArray()));
		
		skip.insert(2, 3, 1);
		Assert.assertEquals(2, skip.search(2).key);
		Assert.assertEquals(3, skip.size());
		Assert.assertTrue(skip.search(2).satteliteData.equals(3));
		System.out.println("ADDED 2 AGAIN TO SKIPLIST: " + Arrays.toString(skip.toArray()));

		skip.remove(2);
		Assert.assertEquals(null, skip.search(2));
		Assert.assertEquals(2, skip.size());
		Assert.assertEquals(1, skip.height());
		System.out.println("ADDED 2 AGAIN TO SKIPLIST: " + Arrays.toString(skip.toArray()));
		
		
		skip.remove(Integer.MIN_VALUE);
		Assert.assertEquals(2, skip.size());
		skip.remove(Integer.MAX_VALUE);
		Assert.assertEquals(2, skip.size());
		
		skip.remove(4);
		Assert.assertEquals(2, skip.size());
		
		skip.insert(10, 10, 100);
		Assert.assertEquals(2, skip.size());
				
		skip.insert(10,  10, -1);
		Assert.assertEquals(2, skip.size());
		
		
	}
	
	@Test
	public void testInsert() {
	
		skip.insert(3, 3, 3);
		Assert.assertEquals(3, skip.search(3).key);
		Assert.assertTrue(skip.search(3).satteliteData.equals(3));
		Assert.assertEquals(1, skip.size());
		Assert.assertEquals(3, skip.height());
		System.out.println("ADDED 3 TO SKIPLIST: " + Arrays.toString(skip.toArray()));
		
		
	}
	
	

}
