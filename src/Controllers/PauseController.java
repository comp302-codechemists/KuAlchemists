package Controllers;

import Business.KUAlchemistsGame;
import Screens.PauseFrame;

public class PauseController {
	
	public void pauseHandler() {
		
		KUAlchemistsGame.instance.resume();
	
	}
	
	public void showPause() {
		PauseFrame pauseFrame = new PauseFrame();
		
	}

}
