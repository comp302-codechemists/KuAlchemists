package Controllers;

import Business.KUAlchemistsGame;

public class BuyArtifactController {
	
	public void buyArtifactHandler() {
		KUAlchemistsGame.currentPlayer.buyArtifact();
	}
}
