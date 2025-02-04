package Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Business.Theory;
import Business.Token;
import Controllers.PublishTheoryController;
import DesignSystem.ArtisticButton;
import DesignSystem.GameButton;
import networking.Client;
import soundEffects.PlaySong;

public class PublishTheoryFrame extends FunctionalFrame {
	
	private PublishTheoryController controller;
	private JLabel selectedMarkerLabel;
	private String selectedMarker;
	private JLabel selectedTheoryLabel;
	private String selectedTheory;
	private GameButton publishButton;
	private JButton returnBtn;

    public PublishTheoryFrame(KUAlchemistsGame game) {
        super(game);
        super.setBackground("/FunctionalBackgroundImages/publishTheoryBackground.png");
        setMarkers();
        setTheoryCards();
        initializeSelectedMarkerLabel();
        initializeSelectedTheoryLabel();
        setPublishButton();
        setReturnBtn();
    }
    
    private void setPublishButton()
    {
    	publishButton = new GameButton("Publish");
    	publishButton.setBounds(500, 500, 100, 30);
    	backgroundPanel.add(publishButton);
    	publishButton.addActionListener(e -> handlePublishButton());    	
    }
    
	private void setReturnBtn() {
		
		returnBtn = new ArtisticButton("/Images/return.png", 60, 60);	
    	returnBtn.setBounds(1100, 30, 60, 60);
		backgroundPanel.add(returnBtn);
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PlaySong.play("ButtonClick");                      	

                new PlayerDashboardFrame(game);
                PublishTheoryFrame.this.dispose();
            }
        });	
		
	}
    
    private void handlePublishButton() {
    	PlaySong.play("PublishTheory");
    	
    	if (selectedMarker != null && selectedTheory != null)
    	{
    		controller = new PublishTheoryController(game);
    		controller.handlePublish(selectedMarker,selectedTheory);
    		MainGameFrame newMain = new MainGameFrame(game);
    	    PublishTheoryFrame.this.dispose();

    	    
    	    if(game.isOnline()) {
    	    	String messageToSend = "PUBLISH," + selectedMarker + "," + selectedTheory;
    	    	Client.instance.sendMessage(messageToSend);
    	    	newMain.updatePlayerName(Client.instance.getUsername());
    	    	Client.instance.setView(newMain);
    	    } 
    	}
    }
    private void initializeSelectedMarkerLabel() 
    {
        selectedMarkerLabel = new JLabel();
        selectedMarkerLabel.setBounds(250, 470, 150, 170);
        backgroundPanel.add(selectedMarkerLabel);
    }
    private void initializeSelectedTheoryLabel() 
    {
    	selectedTheoryLabel = new JLabel();
    	selectedTheoryLabel.setBounds(750, 470, 200, 150);
        backgroundPanel.add(selectedTheoryLabel);
    }
    private void handleMarkerSelection(JToggleButton selectedMarkerButton) 
    {
        if (selectedMarker == null || !selectedMarker.equals(selectedMarkerButton.getActionCommand())) {
            selectedMarker = selectedMarkerButton.getActionCommand();
            // set selected marker image
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/alchemyMarkerImages/" + selectedMarker + ".png"));
        	Image image = imageIcon.getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH);
            selectedMarkerLabel.setIcon(new ImageIcon(image));
            System.out.println(selectedMarker + " selected");
        } 
        else 
        {
            System.out.println(selectedMarker + " unselected");
            selectedMarker = null;
            selectedMarkerButton.setBorder(null);
            selectedMarkerLabel.setText("Selected Marker: "); // Clear label text if unselected
        }
    }
	
	private void setMarkers()
	{
	    JPanel markerPanel = new JPanel(null);
	    markerPanel.setBounds(80, 250, 1000, 100);
	    markerPanel.setOpaque(false);

	    ButtonGroup markerGroup = new ButtonGroup(); // Create a button group

	    int buttonWidth = 75;
	    int buttonHeight = 85;
	    int xOffset = 50;
	    int yOffset = 20;

	    String[] markers = Token.getTokenNames();
	    for (int i = 0; i < markers.length; i++) {
	    	
	    	String markerName = markers[i];
	    	
	        JToggleButton markerButton = new JToggleButton(); 
	    	markerButton.addActionListener(new ActionListener() 
	    	{
	            @Override
	            public void actionPerformed(ActionEvent e) {	            		
	                	 PlaySong.play("ButtonClick");                      	
	            }
	        });
	        
	        markerButton.setBackground(null);
	        markerButton.setContentAreaFilled(false);
	        markerButton.setBorderPainted(false);
	        markerButton.setOpaque(false);

	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/alchemyMarkerImages/" + markerName + ".png"));
	        Image image = imageIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
	        markerButton.setIcon(new ImageIcon(image));

	        int xPosition = (buttonWidth + xOffset) * (i % 8);
	        int yPosition = (buttonHeight + yOffset) * (i / 8);

	        markerButton.setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
	        markerPanel.add(markerButton);

	        markerGroup.add(markerButton); // Add buttons to the group
	        markerButton.setActionCommand(String.valueOf(markerName)); // Set action command for identification
	        markerButton.addActionListener(e -> handleMarkerSelection(markerButton)); // Add action listener
	    }

	    backgroundPanel.setLayout(null);
	    backgroundPanel.add(markerPanel);
	}

	private void setTheoryCards()
	{
		JPanel theoryPanel = new JPanel(null);
		theoryPanel.setBounds(80, 350, 1200, 100);
		theoryPanel.setOpaque(false);

	    ButtonGroup theoryGroup = new ButtonGroup(); // Create a button group

	    int buttonWidth = 66;
	    int buttonHeight = 100;
	    int xOffset = 50;
	    int yOffset = 25;

	   // If there is no theory on that ingredient
	   // show it on the screen
	    int i = 0;
	    for (Ingredient ingredient: Ingredient.ingredients)
	    {
		  if (!Theory.getUnavailableIngredients().contains(ingredient.getName()))
		  {
		   		JToggleButton theoryButton = new JToggleButton(); 
		   		
		    	theoryButton.addActionListener(new ActionListener() 
		    	{
		            @Override
		            public void actionPerformed(ActionEvent e) {	            		
		                	 PlaySong.play("ButtonClick");                      	
		            }
		        });
		    	
		   		theoryButton.setBackground(null);
	    		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/ingredientImages/" + ingredient.getName() + ".png"));
	    		Image image = imageIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);		    		theoryButton.setIcon(new ImageIcon(image));
	
		    	int xPosition = (buttonWidth + xOffset) * (i % 8);
		   		int yPosition = (buttonHeight + yOffset) * (i / 8);
	
		   		theoryButton.setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
		   		theoryPanel.add(theoryButton);
	
		 
		   		theoryGroup.add(theoryButton);
		    	theoryButton.setActionCommand(String.valueOf(ingredient.getName())); 
		   		theoryButton.addActionListener(e -> handleTheorySelection(String.valueOf(ingredient.getName()))); 
		   	}
	    	i += 1;
	    }

	    backgroundPanel.setLayout(null);
	    backgroundPanel.add(theoryPanel);
	}
	
	private void handleTheorySelection(String ingredientName)
	{
		if (selectedTheory == null || !selectedTheory.equals(ingredientName)) {
			selectedTheory = ingredientName;
            // set selected marker image
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/theoryImages/" + ingredientName + ".png"));
        	Image image = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            selectedTheoryLabel.setIcon(new ImageIcon(image));
            System.out.println(selectedTheory + " selected");
        } 
        else 
        {
            System.out.println(selectedTheory + " unselected");
            selectedTheory = null;
            selectedTheoryLabel.setText("Selected Theory: "); 
        }
	}
	
}


