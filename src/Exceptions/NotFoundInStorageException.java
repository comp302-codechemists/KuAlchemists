package Exceptions;

public class NotFoundInStorageException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundInStorageException() {
		super();
	}
	
	public NotFoundInStorageException(String message) {
		super(message);
	}
}