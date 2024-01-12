package Screens;
import javax.swing.*;

import Business.Artifact;
import Business.Ingredient;
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
	    	int selected = Server.playerCount;
	    	KUAlchemistsGame game = playGameController.playGame(selected);
	      
	    	game = KUAlchemistsGame.getInstance(Server.playerCount);
	    	KUAlchemistsGame.instance.setClient(Client.instance);
		    KUAlchemistsGame.instance.setOnline(true);
            StartGameController startGameController = new StartGameController(game);
           

	    	if (Server.playerCount == 2) {
	    		String p1name = Player.players.get(0).getUserName();
	    		String p1avatar = Player.players.get(0).getAvatarPath();
	    	
            	String p2name = Player.players.get(1).getUserName();
            	String p2avatar = Player.players.get(1).getAvatarPath();
                startGameController.handleStartGame(p1name, p2name, p1avatar, p2avatar);

	    	}
	    	
	    	else if (Server.playerCount == 3) {
	    		String p1name = Player.players.get(0).getUserName();
	    		String p1avatar = Player.players.get(0).getAvatarPath();
	    		
            	String p2name = Player.players.get(1).getUserName();
            	String p2avatar = Player.players.get(1).getAvatarPath();
            	
            	String p3name = Player.players.get(2).getUserName();
            	String p3avatar = Player.players.get(2).getAvatarPath();
                startGameController.handleStartGame(p1name, p2name, p3name, p1avatar, p2avatar, p3avatar);

	    	}
	    	
	    	
	    	else if (Server.playerCount == 4) {
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
	    	
	    
	    	if (ClientHandler.clientHandlers.size() == KUAlchemistsGame.instance.getNumberOfPlayers()) {
	    	
	    	KUAlchemistsGame.instance.setOnline(true);
	    	
	    	this.dispose();
	    	
	    	
	    	
	    	for (ClientHandler client : ClientHandler.clientHandlers) {
	    		client.broadCastItself("UPDATENAME," + client.clientUsername);
	    	}
	    	ClientHandler.clientHandlers.get(0).sendAllTheirNames();
	    	ClientHandler.clientHandlers.get(0).broadCastMessage("MAINBOARD," + String.valueOf(Server.playerCount));
	    	
	    	
	    	String ArtifactMessage = "ARTIFACTSTORAGE";
	    	String IngredientMessage = "INGREDIENTSTORAGE";
	    	for (Artifact artiz : KUAlchemistsGame.instance.getArtifactStorage().artifactList) {
	    		 ArtifactMessage += "," + artiz.getName();
	    	}
	    	
	    	for (Ingredient ingredient : KUAlchemistsGame.instance.getIngredientStorage().ingredientList) {
	    		IngredientMessage += "," ;
	    		 IngredientMessage += ingredient.getName();
	    	}
	    	
	    	String playerIngredients = "PLAYERINGREDIENTS";
	    	
	    	String player1Ingredients = ",Player 1";
	    	String player2Ingredients = ",Player 2";
	    	String player3Ingredients = ",Player 3";
	    	String player4Ingredients = ",Player 4";
	    	
	    	for (Ingredient ingre1 : KUAlchemistsGame.instance.getPlayers().get(0).getIngredients()  ) {
	    		player1Ingredients += ",";
	    		player1Ingredients += ingre1.getName();
	    		
	    	}
	    	System.out.println("Sending Ingredient List for Player 1: " +  KUAlchemistsGame.instance.getPlayers().get(0).getIngredients());
	    	Client.instance.sendMessage(playerIngredients+player1Ingredients);

	    	
	    	for (Ingredient ingre2 : KUAlchemistsGame.instance.getPlayers().get(1).getIngredients()  ) {
	    		player2Ingredients += ",";
	    		player2Ingredients += ingre2.getName();
	    	}
	    	System.out.println("Sending Ingredient List for Player 2: " +  KUAlchemistsGame.instance.getPlayers().get(1).getIngredients());
	    	Client.instance.sendMessage(playerIngredients+player2Ingredients);

	    	if (KUAlchemistsGame.instance.getNumberOfPlayers() != 2) {
	    		
	    	for (Ingredient ingre3 : KUAlchemistsGame.instance.getPlayers().get(2).getIngredients()  ) {
	    		player3Ingredients += ",";
	    		player3Ingredients += ingre3.getName();
	    	}
	    	System.out.println("Sending Ingredient List for Player 3: " +  KUAlchemistsGame.instance.getPlayers().get(2).getIngredients());
	    	Client.instance.sendMessage(playerIngredients+player3Ingredients);

	    	}
	    	
	    	if (KUAlchemistsGame.instance.getNumberOfPlayers() == 4) { 
	    	for (Ingredient ingre4 : KUAlchemistsGame.instance.getPlayers().get(3).getIngredients()  ) {
	    		player4Ingredients += ",";
	    		player4Ingredients += ingre4.getName();
	    	}
	       	System.out.println("Sending Ingredient List for Player 4: " +  KUAlchemistsGame.instance.getPlayers().get(3).getIngredients());

	    	Client.instance.sendMessage(playerIngredients+player4Ingredients);
	    }

	    	ClientHandler.clientHandlers.get(0).broadCastMessage(ArtifactMessage);
	    	ClientHandler.clientHandlers.get(0).broadCastMessage(IngredientMessage);
	    	
	    	
	    	MainGameFrame main = new MainGameFrame(KUAlchemistsGame.instance);

	    	
	    	
	    	ClientHandler.clientHandlers.get(0).broadCastAll("COUNTDOWN");
	    	
	       	main.updatePlayerName("Player 1");
	       	
	    
	    	
	    	Client.instance.setView(main);
	    	main.dispose();
	    	new PlayerDashboardFrame(KUAlchemistsGame.instance);
	    	

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