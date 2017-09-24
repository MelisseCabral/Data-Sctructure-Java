package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	private BSTNode<T> NIL = null;
	@Override
	public void insert(T element) {
		if (element != null && search(element).isEmpty())
			insertRecursive(root, element);
	}// insert

	private void insertRecursive(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(NIL);
			node.getLeft().setParent(node);
			node.setRight(NIL);
			node.getRight().setParent(node);
		} else {
			if (element.compareTo(node.getData()) < 0) {
				insertRecursive((BSTNode<T>) node.getLeft(), element);
			} else {
				insertRecursive((BSTNode<T>) node.getRight(), element);
			}// if-else if
			rebalance(node);
		}// if-else
	}// insertRecursive

	private boolean isLeaf(BSTNode<T> node) {
		boolean result = false;
		if (!hasLeftChild(node) && !hasRightChild(node))
			result = true;
		return result;
	}// isLeaf

	private boolean hasRightChild(BSTNode<T> node) {
		boolean result = false;
		if (!node.getRight().isEmpty())
			result = true;
		return result;
	}// hasRightChild

	private boolean hasOneChild(BSTNode<T> node) {
		return (!hasLeftChild(node) || hasRightChild(node))
				|| (hasLeftChild(node) || !hasRightChild(node));
	}// hasOneChild
	
	private boolean hasLeftChild(BSTNode<T> node) {
		boolean result = false;
		if (!node.getLeft().isEmpty())
			result = true;
		return result;
	}// hasLeftChild

	@Override
	public void remove(T element) {
		BSTNode<T> found = search(element);
		recursiveRemove(found);
	}// remove

	private void recursiveRemove(BSTNode<T> node) {
		if (!node.isEmpty()) {

			 if (hasOneChild(node)) {
				if (!node.equals(root)) {
					if (node.getData().compareTo(node.getParent().getData()) < 0) {
						if (hasLeftChild(node)) {
							node.getLeft().setParent(node.getParent());
							node.getLeft().getParent().setLeft(node.getLeft());

						} else {
							node.getRight().setParent(node.getParent());
							node.getRight().getParent()
									.setLeft(node.getRight());
						}// if-else
					} else {
						if (hasLeftChild(node)) {

							node.getLeft().setParent(node.getParent());
							node.getLeft().getParent().setRight(node.getLeft());
						} else {
							node.getRight().setParent(node.getParent());
							node.getRight().getParent()
									.setRight(node.getRight());
						}// if-else
					}// if-else
				} else {
					if (hasLeftChild(node)) {
						root = (BSTNode<T>) node.getLeft();

					} else {
						root = (BSTNode<T>) node.getRight();

					}// if-else
					root.setParent(null);

				}// if-else
				rebalanceUp(node);
			} 
			 else if (isLeaf(node)) {
					node.setData(null);
					node.setLeft(null);
					node.setRight(null);
					rebalanceUp(node);
				} 
			 else {
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				recursiveRemove(sucessor);
			}// if-else if-else

		}// if
	}// recursiveRemove

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node){
		if (!node.isEmpty())
			return height(node.getLeft())- height(node.getRight());
		return 0;
	}

	private int height(BTNode<T> left) {
		// TODO Auto-generated method stub
		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int isBalanceResult = calculateBalance(node);
		if (isBalanceResult > 1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) == -1)
				leftRotation((BSTNode<T>) node.getLeft());
			rightRotation(node);
		} else if (isBalanceResult < -1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) == 1)
				rightRotation((BSTNode<T>) node.getRight());
			leftRotation(node);

		}// if-else if

	}// rebalance

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}// while

	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		node.setRight(pivot.getLeft());
		node.getRight().setParent(node);
		pivot.setLeft(node);
		pivot.setParent(node.getParent());
		node.setParent(pivot);

		if (node.equals(root)) {
			root = pivot;
		} else {
			if (pivot.getData().compareTo(pivot.getParent().getData()) < 0) {
				pivot.getParent().setLeft(pivot);
			} else {
				pivot.getParent().setRight(pivot);
			}// if-else
		}// if-else
	}// leftRotation

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		node.setLeft(pivot.getRight());
		node.getLeft().setParent(node);
		pivot.setRight(node);
		pivot.setParent(node.getParent());
		node.setParent(pivot);

		if (node.equals(root)) {
			root = pivot;
		} else {
			if (pivot.getData().compareTo(pivot.getParent().getData()) > 0) {
				pivot.getParent().setRight(pivot);
			} else {
				pivot.getParent().setLeft(pivot);
			}// if-else
		}// if-else
	}// rightRotation

}