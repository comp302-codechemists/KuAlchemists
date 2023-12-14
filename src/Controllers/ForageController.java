package Controllers;

import Business.KUAlchemistsGame;

public class ForageController {
	
	private KUAlchemistsGame game;
	
	public ForageController(KUAlchemistsGame game)
	{
		this.game = game;
	}

	public String handleForage() {
		String foragedIngredient = game.currentPlayer.forageForIngredient();
		game.nextPlayer();
		return foragedIngredient;
	}
	
}
