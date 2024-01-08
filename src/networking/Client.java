package networking;

import java.awt.Point;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.PauseController;
import Controllers.PlayGameController;
import Controllers.StartGameController;
import Screens.*;
import uiHelpers.MagicFrame;

public class Client {
	public static Client instance;
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String username;
	private JFrame view;
	public static Player playerOfClient;
	
	public  <T extends JFrame> Client (Socket socket,T view) {
		try {
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
			this.socket=socket;
			this.view = view;
			

			listenForMessage();
		}
		catch (IOException e) {
			closeEverything(socket, bufferedReader, bufferedWriter);
		}
	}
	
	
	

	public Socket getSocket() {
		return socket;
	}




	public void setSocket(Socket socket) {
		this.socket = socket;
	}




	public void sendMessage(String messageToSend) {
		try {
			
		
				bufferedWriter.write(messageToSend);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			 }
			catch (IOException e) {
				closeEverything(socket,bufferedReader,bufferedWriter);
				
			}
		
		 }
	

	
	public void listenForMessage() {
		new Thread (new Runnable() {
			public void run() {
				String msgFrom;
				
				while (socket.isConnected()) {
					try {
						msgFrom = bufferedReader.readLine();
						messageProtocol(msgFrom);
						if (username != null)
							System.out.println(" Client named " + username + " recieved this message: " + msgFrom);
						else
							System.out.println("Client recieved this message: " + msgFrom);

					}
					catch (IOException e) {
						closeEverything(socket,bufferedReader, bufferedWriter); 
					}
					
					
				}
			}
		}).start();
	}
	public void messageProtocol(String message) {
		String[] splitArray = message.split(",");
		ArrayList<String> msgList = new ArrayList<>(Arrays.asList(splitArray));
		if (msgList.get(0).equals("JOIN")) {
		
			 
			 Player newPlayer = new Player(msgList.get(1), msgList.get(2));
			 boolean doesContain = false;
			 
			 for (Player player: Player.players) {
				 if (player.getUserName().equals(newPlayer.getUserName())) {
					 doesContain = true;
					 
				 }

			 }
			 if (doesContain == false) {
	            Player.players.add(newPlayer);
	            }
	            }
			 
			 
		if(msgList.get(0).equals("MAINBOARD")) {
			
			
			for (int i = 1; i < ( 1 + Integer.parseInt(msgList.get(1)) ) ; i++) {
				String playerUsername = "Player " + String.valueOf(i);
				String avatarOf = "avatar" + String.valueOf(i);
				Player newPlayer = new Player(playerUsername, avatarOf);
				Player.players.add(newPlayer);
				if (this.username.equals(playerUsername)) {
					this.playerOfClient = newPlayer;
				}

			}
			
			
			System.out.println("Current playerList of Client named " + this.username + " " +  Player.players + "za");
			JoinGameFrame magicFrame = (JoinGameFrame) view;
			magicFrame.dispose();
			
			PlayGameController playGameController = new PlayGameController();
	    	KUAlchemistsGame game = KUAlchemistsGame.getInstance(Player.players.size());
	    	
            StartGameController startGameController = new StartGameController(game);

	    	if (Player.players.size() == 2) {
	    		String p1name = Player.players.get(0).getUserName();
	    		String p1avatar = Player.players.get(0).getAvatarPath();
	    	
            	String p2name = Player.players.get(1).getUserName();
            	String p2avatar = Player.players.get(1).getAvatarPath();
                startGameController.handleStartGame(p1name, p2name, p1avatar, p2avatar);

	    	}
	    	
	    	else if (Player.players.size() == 3) {
	    		String p1name = Player.players.get(0).getUserName();
	    		String p1avatar = Player.players.get(0).getAvatarPath();
	    		
            	String p2name = Player.players.get(1).getUserName();
            	String p2avatar = Player.players.get(1).getAvatarPath();
            	
            	String p3name = Player.players.get(2).getUserName();
            	String p3avatar = Player.players.get(2).getAvatarPath();
                startGameController.handleStartGame(p1name, p2name, p3name, p1avatar, p2avatar, p3avatar);

	    	}
	    	
	    	
	    	else if (Player.players.size() == 4) {
	    		String p1name = Player.players.get(0).getUserName();
	    		String p1avatar = Player.players.get(0).getAvatarPath();
	    		
            	String p2name = Player.players.get(1).getUserName();
            	String p2avatar = Player.players.get(1).getAvatarPath();
            	
            	String p3name = Player.players.get(2).getUserName();
            	String p3avatar = Player.players.get(2).getAvatarPath();
            	
            	String p4name = Player.players.get(3).getUserName();
            	String p4avatar = Player.players.get(3).getAvatarPath();
                startGameController.handleStartGame(p1name, p2name, p3name, p4name, p1avatar, p2avatar, p3avatar, p4avatar);

	    	}
	    	ArrayList<String> nameList = new ArrayList<>();
	    	ArrayList<String> avatarList = new ArrayList<>();
	    	for (int i = 0; i < Player.players.size(); i++) {

	    	    nameList.add(Player.players.get(i).getUserName());
	    	    avatarList.add(Player.players.get(i).getAvatarPath());
	    	} 
	    	game.setPlayers(nameList,avatarList);
	    	game.getPlayers().subList(0, Player.players.size()).clear();
	        
	    	game.setOnline(true);
	    	MainGameFrame main = new MainGameFrame(KUAlchemistsGame.instance);
	    	main.updatePlayerName(this.username);
	    	this.view = main;
	    	System.out.println("ClientName : " + this.username + ", PlayerOfClient name: " + this.playerOfClient.getUserName()  );
	    	main.setVisible(true);

			
			}
		
		if (msgList.get(0).equals("NAME")  ) {
			this.username = msgList.get(1);
			
			
		}
		
		if(msgList.get(0).equals("COUNTDOWN")) {
			SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new CountDownFrame().setVisible(true);
	            }
	        });
		}
		
		if (message.equals("LOBBYJOIN")) {
			 if (view instanceof HostGameFrame) {
				 ((HostGameFrame) this.view).updateChat("A player has joined the server!");
		            
		            
		         }
			 
			 
		}
		
		if (msgList.get(0).equals("TAKETURN")) {
			if (view instanceof MainGameFrame) {
				 ((MainGameFrame) this.view).dispose();
		            
		            
		         }
			this.view.dispose();
			this.view = new PlayerDashboardFrame(KUAlchemistsGame.instance);
			
			
			
		}
		
		if(msgList.get(0).equals("FORAGE")) {
			for (Player player: KUAlchemistsGame.instance.getPlayers()) {
				if(player.getUserName().equals(msgList.get(1)))
					player.addIngredient(Ingredient.getIngredient(msgList.get(2)));
			}
			this.view.dispose();
			this.view = new MainGameFrame(KUAlchemistsGame.instance);
		}
		
		if(msgList.get(0).equals("ENDTURN")) {
			KUAlchemistsGame.instance.nextPlayer();
			String userName = KUAlchemistsGame.instance.currentPlayer.getUserName();
        	KUAlchemistsGame.instance.client.sendMessage("SPESIFIC,TAKETURN," + userName);
        	this.view.dispose();
			this.view = new MainGameFrame(KUAlchemistsGame.instance);
		}
		
		
		
		if (msgList.get(0).equals("PAUSE")) {
			if (KUAlchemistsGame.instance.isPaused() == false) {
				KUAlchemistsGame.instance.pause();

			}
		}
		
		if (msgList.get(0).equals("RESUME")) {
			if (KUAlchemistsGame.instance.isPaused() == true) {
				PauseController controller = new PauseController();
	            controller.pauseHandler();

			} 
			
		}
			 
			
		}
			 
	public void setUpGame() {
		
		ArrayList<String> nameList = new ArrayList<>();
    	ArrayList<String> avatarList = new ArrayList<>();
    	for (int i = 0; i < Player.players.size(); i++) {


    	    nameList.add(Player.players.get(i).getUserName());
    	    avatarList.add(Player.players.get(i).getAvatarPath());
    	} 
		
		PlayGameController playGameController = new PlayGameController();
    	KUAlchemistsGame game = playGameController.playGame(ClientHandler.clientHandlers.size());
        game.setNumberOfPlayers(Server.playerCount);


    	
        StartGameController startGameController = new StartGameController(game);

    	if (Player.players.size() == 2) {
    		String p1name = Player.players.get(0).getUserName();
    		String p1avatar = Player.players.get(0).getAvatarPath();
    	
        	String p2name = Player.players.get(1).getUserName();
        	String p2avatar = Player.players.get(1).getAvatarPath();
            startGameController.handleStartGame(p1name, p2name, p1avatar, p2avatar);

    	}
    	
    	else if (Player.players.size() == 3) {
    		String p1name = Player.players.get(0).getUserName();
    		String p1avatar = Player.players.get(0).getAvatarPath();
    		
        	String p2name = Player.players.get(1).getUserName();
        	String p2avatar = Player.players.get(1).getAvatarPath();
        	
        	String p3name = Player.players.get(2).getUserName();
        	String p3avatar = Player.players.get(2).getAvatarPath();
            startGameController.handleStartGame(p1name, p2name, p3name, p1avatar, p2avatar, p3avatar);

    	}
    	
    	
    	else if (Player.players.size() == 4) {
    		String p1name = Player.players.get(0).getUserName();
    		String p1avatar = Player.players.get(0).getAvatarPath();
    		
        	String p2name = Player.players.get(1).getUserName();
        	String p2avatar = Player.players.get(1).getAvatarPath();
        	
        	String p3name = Player.players.get(2).getUserName();
        	String p3avatar = Player.players.get(2).getAvatarPath();
        	
        	String p4name = Player.players.get(3).getUserName();
        	String p4avatar = Player.players.get(3).getAvatarPath();
            startGameController.handleStartGame(p1name, p2name, p3name, p4name, p1avatar, p2avatar, p3avatar, p4avatar);

    	}
    
		
	}
	
		
	public void closeEverything(Socket socket, BufferedReader buffreader, BufferedWriter buffwriter) {
		try {
			if (buffreader != null)
				buffreader.close();
			
			if (buffwriter != null)
				buffwriter.close();
			
			if (socket != null)
				socket.close();
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JFrame getView() {
		return view;
	}
	
	


	public void setView(JFrame view) {
		this.view = view;
	}




	public static Client getInstance() {
		return instance;
	}




	public static void setInstance(Client instance) {
		Client.instance = instance;
	}




	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}




	public void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}




	public BufferedWriter getBufferedWriter() {
		return bufferedWriter;
	}




	public void setBufferedWriter(BufferedWriter bufferedWriter) {
		this.bufferedWriter = bufferedWriter;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public Player getPlayerOfClient() {
		return playerOfClient;
	}




	public void setPlayerOfClient(Player playerOfClient) {
		this.playerOfClient = playerOfClient;
	}
	
	
	
		
		
} 