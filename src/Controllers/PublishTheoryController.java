package Controllers;

import Business.KUAlchemistsGame;

public class PublishTheoryController 
{
	private KUAlchemistsGame game;

	public PublishTheoryController(KUAlchemistsGame game) {
		this.game = game;
	}
	
	public void handlePublish(String selectedMarker, String selectedTheory)
	{
		game.currentPlayer.publishTheory(selectedMarker,selectedTheory);
		game.nextPlayer();

	}
}
