package Business;

public abstract class State {
	
	protected KUAlchemistsGame game;
	
	public State(KUAlchemistsGame game) {
        this.game = game;
    }

	public abstract void resume();
	public abstract void pause();
}
