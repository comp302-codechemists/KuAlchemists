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
import Screens.HostGameFrame;
import Screens.MainGameFrame;
import uiHelpers.MagicFrame;

public class Client {
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String username;
	private JFrame view;
	
	public  <T extends JFrame> Client (String IPAdress,T view) {
		try {
			

			this.socket=new Socket(IPAdress, 1271);
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
			bufferedWriter.write("hehehehe");
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
		            Player newPlayer = new Player(msgList.get(1), msgList.get(2));
		            Player.players.add(newPlayer);

		}
			 
			 
			 
		if(message.equals("MAINBOARD")) {
			HostGameFrame magicFrame = (HostGameFrame) view;
			magicFrame.dispose();
			new MainGameFrame(KUAlchemistsGame.instance);}
			 
			
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
	