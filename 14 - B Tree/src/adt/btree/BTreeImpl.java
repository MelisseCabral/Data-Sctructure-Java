package adt.btree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BTreeImpl<T extends Comparable<T>> implements
		BTree<T> {

	protected BNode<T> root;
	protected int order;
	protected final int t; 
	protected final int RIGHT_TO_LEFT = 0;
	protected final int LEFT_TO_RIGHT = 1;
	
	
	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
		this.t = order / 2;
	}
	
	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}
	
	private int height(BNode<T> node){
		int resp = -1;
		
		if(!node.isEmpty()){
			if(node.isLeaf())
				resp = 0;
			else
				resp = 1 + height(node.children.get(0));
		}
		
		return resp;
	}
	
	@Override
	public BNode<T>[] depthLeftOrder() {
		List<BNode<T>> lista = new ArrayList<BNode<T>>();
		lista = depthLeftOrder(lista, (BNode<T>) root);

		return (BNode<T>[]) lista.toArray((BNode<T>[]) Array.newInstance(BNode.class, lista.size()));
	}
	
	private List<BNode<T>> depthLeftOrder(List<BNode<T>> lista, BNode<T> node) {
		if (!node.isEmpty()) {
			lista.add((BNode<T>) node);
			List<BNode<T>> children = node.getChildren();
			
			for (BNode<T> child : children)
				depthLeftOrder(lista, child);
		}
		
		return lista;
	}

	@Override
	public int size() {
		int result = 0;
		BNode<T>[] nodes = depthLeftOrder();
		
		for (BNode<T> node : nodes)
			result += node.size();
		
		return result;
	}

	@Override
	public BNodePosition<T> search(T element) {
		return search(root, element);
	}

	private BNodePosition<T> search(BNode<T> node, T element) {
		int i = 0;
		BNodePosition<T> result;
		
		while (i < node.size() && element.compareTo(node.getElementAt(i)) > 0)
			i++;
		
		if (i < node.size() && element.equals(node.getElementAt(i)))
			result =  new BNodePosition<T>(node, i);
		else if (node.isLeaf())
			result =  new BNodePosition<T>();
		else
			result = search(node.getChildren().get(i), element);
		
		return result;
	}
	
	@Override
	public void insert(T element) {
		BNode<T> r = root;
		
		if (r.isFull()) {
			BNode<T> s = new BNode<T>(order);
			root = s;
			s.addChild(0, r);
			split(s, 0);
			insertNonFull(s, element);
		}
		else {
			insertNonFull(r, element);
		}
	}

	private void split(BNode<T> node, int i) {
		BNode<T> child = node.children.get(i);
		BNode<T> z = splitNode(child);
		
		node.addElement(child.getElementAt(child.size() - 1));
		child.removeElement(child.size() - 1);
		
		node.addChild(i + 1, z);
	}
	
	private BNode<T> splitNode(BNode<T> node) {
		BNode<T> newNode = new BNode<T>(order);
		
		List<T> nodeElements = node.getElements();
		int lenElements = nodeElements.size();
		int midElements = (lenElements / 2) + 1;
		
		List<BNode<T>> nodeChildren = node.getChildren();
		int lenChildren = nodeChildren.size();
		int midChildren = lenChildren / 2;
		
		for (int i = midElements ; i < lenElements ; i++)
			newNode.addElement(nodeElements.get(i));
		for (int i = lenElements - 1 ; i >= midElements ; i--)
			node.removeElement(i);
			
		for (int i = midChildren, j = 0; i < lenChildren ; i++, j++)
			newNode.addChild(j, nodeChildren.get(i));
		for (int i = lenChildren - 1 ; i >= midChildren ; i--)
			node.removeChild(nodeChildren.get(i));
		
		return newNode;
	}
	
	private void insertNonFull(BNode<T> node, T element) {
		int i = node.size() - 1;
		
		if (node.isLeaf()) {
			node.addElement(element);
		}
		else {
			while (i >= 0 && element.compareTo(node.getElementAt(i)) < 0)
				i--;
			i++;
			
			if (node.getChildren().get(i).isFull()) {
				split(node, i);
				if (element.compareTo(node.getElementAt(i)) > 0)
					i++;
			}
			
			insertNonFull(node.getChildren().get(i), element);
		}
	}
	
	@Override
	public BNode<T> maximum(BNode<T> node) {
		BNode<T> max = null;
		
		if (!isEmpty()){
			max = node;
			while (max.getChildren().size() != 0)
				max = max.getChildren().getLast();
		}
		
		return max;
	}
	
	@Override
	public BNode<T> minimum(BNode<T> node) {
		BNode<T> min = null;
		
		if (!isEmpty()) {
			min = node;
			while (min.getChildren().size() != 0)
				min = min.getChildren().getFirst();
		}
		
		return min;	
	}
	
	@Override
	public void remove(T element) {
		remove(root, element);
	}

	public void remove(BNode<T> node, T element) {
		if (node.isLeaf()) {	// Case 1: element is in node and node is leaf
			node.removeElement(element);
		}
		else {
			if (node.getElements().contains(element)) { // Case 2: element is in node and node is an internal node
				int pos = node.getElements().indexOf(element);
				BNode<T> leftChild = node.getChildren().get(pos);
				BNode<T> rightChild = node.getChildren().get(pos + 1);
				
				if (leftChild.getElements().size() >= t) { // Case 2a: leftChild of element has as least t elements
					BNode<T> predecessorNode = maximum(leftChild);
					T predecessorElement = predecessorNode.getElements().getLast();
					remove(predecessorNode, predecessorElement);
					node.removeElement(pos);
					node.addElement(predecessorElement);
				}
				else if (rightChild.getElements().size() >= t) {  // Case 2b: rightChild of element has as least t elements
					BNode<T> sucessorNode = minimum(rightChild);
					T sucessorElement = sucessorNode.getElements().getFirst();
					remove(sucessorNode, sucessorElement);
					node.removeElement(pos);
					node.addElement(sucessorElement);
				}
				else { // Case 2c: leftChild and rightChild have only t - 1 elements
					merge(leftChild, rightChild, element, RIGHT_TO_LEFT);
					node.removeElement(pos);
					node.removeChild(rightChild);				
					remove(leftChild, element);
					if (root.size() == 0)
						root = leftChild;
				}
			}
			else { // Case 3: element is not in node and node is an internal node
				int childIndex = subTreeIndex(node, element);
				BNode<T> child = node.getChildren().get(childIndex);	
				
				if (child.size() == t - 1) {
					BNode<T> leftSibling = (childIndex - 1 >= 0) ? node.getChildren().get(childIndex - 1) : null;
					BNode<T> rightSibling = (childIndex  + 1 <= node.size()) ? node.getChildren().get(childIndex + 1) : null;
				
					if (leftSibling != null && leftSibling.size() >= t) { // Case 3a left: leftSibling has at least t elements
						child.addElement(node.getElementAt(childIndex - 1));
						if (!child.isLeaf())
							child.addChild(0, leftSibling.getChildren().getLast());
						
						node.removeElement(childIndex - 1);
						node.addElement(leftSibling.getElements().getLast());
						
						leftSibling.removeElement(leftSibling.size() - 1);
						if (!leftSibling.isLeaf())
							leftSibling.removeChild(leftSibling.getChildren().getLast());
					}
					else if (rightSibling != null && rightSibling.size() >= t) { // Case 3a right: rightSibling has at least t elements
						child.addElement(node.getElementAt(childIndex));
						if (!child.isLeaf())
							child.addChild(child.size(), rightSibling.getChildren().getFirst());
						
						node.removeElement(childIndex);
						node.addElement(rightSibling.getElements().getFirst());
						
						rightSibling.removeElement(0);
						if (!rightSibling.isLeaf())
							rightSibling.removeChild(rightSibling.getChildren().getFirst());
					}
					else { // Case 3b: node, leftSinbling and rightSinbling have t - 1 elements
						if (leftSibling != null) {
							merge(child, leftSibling, node.getElementAt(childIndex - 1), LEFT_TO_RIGHT);
							node.removeElement(childIndex - 1);
							node.removeChild(leftSibling);				
							if (root.size() == 0)
								root = child;
						}
						else if (rightSibling != null) {
							merge(child, rightSibling, node.getElementAt(childIndex), RIGHT_TO_LEFT);
							node.removeElement(childIndex);
							node.removeChild(rightSibling);				
							if (root.size() == 0)
								root = child;
						}
					}
					
				}
				
				remove(child, element);
			}
		}
	}
	
	private int subTreeIndex(BNode<T> node, T element) {
        for (int i = 0 ; i < node.size() ; i++)                          
            if (element.compareTo(node.getElementAt(i)) < 0)
                    return i;
        return node.size();		
	}
	
	private void merge(BNode<T> left, BNode<T> right, T element, int direction) {
		if (direction == RIGHT_TO_LEFT) {
			left.addElement(element);			
			for (int i = 0 ; i < right.size() ; i++) {
				if (!right.isLeaf())
					left.addChild(left.size(), right.getChildren().get(i));
				left.addElement(right.getElementAt(i));
			}	
			if (!right.isLeaf())
				left.addChild(left.size(), right.getChildren().getLast());
		}
		else {
			right.addElement(element);
			for (int i = right.size() - 1 ; i >= 0 ; i--) {
				if (!left.isLeaf())
					left.addChild(0, right.getChildren().get(i + 1));
				left.addElement(right.getElementAt(i));
			}
			if (!left.isLeaf())
				left.addChild(0, right.getChildren().getFirst());
		}
	}
	
}
