package adt.avltree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

public class AVLTreeImpl<T extends Comparable<T>> 
    extends BSTImpl<T> implements AVLTree<T> {

	@Override
	public void insert(T element) {
		insertRecursive(getRoot(), element);
		root = resetRoot(root);
	}
	
	private void insertRecursive(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} 
		
		else {
			if (node.getData().compareTo(element) > 0){
				insertRecursive((BSTNode<T>)node.getLeft(), element);
			}
			else{
				insertRecursive((BSTNode<T>) node.getRight(), element);
			}
			rebalance(node);
		}
	}
	
	@Override
	public void remove(T element) {
		BSTNode<T> node = root;
		
		while (!root.isEmpty() && !element.equals(node.getData())) {
			if (element.compareTo(node.getData()) < 0) {
				node = (BSTNode<T>) node.getLeft();
			} 
			else {
				node = (BSTNode<T>) node.getRight();
			}
		}
		
		recursiveRemove(node);
		root = resetRoot(root);
		
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
					rebalanceUp(tmp);
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
					rebalanceUp(tmp);
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
	
	private BSTNode<T> resetRoot(BSTNode<T> node) {
		BSTNode<T> aux = node;
		
		while(aux.getParent() != null){
			aux = (BSTNode<T>) aux.getParent();
		}
		
		return aux;
	}

	//AUXILIARY
	protected int calculateBalance(BSTNode<T> node){
		if (node != null) return heightRecursive(node.getLeft()) - heightRecursive(node.getRight());
		else return 0;
	}
	
	private int heightRecursive(BTNode<T> node) {
		int elementosDireita = 0, elementosEsquerda = 0;
		
		if (node == null) return -1;

		else {
			elementosEsquerda = heightRecursive(node.getLeft()) + 1;
			elementosDireita = heightRecursive(node.getRight()) + 1;
		}
		
		if (elementosDireita > elementosEsquerda) return elementosDireita;
		else return elementosEsquerda;
	}
	
	//AUXILIARY
	protected void rebalance(BSTNode<T> node){

		//Pesa para esquerda
		if (calculateBalance(node) > 1){
			
			//Filho pesa para esquerda
			if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0){
				rightRotation(node);
			}
			
			//Filho pesa para direita
			else{
				leftRotation((BSTNode<T>) node.getLeft());
				rightRotation((BSTNode<T>) node);
			}
		}
		
		//Pesa para direita
		else if (calculateBalance(node) < -1){
			
			//Filho pesa para direita
			if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
				leftRotation(node);
			}
			
			//Filho pesa para esquerda
			else{
				rightRotation((BSTNode<T>) node.getRight());
				leftRotation((BSTNode<T>) node);
			}
		}		
	}
	
	//AUXILIARY
	protected void rebalanceUp(BSTNode<T> node){
		BTNode<T> Parent = node.getParent();
		while (Parent != null) {
			rebalance((BSTNode<T>) Parent);
			Parent = Parent.getParent();
		}
	}
	
	//AUXILIARY
	protected void leftRotation(BSTNode<T> node){
				
		BSTNode<T> Pivot = (BSTNode<T>) node.getRight();
			
		// Troca os parents entre Pivot e Node
		Pivot.setParent(node.getParent());
		
		if (Pivot.getParent() != null){
			
			//Node eh o filho da esquerda
			if (node.getParent().getLeft().equals(node)){
				Pivot.getParent().setLeft(Pivot);
			}
			
			//Node eh o filho da direita
			else{
				Pivot.getParent().setRight(Pivot);
			}
			
		}
		
		//Ajustando o filho a direita de node
		node.setRight(Pivot.getLeft());
		node.getRight().setParent(node);
		node.setParent(Pivot);
		
		//Ajusta o filho a esquerda de Pivot
		Pivot.setLeft(node);
		Pivot.getLeft().setParent(Pivot);
		
		node = Pivot;
	}
	
	//AUXILIARY
	protected void rightRotation(BSTNode<T> node){
		
		BSTNode<T> Pivot = (BSTNode<T>) node.getLeft();

		// Troca os parents entre Pivot e Node
		Pivot.setParent(node.getParent());
		
		if (Pivot.getParent() != null){
			
			//Node eh o filho da esquerda
			if (node.getParent().getLeft().equals(node)){
				Pivot.getParent().setLeft(Pivot);
			}
			
			//Node eh o filho da direita
			else{
				Pivot.getParent().setRight(Pivot);
			}
			
		}
		
		//Ajustando o filho a esquerda de node
		node.setLeft(Pivot.getRight());
		node.getLeft().setParent(node);
		node.setParent(Pivot);
		
		//Ajusta o filho a esquerda de Pivot
		Pivot.setRight(node);
		Pivot.getRight().setParent(Pivot);
		
		node = Pivot;
	}
	
	public static void main(String[] args) {
		
		AVLTree<Integer> tree1;
		Integer[] data = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		tree1 = new AVLTreeImpl<Integer>();
		tree1.insert(1);
		tree1.insert(4);
		tree1.insert(7);
		tree1.insert(10);
		tree1.insert(13);
		tree1.insert(2);
		tree1.insert(5);
		tree1.insert(8);
		tree1.insert(11);
		tree1.insert(14);
		tree1.insert(3);
		tree1.insert(15);
		tree1.insert(6);
		tree1.insert(12);
		tree1.insert(9);
		
		Integer[] preOrder1 = {10,4,2,1,3,7,5,6,8,9,13,11,12,14,15};
		System.out.println(Arrays.toString(tree1.preOrder()));
	}

}
