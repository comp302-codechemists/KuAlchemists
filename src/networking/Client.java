package networking;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;

import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.PlayGameController;
import Controllers.StartGameController;
import Screens.*;
import uiHelpers.MagicFrame;

public class Client {
	private Socket socket;
	public static Socket socketStatic;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String username;
	private JFrame view;
	
	public  <T extends JFrame> Client (String IPAdress,T view) {
		try {
			

			this.socket=new Socket(IPAdress, 1271);
			this.socketStatic = this.socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.username = username;
			this.view = view;
			

			listenForMessage();
			sendMessage();
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




	public void sendMessage() {
		try {
			bufferedWriter.write("");
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
			Scanner scanner = new Scanner(System.in);
			while (socket.isConnected()) {
				String messageToSend = scanner.nextLine();
				bufferedWriter.write(username + ": " + messageToSend);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			} }
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
						System.out.println(msgFrom);
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
		System.out.println(msgList);
		if (msgList.get(0).equals("JOIN")) {
			 if (view instanceof HostGameFrame) {
		            HostGameFrame magicFrame = (HostGameFrame) view;
		            magicFrame.updateChat("The player has joined the server!");
		            
		            

		}
			 
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
			 
			 
			 
		if(message.equals("MAINBOARD")) {
			System.out.println(Player.players + "za");
			JoinGameFrame magicFrame = (JoinGameFrame) view;
			magicFrame.dispose();
			System.out.println(ClientHandler.clientHandlers.size() + "dogru");
			
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

	    		System.out.println(i);
	    	    nameList.add(Player.players.get(i).getUserName());
	    	    avatarList.add(Player.players.get(i).getAvatarPath());
	    	} 
	    	game.setPlayers(nameList,avatarList);
	    	game.getPlayers().subList(0, Player.players.size()).clear();
	        
	    	game.setOnline(true);
	    	System.out.println(game.getPlayers() + "client");	
	    	MainGameFrame main = new MainGameFrame(KUAlchemistsGame.instance);
	    	main.setVisible(true);

			
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

	
	
		
		
} 
	