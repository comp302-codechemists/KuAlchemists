package Controllers;

import Business.KUAlchemistsGame;

public class DeductionBoardController {
private KUAlchemistsGame game;
	
	public DeductionBoardController(KUAlchemistsGame game)
	{
		this.game = game;
	}
	
	public void deductionBoardHandler(int selectedTriangle, String name, int selectedLeft) {
		game.currentPlayer.putTokenToResultsTriangle(selectedTriangle,name, selectedLeft);
	}
	
	public void existingMarkersHandler(int selectedTriangle, int selectedLeft) {
		
	}
	

}
