package Screens;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Business.KUAlchemistsGame;
import Controllers.PauseController;
import networking.ClientHandler;

public class PauseFrame {
	
    public PauseFrame() {
    	
    	Object[] options = {"Resume"};
    	int result = JOptionPane.showOptionDialog(new JFrame(),
    			"Game is paused",
    			"Pause/Resume",
    			JOptionPane.OK_OPTION,
    			JOptionPane.QUESTION_MESSAGE,
    			null,   
    			options,  
    			options[0]); 

    	
        if (result == JOptionPane.OK_OPTION) {
        	
        	if (KUAlchemistsGame.instance.isOnline()) {
            	
            }
        	
            PauseController controller = new PauseController();
            controller.pauseHandler();
            
          }

    } 

}
