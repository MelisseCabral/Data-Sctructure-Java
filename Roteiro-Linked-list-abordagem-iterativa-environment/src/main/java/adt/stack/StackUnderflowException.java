package adt.stack;

public class StackUnderflowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StackUnderflowException() {
		super("Stack is empty");
	}
}
