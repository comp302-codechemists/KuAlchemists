package Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.MakeExperimentController;
import Controllers.TransmuteController;
import DesignSystem.ArtisticButton;

public class MakeExperimentFrame extends FunctionalFrame {
	
	private MakeExperimentController controller;
	private ArrayList<String> selectedIngredients = new ArrayList<>();
	ArtisticButton testOnStudentButton;
	ArtisticButton testOnYourselfButton;
	
	public MakeExperimentFrame(KUAlchemistsGame game, Player player) {
		super(game, player);
		this.setBackground("/FunctionalBackgroundImages/makeExperimentBackground.png");
		this.setIngredients();
		this.setWhereToTestButtons();
	}

	
	private void setWhereToTestButtons()
	{
		testOnStudentButton = new ArtisticButton("/Images/testOnStudent.png", 200, 200);
		testOnYourselfButton = new ArtisticButton("/Images/testOnYourself.png", 200, 200);
		
		backgroundPanel.add(testOnStudentButton);
		backgroundPanel.add(testOnYourselfButton);
		
		testOnStudentButton.setBounds(360, 420, 200, 200);
		testOnYourselfButton.setBounds(600, 420, 200, 200);
	    
		testOnStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	makeExperiment(1);
            }
        });
		testOnYourselfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	makeExperiment(2);
            }
        });
	}
	
	private void makeExperiment(int selection)
	{
		controller = new MakeExperimentController(game);
		
		if (selectedIngredients.size() != 2)
		{
			JOptionPane.showMessageDialog(null, "", "Please select 2 ingredients", JOptionPane.WARNING_MESSAGE, null);
			return;
		}
		else
		{
			String resultToken;
			
			// TODO there should be two handling method
			if (selection == 1)
			{
				resultToken = controller.handleExperiment(selectedIngredients);
			}
			else
			{
				resultToken = controller.handleExperiment(selectedIngredients);
			}

		    String imagePath = "/potionImages/" + resultToken + ".png";

		    ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
		    JOptionPane.showMessageDialog(null, "", "Experiment Result", JOptionPane.INFORMATION_MESSAGE, icon);
		    
		    // Close the frame
		    new MainGameFrame(game);
		    MakeExperimentFrame.this.dispose();
		}
	}
	
	private void setIngredients() 
	{
	    controller = new MakeExperimentController(game);
	    List<Ingredient> currentIngredients = controller.getPlayersCurrentIngredients();

	    JPanel ingredientPanel = new JPanel(null);
	    ingredientPanel.setBounds(70, 320, 1050, 320);
	    ingredientPanel.setOpaque(false);

	    ButtonGroup ingredientGroup = new ButtonGroup(); // Create a button group

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

	    
	    
/*


	    
*/


}
