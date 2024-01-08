package Screens;
import javax.swing.*;

import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.PlayGameController;
import Controllers.StartGameController;
import DesignSystem.GameFrame;
import networking.*;
import soundEffects.PlaySong;
import uiHelpers.MagicFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;


public class HostGameFrame extends MagicFrame{
	private JButton startGameButton;
	private JTextArea chatTextArea;
	
	
	private JTextArea IPAdress;
	 public HostGameFrame()
	    {
	    	this(null); 
	    }
	    
	    
	    public HostGameFrame(KUAlchemistsGame game) 
	    {
	    	super(game, GameFrame.welcomeFrameWidth, GameFrame.welcomeFrameHeight);
	    	setBackground("/BackgroundImages/welcomeBackground.png");
	    	
	    	
	    	this.chatTextArea = new JTextArea();
	    	chatTextArea.setEditable(false);
	    	chatTextArea.setBounds(50, 50, 670, 100); 
	    	backgroundPanel.add(chatTextArea);
	        
	    	startGameButton = new JButton("Start Game");
	    	startGameButton.setBounds(335, 160, 100, 30);
	    	
	    	startGameButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	startGameButtonClicked();
	            }
	        });
	    	backgroundPanel.add(startGameButton);
	     }
	    
	    
	    
	    
	 
	    
	    private void startGameButtonClicked() {
	    	PlaySong.play("ButtonClick");
	    	
	      
	    	PlayGameController playGameController = new PlayGameController();
	    	game = KUAlchemistsGame.getInstance(Server.playerCount);
	    	KUAlchemistsGame.instance.setClient(Client.instance);
		    KUAlchemistsGame.instance.setOnline(true);
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
	    	this.game.setPlayers(nameList,avatarList);
	    	if (Player.players.size() == ClientHandler.clientHandlers.size()) {
	    	
	    	game.setOnline(true);
	    	MainGameFrame main = new MainGameFrame(KUAlchemistsGame.instance);
	    	
	    	this.dispose();
	    	for (ClientHandler client : ClientHandler.clientHandlers) {
	    		client.broadCastItself("UPDATENAME," + client.clientUsername);
	    	}
	    	ClientHandler.clientHandlers.get(0).sendAllTheirNames();
	    	ClientHandler.clientHandlers.get(0).broadCastMessage("MAINBOARD," + String.valueOf(Server.playerCount));
	    	
	    	
	    	main.updatePlayerName("Player 1");
	    	System.out.println("--This is the host--  playerName: " + Client.playerOfClient.getUserName() + " ClientName: " + Client.instance.getUsername());


}
	    	else {
	    		System.out.println("We have a problem with player and ClientHandler synchronization dawg");
	    		System.out.println("The ClientHandler size:  " + ClientHandler.clientHandlers.size());
	    		System.out.println("The PlayerList size:  " + Player.players.size());

	    	}
	    	


	    }
	    
	    public void updateChat(String message) {
	    	SwingUtilities.invokeLater(() -> {
	            chatTextArea.append(message +"\n");
	        });
	    }



		public JTextArea getChatTextArea() {
			return chatTextArea;
		}


		public void setChatTextArea(JTextArea chatTextArea) {
			this.chatTextArea = chatTextArea;
		}
	    
	    
	}