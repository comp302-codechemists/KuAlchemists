package Controllers;
import java.util.ArrayList;
import java.util.List;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.IngredientStorage;

public class TransmuteController {
	
	private KUAlchemistsGame game;
	
	public TransmuteController(KUAlchemistsGame game)
	{
		this.game = game;
	}

	public List<Ingredient> getPlayersCurrentIngredients()
	{
		List<Ingredient> currentIngredientList = new ArrayList<>();
		
		for (Ingredient ingredient: game.currentPlayer.getIngredients())
		{
			if (!currentIngredientList.contains(ingredient))
			{
				currentIngredientList.add(ingredient);
			}
		}
		
		return currentIngredientList;
	}
	
	
	public String handleTransmute(String transmutedIngredientName) {
		
		boolean found = false;
	
		for(Ingredient ingredient: game.currentPlayer.getIngredients()) {
			if(ingredient.getName().equals(transmutedIngredientName)) {
				String transmutedIngredient = game.currentPlayer.transmuteIngredient(ingredient);
				found = true;
				game.nextPlayer();
				return transmutedIngredient;
			}
		}
		
		if(!found) {
			System.out.printf("Ingredient %s is not found in the player's storage%n",transmutedIngredientName);
		}
		
		return null;
	}
}
