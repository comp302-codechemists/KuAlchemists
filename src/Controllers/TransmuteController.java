package Controllers;
import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.IngredientStorage;

public class TransmuteController {
	
	public void handleTransmute(String transmutedIngredientName) {
		
		boolean found = false;
	
		for(Ingredient ingredient: KUAlchemistsGame.currentPlayer.getIngredients()) {
			if(ingredient.getName().equals(transmutedIngredientName)) {
				KUAlchemistsGame.currentPlayer.transmuteIngredient(ingredient);
				found = true;
				break;
			}
		}
		
		if(!found) {
			System.out.printf("Ingredient %s is not found in the player's storage%n",transmutedIngredientName);
		}
	}
}
