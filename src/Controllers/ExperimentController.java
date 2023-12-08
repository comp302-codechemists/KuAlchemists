package Controllers;

import Business.Ingredient;
import Business.KUAlchemistsGame;

public class ExperimentController {
		
	private KUAlchemistsGame game;
	
	public ExperimentController(KUAlchemistsGame game)
	{
		this.game = game;
	}
		
	public void handleMakeExperiment(Ingredient ingredientOne, Ingredient ingredientTwo, String whereToTest) {
		game.currentPlayer.makeExperiment(game.currentPlayer,
													ingredientOne,
													ingredientTwo,
													whereToTest);
	}
}
