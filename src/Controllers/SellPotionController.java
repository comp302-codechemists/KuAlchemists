package Controllers;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Exceptions.NotFoundInStorageException;

public class SellPotionController {
	
	private KUAlchemistsGame game;

	public SellPotionController(KUAlchemistsGame game) {
		this.game = game;
	}
	
	public void handleSellPotion(String firstIngredientName, String secondIngredientName, String promise ) throws Exception {
		
		if(firstIngredientName == null || secondIngredientName == null) {
			throw new IllegalArgumentException("Please choose both ingredients");
		}
		
		if(firstIngredientName.equals(secondIngredientName)) {
			throw new UnsupportedOperationException("Please choose different ingredients");
		}
		
		boolean found = false;
		Ingredient ingr1 = null;
		Ingredient ingr2 = null;
		
		for(Ingredient ingredient: game.currentPlayer.getIngredients()) {
			if(ingredient.getName().equals(firstIngredientName)) {
				ingr1 = ingredient;
			}
			if(ingredient.getName().equals(secondIngredientName)) {
				ingr2 = ingredient;
			}
			
			if(ingr1 != null && ingr2 != null) {
				found = true;
			}
		}
		
		if(found) {
			game.currentPlayer.sellPotion(ingr1,ingr2,promise);
		}
		else {
			throw new NotFoundInStorageException();
		}
	}

}
