package Exceptions;

public class NoPromiseException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoPromiseException() {
		super("You cannot make potion to sell with null or empty promise");
	}
	
	public NoPromiseException(String message) {
		super(message);
	}
}