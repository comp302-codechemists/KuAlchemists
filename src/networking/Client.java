package networking;

import java.awt.Point;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Business.Artifact;
import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.DebunkTheoryController;
import Controllers.ForageController;
import Controllers.MakeExperimentController;
import Controllers.PauseController;
import Controllers.PlayGameController;
import Controllers.PublishTheoryController;
import Controllers.SellPotionController;
import Controllers.StartGameController;
import Controllers.TransmuteController;
import Controllers.buyArtifactController;
import Exceptions.IngredientNotFoundException;
import Factories.ArtifactFactory;
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
	private int numberOfPlayers;
	
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
						if (username != null) {
							System.out.println(" Client named " + username + " recieved this message: " + msgFrom); 
							}
						else {
							System.out.println("Client recieved this message: " + msgFrom);
						}
						messageProtocol(msgFrom);

					}
					
					catch (IOException e) {
						closeEverything(socket,bufferedReader, bufferedWriter); 
					} catch (IngredientNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
		}).start();
	}
	public void messageProtocol(String message) throws IngredientNotFoundException {
		String[] splitArray = message.split(",");
		ArrayList<String> msgList = new ArrayList<>(Arrays.asList(splitArray));
		
		if (msgList.get(0).equals("NAME")  ) {
			this.username = msgList.get(1);
			
			
		}
		
		else if(msgList.get(0).equals("COUNTDOWN")) {
			SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new CountDownFrame().setVisible(true);
	            }
	        });
		}
		
		else if (message.equals("LOBBYJOIN")) {
			 if (view instanceof HostGameFrame) {
				 ((HostGameFrame) this.view).updateChat("A player has joined the server!");    
		         }
	 		}
		
		else if (msgList.get(0).equals("PUBLISH")) {
			protocolPublish(msgList);
			
		}
		else if(msgList.get(0).equals("RESUME")) {
			KUAlchemistsGame.instance.resume();

		}
		
		else if(msgList.get(0).equals("PAUSE")) {
			KUAlchemistsGame.instance.pause();
			
		}
		
		else if (msgList.get(0).equals("DEBUNK")) {
			protocolDebunk(msgList);

		}
		
		
		else if (msgList.get(0).equals("SELLPOTION")) {
			protocolSellPotion(msgList);
		}
		
		else if (msgList.get(0).equals("TRANSMUTE")) {
			protocolTransmute(msgList);
		}
		
		
		else if (msgList.get(0).equals("PLAYERINGREDIENTS")) {
			protocolPlayerIngredients(msgList);

		}
		else if (message.equals("FORAGE")) {
		  protocolForage(msgList);
		}
		
		else if(msgList.get(0).equals("BUYARTIFACT")) {
			
			protocolBuyArtifact(msgList);
            
		}
		
		else if(msgList.get(0).equals("ELIXIR")) {
			protocolElixir(msgList);
		}
		
		
		
		else if (msgList.get(0).equals("MAKEEXPERIMENT")){
			protocolMakeExperiment(msgList);
		}
		

		
		else if(msgList.get(0).equals("ARTIFACTSTORAGE")) {
			protocolArtifactStorage(msgList);
		}
		
		
		else if (msgList.get(0).equals("INGREDIENTSTORAGE")) {
			protocolIngredientStorage(msgList);

			
		}
		

		else if(msgList.get(0).equals("MAINBOARD")) {
			
		protocolMainboard(msgList);

		} 
		}
			 
	public void protocolPublish(ArrayList<String> msgList) {
		KUAlchemistsGame game = KUAlchemistsGame.getInstance(numberOfPlayers);

		String selectedMarker = msgList.get(1);
		String selectedTheory = msgList.get(2);
		PublishTheoryController controller = new PublishTheoryController(game);
	
		controller.handlePublish(selectedMarker,selectedTheory);
		this.view.dispose();

		if (this.username.equals(game.currentPlayer.getUserName())) {
			this.view.dispose();
			new PlayerDashboardFrame(game);
			
			
		}
		
		else {
			this.view.dispose();
			MainGameFrame newMain = new MainGameFrame(game);
			newMain.updatePlayerName(this.username);
			this.view = newMain;
			
		}
		
	}
	public void protocolDebunk(ArrayList<String> msgList) {
		KUAlchemistsGame game = KUAlchemistsGame.getInstance(numberOfPlayers);
		DebunkTheoryController controller = new DebunkTheoryController(game);
		
		String selectedTheory = msgList.get(1);
		String selectedAspect = msgList.get(2);
		
		
		System.out.println("I am " + this.username + " replicating prev DEBUNK Theory is : " +selectedTheory);
		System.out.println("I am " + this.username + " replicating prev DEBUNK Aspect is : " +selectedAspect);
		 	controller.handleDebunk(selectedTheory, selectedAspect);
		this.view.dispose();
		
		if (this.username.equals(game.currentPlayer.getUserName())) {
			this.view.dispose();
			new PlayerDashboardFrame(game);
			
			
		}
		
		else {
			this.view.dispose();
			MainGameFrame newMain = new MainGameFrame(game);
			newMain.updatePlayerName(this.username);
			this.view = newMain;
			
		}
	}
	public void protocolSellPotion(ArrayList<String> msgList) throws IngredientNotFoundException {
		KUAlchemistsGame game = KUAlchemistsGame.getInstance(numberOfPlayers);

		SellPotionController controller = new SellPotionController(game);
		String firstIngredient = msgList.get(1);
		String secondIngredient = msgList.get(2);
		String promise = msgList.get(3);
		
		controller.handleSellPotion(firstIngredient, secondIngredient, promise);
		this.view.dispose();
	

		if (this.username.equals(game.currentPlayer.getUserName())) {
			this.view.dispose();
			new PlayerDashboardFrame(game);
			
			
		}
		else {
			this.view.dispose();
			MainGameFrame newMain = new MainGameFrame(game);
			newMain.updatePlayerName(this.username);
			this.view = newMain;
			
		}
		
	}
	public void protocolTransmute(ArrayList<String> msgList) {
		KUAlchemistsGame game = KUAlchemistsGame.getInstance(this.numberOfPlayers);
		String theDude = game.currentPlayer.getUserName();
		String theTransmuted = msgList.get(1);
		
		System.out.println(theDude + " before transmute: " + game.currentPlayer.getIngredients());

		TransmuteController controller = new TransmuteController(game);
		controller.handleTransmute(msgList.get(1));
		
		for (Player pla : KUAlchemistsGame.instance.getPlayers()) {
			if(pla.getUserName().equals(theDude)) {
				System.out.println(theDude + " after transmute: " + pla.getIngredients() + " The transmuted: " + theTransmuted);
				System.out.println(theDude + " balance : " + pla.getBalance());
			}
		}
		System.out.println("Now the player is: " + game.currentPlayer.getUserName());
		this.view.dispose();
		if (this.username.equals(game.currentPlayer.getUserName())) {
			new PlayerDashboardFrame(game);
			
			
		}
		
		else {
		
			MainGameFrame newMain = new MainGameFrame(game);
			newMain.updatePlayerName(this.username);
			this.view = newMain;
			
		}
		
	}
	public void protocolPlayerIngredients(ArrayList<String> msgList) {
		String player = msgList.get(1);
		
		List<Ingredient> newIngredients = new ArrayList<Ingredient>();
		
		for (int i = 2 ;  i <msgList.size() ; i++) {
			Ingredient newOne = Ingredient.getIngredient(msgList.get(i));
			newIngredients.add(newOne);
		}
		
		for(Player dude : KUAlchemistsGame.instance.getPlayers()) {
			if (dude.getUserName().equals(player)) {
				System.out.println("Before the update :" + dude.getUserName() + "  " +  dude.getIngredients());

				dude.setIngredients(newIngredients);
				System.out.println("After the update :" + dude.getUserName() + "  " +  dude.getIngredients());

			}
		}
	}
	
	public void protocolForage(ArrayList<String> msgList) {
		  System.out.println(KUAlchemistsGame.instance.currentPlayer.getUserName() + " did the foraging");
		    System.out.println("BEFORE " + KUAlchemistsGame.getInstance(numberOfPlayers).getIngredientStorage().getIngredientList());

		    ForageController controller = new ForageController(KUAlchemistsGame.getInstance(numberOfPlayers));
		    String takenIngredient =  controller.handleForage();
		    System.out.println("AFTER " + KUAlchemistsGame.getInstance(numberOfPlayers).getIngredientStorage().getIngredientList());
		    this.view.dispose();

		    if (!this.username.equals(KUAlchemistsGame.instance.currentPlayer.getUserName())) {
		    	 MainGameFrame newMain = new MainGameFrame(KUAlchemistsGame.instance);
		            newMain.updatePlayerName(this.username);
		            this.view = newMain;
		   
		            
		           
		            this.view.setVisible(true);
		    } 
		    else {
		    	 new PlayerDashboardFrame(KUAlchemistsGame.instance);
		            System.out.println("After foraging done by prev player: " + KUAlchemistsGame.instance.getIngredientStorage().getIngredientList());
		      }
		
	}
	
	public void protocolBuyArtifact(ArrayList<String> msgList) {
		buyArtifactController controller = new buyArtifactController(KUAlchemistsGame.instance);
        String boughtArtifact = controller.buyArtifactHandler();
	    this.view.dispose();
	    
	    if (!this.username.equals(KUAlchemistsGame.instance.currentPlayer.getUserName())) {
	    	 MainGameFrame newMain = new MainGameFrame(KUAlchemistsGame.instance);
	            newMain.updatePlayerName(this.username);
	            this.view = newMain;
	            this.view.setVisible(true);
	    }
	    
	    else {
	    	 new PlayerDashboardFrame(KUAlchemistsGame.instance);
	      }
		
	}
	
	public void protocolElixir(ArrayList<String> msgList) {
		KUAlchemistsGame game = KUAlchemistsGame.getInstance(numberOfPlayers);
		
		List<Ingredient> ingList = game.getIngredientStorage().getIngredientList();
		Ingredient ing1 = ingList.get(0);
		Ingredient ing2 = ingList.get(1);
		Ingredient ing3 = ingList.get(2);
		
		String selectedOrder1 = msgList.get(1);
		String selectedOrder2 = msgList.get(2);
		String selectedOrder3 = msgList.get(3);


		
		ingList.set(Integer.parseInt(selectedOrder1) - 1, ing1);
		ingList.set(Integer.parseInt(selectedOrder2) - 1, ing2);
		ingList.set(Integer.parseInt(selectedOrder3) - 1, ing3);
        game.getIngredientStorage().setIngredientList(ingList);
        
        System.out.println("Recieved Elixir msg as " + this.username + " newIngreList: " + game.getIngredientStorage().ingredientList);;
		
	}
	
	public void protocolMakeExperiment(ArrayList<String> msgList) {
		String theResponsiblePlayer = KUAlchemistsGame.instance.getCurrentPlayer().getUserName();
		MakeExperimentController controller = new MakeExperimentController(KUAlchemistsGame.instance);
		List<String> selectedIngredients = new ArrayList<String>();
	    this.view.dispose();

		String resultToken = "";
;			String keptIngredient = "";
		boolean haveMagicMortar;
		if(msgList.get(1).equals("YES")) {
			haveMagicMortar = true;
			keptIngredient = msgList.get(5);
		}
		else {
			haveMagicMortar = false;
		}
	
		selectedIngredients.add(msgList.get(2));
		selectedIngredients.add(msgList.get(3));

		int selection = Integer.parseInt(msgList.get(4)); 
		
		
		if (selection == 1)
		{
			resultToken = (haveMagicMortar ?
							controller.handleExperiment(selectedIngredients, 1, keptIngredient) :
								controller.handleExperiment(selectedIngredients, 1));
		}
		else
		{
			resultToken = (haveMagicMortar ?
							controller.handleExperiment(selectedIngredients, 2, keptIngredient) :
								controller.handleExperiment(selectedIngredients, 2));
		}
		
		// For displaying the result to other players
		String imagePath = "/potionImages/" + resultToken + ".png";
	    ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
	    JOptionPane.showMessageDialog(null, "", "Experiment Result of " + theResponsiblePlayer, JOptionPane.INFORMATION_MESSAGE, icon);

	    
	    if (!this.username.equals(KUAlchemistsGame.instance.currentPlayer.getUserName())) {
	    	 MainGameFrame newMain = new MainGameFrame(KUAlchemistsGame.instance);
	            newMain.updatePlayerName(this.username);
	            this.view = newMain;		       		   	            
	  } 	
	    
	    else {
	    	 new PlayerDashboardFrame(KUAlchemistsGame.instance);
	    	
	    }
	    
    	 System.out.println("I am client: " + this.username + " Make experiment by previous player: " + theResponsiblePlayer + " The resultToken: " + resultToken);

		
	}
	
	public void protocolArtifactStorage(ArrayList<String> msgList) {
		List<Artifact> newArtiList = new ArrayList<Artifact>();
		for(int i =1; i< msgList.size() ; i++) {

			newArtiList.add(ArtifactFactory.getInstance().getArtifacts(msgList.get(i)));
		}
		KUAlchemistsGame.instance.getArtifactStorage().artifactList = newArtiList;
    	System.out.println(this.getUsername() + " " + Client.playerOfClient.getUserName() + " :" +  KUAlchemistsGame.instance.getArtifactStorage().artifactList);
		
	}

	public void protocolIngredientStorage(ArrayList<String> msgList) {
		List<Ingredient> newIngList = new ArrayList<Ingredient>();
		for(int i =1; i< msgList.size() ; i++) {
			newIngList.add(Ingredient.getIngredient(msgList.get(i)));
		}
		KUAlchemistsGame.instance.getIngredientStorage().ingredientList = newIngList;
    	System.out.println(this.getUsername() + " " + Client.playerOfClient.getUserName() + " :" +  KUAlchemistsGame.instance.getIngredientStorage().ingredientList);
	
}

	public void protocolMainboard(ArrayList<String> msgList) {
		this.numberOfPlayers = Integer.parseInt(msgList.get(1));
		for (int i = 1; i < ( 1 + Integer.parseInt(msgList.get(1)) ) ; i++) {
			String playerUsername = "Player " + String.valueOf(i);
			String avatarOf = "avatar" + String.valueOf(i);
			Player newPlayer = new Player(playerUsername, avatarOf);
			Player.players.add(newPlayer);
			if (this.username.equals(playerUsername)) {
				this.playerOfClient = newPlayer;
			}

		}
		
    	PlayGameController playGameController = new PlayGameController();
    	int selected = this.numberOfPlayers;
    	KUAlchemistsGame game = playGameController.playGame(selected);
      
    	game = KUAlchemistsGame.getInstance(this.numberOfPlayers);
    	KUAlchemistsGame.instance.setClient(Client.instance);
	    KUAlchemistsGame.instance.setOnline(true);
        StartGameController startGameController = new StartGameController(game);
		
		
		System.out.println("Current playerList of Client named " + this.username + " " +  Player.players + "za");
		JoinGameFrame magicFrame = (JoinGameFrame) view;
		magicFrame.dispose();
		


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
    	
    	
    	KUAlchemistsGame.instance.setOnline(true);
    	
        
    	game.setOnline(true);
    	MainGameFrame main = new MainGameFrame(KUAlchemistsGame.instance);
    	main.updatePlayerName(this.username);
    	this.view = main;
    	System.out.println("ClientName : " + this.username + ", PlayerOfClient name: " + this.playerOfClient.getUserName());
    	

    	main.setVisible(true);

		
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
		if (view instanceof MainGameFrame) {
			 ((MainGameFrame) this.view).updatePlayerName(this.username);
	            
	            
	         }
	
		
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