package Controllers;

import java.util.ArrayList;
import java.util.List;

import Business.Ingredient;
import Business.KUAlchemistsGame;

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
	
	
	public void handleExperiment() {
		

	}
	
	
}
