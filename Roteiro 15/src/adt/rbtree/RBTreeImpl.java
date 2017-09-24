package adt.rbtree;

import java.util.ArrayList;
import java.util.List;

import adt.avltree.AVLTreeImpl;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends AVLTreeImpl<T> 
	implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}
	
	protected int blackHeight(){
		return blackHeight((RBNode<T>) root);
	}

	private int blackHeight(RBNode<T> node) {
		int height = 0;
		if (!node.isEmpty()) {
			if (node.getColour().equals(Colour.BLACK)) {
				height = 1 + max(blackHeight((RBNode<T>) node.getLeft()),
						blackHeight((RBNode<T>) node.getLeft()));
			} else {
				height = max(blackHeight((RBNode<T>) node.getLeft()),
						blackHeight((RBNode<T>) node.getLeft()));
			}
		}
		return height;
	}
	
	protected int max(int heightLeft, int heightRight) {
		return heightLeft >= heightRight ? heightLeft : heightRight;
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
	private boolean verifyChildrenOfRedNodes(){
		boolean resp = true;
		if(!this.isEmpty()){
			resp = verifyChildrenOfRedNodes((RBNode<T>) this.root);
		}
		return resp;
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		boolean resp = true;
		if (!node.isEmpty()) {
			if (node.getColour().equals(Colour.RED)) {
				if (((RBNode<T>) node.getRight()).getColour().equals(
						Colour.BLACK)) {
					resp = ((RBNode<T>) node.getLeft()).getColour().equals(
							Colour.BLACK)
							&& verifyChildrenOfRedNodes((RBNode<T>) node
									.getLeft())
							&& verifyChildrenOfRedNodes((RBNode<T>) node
									.getRight());
				}
			} else {
				resp = verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())
						&& verifyChildrenOfRedNodes((RBNode<T>) node
								.getRight());
			}
		}		
		return resp;
	}
	
	/**
	 * Verifies the black-height property from the root. The method blackHeight returns an exception if the black heights are different.  
	 */
	private boolean verifyBlackHeight(){
		return blackHeight((RBNode<T>) root.getLeft()) == blackHeight((RBNode<T>) root.getRight());
	}
	
	private void fixUpInsert(RBNode<T> node) {

		if (!node.getParent().equals(root) && !node.getParent().equals(null)
				&& !node.getParent().getParent().equals(null))
		{
			RBNode<T> pai = (RBNode<T>) node.getParent();
			RBNode<T> tio = new RBNode<T>();
			RBNode<T> avo = (RBNode<T>) pai.getParent();

			if ((node.getColour().compareTo(Colour.RED) == 0)
					&& (pai.getColour().compareTo(Colour.RED) == 0)) {
				if (avo.getLeft().equals(pai)) {
					tio = (RBNode<T>) avo.getRight();
				} else if (avo.getRight().equals(pai)) {
					tio = (RBNode<T>) avo.getLeft();
				}

				if ((tio.getColour().compareTo(Colour.BLACK) == 0)) { //Caso2
					boolean caso2 = false; 
					if ((pai.getRight().equals(node))
							&& avo.getLeft().equals(pai)) {
						leftRotation(pai);
						pai = (RBNode<T>) avo.getLeft();
						node = (RBNode<T>) pai.getLeft();
						caso2 = true;
						// atualizando os dados do pai, avo e filho
					} else if (pai.getLeft().equals(node)
							&& avo.getRight().equals(pai)) {
						rightRotation(pai);
						pai = (RBNode<T>) avo.getRight();
						node = (RBNode<T>) pai.getRight();
						caso2 = true;
						// atualizando os dados do pai, avo e filho
					}
					if (caso2)
						fixUpInsert(node);
					else if (pai.getLeft().equals(node) //Caso3
							&& avo.getLeft().equals(pai)) {
						avo.setColour(Colour.RED);
						pai.setColour(Colour.BLACK);
						rightRotation(avo);
					} else if (pai.getRight().equals(node)
							&& avo.getRight().equals(pai)) {
						avo.setColour(Colour.RED);
						pai.setColour(Colour.BLACK);
						leftRotation(avo);
					}

				}else if (tio.getColour().compareTo(Colour.RED) == 0) { //Caso1
					if (!avo.equals(root)) {
						pai.setColour(Colour.BLACK);
						tio.setColour(Colour.BLACK);
						avo.setColour(Colour.RED);
						fixUpInsert(avo);
					} else {
						tio.setColour(Colour.BLACK);
						pai.setColour(Colour.BLACK);
					}
				}
			}
		}
	}
	
	@Override
	public void insert(T value) {
		RBNode<T> node = new RBNode<T>();
		if (this.isEmpty()) {
			node.setColour(Colour.BLACK);
			node.setData(value);
			node.setLeft(new RBNode<T>());
			node.setParent(null);
			node.setRight(new RBNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			this.root = node;
		} else {
			insertRecursivo(value, (RBNode<T>) this.root);
		}
	}
	
	private void insertRecursivo(T value, RBNode<T> node) {
		if (node.isEmpty()) {
			node.setData(value);
			node.setLeft(new RBNode<T>());
			node.setRight(new RBNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			((RBNode<T>) node).setColour(Colour.RED);
			fixUpInsert(node);

		} else {
			if (value.compareTo(node.getData()) > 0) {
				insertRecursivo(value, (RBNode<T>) node.getRight());
			} else
				insertRecursivo(value, (RBNode<T>) node.getLeft());
		}

	}
	
	@Override
	public RBNode<T>[] extendedPreOrder(){
		List<RBNode<T>> array = new ArrayList<RBNode<T>>();
		Comparable[] a = new Comparable[size()];
		extendedPreOrderRecursive((RBNode<T>) root, array);
		return (RBNode<T>[]) array.toArray(a);
	}
	
	private void extendedPreOrderRecursive(RBNode<T> node, List<RBNode<T>> array) {
		if (!node.isEmpty()) {
			array.add(node);
			extendedPreOrderRecursive((RBNode<T>) node.getLeft(), array);
			extendedPreOrderRecursive((RBNode<T>) node.getRight(), array);
		}
	}
	
	//AUXILIARY
	private void leftRotation(RBNode<T> node) {
		RBNode<T> pivot = new RBNode<T>();
		if (!node.isEmpty() && node != null
				&& node.getRight().getRight() != null) {

			pivot.setData(node.getRight().getData());
			pivot.setLeft(node.getRight().getLeft());
			pivot.getLeft().setParent(pivot);
			pivot.setRight(node.getRight().getRight());
			pivot.getRight().setParent(pivot);
			pivot.setColour(((RBNode<T>) node.getRight()).getColour());

			node.getRight().setData(pivot.getLeft().getData());
			node.getRight().setLeft(pivot.getLeft().getLeft());
			node.getRight().setRight(pivot.getLeft().getRight());
			node.getRight().setParent(node);
			((RBNode<T>) node.getRight()).setColour(((RBNode<T>) pivot
					.getLeft()).getColour());

			pivot.getLeft().setData(node.getData());
			pivot.getLeft().setLeft(node.getLeft());
			pivot.getLeft().getLeft().setParent(pivot.getLeft());
			pivot.getLeft().setRight(node.getRight());
			pivot.getLeft().getRight().setParent(pivot.getLeft());
			pivot.getLeft().setParent(pivot);
			((RBNode<T>) pivot.getLeft()).setColour(((RBNode<T>) node)
					.getColour());

			node.setData(pivot.getData());
			node.setLeft(pivot.getLeft());
			node.getLeft().setParent(node);
			node.setRight(pivot.getRight());
			node.getRight().setParent(node);
			node.setParent(node.getParent());
			((RBNode<T>) node).setColour(((RBNode<T>) pivot).getColour());

		}
	}

	// AUXILIARY
	private void rightRotation(RBNode<T> node) {
		RBNode<T> pivot = new RBNode<T>();
		if (!node.isEmpty() && node != null && node.getLeft().getLeft() != null) {

			pivot.setData(node.getLeft().getData());
			pivot.setLeft(node.getLeft().getLeft());
			pivot.getLeft().setParent(pivot);
			pivot.setRight(node.getLeft().getRight());
			pivot.getRight().setParent(pivot);
			pivot.setColour(((RBNode<T>) node.getLeft()).getColour());

			node.getLeft().setData(pivot.getRight().getData());
			node.getLeft().setLeft(pivot.getRight().getLeft());
			node.getLeft().setRight(pivot.getRight().getRight());
			node.getLeft().setParent(node);
			((RBNode<T>) node.getLeft()).setColour(((RBNode<T>) pivot
					.getRight()).getColour());

			pivot.getRight().setData(node.getData());
			pivot.getRight().setLeft(node.getLeft());
			pivot.getRight().getLeft().setParent(pivot.getRight());
			pivot.getRight().setRight(node.getRight());
			pivot.getRight().getRight().setParent(pivot.getRight());
			pivot.getRight().setParent(pivot);
			((RBNode<T>) pivot.getRight()).setColour(((RBNode<T>) node)
					.getColour());

			node.setData(pivot.getData());
			node.setLeft(pivot.getLeft());
			node.getLeft().setParent(node);
			node.setRight(pivot.getRight());
			node.getRight().setParent(node);
			node.setParent(node.getParent());
			((RBNode<T>) node).setColour(((RBNode<T>) pivot).getColour());

		}
	}
}
