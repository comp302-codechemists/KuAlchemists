package Controllers;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Business.Potion;
import Exceptions.IngredientNotFoundException;
import Exceptions.NotFoundInStorageException;

public class SellPotionController {
	
	private KUAlchemistsGame game;

	public SellPotionController(KUAlchemistsGame game) {
		this.game = game;
	}
	
	public int handleSellPotion(String firstIngredientName, String secondIngredientName, String promise ) throws IngredientNotFoundException 
	{
		Ingredient ingredientOne = Ingredient.getIngredient(firstIngredientName);
		Ingredient ingredientTwo = Ingredient.getIngredient(secondIngredientName);
		
		if (ingredientOne == null || ingredientTwo == null)
		{
			throw new IngredientNotFoundException();
		}
		int payment;
		
		if (promise.contains("+"))
		{
			payment = game.currentPlayer.sellPotion(ingredientOne, ingredientTwo, "+");

		}
		else if (promise.contains("-"))
		{
			payment = game.currentPlayer.sellPotion(ingredientOne, ingredientTwo, "-");
		}
		else
		{
			payment = game.currentPlayer.sellPotion(ingredientOne, ingredientTwo, "0");
		}
		
		for (Player player : game.getPlayers() ) {
			if(player.getUserName().equals(game.currentPlayer.getUserName())) {
				//player = game.currentPlayer;
				player.setIngredients(game.currentPlayer.getIngredients());
			}
		}
		
		game.nextPlayer();
		return payment;
	}

}
