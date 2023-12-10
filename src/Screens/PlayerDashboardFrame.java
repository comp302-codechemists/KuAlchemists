package Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Business.Artifact;
import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.ForageController;
import Controllers.TransmuteController;
import Controllers.buyArtifactController;
import DesignSystem.ArtisticButton;
import DesignSystem.DashboardLabel;
import DesignSystem.GameText;

import java.awt.FlowLayout;



public class PlayerDashboardFrame extends GeneralFrame{
	
	/*
	 * A dedicated panel that showcases the current player's avatar, available resources, and actions.
	 * Offers interactive buttons for actions like collecting ingredients, brewing potions, and
	 * submitting publications.
	 */
	private KUAlchemistsGame game;
	private Player player;
	
	private JPanel backgroundPanel;
	private JPanel upperBackground;
	private JPanel bottomBackground;
	private JPanel leftHandBackground;
	private JPanel upperButtonPanel;
	private JPanel leftButtonPanel;
	private JPanel artifactPannel;
	private JPanel ingredientPannel;
	
	List<JRadioButton> leftButtons;
	List<JRadioButton> triangleButtons;
	
	private ButtonGroup triangleButtonGroup;
	private ButtonGroup leftButtonGroup;
	private JButton buyArtifactButton;
	private JButton forageIngredientButton;
	private JButton transmuteIngredientButton;
	private JButton makeExperimentButton;
	private JButton publishTheoryButton;
	private JButton sellPotionButton;
	private JButton debunkTheoryButton;
	private JLabel userGoldInfo;
	private JLabel userAvatar;
	private JLabel reputationLabel;
	private JLabel repPointsLabel;
	private JLabel balanceLabel;
	
	public PlayerDashboardFrame(KUAlchemistsGame game, Player player) 
	{
		super();
		this.game = game;
		this.player = player;
		
		this.setBackground();
		setUpperDeductionBoard();
		setUpperButtons();
		seBottomDeductionBoard();
		setLeftHand();
		setLeftHandButtons();
		setBuyArtifactButton();
		setForageIngredientButton();
		setTransmuteIngredientButton();
		setMakeExperimentButton();
		setPublishTheoryButton();
		setSellPotionButton();
		setDebunkTheoryButton();
		setPlayerNameLabel();
		setPlayerInfo();
		setPlayerArtifacts();
		setPlayerIngredients();
		
	}
	
	private void setPlayerNameLabel()
	{
		System.out.println(game.currentPlayer.getUserName());
		JLabel userNameLabel = new JLabel(game.currentPlayer.getUserName());
        userNameLabel.setForeground(Color.WHITE); // Set text color to white
        userNameLabel.setBounds(1033, 60, 187, 30); // Adjust the position as needed
        userNameLabel.setOpaque(false); // Make the label transparent
        userNameLabel.setFont(new Font("Tahoma", Font.ITALIC, 35));
        backgroundPanel.add(userNameLabel);
        
	}
	
	private void setPlayerInfo() {
		
        
        userAvatar = new JLabel();
        String avatarPath = game.currentPlayer.getAvatarPath();
        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/Images/" + avatarPath + ".png"));
        Image originalImage1 = originalIcon1.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon avatar = new ImageIcon(originalImage1);
        userAvatar.setIcon(avatar);
        userAvatar.setBounds(912, 20, 95, 95);
        backgroundPanel.add(userAvatar);
        
        repPointsLabel = new JLabel("Reputation Points:");
        repPointsLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
        repPointsLabel.setForeground(Color.WHITE);
        repPointsLabel.setBounds(1246, 42, 203, 30);
        backgroundPanel.add(repPointsLabel);
        
        balanceLabel = new JLabel("Balance:");
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
        balanceLabel.setBounds(1246, 82, 203, 30);
        backgroundPanel.add(balanceLabel);
        
        int repPoints = game.currentPlayer.getReputationPoints();
        reputationLabel = new JLabel(Integer.toString(repPoints));
        reputationLabel.setForeground(Color.WHITE);
        reputationLabel.setFont(new Font("Tahoma", Font.ITALIC, 25));
        reputationLabel.setBounds(1434, 34, 66, 42);
        backgroundPanel.add(reputationLabel);
        
        int balance = game.currentPlayer.getBalance();
        userGoldInfo = new JLabel(Integer.toString(balance));
        userGoldInfo.setForeground(Color.WHITE);
        userGoldInfo.setFont(new Font("Tahoma", Font.ITALIC, 25));
        userGoldInfo.setBounds(1434, 70, 68, 42);
        backgroundPanel.add(userGoldInfo);
	}
	
	private void setBackground() {
    	
		backgroundPanel = super.setBackground("/Images/playerDashboardBackground.png");
        getContentPane().add(backgroundPanel);
	}
	
	private void setUpperDeductionBoard() {
		
		upperBackground = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Image originalImage = new ImageIcon(this.getClass().getResource("/Images/deduction_board_up.png")).getImage();

                int originalWidth = originalImage.getWidth(null);
                int originalHeight = originalImage.getHeight(null);
                int maxWidth = getWidth();
                int maxHeight = getHeight();

                double scale = Math.min((double) maxWidth / originalWidth, (double) maxHeight / originalHeight);
                int scaledWidth = (int) (originalWidth * scale);
                int scaledHeight = (int) (originalHeight * scale);

                int x = (getWidth() - scaledWidth) / 2;
                int y = (getHeight() - scaledHeight) / 2;
                g.drawImage(originalImage, x, y, scaledWidth, scaledHeight, this);
            }
    	};
    	
        upperBackground.setLocation(40, 20);
        upperBackground.setLayout(null);
        upperBackground.setOpaque(false);
        upperBackground.setSize(new Dimension(789, 466));       
        backgroundPanel.add(upperBackground);
	}
		
	private void setUpperButtons() {
		
        upperButtonPanel = new JPanel();
        upperButtonPanel.setOpaque(false);
        upperButtonPanel.setLayout(null);
        upperButtonPanel.setBounds(10, 10, 769, 456);
        
        triangleButtonGroup = new ButtonGroup();
        triangleButtons = new ArrayList<JRadioButton>();

        int rows = 7;
        int startX = 372;
        int startY = 20; 

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col <= row; col++) {
                JRadioButton btn = new JRadioButton();
                triangleButtonGroup.add(btn);
                triangleButtons.add(btn);
                int x = startX - row * 65 / 2 + col * 65;
                int y = startY + row * 57;
                btn.setBounds(x, y, 20, 20);
                upperButtonPanel.add(btn);
            }
        }   
        
        upperBackground.add(upperButtonPanel);
	}
	
	private void seBottomDeductionBoard() 
	{
	    bottomBackground = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);

	            Image originalImage = new ImageIcon(this.getClass().getResource("/Images/deduction_board_bottom.png")).getImage();

	            // Calculate the scaled width and height while maintaining the aspect ratio
	            int originalWidth = originalImage.getWidth(null);
	            int originalHeight = originalImage.getHeight(null);
	            int maxWidth = getWidth();
	            int maxHeight = getHeight();

	            double scale = Math.min((double) maxWidth / originalWidth, (double) maxHeight / originalHeight);
	            int scaledWidth = (int) (originalWidth * scale);
	            int scaledHeight = (int) (originalHeight * scale);

	            // Calculate the position to center the scaled image
	            int x = (getWidth() - scaledWidth) / 2;
	            int y = (getHeight() - scaledHeight) / 2;

	            // Draw the scaled image
	            g.drawImage(originalImage, x, y, scaledWidth, scaledHeight, this);
	        }
	    };
	    bottomBackground.setBounds(40, 490, 789, 297);
	    backgroundPanel.add(bottomBackground);
	    bottomBackground.setLayout(null);
	    bottomBackground.setOpaque(false);
	}
	
	private void setLeftHand() 
	{
		leftHandBackground = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Image originalImage = new ImageIcon(this.getClass().getResource("/Images/leftHand.png")).getImage();

                int originalWidth = originalImage.getWidth(null);
                int originalHeight = originalImage.getHeight(null);
                int maxWidth = getWidth();
                int maxHeight = getHeight();

                double scale = Math.min((double) maxWidth / originalWidth, (double) maxHeight / originalHeight);
                int scaledWidth = (int) (originalWidth * scale);
                int scaledHeight = (int) (originalHeight * scale);

                int x = (getWidth() - scaledWidth) / 2;
                int y = (getHeight() - scaledHeight) / 2;
                g.drawImage(originalImage, x, y, scaledWidth, scaledHeight, this);
            }
    	};
    	
        leftHandBackground.setLocation(40, 20);
        leftHandBackground.setLayout(null);
        leftHandBackground.setOpaque(false);
        leftHandBackground.setSize(new Dimension(85, 466));       
        backgroundPanel.add(leftHandBackground);
		
	}

	private void setLeftHandButtons() {
		leftButtonPanel = new JPanel();
        leftButtonPanel.setLocation(10, 50);
        leftButtonPanel.setLayout(null);
        leftButtonPanel.setOpaque(false);
        leftButtonPanel.setSize(new Dimension(85, 466)); 
        
        int buttonCount = 7;
        leftButtonGroup = new ButtonGroup();
        leftButtons = new ArrayList<JRadioButton>();
        
        for (int i = 0; i < buttonCount; i++) {
        	JRadioButton btn = new JRadioButton();
        	leftButtonGroup.add(btn);
        	leftButtons.add(btn);
        	leftButtonPanel.add(btn);
        	btn.setBounds(50, i*65, 20, 20);
        	
        }
        leftHandBackground.add(leftButtonPanel);   	
		
	}
	
	private void setPlayerIngredients() {
		
        ingredientPannel = new JPanel();
        ingredientPannel.setBounds(839, 136, 653, 123);
        ingredientPannel.setOpaque(false);
        backgroundPanel.add(ingredientPannel);
        ingredientPannel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setForeground(Color.WHITE);
        ingredientsLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        ingredientsLabel.setBounds(839, 125, 85, 13);
        backgroundPanel.add(ingredientsLabel);

        
        

        for (Ingredient ingredient : player.getIngredients()) {
        	int index = Ingredient.getIngredientIndex(ingredient.getName());
	    	Image image = new ImageIcon(this.getClass().getResource("/Images/ingredient" + index + ".png")).getImage();
	    	Image newImage = image.getScaledInstance(60, 100, Image.SCALE_DEFAULT);
	    	ImageIcon icon = new ImageIcon(newImage);
	    	JLabel picLabel = new JLabel(icon);
	    	ingredientPannel.add(picLabel);
        }
        
		
	}
	
	private void setPlayerArtifacts() {
		
        artifactPannel = new JPanel();
        artifactPannel.setBounds(839, 269, 653, 123);
        backgroundPanel.add(artifactPannel);
        artifactPannel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        artifactPannel.setOpaque(false);
        artifactPannel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel artifactsLabel = new JLabel("Artifacts:");
        artifactsLabel.setForeground(Color.WHITE);
        artifactsLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        artifactsLabel.setBounds(839, 256, 85, 13);
        backgroundPanel.add(artifactsLabel);
        
        for (Artifact artifact : player.getArtifacts()) {
	    	Image image = new ImageIcon(this.getClass().getResource("/Images/" + artifact.getName() + ".png")).getImage();
	    	Image newImage = image.getScaledInstance(60, 100, Image.SCALE_DEFAULT);
	    	ImageIcon icon = new ImageIcon(newImage);
	    	JLabel picLabel = new JLabel(icon);
	    	artifactPannel.add(picLabel);
        }
		
	}
	


	 private void setForageIngredientButton() 
	 {
	        forageIngredientButton = new ArtisticButton("/Images/forage-ingredient.png");
	        forageIngredientButton.setBounds(929, 391, 141, 160);
	        backgroundPanel.add(forageIngredientButton);
	        
			//addActionListener is an example of Observer Pattern in GoF
	        forageIngredientButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new ForageIngredientFrame(game, player);
	                PlayerDashboardFrame.this.dispose();
	            }
	        });
	        
	        JLabel forageLabel = new DashboardLabel("Forage for Ingredient");
	        forageLabel.setBounds(901, 547, 200, 30);backgroundPanel.add(forageLabel);
	    }
	 
	 private void setTransmuteIngredientButton() 
	 {
	        transmuteIngredientButton = new ArtisticButton("/Images/transmute-ingredient.png");
	        transmuteIngredientButton.setBounds(1285, 391, 141, 160);
	        backgroundPanel.add(transmuteIngredientButton);

	        transmuteIngredientButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new TransmuteIngredientFrame(game, player);
	                PlayerDashboardFrame.this.dispose();
	            }
	        });
	        
	        JLabel label = new DashboardLabel("Transmute Ingredient");
	        label.setBounds(1272, 547, 200, 30); 
	        backgroundPanel.add(label);
	    }

		
		private void setBuyArtifactButton() 
		{
			buyArtifactButton = new ArtisticButton("/Images/buy-artifact.png");
			buyArtifactButton.setBounds(1359, 587, 141, 160);
	        backgroundPanel.add(buyArtifactButton);

	        buyArtifactButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	new BuyArtifactFrame(game, player);
	            	PlayerDashboardFrame.this.dispose();
	            }
	        });
	        
	        JLabel label = new DashboardLabel("Buy Artifact");
	        label.setBounds(1380, 744, 200, 30); 
	        backgroundPanel.add(label);
		}
		

		private void setMakeExperimentButton()
		{
			makeExperimentButton = new ArtisticButton("/Images/make-experiment.png");
			makeExperimentButton.setBounds(1111, 391, 141, 160);
	        backgroundPanel.add(makeExperimentButton);

	        makeExperimentButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO
	            }
	        });
	        
	        JLabel label = new DashboardLabel("Make Experiment");
	        label.setBounds(1103, 547, 200, 30); 
	        backgroundPanel.add(label);
		}

		private void setPublishTheoryButton()
		{
			publishTheoryButton = new ArtisticButton("/Images/publish-theory.png");
			publishTheoryButton.setBounds(1194, 587, 141, 160);
	        backgroundPanel.add(publishTheoryButton);

	        publishTheoryButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO
	            }
	        });
	        
	        JLabel label = new DashboardLabel("Publish Theory");
	        label.setBounds(1204, 744, 200, 30); 
	        backgroundPanel.add(label);
		}
		
		private void setSellPotionButton()
		{
			sellPotionButton = new ArtisticButton("/Images/sell-potion.png");
			sellPotionButton.setBounds(1011, 587, 141, 160);
	        backgroundPanel.add(sellPotionButton);

	        sellPotionButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO
	            }
	        });
	        
	        JLabel label = new DashboardLabel("Sell Potion");
	        label.setBounds(1033, 744, 200, 30);
	        backgroundPanel.add(label);
		}
		
		private void setDebunkTheoryButton()
		{
			debunkTheoryButton = new ArtisticButton("/Images/debunk-theory.png");
			debunkTheoryButton.setBounds(849, 587, 141, 160);
	        backgroundPanel.add(debunkTheoryButton);

	        debunkTheoryButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO
	            }
	        });
	        

			JLabel label = new DashboardLabel("Debunk Theory");
			label.setBounds(849, 744, 200, 30); // Adjust the position as needed
			backgroundPanel.add(label);
			
		}
}



