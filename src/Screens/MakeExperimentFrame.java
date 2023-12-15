package Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.MakeExperimentController;
import DesignSystem.ArtisticButton;

public class MakeExperimentFrame extends FunctionalFrame {
	
	private MakeExperimentController controller;
	private ArrayList<Integer> chosenIngredients = new ArrayList<>();

	public MakeExperimentFrame(KUAlchemistsGame game, Player player) {
		super(game, player);
		this.setBackground("/FunctionalBackgroundImages/makeExperimentBackground.png");
		this.setIngredients();
		this.setWhereToTestButtons();
	}

	private void setIngredients() 
	{
	    controller = new MakeExperimentController(game);
	    List<Ingredient> currentIngredients = controller.getPlayersCurrentIngredients();

	    JPanel ingredientPanel = new JPanel(null);
	    ingredientPanel.setBounds(70, 220, 1050, 320);
	    ingredientPanel.setOpaque(false);

	    int buttonWidth = 100;
	    int buttonHeight = 150;
	    int xOffset = 5;
	    int yOffset = 5;

	    for (int i = 0; i < currentIngredients.size(); i++) {
	    	
	    	Ingredient ingredient = currentIngredients.get(i);
	    	int ingredientIndex = Ingredient.getIngredientIndex(ingredient.getName());
	    	
	        JToggleButton ingredientButton = new JToggleButton(); // Use JToggleButton for radio button behavior
	        ingredientButton.setBackground(null);
	        ingredientButton.setOpaque(false);
	        ingredientButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
	        ingredientButton.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	           
	            }

	            @Override
	            public void mousePressed(MouseEvent e) {
	            }

	            @Override
	            public void mouseReleased(MouseEvent e) {
	                // Handle mouse release event if needed
	            }

	            @Override
	            public void mouseEntered(MouseEvent e) {
	                // Change button appearance on mouse enter (hover effect)
	            	ingredientButton.setBorder(new LineBorder(Color.green, 2));
	            	setForeground(Color.green);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                // Restore button appearance on mouse exit
	            	ingredientButton.setBorder(new LineBorder(Color.white, 2));
	            	setForeground(Color.white);
	            }
			});
	        
	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/ingredient" + ingredientIndex + ".png"));
	        Image image = imageIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
	        ingredientButton.setIcon(new ImageIcon(image));

	        int xPosition = (buttonWidth + xOffset) * (i % 8);
	        int yPosition = (buttonHeight + yOffset) * (i / 8);

	        ingredientButton.setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
	        ingredientPanel.add(ingredientButton);

	    }

	    backgroundPanel.setLayout(null);
	    backgroundPanel.add(ingredientPanel);
	}
	
	private void setWhereToTestButtons()
	{
		ArtisticButton testOnStudentButton = new ArtisticButton("/Images/testOnStudent.png", 200, 200);
		ArtisticButton testOnYourselfButton = new ArtisticButton("/Images/testOnYourself.png", 200, 200);
		
		backgroundPanel.add(testOnStudentButton);
		backgroundPanel.add(testOnYourselfButton);
		

		testOnStudentButton.setBounds(360, 420, 200, 200);
		testOnYourselfButton.setBounds(600, 420, 200, 200);
	}

}
