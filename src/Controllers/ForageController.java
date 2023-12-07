package Controllers;

import Business.KUAlchemistsGame;

public class ForageController {

	public void handleForage() {
		KUAlchemistsGame.currentPlayer.forageForIngredient();
	}
	
}
