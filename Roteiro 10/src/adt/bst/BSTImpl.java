package adt.bst;

import java.util.ArrayList;
import java.util.List;
import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected  BSTNode<T> root;
	
	
	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot(){
		return this.root;
	}
	
	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return heightRecursive(root);
	}

	private int heightRecursive(BTNode<T> node) {
		int elementosDireita = 0, elementosEsquerda = 0;
		
		if (node.isEmpty()) return -1;

		else {
			elementosEsquerda = heightRecursive(node.getLeft()) + 1;
			elementosDireita = heightRecursive(node.getRight()) + 1;
		}
		
		if (elementosDireita > elementosEsquerda) return elementosDireita;
		else return elementosEsquerda;
		
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> aux = root;
		while (!aux.isEmpty() && !element.equals(aux.getData())) {
			if (element.compareTo(aux.getData()) < 0) {
				aux = (BSTNode<T>) aux.getLeft();
			} else {
				aux = (BSTNode<T>) aux.getRight();
			}
		}
		
		return aux;
	}

	@Override
	public BSTNode<T> maximum() {
		if (root.isEmpty()){
			return null;
		}
		
		else{
			BSTNode<T> aux = root;
			while(!aux.getRight().isEmpty()) aux = (BSTNode<T>) aux.getRight();
			return aux;
		}
	}

	private BSTNode<T> nodeMaximum(BSTNode<T> node) {
		if (node.isEmpty()){
			return null;
		}
		
		else{
			BSTNode<T> aux = node;
			while(!aux.getRight().isEmpty()) aux = (BSTNode<T>) aux.getRight();
			return aux;
		}
	}
	
	@Override
	public BSTNode<T> minimum() {
		if (root.isEmpty()){
			return null;
		}
		
		else{
			BSTNode<T> aux = root;
			while(!aux.getLeft().isEmpty()) aux = (BSTNode<T>) aux.getLeft();
			return aux;
		}
	}

	private BSTNode<T> nodeMinimum(BSTNode<T> node) {
		if (node.isEmpty()){
			return null;
		}
		
		else{
			BSTNode<T> aux = node;
			while(!aux.getLeft().isEmpty()) aux = (BSTNode<T>) aux.getLeft();
			return aux;
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> aux = search(element);
		
		if (aux.isEmpty()) {
			return null;
		}

		if (!aux.getRight().isEmpty()) {
			return nodeMinimum((BSTNode<T>) aux.getRight());
		}

		BSTNode<T> y = (BSTNode<T>) aux.getParent();
		
		if (y == null) {
			return null;
		}

		while (y != null && aux.equals(y.getRight())) {
			aux = y;
			y = (BSTNode<T>) y.getParent();
		}
		return y;
		
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> aux = search(element);
		
		if (aux.isEmpty()) {
			return null;
		}

		if (!aux.getLeft().isEmpty()) {
			return nodeMaximum((BSTNode<T>) aux.getLeft());
		}

		BSTNode<T> y = (BSTNode<T>) aux.getParent();
		
		if (y == null) {
			return null;
		}

		while (y != null && aux.equals(y.getLeft())) {
			aux = y;
			y = (BSTNode<T>) y.getParent();
		}
		return y;
	}

	@Override
	public T[] preOrder() {
		List<T> array = new ArrayList<T>();
		Comparable[] a = new Comparable[size()];
		preOrderRecursive(root, array);
		return (T[]) array.toArray(a);
	}

	private void preOrderRecursive(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			array.add(node.getData());
			preOrderRecursive((BSTNode<T>) node.getLeft(), array);
			preOrderRecursive((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] order() {
		List<T> array = new ArrayList<T>();
		Comparable[] a = new Comparable[size()];
		orderRecursive(root, array);
		return (T[]) array.toArray(a);
	}
	
	private void orderRecursive(BSTNode<T> node, List<T> array) {

		if (!node.isEmpty()) {
			orderRecursive((BSTNode<T>) node.getLeft(), array);
			array.add(node.getData());
			orderRecursive((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] postOrder() {
		List<T> array = new ArrayList<T>();
		Comparable[] a = new Comparable[size()];
		postOrderRecursive(root, array);
		return (T[]) array.toArray(a);
	}
	
	private void postOrderRecursive(BSTNode<T> node, List<T> array) {

		if (!node.isEmpty()) {
			postOrderRecursive((BSTNode<T>) node.getLeft(), array);
			postOrderRecursive((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
		}
	}
	
	@Override
	public void insert(T element) {
		BSTNode<T> aux = root;
	
		while (!aux.isEmpty()){
			if (element.compareTo(aux.getData()) < 0) {
				aux = (BSTNode<T>) aux.getLeft();
			}
			else if (element.compareTo(aux.getData()) > 0) {
				aux = (BSTNode<T>) aux.getRight();
			}
		}
		
		aux.setData(element);
		aux.setLeft(new BSTNode<T>());
		aux.setRight(new BSTNode<T>());
		aux.getLeft().setParent(aux);
		aux.getRight().setParent(aux);
	}
	
	private void recursiveRemove(BSTNode<T> node) {
		BSTNode<T> tmp = node;
		
		//No nao eh nulo
		if (tmp != null) {
			
			//No nao eh uma lista vazia
			if (!tmp.isEmpty()) {
				
				//No eh uma folha
				if (tmp.getLeft().isEmpty() && tmp.getRight().isEmpty()) {				
					tmp = new BSTNode<T>();
				} 
				
				//No tem filhos
				else if (tmp.getLeft().isEmpty() || tmp.getRight().isEmpty()) {
					
					//No nao eh raiz.
					if (tmp.getParent() != null) {
						
						//No eh o filho da esquerda
						if (tmp.getParent().getLeft().equals(tmp)) {
							
							//Filho da esquerda nao eh vazio
							if (!tmp.getLeft().isEmpty()) {
								tmp.getParent().setLeft(tmp.getLeft());
							} 
							
							//Filho da esquerda eh vazio
							else {
								tmp.getParent().setLeft(tmp.getRight());
							}
						} 
						
						//No eh o filho da direita
						else {
							
							//Filho da direita nao eh vazio
							if (!tmp.getLeft().isEmpty()) {
								tmp.getParent().setRight(tmp.getLeft());
							} 
							
							//Filho da direita eh vazio
							else {
								tmp.getParent().setRight(tmp.getRight());							}
							}
						
					} 
					
					//Eh raiz e tem filho soh em um dos lados
					else {
						
						//Filhos a esquerda
						if (!tmp.getLeft().isEmpty() && tmp.getRight().isEmpty()) {
							tmp = (BSTNode<T>) tmp.getLeft();
						} 
						
						//Filhos a direita
						else if (tmp.getLeft().isEmpty() && !tmp.getRight().isEmpty()) {
							tmp = (BSTNode<T>)tmp.getRight();
						}
						
						tmp.setParent(null);
						
					}
					
				} 
				
				//Caso recursivo
				else {
					BSTNode<T> sucessor = sucessor(tmp.getData());
					tmp.setData(sucessor.getData());
					recursiveRemove(sucessor);
				}
			}
		}
		
		node.setLeft(tmp.getLeft());
		node.setParent(tmp.getParent());
		node.setRight(tmp.getRight());
		node.setData(tmp.getData());

	}
	
	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		recursiveRemove(node);
	}

	@Override
	public int size() {
		return size(root);
	}
	
	private int size(BSTNode<T> node){
		int result = 0;
		if(!node.isEmpty()){
			result = 1 + size((BSTNode<T>)node.getLeft()) + size((BSTNode<T>)node.getRight());
		}
		return result;
	}

}
