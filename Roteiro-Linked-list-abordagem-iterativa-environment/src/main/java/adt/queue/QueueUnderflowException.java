package adt.queue;

public class QueueUnderflowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueueUnderflowException() {
		super("Fila vazia");
	}

}
