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
	
	public void removeTokenHandler(int selectedTriangle) {
		game.currentPlayer.getDeductionBoard().getTriangle().remove(selectedTriangle);
		game.currentPlayer.getDeductionBoard().getExistingItems().remove(selectedTriangle);

	}
	
	public void existingMarkersHandler(int selectedTriangle, int selectedLeft) {
		
	}
	

}
