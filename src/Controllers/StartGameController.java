package Controllers;

import Business.KUAlchemistsGame;

public class StartGameController {
	
	public void handleStartGame(String p1name, String p2name, String avatar1, String avatar2) {
		
    	KUAlchemistsGame game = new KUAlchemistsGame(2);
    	game.play(p1name, p2name, avatar1, avatar2);
	}

}
