package Controllers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Potion;
import Exceptions.IngredientNotFoundException;
import Exceptions.InsufficientIngredientException;
import Exceptions.NoPromiseException;
import Exceptions.NotFoundInStorageException;

public class SellPotionController {
	
	private KUAlchemistsGame game;

	public SellPotionController(KUAlchemistsGame game) {
		this.game = game;
	}
	
	public int handleSellPotion(String firstIngredientName, String secondIngredientName, String promise ) 
	{
		Ingredient ingredientOne = Ingredient.getIngredient(firstIngredientName);
		Ingredient ingredientTwo = Ingredient.getIngredient(secondIngredientName);
		
		int payment;
		
		if (promise.contains("+"))
		{
			try {
				payment = game.currentPlayer.sellPotion(ingredientOne, ingredientTwo, "+");
			} catch (InsufficientIngredientException | NoPromiseException e) {
				String notify = e.getMessage();
				JOptionPane.showMessageDialog(new JFrame(), notify, "Invalid",
	                    JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				payment = 0;
			}

		}
		else if (promise.contains("-"))
		{
			try {
				payment = game.currentPlayer.sellPotion(ingredientOne, ingredientTwo, "-");
			} catch (InsufficientIngredientException | NoPromiseException e) {
				String notify = e.getMessage();
				JOptionPane.showMessageDialog(new JFrame(), notify, "Invalid",
	                    JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				payment = 0;
			}
		}
		else
		{
			try {
				payment = game.currentPlayer.sellPotion(ingredientOne, ingredientTwo, "0");
			} catch (InsufficientIngredientException | NoPromiseException e) {
				String notify = e.getMessage();
				JOptionPane.showMessageDialog(new JFrame(), notify, "Invalid",
	                    JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				payment = 0;
			}
		}
		game.nextPlayer();
		
		return payment;
	}

}
