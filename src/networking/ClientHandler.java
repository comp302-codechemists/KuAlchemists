package networking;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientHandler implements Runnable{

	public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	public String clientUsername;
	private static Integer z = 0;
	public ClientHandler(Socket socket,String username) {
		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.clientUsername = username;
			clientHandlers.add(this);
			
			
		
		}
		catch (IOException e) {
			
			closeEverything(socket, bufferedReader, bufferedWriter);
		}
		
	}
	
	public ClientHandler(Socket socket) {
		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.clientUsername = bufferedReader.readLine();
			clientHandlers.add(this);
			this.clientUsername = "Player " +  Integer.toString(z);
			z+=1;
			
		
		}
		catch (IOException e) {
			
			closeEverything(socket, bufferedReader, bufferedWriter);
		}
		
	}
	
	
	@Override
	public void run() {
		String messageFromClient;
		
		while (socket.isConnected()) {
			try {
				
				messageFromClient = bufferedReader.readLine();
				String[] splitArray = messageFromClient.split(",");
				ArrayList<String> msgList = new ArrayList<>(Arrays.asList(splitArray));
				
				if(msgList.get(0).equals("SPESIFIC")) {
					int i;
					String restOfMessage = "";
					for (i = 2 ; i < msgList.size(); i++ ) {
						restOfMessage += ",";
						restOfMessage += msgList.get(i);
						
					}
					toSingleClientMessage(msgList.get(1),restOfMessage);
				}
				
				
				else if(msgList.get(0).equals("ALL")) {
					int i;
					String restOfMessage = "";
					for (i = 1 ; i < msgList.size(); i++ ) {
						restOfMessage += ",";
						restOfMessage += msgList.get(i);
						
					}
					broadCastAll(restOfMessage);
					
				}
				else {
				broadCastMessage(messageFromClient);
				}
			}
			catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
				break;
			}
			
		}
	}
	
	public void broadCastMessage(String messageToSend) {
		
		System.out.println("Sending message to everyone except " + clientUsername );

		for (ClientHandler clientHandler: clientHandlers) {
			try {
				if (!clientHandler.clientUsername.equals(clientUsername)) {
					System.out.println(clientHandler.clientUsername + " is recieving the message " +  messageToSend );
					clientHandler.bufferedWriter.write(messageToSend);
					clientHandler.bufferedWriter.newLine();
					clientHandler.bufferedWriter.flush();
				}
				
			}
			catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
				
			}
			
		}
			
		
	}
	
	public void sendAllTheirNames() {
		for (ClientHandler clientHandler: clientHandlers) {
			try {
				
					
					clientHandler.bufferedWriter.write("NAME,"+clientHandler.clientUsername);
					clientHandler.bufferedWriter.newLine();
					clientHandler.bufferedWriter.flush();
				
				
			}
			catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
				
			}
			
		}
		
	}
	
	public void broadCastAll(String messageToSend) {
		System.out.println("Broadcasting to All: " + messageToSend );
		
		for (ClientHandler clientHandler: clientHandlers) {
			try {
				
					
					clientHandler.bufferedWriter.write(messageToSend);
					clientHandler.bufferedWriter.newLine();
					clientHandler.bufferedWriter.flush();
				
				
			}
			catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
				
			}
			
		}
		
	}
	
	public void broadCastItself(String messageToSend) {
		System.out.println(this.clientUsername + " is broadcasting itself: " + messageToSend);
		for (ClientHandler clientHandler: clientHandlers) {
			try {
				if (clientHandler.clientUsername.equals(this.clientUsername)) {
					
					clientHandler.bufferedWriter.write(messageToSend);
					clientHandler.bufferedWriter.newLine();
					clientHandler.bufferedWriter.flush();
				}
				
			}
			catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
				
			}
			
		}
		
	}
	
	public void toSingleClientMessage(String username, String messageToSend) {
		System.out.println("Broadcasting to only " + username + ": " + messageToSend);
		for (ClientHandler clientHandler: clientHandlers) {
			try {
				if (clientHandler.clientUsername.equals(username)) {
					
					clientHandler.bufferedWriter.write(messageToSend);
					clientHandler.bufferedWriter.newLine();
					clientHandler.bufferedWriter.flush();
				}
				
			}
			catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
				
			}
			
		}
		
	}
	
	public void removeClientHandler() {
		clientHandlers.remove(this);
		broadCastMessage("SERVER: " + clientUsername + " has left the game!");
		
	}
	
	public void closeEverything(Socket socket, BufferedReader buffreader, BufferedWriter buffwriter) {
		removeClientHandler();
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
}
