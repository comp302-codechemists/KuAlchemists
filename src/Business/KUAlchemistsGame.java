package Business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Controllers.PauseController;
import Screens.EndGameFrame;
import networking.*;

public class KUAlchemistsGame {
	
	//================================================================================
    // Properties
    //================================================================================

	public static KUAlchemistsGame instance;

	// player related
	private List<Player> players = new ArrayList<Player>();
	private int currentPlayerIndex;
	public Player currentPlayer;
	int numberOfPlayers;
	public static Client client;
	// each game has an ingredient storage, an artifact storage and a publication board
	private IngredientStorage ingredientStorage;
	private ArtifactStorage artifactStorage;
	
	// game situation
	int round = 1;
	private State state;
	boolean finished = false;
	int turns = 0;
	private boolean isOnline;
	private boolean isPaused;

	//================================================================================
    // Constructors
    //================================================================================


	public KUAlchemistsGame(int numberOfPlayers) {
		
		// set number of players
		this.numberOfPlayers = numberOfPlayers;

		// create ingredientStorage
		ingredientStorage = IngredientStorage.getInstance();

		// create artifactStorage
		artifactStorage = new ArtifactStorage();

		// set game state
		this.state = new ResumeState(this);

		System.out.printf("The game started with %d players.\n", numberOfPlayers);
		isPaused = false;
		isOnline = false;

	}
	
	//================================================================================
    // Accessors
    //================================================================================
	
	
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

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
	
	public void setState(State state) {
        this.state = state;
    }

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	
	//================================================================================
    // Methods
    //================================================================================
	
	
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
		
		// If each player took 3 turns go to the next round and reset the turns.
		turns++;
		if (turns == 3 * players.size()) {
			nextRound();
			turns = 0;
		}
		
		
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
		
		GameEvent event = new GameEvent(this, null, GameEvent.EventID.START_GAME);

		System.out.println("KUAlchemistsGame: Game started. Players are waiting to begin.");
	}

	public void setPlayers(List<String> nameList, List<String> avatarList) {

		for (int i = 0; i < numberOfPlayers; i++) {

			// create new player
			Player newPlayer = new Player(nameList.get(i), avatarList.get(i));
			GameEvent event = new GameEvent(this, newPlayer, GameEvent.EventID.JOIN_GAME);

			// set new player's balance
			newPlayer.setBalance(+10);

			// deal 2 ingredientCards to newPlayer
			for (int j = 0; j < 2; j++) {
				giveRandomIngredientCardToPlayer(newPlayer);
				
			}

			// add newPlayer to players list
			addPlayer(newPlayer);
			System.out.printf("Players set: Player %d: %s\n", i, players.get(i).getUserName());
		}
	}

	private void giveRandomIngredientCardToPlayer(Player player) {
		Ingredient ingredient = ingredientStorage.getIngredient();
		player.addIngredient(ingredient);
	}

	private void nextRound() {
		/*
		 * This method increases the level by one. Some verification may be added
		 */
		if (getRound() < 3) {
			setRound(getRound() + 1);
			System.out.println("KUAlchemistsGame: Next level. Level: " + getRound());
			GameEvent event = new GameEvent(this, null, GameEvent.EventID.LEVEL_UP);
		}
		
		else {
			finish();
		}
	}
	

	private void addPlayer(Player player) {

		this.players.add(player);
	}

	public void finish() {

		setFinished(true);
		Player player = showWinner();
		GameEvent events = new GameEvent(this, player, GameEvent.EventID.FINISH_GAME);
		//TODO Doesn't follow ui separation ??
		new EndGameFrame(this, player);

	}

	private Player showWinner() {
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

	public void pause() 
	{
		state.pause();
	}

	public void resume() 
	{
		state.resume();
	}


	


	public boolean isOnline() {
		return isOnline;
	}


	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}


	public boolean isPaused() {
		return isPaused;
	}


	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}


	public static Client getClient() {
		return client;
	}


	public static void setClient(Client client) {
		KUAlchemistsGame.client = client;
	}
	
	

}
