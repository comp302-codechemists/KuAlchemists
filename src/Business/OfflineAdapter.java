package Business;

import Controllers.PlayGameController;
import Screens.StartGameFrame;
import soundEffects.PlaySong;

public class OfflineAdapter implements MainAdapter {

	private int numberOfPlayers;
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
		PlaySong.play("ButtonClick");
    	PlayGameController playGameController = new PlayGameController();
    	KUAlchemistsGame game = playGameController.playGame(numberOfPlayers);
        new StartGameFrame(game);
        
	}

	public OfflineAdapter(int numberOfPlayers) {
		super();
		this.numberOfPlayers = numberOfPlayers;
	}

}
