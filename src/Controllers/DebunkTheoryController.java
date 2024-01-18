package Controllers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Business.KUAlchemistsGame;
import Exceptions.InvalidTheoryDebunkException;
import Exceptions.TheoryNotFoundException;

public class DebunkTheoryController {
	
private KUAlchemistsGame game;
	
	public DebunkTheoryController(KUAlchemistsGame game)
	{
		this.game = game;
	}

	public void handleDebunk(String selectedTheory, String selectedAspect)
	{
		boolean result;
		
		result = game.currentPlayer.debunkTheory(selectedTheory, selectedAspect);
		if(result) {
			game.currentPlayer.setReputationPoints(game.currentPlayer.getReputationPoints() + 1);
		}
		else {
			game.currentPlayer.setReputationPoints(game.currentPlayer.getReputationPoints() - 1);
		}
		System.out.println(result);
		
		game.nextPlayer();
			
		
	}
	
	public boolean isSuccesful(String selectedTheory) {
		return game.currentPlayer.isSuccesfullDebunk(selectedTheory);
	}
}
