package Controllers;

import Business.KUAlchemistsGame;

public class PlayGameController {

	KUAlchemistsGame game;
	
	public KUAlchemistsGame playGame(int numberOfPlayers) {
		game = KUAlchemistsGame.getInstance(numberOfPlayers);
		return game;
	}
	
}
