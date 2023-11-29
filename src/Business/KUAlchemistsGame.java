package Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  

public class KUAlchemistsGame {

	int numberOfPlayers;
	List<Player> players;
	Player currentPlayer;
	int level = 0;
	boolean paused = false;
	IngredientStorage ingredientStorage;
	
	public KUAlchemistsGame(int numberOfPlayers) 
	{
		// set number of players
		this.numberOfPlayers = numberOfPlayers;
		System.out.printf("The game is created with %d players.\n", numberOfPlayers);
	}
	
	private void setPlayers(int numberOfPlayers){
		
		/*
		 * We should handle input in a separate class/interface
		 * But for now, let's do it here.
		 * */
		
		String name;
		String avatar;
		Scanner playerScanner = new Scanner(System.in);
		
		for (int i = 0; i < numberOfPlayers; i++) 
		{

			// take player information
			System.out.println("Enter player username: ");
			name = playerScanner.next(); 		// input is a string ( one word )
			System.out.println("Enter player avatar: ");
			avatar = playerScanner.next();  	// input is an string
			
			// add new player
			players.add(new Player(name, avatar));
		}
		
	}
	
	
	public void play()
	{
		// take player information
		setPlayers(numberOfPlayers);
				
		// start game
		startGame(players);
	}
	
	private void startGame(List<Player> players) {
		this.players = players;
		
		System.out.println("The game has started. Players are waiting to begin.");
		for (Player player: players){
			System.out.printf("Player One: %s %s\n", player.getUserName(), player.getAvatarPath());
		}
		
		ingredientStorage = new IngredientStorage();
		
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
