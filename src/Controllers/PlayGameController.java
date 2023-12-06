package Controllers;

import Business.KUAlchemistsGame;

public class PlayGameController {

	KUAlchemistsGame game;
	
	public KUAlchemistsGame playGame(int numberOfPlayers) {
		game = new KUAlchemistsGame(numberOfPlayers);
		return game;
	}
	
}
