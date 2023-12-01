package Controllers;

import Business.Ingredient;
import Business.KUAlchemistsGame;

public class ExperimentController {
	
	public void handleMakeExperiment(Ingredient ingredientOne, Ingredient ingredientTwo, String whereToTest) {
		KUAlchemistsGame.currentPlayer.makeExperiment(KUAlchemistsGame.currentPlayer,
													ingredientOne,
													ingredientTwo,
													whereToTest);
	}
}
