package adt.btree;

import java.util.Collections;
import java.util.LinkedList;

public class BNode<T extends Comparable<T>> {
	protected LinkedList<T> elements; //PODE TRABALHAR COM ARRAY TAMBEM
	protected LinkedList<BNode<T>> children; //PODE TRABALHAR COM ARRAY TAMBEM
	protected BNode<T> parent;
	protected int maxKeys;
	protected int maxChildren;
	
	public BNode(int order){
		this.maxChildren = order;
		this.maxKeys = order - 1;
		this.elements = new LinkedList<T>();	
		this.children = new LinkedList<BNode<T>>();
	}
	@Override
	public String toString() {
		return this.elements.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean resp = false;
		if(obj != null){
			if(obj instanceof BNode){
				if(this.size() == ((BNode<T>) obj).size()){
					resp = true;
					int i = 0;
					while(i<this.size() && resp){
						resp = resp && this.getElementAt(i).equals(((BNode<T>) obj).getElementAt(i));
						i++;
					}
				}
			}
		}
		return resp;
	}
	public boolean isEmpty(){
		return this.size() == 0;
	}
	public int size(){
		return this.elements.size();
	}
	public boolean isLeaf(){
		return this.children.size() == 0;
	}
	public boolean isFull(){
		return this.size()== maxKeys;
	}
	public void addElement(T element){
		this.elements.add(element);
		Collections.sort(elements);
	}
	public void removeElement(T element){
		this.elements.remove(element);
	}
	public void removeElement(int position){
		this.elements.remove(position);
	}
	public void addChild(int position, BNode<T> child){
		this.children.add(position, child);
		child.parent = this;
	}
	public void removeChild(BNode<T> child){
		this.children.remove(child);
	}
	public int indexOfChild(BNode<T> child){
		return this.children.indexOf(child);
	}
	public T getElementAt(int index){
		return this.elements.get(index);
	}
	protected void split(){
		//TODO Implement this method
		//ESTE METODO PODE SER IMPLEMENTADO NA BTREEIMPL TAMBEM. FICA A SEU CRITERIO
	}
	protected void promote(){
		//TODO Implement this method
		//ESTE METODO PODE SER IMPLEMENTADO NA BTREEIMPL TAMBEM. FICA A SEU CRITERIO
	}
	public LinkedList<T> getElements() {
		return elements;
	}
	public void setElements(LinkedList<T> elements) {
		this.elements = elements;
	}
	public LinkedList<BNode<T>> getChildren() {
		return children;
	}
	public void setChildren(LinkedList<BNode<T>> children) {
		this.children = children;
	}
}
