package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;
	
	protected int height;
	protected int maxHeight;

	//SET THIS VALUE TO TRUE IF YOU ARE IMPLEMENTING MAX_LEVEL = LEVEL
	//SET THIS VALUE TO FALSE IF YOU ARE IMPLEMENTING MAX_LEVEL != LEVEL
	protected boolean USE_MAX_HEIGHT_AS_HEIGHT = false;
	protected double PROBABILITY = 0.5; 
	
	
	public SkipListImpl(int maxHeight) {
		if(USE_MAX_HEIGHT_AS_HEIGHT){
			this.height = maxHeight;
		}else{
			this.height = 1;
		}
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}
	
	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL
	 * Caso esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com 
	 * level=1 e o metodo deve conectar apenas o forward[0].  
	 */
	private void connectRootToNil(){
	  root.forward[0] = NIL;
	}
	
	/**
	 * Metodo que gera uma altura aleatoria para ser atribuida a um novo no no metodo
	 * insert(int,V) 
	 */
	private int randomLevel(){
		int randomLevel = 1;
		double random = Math.random();
		while(Math.random() <= PROBABILITY && randomLevel < maxHeight){
			randomLevel = randomLevel + 1;
		}
		return randomLevel;
	}
	
	@Override
	public void insert(int key, T newValue) {
		insert(key, newValue, randomLevel());
	}

	@Override
	public void insert(int key, T newValue, int height) {
		if(maxHeight < height){
			maxHeight = height;
		}
		
		if(newValue != null && height <= maxHeight && height > 0){
			SkipListNode<T> x = root;
			SkipListNode<T>[] update = new SkipListNode[maxHeight];
			
			for(int i = 0; i < this.height - 1; i--){
				while(x.getForward(i).getKey() < key){
					x = x.getForward(i);
				} 
				update[i] = x;
			}
			x = x.getForward(0);
			
			if(x.getKey() == key){
				x.value =  newValue;
				
			} else {
				
				if(height > this.height){
					
					for(int i = 0; i < height; i++){
						update[i] = root;
						root.forward[i] = NIL;
					}
					this.height = height;
				}
				
				SkipListNode<T> newNode = new SkipListNode<T>(key, height, newValue);
				
				for(int i = 0; i < height; i ++){
					newNode.forward[i] = update[i].getForward(height);
					update[i].forward[i] = newNode;
				}
			}
		
		}
	}
	@Override
	public void remove(int key) {
		if(key != Integer.MAX_VALUE && key != Integer.MIN_VALUE){
			SkipListNode<T> x = root;
			SkipListNode[] update = new SkipListNode[maxHeight];
			
			for(int i = height - 1; i >= 0; i--){
				while(x.getForward(i).getKey() > key){
					x = x.getForward(i);
				}
				update[i] = x;
			}
			if(x.getKey() == key){
				for(int i = 0; i < height; i ++){
					if(update[i].getForward(i) == x){
						update[i].forward[i] = x.getForward(i);
					}
				}
				while(height > 1 && root.getForward(height - 1) == x){
					height--;
				}
			}
			
		}
	}

	@Override
	public int height() {
		if(root.getForward(0) == (NIL)){
			return 0;
		}
		return height;
	}

	@Override
	public SkipListNode<T> search(int key) {
		if(key == Integer.MIN_VALUE){
			return root;
		}
		SkipListNode<T> x = root;
		for(int i = height -1; i >= 0; i--){
			while(x.getForward(i).getKey() < key){
				x = x.getForward(i);
			}
		}
		if(x.getKey() == key){
			return x;
		} else {
			return null;
		}
		
	}
	

	@Override
	public int size(){
		int cont = 0;
		SkipListNode<T> auxNode = root;
		while(!auxNode.getForward(0).equals(NIL)){
			cont++;
			auxNode = auxNode.getForward(0);
		}
		return cont;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T> auxNode = root;
		SkipListNode<T>[] array = new SkipListNode[size() + 2];
		
		for(int i = 0; i < height; i++){
			array[i] = auxNode;
			auxNode = auxNode.getForward(0);
		}
		return array;
	}

}

	
