package Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Business.Potion;
import Controllers.SellPotionController;
import DesignSystem.GameButton;

public class SellPotionFrame extends FunctionalFrame{

	private SellPotionController controller;
	private JLabel selectedAspectLabel;
	private String selectedAspect;
	private ArrayList<String> selectedIngredients = new ArrayList<>();
	private GameButton sellButton;
	
	public SellPotionFrame(KUAlchemistsGame game, Player player) {
		super(game, player);
		setBackground("/FunctionalBackgroundImages/sellPotionBackground.png");
		initializeSelectedAspectLabel();
		setSellButton();
		setAspects();
		setIngredients();
	}
	
	private void setSellButton()
    {
    	sellButton = new GameButton("Sell");
    	sellButton.setBounds(550, 500, 100, 30);
    	backgroundPanel.add(sellButton);
    	
    	if (selectedAspect != null && selectedIngredients.size() == 2)
    	{
    		try {
    			//TODO
    			controller = new SellPotionController(game);
				controller.handleSellPotion("", "", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
	
	private void initializeSelectedAspectLabel()
	{
		selectedAspectLabel = new JLabel();
		selectedAspectLabel.setBounds(80, 320, 150, 170);
        backgroundPanel.add(selectedAspectLabel);
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
	private void setIngredients() 
	{
	    JPanel ingredientPanel = new JPanel(null);
	    ingredientPanel.setBounds(270, 320, 1050, 320);
	    ingredientPanel.setOpaque(false);

	    ButtonGroup ingredientGroup = new ButtonGroup(); // Create a button group

	    int buttonWidth = 100;
	    int buttonHeight = 150;
	    int xOffset = 5;
	    int yOffset = 5;

	    for (int i = 0; i < game.currentPlayer.getIngredients().size(); i++) {
	    	
	    	Ingredient ingredient = game.currentPlayer.getIngredients().get(i);
	    	int ingredientIndex = Ingredient.getIngredientIndex(ingredient.getName());
	    	
	        JToggleButton ingredientButton = new JToggleButton(); // Use JToggleButton for radio button behavior
	        ingredientButton.setBackground(null);
	        ingredientButton.setOpaque(false);
	        ingredientButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/ingredient" + ingredientIndex + ".png"));
	        Image image = imageIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
	        ingredientButton.setIcon(new ImageIcon(image));

	        int xPosition = (buttonWidth + xOffset) * (i % 8);
	        int yPosition = (buttonHeight + yOffset) * (i / 8);

	        ingredientButton.setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
	        ingredientPanel.add(ingredientButton);

	        ingredientGroup.add(ingredientButton); 
	        ingredientButton.setActionCommand(String.valueOf(ingredient.getName())); 
	        ingredientButton.addActionListener(e -> handleIngredientSelection(ingredientButton));
	    }

	    backgroundPanel.setLayout(null);
	    backgroundPanel.add(ingredientPanel);
	}

	private void handleIngredientSelection(JToggleButton selectedButton) {
	    if (selectedButton.isSelected() && !selectedIngredients.contains(selectedButton.getActionCommand())) {
	        selectedButton.setBorder(new LineBorder(Color.green, 3));
	        selectedIngredients.add(selectedButton.getActionCommand());
	        System.out.println(selectedButton.getActionCommand());
	    } else {
	        selectedButton.setBorder(null);
	        selectedIngredients.remove(selectedButton.getActionCommand());
	        System.out.println(selectedButton.getActionCommand() + " unselected");
	    }
	}

}
