package Business;

import networking.Client;

public class PauseState extends State{

	public PauseState(KUAlchemistsGame game)
	{
		super(game);
	}

	@Override
	public void resume() {
		game.setState(new ResumeState(game));
		System.out.println("Game resumes.\n");
		GameEvent event = new GameEvent(game, null, GameEvent.EventID.RESUME_GAME);
		
		if(game.isOnline()) {
			Client.instance.sendMessage("RESUME");
		}
	}

	@Override
	public void pause() {
		// nothing to do
		// already paused
	}
}
