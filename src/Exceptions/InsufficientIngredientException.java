package Exceptions;

public class InsufficientIngredientException extends Exception {

	private static final long serialVersionUID = 1L;

	public InsufficientIngredientException() {
		super("You cannot make potion to sell with null ingredients. You need to have 2 ingredients selected.");
	}
	
	public InsufficientIngredientException(String message) {
		super(message);
	}
}