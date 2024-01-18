package Exceptions;

public class PlayerDoesNotHaveSuchIngredientException extends Exception {

	private static final long serialVersionUID = 1L;

	public PlayerDoesNotHaveSuchIngredientException() {
		super();
	}
	
	public PlayerDoesNotHaveSuchIngredientException(String message) {
		super(message);
	}
}