package Controllers;

import java.util.ArrayList;
import java.util.List;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Potion;

public class MakeExperimentController {
	
	private KUAlchemistsGame game;
	
	public MakeExperimentController(KUAlchemistsGame game)
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
	
	
	public String handleExperiment(List<String> ingredientList) {
		
		Ingredient ingredientOne = Ingredient.getIngredient(ingredientList.get(0));
		Ingredient ingredientTwo = Ingredient.getIngredient(ingredientList.get(1));

		Potion potion = Potion.makePotion(ingredientOne, ingredientTwo);
		System.out.println("Potion: " + potion.getName());
		return potion.getName();
	}
	
	
}
