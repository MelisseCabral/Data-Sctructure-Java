package adt.rbtree;

import adt.avltree.AVLTreeImpl;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends AVLTreeImpl<T> 
	implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}
	
	protected int blackHeight(){
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
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
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	
	/**
	 * Verifies the black-height property from the root. The method blackHeight returns an exception if the black heights are different.  
	 */
	private boolean verifyBlackHeight(){
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void insert(T value) {
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public RBNode<T>[] extendedPreOrder(){
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	//FIXUP methods
		protected void fixUpCase1(RBNode<T> node){
			if(node != root){
				fixUpCase2(node);
			} else {
				node.setColour(Colour.BLACK);
			}
		}
		protected void fixUpCase2(RBNode<T> node){
			if(((RBNode<T>) node.getParent()).getColour() == Colour.RED){
				fixUpCase3(node);
			}else {
				
			}
		}
		protected void fixUpCase3(RBNode<T> node){
			// tio vermelho
		}
		protected void fixUpCase4(RBNode<T> node){
			//tio preto
			throw new UnsupportedOperationException("Not implemented yet!");
		}
		protected void fixUpCase5(RBNode<T> node){
			//TODO Implement your code here
		}
}
