package adt.stack;

public class StackOverflowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StackOverflowException() {
		super("Stack is full");
	}
	
}
