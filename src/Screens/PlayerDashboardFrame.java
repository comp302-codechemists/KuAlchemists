package Screens;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
	private Player player;
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
	
	public PlayerDashboardFrame(Player player) {
		super();
		
		setUpperDeductionBoard();

		setUpperButtons();
		
		seBottomDeductionBoard();
		
		setLeftHand();
		
		setLeftHandButtons();
		
		setBuyArtifactButton();
		
		setForageIngredientButton();
		
		setTransmuteIngredientButton();
		
		this.player = player;
    
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
    	
        upperBackground.setLocation(10, 0);
        upperBackground.setLayout(null);
        upperBackground.setOpaque(false);
        upperBackground.setSize(new Dimension(789, 466));       
        getContentPane().add(upperBackground);
	}
	
	
	public void setUpperButtons() {
		
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
	
	public void seBottomDeductionBoard() {
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
	    bottomBackground.setBounds(10, 476, 789, 297);
	    getContentPane().add(bottomBackground);
	    bottomBackground.setLayout(null);
	    bottomBackground.setOpaque(false);
	}
	
	private void setLeftHand() {
		
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
    	
        leftHandBackground.setLocation(10, 0);
        leftHandBackground.setLayout(null);
        leftHandBackground.setOpaque(false);
        leftHandBackground.setSize(new Dimension(85, 466));       
        getContentPane().add(leftHandBackground);
		
	}
	
	public void setLeftHandButtons() {
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
	
	public void setBuyArtifactButton() {
		buyArtifactButton = new JButton();
		buyArtifactButton.setBounds(1000, 200, 200, 30);
		buyArtifactButton.setText("Buy Artifact");
		getContentPane().add(buyArtifactButton);
		
		buyArtifactButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buyArtifactController controller = new buyArtifactController();
				controller.buyArtifactHandler();
			}
		});
		
	}
	
	public void setForageIngredientButton() {
		forageIngredientButton = new JButton();
		forageIngredientButton.setBounds(1000, 250, 200, 30);
		forageIngredientButton.setText("Forage Ingredient");;
		getContentPane().add(forageIngredientButton);
		
		forageIngredientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ForageController controller = new ForageController();
				controller.handleForage();
			}
		});
		
		
	}
	
	public void setTransmuteIngredientButton() {
		transmuteIngredientButton = new JButton();
		transmuteIngredientButton.setBounds(1000, 300, 200, 30);
		transmuteIngredientButton.setText("Transmute Ingredient");
		getContentPane().add(transmuteIngredientButton);
		
		transmuteIngredientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TransmuteController controller = new TransmuteController();
				controller.handleTransmute("Terror Root");
			}
		});
		
		
	}

	
    public static void main(String[] args) {
        // Create and display the frame   
    	Player player = new Player("Simge", "Path", null, null, 10, 0, null);
        new PlayerDashboardFrame(player);
    }
}
