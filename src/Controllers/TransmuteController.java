package Controllers;
import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.IngredientStorage;

public class TransmuteController {
	
	private KUAlchemistsGame game;
	
	public TransmuteController(KUAlchemistsGame game)
	{
		this.game = game;
	}

	
	
	public void handleTransmute(String transmutedIngredientName) {
		
		boolean found = false;
	
		for(Ingredient ingredient: game.currentPlayer.getIngredients()) {
			if(ingredient.getName().equals(transmutedIngredientName)) {
				game.currentPlayer.transmuteIngredient(ingredient);
				found = true;
				break;
			}
		}
		
		if(!found) {
			System.out.printf("Ingredient %s is not found in the player's storage%n",transmutedIngredientName);
		}
	}
}
