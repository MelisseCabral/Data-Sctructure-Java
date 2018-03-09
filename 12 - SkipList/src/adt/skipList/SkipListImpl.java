package adt.skipList;

public class SkipListImpl<V> implements SkipList<V> {

	protected SkipNode<V> root;
	protected int level;
	protected int maxLevel;

	//SET THIS VALUE TO TRUE IF YOU ARE IMPLEMENTING MAX_LEVEL = LEVEL
	//SET THIS VALUE TO FALSE IF YOU ARE IMPLEMENTING MAX_LEVEL != LEVEL
	protected boolean useMaxLevelAsLevel = false;
	protected double probability = 0.5; 
	protected SkipNode<V> NIL;
	
	public SkipListImpl(int maxLevel) {
		if(useMaxLevelAsLevel){
			this.level = maxLevel;
		}else{
			this.level = 1;
		}
		this.maxLevel = maxLevel;
		root = new SkipNode(Integer.MIN_VALUE, maxLevel, new Integer(Integer.MIN_VALUE));
		NIL = new SkipNode(Integer.MAX_VALUE, maxLevel, new Integer(Integer.MAX_VALUE));
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
		while(Math.random() <= probability && randomLevel < maxLevel){
			randomLevel = randomLevel + 1;
		}
		return randomLevel;
	}
	
	@Override
	public void insert(int key, V newValue) {
		insert (key, newValue, randomLevel());
		
	}

	@Override
	public void insert(int key, V newValue, int height) {
		if (newValue != null && height > 0 && height <= this.maxLevel) {   // verifica valores invalidos
		
			SkipNode<V> x = root;
			SkipNode<V>[] update = new SkipNode[maxLevel];
			for (int i = level - 1; i >= 0; i--) {
				while (x.getForward(i).getKey() < key){
					x = x.getForward(i);
				}                                                   // procura
				update[i] = x;
			}
			x = x.getForward(0);
			
			if (x.getKey() == key){
				x.satteliteData = newValue;                        // atualiza se for o caso
				
			} else {                            // calcula altura
				if (height > level){                                  
					for (int i = level; i < height; i++) {              // organiza updates caso nova altura ultrapasse o level        
						update[i] = root;
						root.forward[i] = NIL;
					}
					this.level = height;
				}
				
				SkipNode<V> newNode = new SkipNode<V>(key, height, newValue);
				for (int i = 0; i < height; i++) {
					newNode.forward[i] = update[i].getForward(i);               // cria o novo no e ajusta os ponteiros
					update[i].forward[i] = newNode;
				}
			}
		
		}
	}

	@Override
	public void remove(int key) {
		if (key != Integer.MAX_VALUE && key != Integer.MIN_VALUE){ // impede remocao de root e NIL
			
			SkipNode<V> x = root;
			SkipNode<V>[] update = new SkipNode[maxLevel];
			for (int i = level - 1; i >= 0; i--) {
				while (x.getForward(i).getKey() < key){
					x = x.getForward(i);
				}                                                   // procura
				update[i] = x;
			}
			x = x.getForward(0);
			
			if (x.getKey() == key){
				for (int i = 0; i < level; i++) {
					if (update[i].getForward(i).equals(x)){
						update[i].forward[i] = x.getForward(i);          // ajusta ponteiros
					}
				}
				
				while (level > 1 && root.getForward(level - 1).equals(NIL)){ // ajusta altura
					level--;
					
				}
			}
		
		}

	}

	@Override
	public int height() {   
		if (root.getForward(0).equals(NIL))
			return 0;
		
		return level;
	}

	@Override
	public SkipNode<V> search(int key) {
		if (key == Integer.MIN_VALUE){               // garante busca no root tambem, o NIL ja esta garantido por implementacao
			return root;
		}
		
		SkipNode<V> x = root;
		for (int i = level - 1; i >= 0; i--) {
			while (x.getForward(i).getKey() < key){
				x = x.getForward(i);
			}
		}
		x = x.getForward(0);
		
		if (x.key == key){
			return x;
			
		} else {
			return null;
		}
	}

	@Override
	public int size(){
		SkipNode<V> auxNode = root;
		int cont = 0;
		while (!auxNode.getForward(0).equals(NIL)){
			cont++;
			auxNode = auxNode.getForward(0);
		}
		return cont;
		
	}

	@Override
	public SkipNode<V>[] toArray() {
		SkipNode<V> auxNode = root;
		SkipNode<V>[] array = new SkipNode[size() + 2];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = auxNode;
			auxNode = auxNode.getForward(0);	
		}
		
		return array;
	}
	
}
