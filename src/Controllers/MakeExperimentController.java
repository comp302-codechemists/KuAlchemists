package Controllers;

import java.util.ArrayList;
import java.util.List;

import Business.Experiment;
import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Potion;
import Exceptions.PlayerDoesNotHaveSuchIngredientException;

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
	
	
	public String handleExperiment(List<String> ingredientList, int whereToTest) 
	{
		Potion potion;
		try {
			potion = game.currentPlayer.makeExperiment(ingredientList, whereToTest);
			System.out.println("Potion: " + potion.getName());
			game.nextPlayer();
			return potion.getName();
		} catch (PlayerDoesNotHaveSuchIngredientException e) {
			e.printStackTrace();
			return e.getMessage();
		} 
		
	}
	
	public int getNumberOfIngredientsToBeRemoved() {
		return game.currentPlayer.getNumberOfIngreientToBeRemovedWhileExperimenting();
	}
	
	public String handleExperiment(List<String> ingredientList, int whereToTest, String keptIngredient) 
	{
		Potion potion = game.currentPlayer.makeExperiment(ingredientList, whereToTest, keptIngredient); 
		System.out.println("Potion: " + potion.getName());
		game.nextPlayer();
		return potion.getName();
	}

	
}
