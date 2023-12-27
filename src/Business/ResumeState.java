package Business;

import Controllers.PauseController;

public class ResumeState extends State{

	public ResumeState(KUAlchemistsGame game) {
		super(game);
	}

	@Override
	public void resume() {
		// do nothing
		// already resuming
		
	}

	@Override
	public void pause() {
		game.setState(new PauseState(game));
		System.out.println("Game paused.\n");
		GameEvent event = new GameEvent(game, null, GameEvent.EventID.PAUSE_GAME);
		PauseController pc = new PauseController();
		pc.showPause();
	}

}
