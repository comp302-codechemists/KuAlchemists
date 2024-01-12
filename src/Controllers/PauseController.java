package Controllers;

import Business.KUAlchemistsGame;
import Screens.PauseFrame;
import networking.Client;

public class PauseController {
	
	public void pauseHandler() {
		
		KUAlchemistsGame.instance.resume();
		
		if(KUAlchemistsGame.instance.isOnline()) {
			Client.instance.sendMessage("RESUME");
		}
	
	}
	
	public void showPause() {
		PauseFrame pauseFrame = new PauseFrame();
		
	}

}
