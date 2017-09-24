package adt.avltree;

import java.util.ArrayList;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	@Override
	protected void insert(BTNode<T> node, T element) {
		super.insert(node, element);
		rebalance((BSTNode<T>) node);
	}

	@Override
	protected void remove(BTNode<T> node) {
		super.remove(node);
		rebalanceUp((BSTNode<T>) node);
	}
	
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null)
			return height(node.getLeft()) - height(node.getRight());
		return 0;
	}
	
	private int height(BTNode<T> node) {
		if (node == null || node.isEmpty()) return -1;
		return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node == null)
			return;

		int balance = calculateBalance(node);
		if (balance > 1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) < 0)
				leftRotation((BSTNode<T>) node.getLeft());
			rightRotation(node);
		} else if (balance < -1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) > 0)
				rightRotation((BSTNode<T>) node.getRight());
			leftRotation(node);
		}
		
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node == null)
			return;

		while(node.getParent() != null) {
			node = (BSTNode<T>) node.getParent();
			rebalance(node);
		}
	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
		if (node == null)
			return;

		BSTNode<T> aux = (BSTNode<T>) node.getRight();
		node.setRight(aux.getLeft());
		aux.getLeft().setParent(node);
		aux.setLeft(node);
		aux.setParent(node.getParent());
		node.setParent(aux);
		if (node != root) {
			if (aux.getParent().getLeft() == node)
				aux.getParent().setLeft(aux);
			else
				aux.getParent().setRight(aux);
		} else
			setRoot(aux);
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
		if (node == null)
			return;

		BSTNode<T> aux = (BSTNode<T>) node.getLeft();
		node.setLeft(aux.getRight());
		aux.getRight().setParent(node);
		aux.setRight(node);
		aux.setParent(node.getParent());
		node.setParent(aux);
		if (node != root) {
			if (aux.getParent().getLeft() == node)
				aux.getParent().setLeft(aux);
			else
				aux.getParent().setRight(aux);
		} else
			setRoot(aux);
	}

// procurar min - no mais a esquerda
//	min = raiz;
//	while (min.getLeft() != null){
//			min = min.getLeft();}
//
//	
// procurar max - no mais a direita
//	max = raiz;
//	while (max.getRight() != null){
//			max = max.getRight();}
}