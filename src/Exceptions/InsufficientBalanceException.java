package Exceptions;

public class InsufficientBalanceException extends Exception {

	public InsufficientBalanceException() {
		super("Balance is unsufficient, come back when you have more gold :D");
	}
	
	public InsufficientBalanceException(String message) {
		super(message);
	}
}
