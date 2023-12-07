package Controllers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PauseController {
	
	public boolean pauseHandler() {
		JOptionPane.showMessageDialog(new JFrame(), "Game is paused.", "Pause/Resume", JOptionPane.ERROR_MESSAGE);
		
		return false;
		//TODO
	}

}
