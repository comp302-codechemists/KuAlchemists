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
		boolean result = game.currentPlayer.debunkTheory(selectedTheory, selectedAspect);
		
		
		if(result) {
			game.currentPlayer.setReputationPoints(game.currentPlayer.getReputationPoints() + 1);
		}
		else {
			game.currentPlayer.setReputationPoints(game.currentPlayer.getReputationPoints() - 1);
		}
		System.out.println(result);
		
		game.nextPlayer();
	}
}
