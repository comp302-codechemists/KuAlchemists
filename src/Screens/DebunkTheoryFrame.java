package Screens;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Business.Potion;
import Controllers.DebunkTheoryController;
import Controllers.SellPotionController;
import DesignSystem.GameButton;

public class DebunkTheoryFrame extends FunctionalFrame{

	private DebunkTheoryController controller;
	private JLabel selectedAspectLabel;
	private String selectedAspect;
	private JLabel selectedTheoryLabel;
	private String selectedTheory;
	private GameButton debunkButton;

	
	public DebunkTheoryFrame(KUAlchemistsGame game, Player player) {
		super(game, player);
		setBackground("/FunctionalBackgroundImages/debunkTheoryBackground.png");
		initializeSelectedAspectLabel();
		initializeSelectedTheoryLabel();
		setDebunkButton();
		setAspects();
		setTheoryCards();
	}
	
	private void setDebunkButton()
    {
		debunkButton = new GameButton("Debunk");
		debunkButton.setBounds(550, 500, 100, 30);
    	backgroundPanel.add(debunkButton);
    	
    	if (selectedAspect != null && selectedTheory != null)
    	{
    		try {
    			//TODO
    			controller = new DebunkTheoryController(game);
				//controller.handleDebunk(selectedTheory,selectedAspect);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
	
	
	private void initializeSelectedAspectLabel()
	{
		selectedAspectLabel = new JLabel();
		selectedAspectLabel.setBounds(250, 450, 150, 170);
        backgroundPanel.add(selectedAspectLabel);
	}
	
	private void initializeSelectedTheoryLabel() 
    {
    	selectedTheoryLabel = new JLabel();
    	selectedTheoryLabel.setBounds(750, 450, 200, 150);
        backgroundPanel.add(selectedTheoryLabel);
    }
	
	private void setAspects()
	{
		JPanel aspectPanel = new JPanel(null);
		aspectPanel.setBounds(80, 220, 1200, 100);
		aspectPanel.setOpaque(false);

	    ButtonGroup aspectGroup = new ButtonGroup(); // Create a button group

	    int buttonWidth = 75;
	    int buttonHeight = 85;
	    int xOffset = 70;
	    int yOffset = 20;

	    
	    for (int i = 0; i < Potion.potions.length; i++) {
	    	
	    	String potion = Potion.potions[i];
	    		    	
	        JToggleButton aspectButton = new JToggleButton(); 
	        aspectButton.setBackground(null);
	        aspectButton.setOpaque(false);
	        aspectButton.setContentAreaFilled(false);
	        aspectButton.setBorderPainted(false);
	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/aspectImages/" + potion + ".png"));
	        Image image = imageIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
	        aspectButton.setIcon(new ImageIcon(image));

	        int xPosition = (buttonWidth + xOffset) * (i % 8);
	        int yPosition = (buttonHeight + yOffset) * (i / 8);

	        aspectButton.setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
	        aspectPanel.add(aspectButton);

	        aspectGroup.add(aspectButton);
	        aspectButton.setActionCommand(potion); 
	        aspectButton.addActionListener(e -> handleAspectSelection(potion)); 
	    }

	    backgroundPanel.setLayout(null);
	    backgroundPanel.add(aspectPanel);
	}
	
	private void handleAspectSelection(String aspect)
	{
		if (selectedAspect == null || !selectedAspect.equals(aspect)) {
            selectedAspect = aspect;
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/aspectImages/" + selectedAspect + ".png"));
        	Image image = imageIcon.getImage().getScaledInstance(150, 170, Image.SCALE_SMOOTH);
        	selectedAspectLabel.setIcon(new ImageIcon(image));
            System.out.println(selectedAspect + " selected");
        } 
        else 
        {
            System.out.println(selectedAspect + " unselected");
            selectedAspect = null;
        }
	}
	
	private void setTheoryCards()
	{
		JPanel theoryPanel = new JPanel(null);
		theoryPanel.setBounds(80, 350, 1000, 100);
		theoryPanel.setOpaque(false);

	    ButtonGroup theoryGroup = new ButtonGroup(); // Create a button group

	    int buttonWidth = 100;
	    int buttonHeight = 75;
	    int xOffset = 20;
	    int yOffset = 20;

	    
	    // this will iterate over existing theories
	    // TODO do not forget to change
	    for (int i = 0; i < Ingredient.ingredients.size(); i++) {
	    	
	    	int theoryNo = i + 1;
	    	
	        JToggleButton theoryButton = new JToggleButton(); 
	        theoryButton.setBackground(null);
	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/theory" + theoryNo + ".png"));
	        Image image = imageIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
	        theoryButton.setIcon(new ImageIcon(image));

	        int xPosition = (buttonWidth + xOffset) * (i % 8);
	        int yPosition = (buttonHeight + yOffset) * (i / 8);

	        theoryButton.setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
	        theoryPanel.add(theoryButton);

	        theoryGroup.add(theoryButton);
	        theoryButton.setActionCommand(String.valueOf(theoryNo)); 
	        theoryButton.addActionListener(e -> handleTheorySelection(String.valueOf(theoryNo))); 
	    }

	    backgroundPanel.setLayout(null);
	    backgroundPanel.add(theoryPanel);
	}
	
	private void handleTheorySelection(String selectedTheoryNo)
	{
		System.out.println(selectedTheoryNo);
		if (selectedTheory == null || !selectedTheory.equals(selectedTheoryNo)) {
            // set selected marker image
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/theory" + selectedTheoryNo + ".png"));
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
