package Exceptions;

public class TheoryNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public TheoryNotFoundException() {
		super("Thory Not Found.");
	}
	
	public TheoryNotFoundException(String message) {
		super(message);
	}
}