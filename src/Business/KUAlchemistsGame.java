package Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  

public class KUAlchemistsGame {

	List<Player> players;
	Player currentPlayer;
	int numberOfPlayers;
	int level;
	boolean paused;
	
	public KUAlchemistsGame(int numberOfPlayers) {
		
		/*
		 * The game will be initialized
		 * after the user enter the 
		 * number of players.
		 * */
		
		this.numberOfPlayers = numberOfPlayers;
	
		/*
		 * We should handle input in a seperate class/interface
		 * But for now, let's do it here.
		 * */
		
		String playerNameOne;
		String playerNameTwo;
		String playerAvatarOne;
		String playerAvatarTwo;
		
		
		Scanner playerScanner = new Scanner(System.in);
		System.out.println("Enter player 1 username: ");
		playerNameOne = playerScanner.next(); 		// input is a string ( one word )
		System.out.println("Enter player 1 avatar: ");
		playerAvatarOne = playerScanner.next();  // input is an string
		System.out.println("Enter player 2 username: ");
		playerNameTwo = playerScanner.next(); 		// input is a string ( one word )
		System.out.println("Enter player 2 avatar: ");
		playerAvatarTwo = playerScanner.next();  // input is an string
		
		Player playerOne = new Player(playerNameOne, playerAvatarOne);
		Player playerTwo = new Player(playerNameTwo, playerAvatarTwo);
		List<Player> players = new ArrayList<Player>();
		players.add(playerOne);
		players.add(playerTwo);
		
		startGame(players);
		
		System.out.printf("The game is initialized with %d players.\n", numberOfPlayers);
	}
	
	
	public void play()
	{
		
	}
	
	private void startGame(List<Player> players) {
		this.players = players;
		
		System.out.println("The game has started.");
		for (Player player: players){
			System.out.printf("Player One: %s %s\n", player.getUserName(), player.getAvatarPath());
		}
		
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
