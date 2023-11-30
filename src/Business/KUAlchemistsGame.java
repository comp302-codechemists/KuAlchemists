package Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  

public class KUAlchemistsGame {

	public static Player currentPlayer;
	
	int numberOfPlayers;
	IngredientStorage ingredientStorage;
	ArtifactStorage artifactStorage;
	List<Player> players = new ArrayList<Player>();
	int level = 0;
	boolean paused = false;
	
	public KUAlchemistsGame(int numberOfPlayers) 
	{
		// set number of players
		this.numberOfPlayers = numberOfPlayers;
		
		// create ingredientStorage
		ingredientStorage = IngredientStorage.getInstance();
				
		// create artifactStorage
		artifactStorage = new ArtifactStorage();
				
				
		System.out.printf("The game is created with %d players.\n", numberOfPlayers);
	}
	
	public void play(String p1name, String p2name, String avatar1, String avatar2)
	{
		// take player information
		setPlayers(p1name, p2name, avatar1, avatar2);
				
		// start game
		startGame();
	}
	
	private void startGame() 
	{			
		// set current player
		currentPlayer = players.get(0);
		
		// shuffle ingredients
		ingredientStorage.shuffleIngredients();
		
		// shuffle artifacts
		artifactStorage.shuffleArtifacts();
		
		System.out.println("The game has started. Players are waiting to begin.");
		for (Player player: players){
			System.out.printf("Player: %s %s\n", player.getUserName(), player.getAvatarPath());
		}
	}
	
	private void setPlayers(String p1name, String p2name, String avatar1, String avatar2){
		
		/*
		 * We should handle input in a separate class/interface
		 * But for now, let's do it here.
		 * */
		
		String name;
		String avatar;
		Scanner playerScanner = new Scanner(System.in);
		
		for (int i = 0; i < 2; i++) 
		{

			/*
			// take player information
			System.out.println("Enter player username: ");
			name = playerScanner.next(); 		// input is a string ( one word )
			System.out.println("Enter player avatar: ");
			avatar = playerScanner.next();  	// input is an string
			*/
			
			// create new player
			Player newPlayer = new Player(p1name, avatar1);
			
			// set new player's balance
			newPlayer.setBalance(+5);
			
			// deal 5 ingredientCards to newPlayer
			for (int j = 0; j < 2; j++)
			{
				giveRandomIngredientCardToPlayer(newPlayer);
			}
			
			// add newPlayer to players list
			players.add(newPlayer);
			
		}
		
		System.out.printf("The players have been set. Player 1: %s, Player 2: %s.\n", players.get(0).getUserName(), players.get(1).getUserName());
	}
	
	private void giveRandomIngredientCardToPlayer(Player player)
	{
	    Ingredient ingredient = ingredientStorage.getRandomIngredient();
		player.addIngredient(ingredient);
	}
		
	private void nextLevel()
	{
		/*
		 * This method increases the level by one.
		 * Some verification may be added
		 * */
		
		this.level++;
	}
	
	private void addPlayer(Player player) {
		
		/*
		 * This method will add a new player
		 * to the game's player list.
		 * Some verification may be added
		 * */
		
		this.players.add(player);
	}
		
	public void finish() {
		
		/*
		 * This method will end the game.
		 * Some verification may be added
		 * */
	}
	
	public Player showWinner()
	{
		/*
		 * This method will return the winner
		 * */
		
		return null;
	}
	
	public void pause() {
		
		/*
		 * This method will pause the game,
		 * if it is not paused already.
		 * */
	}
	
	public void resume() {
		
		/*
		 * This method will resume the game,
		 * if it is not resuming already.
		 * */
	}
}
