package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected  BSTNode<T> root;
	private int index;
	
	
	public BSTImpl() {
		root = new BSTNode<T>();
		index = 0;
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
		return height(root);
	}
	private int height(BSTNode<T> node){
		int leftHeight = 0;
		int rightHeight = 0;
		final int EMPTY_TREE = -1;
		
		if (node.isEmpty()) return EMPTY_TREE;
		
		if(!node.getLeft().isEmpty())
			leftHeight += 1 + height((BSTNode<T>)node.getLeft());
		
		if (!node.getRight().isEmpty())
			rightHeight += 1 + height((BSTNode<T>)node.getRight());
		
		return Math.max(leftHeight, rightHeight);
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> aux = root; 
		while(!aux.isEmpty() && !aux.getData().equals(element)){ //percorre a arvore
			if(aux.getData().compareTo(element) > 0)
				aux = (BSTNode<T>) aux.getLeft();
			else{
				aux = (BSTNode<T>) aux.getRight();
			}
		}
		if(aux.isEmpty()) return new BSTNode<T>(); //elemento nao presente na arvore
		return aux; //elemento encontrado 
	}

	@Override
	public void insert(T value){
		if (root.isEmpty()){
			root.setData(value);
			root.setLeft(new BSTNode<T>());
			root.setRight(new BSTNode<T>());
		}
		else{
			insert(value, root);
		}
	}
	private void insert(T value, BSTNode<T> node){
		BSTNode<T> aux;
		if ( value.compareTo(node.getData())< 0){
			aux = (BSTNode<T>) node.getLeft();
		}
		else{
			aux = (BSTNode<T>) node.getRight();
		}
		
		if (aux.isEmpty()){
			aux.setParent(node);
			aux.setData(value);
			aux.setLeft(new BSTNode<T>());
			aux.setRight(new BSTNode<T>());
		}
		else{
			insert(value, aux);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if(isEmpty()) return null; //arvore vazia
		return maximum(root);
	}
	
	private BSTNode<T> maximum(BSTNode<T> node){
		while(!node.getRight().isEmpty()){
			node = (BSTNode<T>)node.getRight();
		}
		return node;
	}

	@Override
	public BSTNode<T> minimum() {
		if(isEmpty()) return null; //arvore vazia
		return minimum(root);
	}
	protected BSTNode<T> minimum(BSTNode<T> node){
		while(!node.getLeft().isEmpty()){
			node = (BSTNode<T>)node.getLeft();
		}
		return node;
	}

	 @Override
		public BSTNode<T> sucessor(T element) {
			
			BSTNode<T> node = search(element);
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			
			if (node.isEmpty()){
				return null;
			}	
			
			if (!node.getRight().isEmpty()) {
				return minimum((BSTNode<T>) node.getRight());
			}
			
			while (parent != null && node.equals(parent.getRight())) {
				node = parent;
				parent = (BSTNode<T>) parent.getParent();
			}
			return parent;
		}

		@Override
		public BSTNode<T> predecessor(T element) {
			
			BSTNode<T> node = search(element);
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			
			if (node.isEmpty()){
				return null;
			}
			
			if (!node.getLeft().isEmpty()){
				return (BSTNode<T>) maximum((BSTNode<T>) node.getLeft());
			}
			
			while (parent != null && node.equals(parent.getLeft())) {
				node = parent;
				parent = (BSTNode<T>) parent.getParent();
			}
			return parent;
		}

		@Override
		public void remove(T element) {
			BSTNode<T> node = search(element);
			
			if(node.isEmpty()) {
				return;
			
			} else {
				if(node.getLeft().isEmpty() && node.getRight().isEmpty()) {
					node.setData(null);				
				
				}else if(node.getLeft().isEmpty() || node.getRight().isEmpty()) {
					
					if(node.getParent() != null) {

						if(!node.getParent().getLeft().equals(node)) {
						
							if(!node.getLeft().isEmpty()){
								node.getParent().setRight(node.getLeft());
								node.getLeft().setParent(node.getParent());
							
							}else{
								node.getParent().setRight(node.getRight());
								node.getRight().setParent(node.getParent());
							}
						
						}else{
						
							if(!node.getLeft().isEmpty()){
								node.getParent().setLeft(node.getLeft());
								node.getLeft().setParent(node.getParent());
							
							}else{
								node.getParent().setLeft(node.getRight());
								node.getRight().setParent(node.getParent());
							
							}
						}
					}else{
						
						if(node.getLeft().isEmpty()){
							root = (BSTNode<T>) node.getRight();
						
						}else{
							root = (BSTNode<T>) node.getLeft();
						}
						
						root.setParent(null);					
					}
				
				} else {
					T sucessor = sucessor(node.getData()).getData();
					remove(sucessor);
					node.setData(sucessor);
				}
			}
	}

	@Override
	public T[] preOrder() {
		@SuppressWarnings("rawtypes")
		Comparable[] array = new Comparable[size()];
		preOrder((T[])array, root);
		index=0;
		return (T[])array;
	}
	private void preOrder(T[] array, BSTNode<T> node){
		if (!node.isEmpty()){
			array[index++] = node.getData();
			preOrder( array, (BSTNode<T>)node.getLeft());
			preOrder( array, (BSTNode<T>)node.getRight());
		}
	}

	@Override
	public T[] order() {
		@SuppressWarnings("rawtypes")
		Comparable[] array = new Comparable[size()];
		order((T[])array, root);
		index=0;
		return (T[])array;
	}
	private void order( T[] array, BSTNode<T> node){
		if (!node.isEmpty()){
			order( array, (BSTNode<T>)node.getLeft());
			array[index++] = node.getData();
			order( array, (BSTNode<T>)node.getRight());
		}
	}

	@Override
	public T[] postOrder() {
		@SuppressWarnings("rawtypes")
		Comparable[] array = new Comparable[size()];
		postOrder((T[])array, root);
		index=0;
		return (T[])array;
	}

	private void postOrder(T[] array, BSTNode<T>node){
		if (!node.isEmpty()){
			postOrder( array, (BSTNode<T>)node.getLeft());
			postOrder( array, (BSTNode<T>)node.getRight());
			array[index++] = node.getData();
			
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand how it work and 
	 * use similar idea with the other methods. 
	 */
	@Override
	public int size() {
		return size(root);
	}
	private int size(BSTNode<T> node){
		int result = 0;
		//base case means doing nothing (return 0)
		if(!node.isEmpty()){ //inductive case
			result = 1 + size((BSTNode<T>)node.getLeft()) + size((BSTNode<T>)node.getRight());
		}
		return result;
	}

}
