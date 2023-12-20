package Business;

import java.util.ArrayList;
import java.util.List;


import Controllers.PauseController;

public class KUAlchemistsGame {

	private int currentPlayerIndex;
	public Player currentPlayer;
	public static KUAlchemistsGame instance;
	int numberOfPlayers;
	IngredientStorage ingredientStorage;
	ArtifactStorage artifactStorage;
	List<Player> players = new ArrayList<Player>();
	int level = 1;
	boolean paused = false;
	boolean finished = false;


	private KUAlchemistsGame(int numberOfPlayers) {
		
		// set number of players
		this.numberOfPlayers = numberOfPlayers;

		// create ingredientStorage
		ingredientStorage = IngredientStorage.getInstance();

		// create artifactStorage
		artifactStorage = new ArtifactStorage();
		
		System.out.printf("The game is created with %d players.\n", numberOfPlayers);
	}
	
	
	public void nextPlayer()
	{
		/*
		 * This method will handle player transitions.
		 * It will return the current player.
		 * Within any action in the game,
		 * this method should be called.
		 * */
		
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
		currentPlayer = players.get(currentPlayerIndex);
	}
	
	public static KUAlchemistsGame getInstance(int numberOfPlayers) {
		if (instance == null) {
			instance = new KUAlchemistsGame(numberOfPlayers);
		}
		return instance;
	}

	public void play(List<String> nameList, List<String> avatarList) {
		// take player information
		setPlayers(nameList, avatarList);

		// start game
		startGame();
	}

	private void startGame() {
		// set current player
		currentPlayerIndex = 0;
		currentPlayer = players.get(currentPlayerIndex);

		// shuffle ingredients
		ingredientStorage.shuffleIngredients();

		// shuffle artifacts
		artifactStorage.shuffleArtifacts();

		System.out.println("KUAlchemistsGame: Game started. Players are waiting to begin.");
	}

	private void setPlayers(List<String> nameList, List<String> avatarList) {

		for (int i = 0; i < 2; i++) {

			// create new player
			Player newPlayer = new Player(nameList.get(i), avatarList.get(i));
			GameEvent event = new GameEvent(this, newPlayer, GameEvent.EventID.JOIN_GAME);

			// set new player's balance
			newPlayer.setBalance(+5);

			// deal 5 ingredientCards to newPlayer
			for (int j = 0; j < 2; j++) {
				giveRandomIngredientCardToPlayer(newPlayer);
				
			}

			// add newPlayer to players list
			addPlayer(newPlayer);

		}

		System.out.printf("KUAlchemistsGame, Players set: Player 1: %s, Player 2: %s.\n", players.get(0).getUserName(),
				players.get(1).getUserName());
	}

	private void giveRandomIngredientCardToPlayer(Player player) {
		Ingredient ingredient = ingredientStorage.getIngredient();
		player.addIngredient(ingredient);
	}

	private void nextLevel() {
		/*
		 * This method increases the level by one. Some verification may be added
		 */
		if (getLevel() < 4) {
			setLevel(getLevel() + 1);
		}
		System.out.println("KUAlchemistsGame: Next level. Level: " + this.level);
		GameEvent event = new GameEvent(this, null, GameEvent.EventID.LEVEL_UP);
	}

	private void addPlayer(Player player) {

		/*
		 * This method will add a new player to the game's player list. Some
		 * verification may be added
		 */

		this.players.add(player);
	}

	public void finish() {

		/*
		 * This method will end the game. Some verification may be added
		 */
		if (level == 3 && !isFinished()) {
			setFinished(true);
			GameEvent events = new GameEvent(this, null, GameEvent.EventID.FINISH_GAME);
		}

	}

	public Player showWinner() {
		/*
		 * This method will return the winner
		 */
		if (players == null || players.isEmpty()) {
			return null;
		}

		Player winner = players.get(0);
		for (Player player : players) {
			float playerScore = player.calculateScore();
			float winnerScore = winner.calculateScore();

			if (playerScore > winnerScore) {
				winner = player;
			}
		}
		return winner;

	}

	public void pause() {
		
		if (!isPaused()) {
			setPaused(true);
			GameEvent event = new GameEvent(this, null, GameEvent.EventID.PAUSE_GAME);
			PauseController pc = new PauseController();
			pc.showPause();
		}

	}

	public void resume() {

		if (isPaused()) {
			setPaused(false);
			GameEvent event = new GameEvent(this, null, GameEvent.EventID.RESUME_GAME);
		}

	}

	// Getters and setters:

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public IngredientStorage getIngredientStorage() {
		return ingredientStorage;
	}

	public void setIngredientStorage(IngredientStorage ingredientStorage) {
		this.ingredientStorage = ingredientStorage;
	}

	public ArtifactStorage getArtifactStorage() {
		return artifactStorage;
	}

	public void setArtifactStorage(ArtifactStorage artifactStorage) {
		this.artifactStorage = artifactStorage;
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}
