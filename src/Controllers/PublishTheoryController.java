package Controllers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Business.KUAlchemistsGame;
import Exceptions.InsufficientBalanceException;

public class PublishTheoryController 
{
	private KUAlchemistsGame game;

	public PublishTheoryController(KUAlchemistsGame game) {
		this.game = game;
	}
	
	public void handlePublish(String selectedMarker, String selectedTheory)
	{
		try {
			game.currentPlayer.publishTheory(selectedMarker,selectedTheory);
		} catch (InsufficientBalanceException e) {
			e.printStackTrace();
			String notify = e.getMessage();
			JOptionPane.showMessageDialog(new JFrame(), notify, "Invalid",
                    JOptionPane.ERROR_MESSAGE);
		}
		game.nextPlayer();

	}
}
