package test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import adt.linkedList.LinkedList;
import adt.linkedList.SingleLinkedListImpl;

public class TesteBasic {

	@Test
	public void testBase(){
		LinkedList<Integer> lList = new SingleLinkedListImpl<Integer>();
		Assert.assertTrue(lList.isEmpty());
		Assert.assertEquals(0, lList.size());
		lList.insert(1);
	
		Assert.assertFalse(lList.isEmpty());
		Assert.assertEquals(1, lList.size());
		lList.insert(1);
		lList.insert(1);
		lList.insert(3);
		lList.insert(4);
		lList.insert(2);
		lList.remove(1);
		lList.remove(2);
		lList.remove(2);
		lList.remove(3);
		lList.remove(1);
		lList.remove(1);
		lList.remove(1);
		lList.remove(1);
		Assert.assertEquals(1, lList.size());
		Assert.assertEquals(new Integer (4), lList.search(4));
		lList.remove(4);
		Assert.assertEquals(null, lList.search(4));

		Assert.assertTrue(lList.isEmpty());
		Assert.assertEquals(0, lList.size());
		lList.remove(4);
		lList.insert(10);
		Assert.assertFalse(lList.isEmpty());
		Assert.assertEquals(1, lList.size());
		Assert.assertEquals(new Integer (10), lList.search(10));

	}
}