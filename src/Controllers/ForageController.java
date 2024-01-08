package Controllers;

import Business.KUAlchemistsGame;
import Business.Player;

public class ForageController {
	
	private KUAlchemistsGame game;
	
	public ForageController(KUAlchemistsGame game)
	{
		this.game = game;
	}

	public String handleForage() {
		String foragedIngredient = game.currentPlayer.forageForIngredient();
		for (Player player : game.getPlayers() ) {
			if(player.getUserName().equals(game.currentPlayer.getUserName())) {
				//player = game.currentPlayer;
				player.setIngredients(game.currentPlayer.getIngredients());
			}
		}
		game.nextPlayer();
		return foragedIngredient;
	}
	
}
