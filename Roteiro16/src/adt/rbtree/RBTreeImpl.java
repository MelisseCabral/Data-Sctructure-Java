package adt.rbtree;

import adt.avltree.AVLTreeImpl;
import adt.bt.BTNode;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends AVLTreeImpl<T> 
	implements RBTree<T> {

	private RBNode<T> uncleRight,uncleLeft, parent, grandParent;
	
	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}
	
	protected int blackHeight() {
		return blackHeight((RBNode<T>) root);
	}
 
	private int blackHeight(RBNode<T> node) {
		int result = 0;
 
		if (!node.isEmpty()) {
			result = Math.max(blackHeight((RBNode<T>) node.getLeft()),
					blackHeight((RBNode<T>) node.getRight()));
 
			if (node.getColour().equals(Colour.BLACK))
				result++;
		}
		return result;
	}

	protected boolean verifyProperties(){
		boolean resp = verifyNodesColour()
						&& verifyNILNodeColour()
						&& verifyRootColour()
						&& verifyChildrenOfRedNodes()
						&& verifyBlackHeight();
		
		return resp;
	}
	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed by the type Colour.
	 */
	private boolean verifyNodesColour(){
		return true; //already implemented
	}
	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour(){
		return ((RBNode<T>)root).getColour() == Colour.BLACK; //already implemented
	}
	
	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour(){
		return true; //already implemented
	}
	
	/**
	 * Verifies the property for all RED nodes: the children of a red node must be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes((RBNode<T>) root);
	}
 
	/**
	 * Auxiliary method to verify the property for all RED nodes: the children of a red node must be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		boolean result = true;
		if (!node.isEmpty()) {
			if (node.getColour() == Colour.RED) {
				result = ((RBNode<T>) node.getLeft()).getColour() == Colour.BLACK
						&& ((RBNode<T>) node.getRight()).getColour() == Colour.BLACK
						&& verifyChildrenOfRedNodes((RBNode<T>) node
								.getLeft())
						&& verifyChildrenOfRedNodes((RBNode<T>) node
								.getRight());
			}// if
		}// if
		return result;
	}
	
	
	/**
	 * Verifies the black-height property from the root. The method blackHeight returns an exception if the black heights are different.  
	 */
	private boolean verifyBlackHeight(){
		boolean result = false;
		if (!isEmpty())
			result = blackHeight((RBNode<T>) root.getRight()) == blackHeight((RBNode<T>) root
					.getLeft());
		return result;
	}
	
	@Override
	public void insert(T value) {
		if (value != null)
			recursiveInsert((RBNode<T>) root, value);
	}
 
	private void recursiveInsert(RBNode<T> node, T value) {
		if (node.isEmpty()) {
			node.setData(value);
			node.setLeft(new RBNode<T>());
			node.getLeft().setParent(node);
			node.setRight(new RBNode<T>());
			node.getRight().setParent(node);
			node.setColour(Colour.RED);
			fixUpCase1(node);
 
		} else if (node.getData().compareTo(value) > 0)
			recursiveInsert((RBNode<T>) node.getLeft(), value);
		else
			recursiveInsert((RBNode<T>) node.getRight(), value);
 
	}// recursiveInsert
	
	@Override
	public RBNode<T>[] extendedPreOrder() {
		RBNode<T>[] nodes = (RBNode<T>[]) new RBNode[size()];
		extendedPreOrder((RBNode<T>) getRoot(), nodes, 0);
		return nodes;
	}// extendedPreOrder
 
	private void extendedPreOrder(RBNode<T> node, RBNode<T>[] array,
			int index) {
		if (!node.isEmpty() && index < array.length) {
			array[index] = node;
			extendedPreOrder((RBNode<T>) node.getLeft(), array, index + 1);
			while (index < array.length) {
				if (array[index] == null)
					break;
				index++;
			}
			extendedPreOrder((RBNode<T>) node.getRight(), array, index);
		}// if
	}// auxExtendedPreOrder
	
	//FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(root))
			node.setColour(Colour.BLACK);
		else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (((RBNode<T>) node.getParent()).getColour()!=Colour.BLACK) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		parent = (RBNode<T>) node.getParent();
		grandParent = (RBNode<T>) parent.getParent();
		uncleRight = (RBNode<T>) grandParent.getRight();
		uncleLeft = (RBNode<T>) grandParent.getLeft();
		
		if (parent.getData().compareTo(grandParent.getData()) < 0
				&& (uncleRight).getColour() == Colour.RED) {
			(parent).setColour(Colour.BLACK);
			(uncleRight).setColour(Colour.BLACK); 
			(grandParent).setColour(Colour.RED);
			fixUpCase1(grandParent);
 
		} else if (parent.getData()
				.compareTo(grandParent.getData()) > 0 && (uncleLeft)
				.getColour() == Colour.RED) {
			(parent).setColour(Colour.BLACK);
			(uncleLeft).setColour(Colour.BLACK);
			(grandParent).setColour(Colour.RED);
			fixUpCase1(grandParent);
		} else
			fixUpCase4(node);
 
	}// fixUpCase3

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> help = node;		
		parent = (RBNode<T>) node.getParent();
		grandParent = (RBNode<T>) parent.getParent();
		uncleRight = (RBNode<T>) grandParent.getRight();
		uncleLeft = (RBNode<T>) grandParent.getLeft();
		
		if (parent.getData()
				.compareTo(grandParent.getData()) < 0
				&& node.getData().compareTo(parent.getData()) > 0) {
			leftRotation(parent);
			help = (RBNode<T>) node.getLeft();
		} else if (parent.getData()
				.compareTo(grandParent.getData()) > 0
				&& node.getData().compareTo(parent.getData()) < 0) {
			rightRotation(parent);
			help = (RBNode<T>) node.getRight();
		}// if-else if
 
		fixUpCase5(help);
	}// fixUpCase4

	protected void fixUpCase5(RBNode<T> node) {
		parent = (RBNode<T>) node.getParent();
		grandParent = (RBNode<T>) parent.getParent();
		uncleRight = (RBNode<T>) grandParent.getRight();
		uncleLeft = (RBNode<T>) grandParent.getLeft();
		
		parent.setColour(Colour.BLACK);
		grandParent.setColour(Colour.RED);
		if (node.getData().compareTo(parent.getData()) < 0) {
			(uncleRight).setColour(Colour.BLACK);
			rightRotation(grandParent);
		} else {
			(uncleLeft).setColour(Colour.BLACK);
			leftRotation(grandParent);
		}
	}// fixUpCase5
}
