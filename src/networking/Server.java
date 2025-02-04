package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Business.Player;



public class Server  {
	private ServerSocket serverSocket;
	public static int playerCount=0;

	public Server() throws IOException {
		
		this.serverSocket = new ServerSocket(1271);

		startServer(); 

		
	}
	
	public void startServer() throws IOException {
		try {
			
			while (!serverSocket.isClosed() && playerCount<5) {
				Socket socket = serverSocket.accept();
				
				playerCount+=1;
				String username = "Player " + String.valueOf(playerCount);
				System.out.println("SERVER: A new player has connected!");
				String avatarOf = "avatar" + String.valueOf(playerCount);
				if (playerCount != 1) {
				Player.players.add(new Player(username, avatarOf));
				}
				ClientHandler clientHandler = new ClientHandler(socket,username);
				
				Thread thread = new Thread(clientHandler);
				thread.start();
			}
		}
		catch (IOException e) {}

	}

	public void closeServerSocket() {
		try {
			if (serverSocket != null)
				serverSocket.close();
			
		}
		
		catch(IOException e) {
		}
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int maxPlayer) {
		this.playerCount = maxPlayer;
	}
	
	

}