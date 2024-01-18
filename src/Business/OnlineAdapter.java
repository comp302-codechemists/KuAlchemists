package Business;

import Screens.OnlineOptionFrame;
import soundEffects.PlaySong;

public class OnlineAdapter implements MainAdapter{

	@Override
	public void start() {
		PlaySong.play("ButtonClick");
    	
    	new OnlineOptionFrame();
    	
		
	}

}
