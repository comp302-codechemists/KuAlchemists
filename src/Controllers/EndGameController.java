package Controllers;


import Business.KUAlchemistsGame;
import Business.Player;
import Screens.EndGameFrame;

public class EndGameController {
	
	KUAlchemistsGame game;
	Player player;
	
	public EndGameController(KUAlchemistsGame game, Player player) {
		this.game = game;
		this.player = player;
	}
	
	public void handleEndGame() {
		
		new EndGameFrame(game, player);

	}

}
