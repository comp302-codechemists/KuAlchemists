package networking;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

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
			this.clientUsername = Integer.toString(z);
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
				broadCastMessage(messageFromClient);
			}
			catch (IOException e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
				break;
			}
			
		}
	}
	
	public void broadCastMessage(String messageToSend) {
		System.out.println(messageToSend);
		for (ClientHandler clientHandler: clientHandlers) {
			try {
				if (!clientHandler.clientUsername.equals(clientUsername)) {
					
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
	
	public void broadCastAll(String messageToSend) {
		
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
		for (ClientHandler clientHandler: clientHandlers) {
			try {
				if (clientHandler.clientUsername.equals(clientUsername)) {
					
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
	
	public void singleMessage(String messageToSend, String username) {
		System.out.println( "ClientHandlers size: " + clientHandlers.size());
		for (ClientHandler clientHandler: clientHandlers) {
			try {
				if (clientHandler.clientUsername.equals(clientUsername)) {
					
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
