package adt.bst;

import java.util.ArrayList;
import java.util.List;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	public void setRoot(BSTNode<T> newroot) {
		if (newroot == null) return;
		root = newroot;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return recursiveHeight(getRoot());
	}

	protected int recursiveHeight(BTNode<T> node) {
		if (node.isEmpty())
			return -1;
		return max(recursiveHeight(node.getLeft()), recursiveHeight(node.getRight())) + 1;
	}

	private int max(int a, int b) {
		if (a > b)
			return a;
		return b;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element == null) return new BSTNode<T>();
		
		return recursiveSearch(element, root);
	}

	private BSTNode<T> recursiveSearch(T element, BTNode<T> node) {
		if (node.isEmpty() || node.getData().compareTo(element) == 0)
			return (BSTNode<T>) node;
		else if (element.compareTo(node.getData()) < 0)
			return recursiveSearch(element, node.getLeft());
		else
			return recursiveSearch(element, node.getRight());
	}

	@Override
	public void insert(T element) {
		if (element == null) return;
		
		insert(root, element);
	}
	
	protected void insert(BTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			BSTNode<T> son = new BSTNode<>();
			son.setParent(node);
			node.setLeft(son);
			son = new BSTNode<>();
			son.setParent(node);
			node.setRight(son);
		}
		else if (node.getData().compareTo(element) < 0)
			insert(node.getRight(), element);
		else if (node.getData().compareTo(element) > 0)
			insert(node.getLeft(), element);
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty()) return null;

		BTNode<T> temp = root;

		while (!temp.getRight().isEmpty())
			temp = temp.getRight();

		return (BSTNode<T>) temp;
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty()) return null;

		BTNode<T> temp = root;

		while (!temp.getLeft().isEmpty())
			temp = temp.getLeft();

		return (BSTNode<T>) temp;
	}

	protected BSTNode<T> minimum(BTNode<T> node) {
		if (node == null || node.isEmpty()) return null;

		while (!node.getLeft().isEmpty())
			node = node.getLeft();

		return (BSTNode<T>) node;
	}

	protected BSTNode<T> maximum(BTNode<T> node) {
		if (node == null || node.isEmpty()) return null;

		while (!node.getRight().isEmpty())
			node = node.getRight();

		return (BSTNode<T>) node;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if (element == null || isEmpty())
			return null;

		BTNode<T> temp = search(element);

		if (temp.isEmpty())
			return null;

		if (!temp.getRight().isEmpty())
			return minimum(temp.getRight());

		BTNode<T> p = temp.getParent();
		while (p != null && !p.isEmpty() && temp.equals(p.getRight())) {
			temp = p;
			p = p.getParent();
		}

		if (p == null || p.isEmpty()) return null;
		return (BSTNode<T>) p;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if (element == null || isEmpty())
			return null;

		BTNode<T> temp = search(element);

		if (temp.isEmpty())
			return null;

		if (!temp.getLeft().isEmpty())
			return maximum(temp.getLeft());

		BTNode<T> p = temp.getParent();
		while (p != null && !p.isEmpty() && temp.equals(p.getLeft())) {
			temp = p;
			p = p.getParent();
		}

		if (p == null || p.isEmpty()) return null;
		return (BSTNode<T>) p;
	}

	@Override
	public void remove(T element) {
		if (element == null) return;
		
		remove(search(element));
	}
	
	protected void remove(BTNode<T> node) {
		if (node.isEmpty()) return;
		
		if(node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.setData(null);
			node.setLeft(null);
			node.setRight(null);
		}
		else if(node.getLeft().isEmpty() ^ node.getRight().isEmpty()) {
			BTNode<T> aux = node.getLeft().isEmpty()? node.getRight() : node.getLeft();
			if (node == root) {
				setRoot((BSTNode<T>) aux);
				aux.setParent(null);
			}
			else {
				aux.setParent(node.getParent());
				if (node.getParent().getLeft() == node) node.getParent().setLeft(aux);
				else node.getParent().setRight(aux);
			}
		}
		else {
			BTNode<T> next = sucessor(node.getData());
			node.setData(next.getData());
			remove(next);
		}
	}
	
	@Override
	public T[] preOrder() {
		List<Comparable> list = new ArrayList<>();

		recursivePreOrder(getRoot(), list);

		return list.toArray((T[]) new Comparable[size()]);
	}

	private void recursivePreOrder(BTNode<T> node, List<Comparable> list) {
		if (!node.isEmpty()) {
			list.add(node.getData());
			recursivePreOrder(node.getLeft(), list);
			recursivePreOrder(node.getRight(), list);
		}
	}

	@Override
	public T[] order() {
		List<Comparable> list = new ArrayList<>();

		recursiveOrder(getRoot(), list);

		return list.toArray((T[]) new Comparable[size()]);
	}

	private void recursiveOrder(BTNode<T> node, List<Comparable> list) {
		if (!node.isEmpty()) {
			recursiveOrder(node.getLeft(), list);
			list.add(node.getData());
			recursiveOrder(node.getRight(), list);
		}
	}

	@Override
	public T[] postOrder() {
		List<Comparable> list = new ArrayList<>();

		recursivePostOrder(getRoot(), list);

		return list.toArray((T[]) new Comparable[size()]);
	}

	private void recursivePostOrder(BTNode<T> node, List<Comparable> list) {
		if (!node.isEmpty()) {
			recursivePostOrder(node.getLeft(), list);
			recursivePostOrder(node.getRight(), list);
			list.add(node.getData());
		}
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(BTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			result = 1 + size(node.getLeft()) + size(node.getRight());
		}
		return result;
	}
	
	/**
	 * Representa a arvore em forma de array, sendo que a ordem dos elementos eh
	 * feita de acordo com o nivel da arvore, sempre da esquerda para direita.
	 * 
	 * */
	protected List<BSTNode<T>> separaPorNivel() {
    	List<BSTNode<T>> aux = new ArrayList<>();
    	int atual = 0;
    	aux.add(getRoot());
     
    	while (atual != size()) {
    		if (!aux.get(atual).getLeft().isEmpty())
    			aux.add((BSTNode<T>) aux.get(atual).getLeft());
    		if (!aux.get(atual).getRight().isEmpty())
    			aux.add((BSTNode<T>) aux.get(atual).getRight());
    		atual++;
    	}
     
    	return aux;
    }
}
