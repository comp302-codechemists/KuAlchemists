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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import networking.Client;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.MakeExperimentController;
import Controllers.TransmuteController;
import DesignSystem.ArtisticButton;
import soundEffects.PlaySong;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class MakeExperimentFrame extends FunctionalFrame {
	
	private MakeExperimentController controller;
	private ArrayList<String> selectedIngredients = new ArrayList<>();
	private JButton returnBtn;
	private JComboBox<String> comboBox = new JComboBox<>();
	private DefaultComboBoxModel<String> model;
	boolean haveMagicMortar = (game.currentPlayer.getNumberOfIngreientToBeRemovedWhileExperimenting() == 2 ? false : true);
	ArtisticButton testOnStudentButton;
	ArtisticButton testOnYourselfButton;
	
	public MakeExperimentFrame(KUAlchemistsGame game) {
		super(game);
		this.setBackground("/FunctionalBackgroundImages/makeExperimentBackground.png");
		this.setIngredients();
		this.setWhereToTestButtons();
		this.setReturnBtn();
		this.setIngredientToKeep();
		this.setAnimation();
		String[] initialOption = {"Please select the ingredient to keep"};
		this.model = new DefaultComboBoxModel<>(initialOption);
		

	}
	
	private void setAnimation() {
		
		JPanel animationPanel = new JPanel();
		getContentPane().add(animationPanel, BorderLayout.SOUTH);
		animationPanel.setLayout(null);
		animationPanel.setBounds(0, 500, 1200, 320);
		animationPanel.setOpaque(false);
		
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/bubbles.gif"));
	    Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	    JLabel bubble1 = new JLabel(new ImageIcon(image));
	    JLabel bubble2 = new JLabel(new ImageIcon(image));
	    JLabel bubble3 = new JLabel(new ImageIcon(image));
	    JLabel bubble4 = new JLabel(new ImageIcon(image));
	    bubble1.setBounds(0, 0, 100, 100);
	    
	    
	    animationPanel.add(bubble1);
	    backgroundPanel.add(animationPanel);
	    
		
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
                MakeExperimentFrame.this.dispose();
            }
        });	
		
	}
	
	private void setIngredientToKeep() {
		
		controller = new MakeExperimentController(game);
										        
		backgroundPanel.add(comboBox);
		
		comboBox.setBounds(500, 350, 230, 20);
		
		comboBox.setVisible(haveMagicMortar);
		
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
                PlaySong.play("MakePotion");
            	makeExperiment(1);
            }
        });
		testOnYourselfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlaySong.play("MakePotion");
            	makeExperiment(2);
            }
        });
	}
	
	private void makeExperiment(int selection)
	{
		Player currPlay = game.getCurrentPlayer();
		controller = new MakeExperimentController(game);
		
		if (selectedIngredients.size() != 2)
		{
			JOptionPane.showMessageDialog(null, "", "Please select 2 ingredients", JOptionPane.WARNING_MESSAGE, null);
			return;
		}
		
		if(haveMagicMortar)
		{
			
			if(comboBox.getSelectedItem().toString().equals("Please select the ingredient to keep")) 
			{
				JOptionPane.showMessageDialog(null, "", "Please select ingredient to keep", JOptionPane.WARNING_MESSAGE, null);
				return;	
			}
						
		}
						
				
		String resultToken;
		
		if (selection == 1)
		{
			resultToken = (haveMagicMortar ?
							controller.handleExperiment(selectedIngredients, 1, comboBox.getSelectedItem().toString()) :
								controller.handleExperiment(selectedIngredients, 1));
		}
		else
		{
			resultToken = (haveMagicMortar ?
							controller.handleExperiment(selectedIngredients, 2, comboBox.getSelectedItem().toString()) :
								controller.handleExperiment(selectedIngredients, 2));
		}

	    String imagePath = "/potionImages/" + resultToken + ".png";

	    ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
	    String notify = "";
	    
	    if (resultToken.contains("-")) {
	    	notify = "Upsy, you created a negative potion.\n";
		    if (selection == 2) {
		    	notify += "You poisioned yourself! \n";
		    	int sick = currPlay.getSicknessLevel();
		    	notify += "Your sickness level: " + sick + ".\n";
		    	
		    	if (sick == 3) {
		    		notify += "You got sick, pay all of your money to hospital to get cured.\n";
		    		notify += "Anything for health...";
		    	}
		    	
		    	
		    }
		    
		    else {
		    	notify += "You poisioned your student! \nYou need to pay for your student's hospital expenses. \nPoor kid...\n";
		    	int balan = currPlay.getBalance() - 1;
		    	notify += "New balance: " + balan + ".";
		    }
	    }
	    else {
	    	notify = "Yay! You didn't create a negative potion!";
	    }
	    
		// Networking handling
		if (KUAlchemistsGame.instance.isOnline()) {
			String messageToSend = "MAKEEXPERIMENT,";
			if (haveMagicMortar) {
				messageToSend += "YES";
			} else {
				messageToSend += "NO";

			}
			for (String ingre : selectedIngredients) {
				messageToSend += ",";
				messageToSend += ingre;
			}
			messageToSend += ",";
			messageToSend += Integer.toString(selection);

			if (haveMagicMortar) {
				messageToSend += ",";
				messageToSend += comboBox.getSelectedItem().toString();
			}

			Client.instance.sendMessage(messageToSend);
		}

		MainGameFrame main = new MainGameFrame(game);
		if (KUAlchemistsGame.instance.isOnline()) {
			main.updatePlayerName(Client.instance.getUsername());
	    	Client.instance.setView(main);
	    	}

	    

	    
	    JOptionPane.showMessageDialog(null, notify, "Experiment Result", JOptionPane.INFORMATION_MESSAGE, icon);
	    
	    // Close the frame
	    MakeExperimentFrame.this.dispose();
			
		
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
	    	
	        JToggleButton ingredientButton = new JToggleButton(); // Use JToggleButton for radio button behavior
	        
	    	ingredientButton.addActionListener(new ActionListener() 
	    	{
	            @Override
	            public void actionPerformed(ActionEvent e) {	            		
	                	 PlaySong.play("ButtonClick");                      	
	            }
	        });
	        
	        ingredientButton.setBackground(null);
	        ingredientButton.setOpaque(false);
	        ingredientButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/ingredientImages/" + ingredient.getName() + ".png"));
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
	        model.addElement(selectedButton.getActionCommand());
	        System.out.println(selectedButton.getActionCommand());
	    } else {
	        selectedButton.setBorder(null);
	        selectedIngredients.remove(selectedButton.getActionCommand());
	        model.removeElement(selectedButton.getActionCommand());
	        System.out.println(selectedButton.getActionCommand() + " unselected");
	    }
	}

	    
	    
/*


	    
*/


}
