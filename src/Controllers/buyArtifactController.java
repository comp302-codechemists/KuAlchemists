package Controllers;

import Business.KUAlchemistsGame;

public class buyArtifactController {
	
	private KUAlchemistsGame game;
	
	public buyArtifactController(KUAlchemistsGame game)
	{
		this.game = game;
	}
	
	public String buyArtifactHandler() {
		String boughtArtifact = game.currentPlayer.buyArtifact();
		game.nextPlayer();
		
		return boughtArtifact;
	}

}
