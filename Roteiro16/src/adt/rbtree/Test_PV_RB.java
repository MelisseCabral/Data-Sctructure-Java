package adt.rbtree;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import adt.rbtree.RBNode.Colour;

public class Test_PV_RB {
	RBTreeImpl<Integer> tree;
	
	@Test
	public void test1(){
		tree = new RBTreeImpl<>();
		try {
			Assert.assertEquals(0, tree.blackHeight());
		} catch (Exception e) {
			Assert.fail();;
		}
		tree.insert(10);
		try {
			Assert.assertEquals(1, tree.blackHeight());
		} catch (Exception e) {
			Assert.fail();;
		}
		tree.insert(15);
		try {
			Assert.assertEquals(1, tree.blackHeight());
		} catch (Exception e) {
			Assert.fail();;
		}
		tree.insert(5);
		try {
			Assert.assertEquals(1, tree.blackHeight());
		} catch (Exception e) {
			Assert.fail();;
		}
		Assert.assertTrue(tree.verifyProperties());
		
		RBNode<Integer> ten = (RBNode<Integer>) tree.search(10);
		RBNode<Integer> fifteen = (RBNode<Integer>) tree.search(15);
		RBNode<Integer> five = (RBNode<Integer>) tree.search(5);
		Assert.assertTrue(ten.getColour() == Colour.BLACK);
		Assert.assertTrue(fifteen.getColour() == Colour.RED);
		Assert.assertTrue(five.getColour() == Colour.RED);
		
		tree.insert(3);
		RBNode<Integer> three = (RBNode<Integer>) tree.search(3);
		Assert.assertTrue(ten.getColour() == Colour.BLACK);
		Assert.assertTrue(fifteen.getColour() == Colour.BLACK);
		Assert.assertTrue(five.getColour() == Colour.BLACK);
		Assert.assertTrue(three.getColour() == Colour.RED);
		try {
			Assert.assertEquals(2, tree.blackHeight());
		} catch (Exception e) {
			Assert.fail();;
		}
		tree.insert(4);
		RBNode<Integer> four = (RBNode<Integer>) tree.search(4);
		
		Assert.assertTrue(ten.getColour() == Colour.BLACK);
		Assert.assertTrue(fifteen.getColour() == Colour.BLACK);
		Assert.assertTrue(five.getColour() == Colour.RED);
		Assert.assertTrue(three.getColour() == Colour.RED);
		Assert.assertTrue(four.getColour() == Colour.BLACK);
		
		Assert.assertTrue(tree.verifyProperties());
		try {
			Assert.assertEquals(2, tree.blackHeight());
		} catch (Exception e) {
			Assert.fail();;
		}
		RBNode<Integer> nil = new RBNode<>();
		Assert.assertEquals(null, ten.getParent());
		Assert.assertEquals(new Integer(10), ten.getData());
		Assert.assertEquals(four, ten.getLeft());
		Assert.assertEquals(fifteen, ten.getRight());
		
		Assert.assertEquals(ten, fifteen.getParent());
		Assert.assertEquals(new Integer(15), fifteen.getData());
		Assert.assertEquals(nil, fifteen.getLeft());
		Assert.assertEquals(nil, fifteen.getRight());
		
		Assert.assertEquals(ten, four.getParent());
		Assert.assertEquals(new Integer(4), four.getData());
		Assert.assertEquals(three, four.getLeft());
		Assert.assertEquals(five, four.getRight());
		
		Assert.assertEquals(four, three.getParent());
		Assert.assertEquals(new Integer(3), three.getData());
		Assert.assertEquals(nil, three.getLeft());
		Assert.assertEquals(nil, three.getRight());
		
		Assert.assertEquals(four, five.getParent());
		Assert.assertEquals(new Integer(5), five.getData());
		Assert.assertEquals(nil, five.getLeft());
		Assert.assertEquals(nil, five.getRight());
		
		Assert.assertEquals("[(10,B), (4,B), (3,R), (5,R), (15,B)]", Arrays.toString(tree.extendedPreOrder()));
		
	}
}