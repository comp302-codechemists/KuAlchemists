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

import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.ForageController;
import Controllers.TransmuteController;
import Controllers.buyArtifactController;



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
	}
	
	private void setPlayerNameLabel()
	{
		System.out.println(game.currentPlayer.getUserName());
		JLabel label = new JLabel(game.currentPlayer.getUserName());
        label.setForeground(Color.WHITE); // Set text color to white
        label.setBounds(1100, 40, 120, 30); // Adjust the position as needed
        label.setOpaque(false); // Make the label transparent
        label.setFont(new Font("Tahoma", Font.ITALIC, 35));
        backgroundPanel.add(label);
	}
	
	private void setBackground() {
    	
    	backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                
           	 super.paintComponent(g);
                
                Image image = new ImageIcon(this.getClass().getResource("/Images/playerDashboardBackground.png")).getImage();
                
                // Calculate the scaled width and height to fit the panel
                int panelWidth = getWidth();
                int panelHeight = getHeight();
                
                double imageWidth = image.getWidth(null);
                double imageHeight = image.getHeight(null);
                
                double scaleX = panelWidth / imageWidth;
                double scaleY = panelHeight / imageHeight;
                
                double scale = Math.max(scaleX, scaleY);
                
                int scaledWidth = (int) (imageWidth * scale);
                int scaledHeight = (int) (imageHeight * scale);
                
                // Calculate the x and y positions to center the image
                int x = (panelWidth - scaledWidth) / 2;
                int y = (panelHeight - scaledHeight) / 2;
                
                // Draw the scaled image
                g.drawImage(image, x, y, scaledWidth, scaledHeight, this);
            }
        };
        
        backgroundPanel.setLocation(0, 0);
        backgroundPanel.setSize(new Dimension(1540, 820));
        backgroundPanel.setLayout(null);
        backgroundPanel.setOpaque(false);
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




	 private void setForageIngredientButton() 
	 {
	        forageIngredientButton = new JButton("Forage for Ingredient");
	        forageIngredientButton.setBounds(850, 60, 141, 160);
	        forageIngredientButton.setBorderPainted(false); // Remove border
	        forageIngredientButton.setContentAreaFilled(false); // Remove default background

	        // Load the original image
	        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Images/forage-ingredient.png"));
	        Image originalImage = originalIcon.getImage();

	        // Resize the image
	        Image resizedImage = originalImage.getScaledInstance(141, 160, Image.SCALE_SMOOTH);
	        ImageIcon resizedIcon = new ImageIcon(resizedImage);

	        forageIngredientButton.setIcon(resizedIcon);

	        backgroundPanel.add(forageIngredientButton);
	        
			//addActionListener is an example of Observer Pattern in GoF
	        forageIngredientButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new ForageIngredientFrame(game, player);
	            }
	        });
	        
	        JLabel forageLabel = new JLabel("Forage for Ingredient");
	        forageLabel.setForeground(Color.WHITE); // Set text color to white
	        forageLabel.setBounds(1000, 140, 200, 30); // Adjust the position as needed
	        forageLabel.setOpaque(false); // Make the label transparent
	        forageLabel.setFont(new Font("Tahoma", Font.ITALIC, 17));
	        backgroundPanel.add(forageLabel);
	    }
	 
	 private void setTransmuteIngredientButton() 
	 {
	        transmuteIngredientButton = new JButton();
	        transmuteIngredientButton.setBounds(1180, 60, 141, 160);
	        transmuteIngredientButton.setBorderPainted(false); // Remove border
	        transmuteIngredientButton.setContentAreaFilled(false); // Remove default background

	        // Load the original image
	        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Images/transmute-ingredient.png"));
	        Image originalImage = originalIcon.getImage();

	        // Resize the image
	        Image resizedImage = originalImage.getScaledInstance(141, 160, Image.SCALE_SMOOTH);
	        ImageIcon resizedIcon = new ImageIcon(resizedImage);

	        transmuteIngredientButton.setIcon(resizedIcon);

	        backgroundPanel.add(transmuteIngredientButton);

	        transmuteIngredientButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new TransmuteIngredientFrame(game, player);
	            }
	        });
	        
	        JLabel label = new JLabel("Transmute Ingredient");
	        label.setForeground(Color.WHITE); // Set text color to white
	        label.setBounds(1330, 140, 200, 30); // Adjust the position as needed
	        label.setOpaque(false); // Make the label transparent
	        label.setFont(new Font("Tahoma", Font.ITALIC, 17));
	        backgroundPanel.add(label);
	    }

		
		private void setBuyArtifactButton() 
		{
			buyArtifactButton = new JButton();
			buyArtifactButton.setBounds(850, 240, 141, 160);
			buyArtifactButton.setBorderPainted(false); // Remove border
			buyArtifactButton.setContentAreaFilled(false); // Remove default background

	        // Load the original image
	        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Images/buy-artifact.png"));
	        Image originalImage = originalIcon.getImage();

	        // Resize the image
	        Image resizedImage = originalImage.getScaledInstance(141, 160, Image.SCALE_SMOOTH);
	        ImageIcon resizedIcon = new ImageIcon(resizedImage);

	        buyArtifactButton.setIcon(resizedIcon);

	        backgroundPanel.add(buyArtifactButton);

	        buyArtifactButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	buyArtifactController controller = new buyArtifactController(game);
	                controller.buyArtifactHandler();
	            }
	        });
	        
	        JLabel label = new JLabel("Buy Artifact");
	        label.setForeground(Color.WHITE); // Set text color to white
	        label.setBounds(1000, 320, 200, 30); // Adjust the position as needed
	        label.setOpaque(false); // Make the label transparent
	        label.setFont(new Font("Tahoma", Font.ITALIC, 17));
	        backgroundPanel.add(label);
		}
		

		private void setMakeExperimentButton()
		{
			makeExperimentButton = new JButton();
			makeExperimentButton.setBounds(1180, 240, 141, 160);
			makeExperimentButton.setBorderPainted(false); // Remove border
			makeExperimentButton.setContentAreaFilled(false); // Remove default background

	        // Load the original image
	        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Images/make-experiment.png"));
	        Image originalImage = originalIcon.getImage();

	        // Resize the image
	        Image resizedImage = originalImage.getScaledInstance(141, 160, Image.SCALE_SMOOTH);
	        ImageIcon resizedIcon = new ImageIcon(resizedImage);

	        makeExperimentButton.setIcon(resizedIcon);

	        backgroundPanel.add(makeExperimentButton);

	        makeExperimentButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO
	            }
	        });
	        
	        JLabel label = new JLabel("Make Experiment");
	        label.setForeground(Color.WHITE); // Set text color to white
	        label.setBounds(1330, 320, 200, 30); // Adjust the position as needed
	        label.setOpaque(false); // Make the label transparent
	        label.setFont(new Font("Tahoma", Font.ITALIC, 17));
	        backgroundPanel.add(label);
		}

		private void setPublishTheoryButton()
		{
			publishTheoryButton = new JButton();
			publishTheoryButton.setBounds(850, 420, 141, 160);
			publishTheoryButton.setBorderPainted(false); // Remove border
			publishTheoryButton.setContentAreaFilled(false); // Remove default background

	        // Load the original image
	        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Images/publish-theory.png"));
	        Image originalImage = originalIcon.getImage();

	        // Resize the image
	        Image resizedImage = originalImage.getScaledInstance(141, 160, Image.SCALE_SMOOTH);
	        ImageIcon resizedIcon = new ImageIcon(resizedImage);

	        publishTheoryButton.setIcon(resizedIcon);

	        backgroundPanel.add(publishTheoryButton);

	        publishTheoryButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO
	            }
	        });
	        
	        JLabel label = new JLabel("Publish Theory");
	        label.setForeground(Color.WHITE); // Set text color to white
	        label.setBounds(1000, 500, 200, 30); // Adjust the position as needed
	        label.setOpaque(false); // Make the label transparent
	        label.setFont(new Font("Tahoma", Font.ITALIC, 17));
	        backgroundPanel.add(label);
		}
		
		private void setSellPotionButton()
		{
			sellPotionButton = new JButton();
			sellPotionButton.setBounds(1180, 420, 141, 160);
			sellPotionButton.setBorderPainted(false); // Remove border
			sellPotionButton.setContentAreaFilled(false); // Remove default background

	        // Load the original image
	        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Images/sell-potion.png"));
	        Image originalImage = originalIcon.getImage();

	        // Resize the image
	        Image resizedImage = originalImage.getScaledInstance(141, 160, Image.SCALE_SMOOTH);
	        ImageIcon resizedIcon = new ImageIcon(resizedImage);

	        sellPotionButton.setIcon(resizedIcon);

	        backgroundPanel.add(sellPotionButton);

	        sellPotionButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO
	            }
	        });
	        
	        JLabel label = new JLabel("Sell Potion");
	        label.setForeground(Color.WHITE); // Set text color to white
	        label.setBounds(1330, 500, 200, 30); // Adjust the position as needed
	        label.setOpaque(false); // Make the label transparent
	        label.setFont(new Font("Tahoma", Font.ITALIC, 17));
	        backgroundPanel.add(label);
		}
		
		private void setDebunkTheoryButton()
		{
			debunkTheoryButton = new JButton();
			debunkTheoryButton.setBounds(850, 600, 141, 160);
			debunkTheoryButton.setBorderPainted(false); // Remove border
			debunkTheoryButton.setContentAreaFilled(false); // Remove default background

	        // Load the original image
	        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Images/debunk-theory.png"));
	        Image originalImage = originalIcon.getImage();

	        // Resize the image
	        Image resizedImage = originalImage.getScaledInstance(141, 160, Image.SCALE_SMOOTH);
	        ImageIcon resizedIcon = new ImageIcon(resizedImage);

	        debunkTheoryButton.setIcon(resizedIcon);

	        backgroundPanel.add(debunkTheoryButton);

	        debunkTheoryButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // TODO
	            }
	        });
	        
	        JLabel label = new JLabel("Debunk Theory");
	        label.setForeground(Color.WHITE); // Set text color to white
	        label.setBounds(1000, 680, 200, 30); // Adjust the position as needed
	        label.setOpaque(false); // Make the label transparent
	        label.setFont(new Font("Tahoma", Font.ITALIC, 17));
	        backgroundPanel.add(label);
		}


}
