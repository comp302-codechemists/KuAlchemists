package Controllers;

import Business.KUAlchemistsGame;

public class buyArtifactController {
	
	private KUAlchemistsGame game;
	
	public buyArtifactController(KUAlchemistsGame game)
	{
		this.game = game;
	}
	
	public void buyArtifactHandler() {
		game.currentPlayer.buyArtifact();
	}
}
