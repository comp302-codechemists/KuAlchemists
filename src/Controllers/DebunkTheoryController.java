package Controllers;

import Business.KUAlchemistsGame;

public class DebunkTheoryController {
	
private KUAlchemistsGame game;
	
	public DebunkTheoryController(KUAlchemistsGame game)
	{
		this.game = game;
	}

	public void handleDebunk(String selectedTheory, String selectedAspect)
	{
		game.currentPlayer.debunkTheory(selectedTheory, selectedAspect);
	}
}
