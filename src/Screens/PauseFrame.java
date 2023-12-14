package Screens;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controllers.PauseController;

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
            PauseController controller = new PauseController();
            controller.pauseHandler();
          }

    } 

}
