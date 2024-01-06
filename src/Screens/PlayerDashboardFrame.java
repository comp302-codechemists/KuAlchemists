package Screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Business.Artifact;
import Business.DeductionBoard;
import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Business.Theory;
import Controllers.DeductionBoardController;
import Controllers.ForageController;
import Controllers.TransmuteController;
import Controllers.buyArtifactController;
import DesignSystem.ArtisticButton;
import DesignSystem.DashboardLabel;
import DesignSystem.GameButton;
import DesignSystem.GameText;
import artifactScreens.ElixirOfInsightFrame;
import soundEffects.PlaySong;

import java.awt.FlowLayout;



public class PlayerDashboardFrame extends GeneralFrame{
	
	/*
	 * A dedicated panel that showcases the current player's avatar, available resources, and actions.
	 * Offers interactive buttons for actions like collecting ingredients, brewing potions, and
	 * submitting publications.
	 */
	
	private JPanel upperBackground;
	private JPanel bottomBackground;
	private JPanel leftHandBackground;
	private JPanel upperButtonPanel;
	private JPanel artifactPannel;
	private JPanel ingredientPannel;
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
	private JLabel sickInfo;
	private JLabel sickLabel;
	private JButton testElixir;
	private JButton clearBtn;
	private JButton removeDeduction;
	private JPanel deductionPanel;
	
	private List<JLabel> labels; // For cross labels
	
	public List<JRadioButton> deductionBoardButtons = new ArrayList<JRadioButton>();
	
	public PlayerDashboardFrame(KUAlchemistsGame game) 
	{
		super(game);
		setBackground("/BackgroundImages/playerDashboardBackground.png");
		
		setUpperDeductionBoard();
		setUpperButtons();
		setBottomDeductionBoard();
		setLeftHand();
		setDeduction();
		setDeductionPanel();
		setClearSelection();
		setBuyArtifactButton();
		setForageIngredientButton();
		setTransmuteIngredientButton();
		setMakeExperimentButton();
		setPublishTheoryButton();
		setSellPotionButton();
		setDebunkTheoryButton();
		setPlayerInfo();
		setPlayerArtifacts();
		setPlayerIngredients();
		setBottomCrosses();
		setRemoveDeductionButton();
		sendDebunkWarning();
		//testElixir();
		
	}
	
	private void setPlayerInfo() {
		
        // avatar
        userAvatar = new JLabel();
        String avatarPath = game.currentPlayer.getAvatarPath();
        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/Images/" + avatarPath + ".png"));
        Image originalImage1 = originalIcon1.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon avatar = new ImageIcon(originalImage1);
        userAvatar.setIcon(avatar);
        userAvatar.setBounds(70, 70, 200, 200);
        backgroundPanel.add(userAvatar);
        
        // username
        System.out.println(game.currentPlayer.getUserName());
		JLabel userNameLabel = new JLabel(game.currentPlayer.getUserName());
        userNameLabel.setForeground(Color.WHITE);
        userNameLabel.setBounds(300, 150, 187, 30); 
        userNameLabel.setOpaque(false); 
        userNameLabel.setFont(new Font("Tahoma", Font.ITALIC, 35));
        backgroundPanel.add(userNameLabel);
        
        // reputation points
        repPointsLabel = new JLabel("Reputation Points:");
        repPointsLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
        repPointsLabel.setForeground(Color.WHITE);
        repPointsLabel.setBounds(70, 320, 200, 30);
        backgroundPanel.add(repPointsLabel);
        
        balanceLabel = new JLabel("Balance:");
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
        balanceLabel.setBounds(70, 350, 120, 30);
        backgroundPanel.add(balanceLabel);
        
        int repPoints = game.currentPlayer.getReputationPoints();
        reputationLabel = new JLabel(Integer.toString(repPoints));
        reputationLabel.setForeground(Color.WHITE);
        reputationLabel.setFont(new Font("Tahoma", Font.ITALIC, 25));
        reputationLabel.setBounds(300, 320, 60, 40);
        backgroundPanel.add(reputationLabel);
        
        int balance = game.currentPlayer.getBalance();
        userGoldInfo = new JLabel(Integer.toString(balance));
        userGoldInfo.setForeground(Color.WHITE);
        userGoldInfo.setFont(new Font("Tahoma", Font.ITALIC, 25));
        userGoldInfo.setBounds(300, 350, 60, 40);
        backgroundPanel.add(userGoldInfo);
        
        //Sickness Level
        int sicknessLvl = game.currentPlayer.getSicknessLevel();
        sickInfo = new JLabel(Integer.toString(sicknessLvl));
        sickInfo.setBounds(300, 380, 60, 40);
        backgroundPanel.add(sickInfo);
        sickInfo.setForeground(Color.WHITE);
        sickInfo.setFont(new Font("Tahoma", Font.ITALIC, 25));
    	
        sickLabel = new JLabel("Sickness Level:");
        sickLabel.setForeground(Color.WHITE);
        sickLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
        sickLabel.setBounds(70, 380, 200, 30);
        backgroundPanel.add(sickLabel);
        
	}
	
	private void setDeductionPanel() {
    
    	deductionPanel = new JPanel();
    	deductionPanel.setLayout(null);
    	deductionPanel.setOpaque(false);
    	deductionPanel.setBounds(135, 42, 614, 402);
    	deductionPanel.setVisible(true);
    	upperBackground.add(deductionPanel);

        
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
    	
        upperBackground.setLocation(339, 57);
        upperBackground.setLayout(null);
        upperBackground.setOpaque(false);
        upperBackground.setSize(new Dimension(454, 323));       
        backgroundPanel.add(upperBackground);
	}	
	private void setUpperButtons() {
		
        upperButtonPanel = new JPanel();
        upperButtonPanel.setOpaque(false);
        upperButtonPanel.setLayout(null);
        upperButtonPanel.setBounds(10, 10, 769, 456);
        
        triangleButtonGroup = new ButtonGroup();

        int rows = 7;
        int startX = 199;
        int startY = 0; 
        int index = 0;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col <= row; col++) {
                JRadioButton btn = new JRadioButton();
    	    	btn.addActionListener(new ActionListener() 
    	    	{
    	            @Override
    	            public void actionPerformed(ActionEvent e) {	            		
    	                	 PlaySong.play("ButtonClick");                      	
    	            }
    	        });
                triangleButtonGroup.add(btn);
                int x = startX - row * 46 / 2 + col * 46;
                int y = startY + row * 40;
                btn.setBounds(x, y, 35, 35);
                
                if(game.currentPlayer.getDeductionBoard().getExistingItems().containsKey(index)) {
                	
                	int selectedLeft = game.currentPlayer.getDeductionBoard().getExistingItems().get(index);
                	
                	Image image = new ImageIcon(this.getClass().getResource("/Images/circle" + selectedLeft + ".png")).getImage();
        	    	if (image == null) System.out.println("can't load image.\n");
        	    	Image newImage = image.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        	    	ImageIcon icon = new ImageIcon(newImage);
        	    	btn.setIcon(new ImageIcon(newImage));
        	    	
                }
            	
        	    btn.setOpaque(false);
        	    btn.setContentAreaFilled(false);
        	    btn.setBorderPainted(false);
                upperButtonPanel.add(btn);
                deductionBoardButtons.add(btn);  	
    	    	index++;
            }
        }   
        
        upperBackground.add(upperButtonPanel);
        
	}
	private void setBottomDeductionBoard() 
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
	    bottomBackground.setBounds(313, 380, 500, 200);
	    backgroundPanel.add(bottomBackground);
	    bottomBackground.setLayout(null);
	    bottomBackground.setOpaque(false);

	}
	
	// Helper functions for the crosses on the deduction board.
	private static List<Integer> generateSequenceLeft() {
        List<Integer> sequence = new ArrayList<>();
        for (int row = 0; row <= 7; row++) {
            for (int value = 0; value <= row; value++) {
                sequence.add(value);
            }
        }
        return sequence;
    }
	
	private static List<Integer> generateSequenceRight() {
        List<Integer> sequence = new ArrayList<>();

        int max = 7;
        int min = 0;
        while (max >= min) {
            for (int i = max; i >= min; i--) {
            	sequence.add(i);
            }
            min++;
        }
        Collections.reverse(sequence);
        return sequence;
    }
	
	private int[] getIndices() {
		
		int[] arr = new int[4];
	    int[][] matrix = new int[][] {
	    	{1, 7},
	    	{0, 6},
	    	{3, 7},
	    	{2, 6},
	    	{5, 7},
	    	{4, 6}
	    };
	    
	    DeductionBoard db = game.currentPlayer.getDeductionBoard();
	    Map<Integer, Integer> locations = db.getExistingItems();
	    List<Integer> leftIndex = generateSequenceLeft();
	    List<Integer> rightIndex = generateSequenceRight();
	    
	    for (int i = 0; i < 28; i++) {
	    	int left = leftIndex.get(i);
	    	int right = rightIndex.get(i);
	    	
	    	Integer value = locations.get(i);
    	
	    	if (value == null) {
	    		continue;
	    	}
	    	
	    	for (int j = 0; j < 8; j++) {
	    		if (value == j) { 			    		
		    		arr[0] = left  + 8 * matrix[j][0];	
		    		arr[1] = right + 8 * matrix[j][0];
		    		arr[2] = left  + 8 * matrix[j][1];
		    		arr[3] = right + 8 * matrix[j][1];
	    		}
	    	}
	    }
		
		return arr;
	}
	
	private void setBottomCrosses() {
	    int labelSize = 20;
	    int gapX = 25;
	    int gapY = 5;
	    int startX = 93;
	    int startY = 3;
	    
	    List<JLabel> labels = new ArrayList<>();
	    
    	Image image = new ImageIcon(this.getClass().getResource("/Images/cross.png")).getImage();
    	Image newImage = image.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    	ImageIcon icon = new ImageIcon(newImage);
	    
	    for (int row = 0; row < 8; row++) {
	        for (int col = 0; col < 8; col++) {
	    
	            int x = startX + col * (labelSize + gapX);
	            int y = startY + row * (labelSize + gapY);
		    	JLabel cross = new JLabel();
		    	cross.setBounds(x, y, labelSize, labelSize);
		    	labels.add(cross);
	            bottomBackground.add(cross);
	        }
	    }
	    	    
	    DeductionBoard db = game.currentPlayer.getDeductionBoard();
	    Map<Integer, Integer> locations = db.getExistingItems();
	    List<Integer> leftIndex = generateSequenceLeft();
	    List<Integer> rightIndex = generateSequenceRight();
	    
	    
	    // Matrix for marking the table
	    int[][] matrix = new int[][] {
	    	{1, 7},
	    	{0, 6},
	    	{3, 7},
	    	{2, 6},
	    	{5, 7},
	    	{4, 6}
	    };

	    // Marking the table with the indices.
	    for (int i = 0; i < 28; i++) {
	    	int left = leftIndex.get(i);
	    	int right = rightIndex.get(i);
	    	
	    	Integer value = locations.get(i);
    	
	    	if (value == null) {
	    		continue;
	    	}
	    	
	    	for (int j = 0; j < 8; j++) {
	    		if (value == j) {
	    			int index1 = left  + 8 * matrix[j][0];
	    			int index2 = right + 8 * matrix[j][0];
		    		int index3 = left  + 8 * matrix[j][1];
		    		int index4 = right + 8 * matrix[j][1];
		    		
		    		labels.get(index1).setIcon(icon);
		    		labels.get(index2).setIcon(icon);
		    		labels.get(index3).setIcon(icon);
		    		labels.get(index4).setIcon(icon);

		    		
		    		System.out.printf("Indicies: %d %d %d %d.\n", index1, index2, index3, index4);
	    		}	
	    	}	
	    }
	    
		bottomBackground.revalidate();
		bottomBackground.repaint();
	    
	    
	}
	
	
	private void setLeftHand() 
	{
		leftHandBackground = new JPanel();
		
		leftHandBackground.setLayout(new FlowLayout());
		leftButtonGroup = new ButtonGroup();
		
		for (int i = 0; i < 7; i++) {
	    	Image image = new ImageIcon(this.getClass().getResource("/Images/circle" + i + ".png")).getImage();
	    	if (image == null) System.out.println("can't load image.\n");
	    	Image newImage = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
	    	ImageIcon icon = new ImageIcon(newImage);
	    	JLabel circleLabel = new JLabel(icon);
	    	circleLabel.setVisible(true);
	    	leftHandBackground.add(circleLabel);
        	JRadioButton btn = new JRadioButton();
	    	btn.addActionListener(new ActionListener() 
	    	{
	            @Override
	            public void actionPerformed(ActionEvent e) {	            		
	                	 PlaySong.play("ButtonClick");                      	
	            }
	        });
        	leftButtonGroup.add(btn);
        	leftHandBackground.add(btn);
        	btn.setBounds(0, i*32, 50, 40);
    	    btn.setOpaque(false);
    	    btn.setContentAreaFilled(false);
    	    btn.setBorderPainted(false);
		}

    	
        leftHandBackground.setLocation(758, 57);
        leftHandBackground.setOpaque(false);
        leftHandBackground.setSize(new Dimension(94, 313));       
        backgroundPanel.add(leftHandBackground);
		
	}
	
	private void setRemoveDeductionButton() {
			
    	removeDeduction = new GameButton("<html>Remove<br>Deduction</html>");
    	removeDeduction.setBounds(445, 130, 55, 44);
		removeDeduction.setBackground(Color.LIGHT_GRAY);
		removeDeduction.setFont(new Font("Tahoma", Font.ITALIC, 8));
    	bottomBackground.add(removeDeduction);
    
    	
        removeDeduction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           	 	PlaySong.play("ButtonClick");                      	

            	int i = -1;
            	for (i = 0; i < deductionBoardButtons.size(); i++) {
            		if (deductionBoardButtons.get(i).isSelected()) {
            			break;
            		}
            	}
            	if (i != -1) {
            		deductionBoardButtons.get(i).setIcon(null);
            		int arr[] = getIndices();
                	labels.get(arr[0]).setIcon(null);
                	labels.get(arr[1]).setIcon(null);
                	labels.get(arr[2]).setIcon(null);
                	labels.get(arr[3]).setIcon(null);
            		            		
            		DeductionBoardController controller = new DeductionBoardController(game);
            		controller.removeTokenHandler(i);
            		
            	}
                
            }
        });
    	
	}

	
	
	private void setClearSelection() {
		clearBtn = new GameButton("<html>Clear<br>Selection</html>");
		clearBtn.setBackground(Color.LIGHT_GRAY);
		clearBtn.setFont(new Font("Tahoma", Font.ITALIC, 8));
    	clearBtn.setBounds(445, 22, 55, 44);
    	
    	bottomBackground.add(clearBtn);
    	
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           	 	PlaySong.play("ButtonClick");                      	

            	leftButtonGroup.clearSelection();
                triangleButtonGroup.clearSelection();
            }
        });	
	}
	
	private void setDeduction() {
	    JButton submitBtn = new GameButton("<html>Submit<br>Deduction</html>");
	    submitBtn.setBackground(Color.LIGHT_GRAY);
	    submitBtn.setFont(new Font("Tahoma", Font.ITALIC, 8));
	    submitBtn.setBounds(445, 76, 55, 44);
	    bottomBackground.add(submitBtn);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           	 	PlaySong.play("ButtonClick");                      	
            	
            	int selectedLeft = -1;
            	Enumeration<AbstractButton> buttonsLeft = leftButtonGroup.getElements();
            	int currentIndex = 0;
            	while (buttonsLeft.hasMoreElements()) {
            	    AbstractButton button1 = buttonsLeft.nextElement();
            	    
            	    if (button1.isSelected()) {
            	        selectedLeft = currentIndex;
            	        break;
            	    }
            	    
            	    currentIndex++;

            	}
            	System.out.printf("Selected button index for left: %d\n", selectedLeft);
            	
            	int selectedTriangle = -1;
            	Enumeration<AbstractButton> buttonsTriangle = triangleButtonGroup.getElements();
            	int currentIndexT = 0;
            	while (buttonsTriangle.hasMoreElements()) {
            	    AbstractButton button2 = buttonsTriangle.nextElement();
            	    
            	    if (button2.isSelected()) {
            	        selectedTriangle = currentIndexT;
            	        break;
            	    }
            	    
            	    currentIndexT++;
            	}
            	System.out.printf("Selected button index for triangle: %d\n", selectedTriangle);
            	
            	if (selectedTriangle == -1 || selectedLeft == -1) {
               	 JOptionPane.showMessageDialog(new JFrame(), "Please mark your choices, action can't be submitted.",
                         "Submit Error", JOptionPane.ERROR_MESSAGE);
            		
            	}
            	
            	else {
            		DeductionBoardController controller = new DeductionBoardController(game);
            		controller.deductionBoardHandler(selectedTriangle, DeductionBoard.getName(selectedLeft),selectedLeft);
            		
            		
            	}
            	

    	    	Image image = new ImageIcon(this.getClass().getResource("/Images/circle" + selectedLeft + ".png")).getImage();
    	    	if (image == null) System.out.println("can't load image.\n");
    	    	Image newImage = image.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    	    	ImageIcon icon = new ImageIcon(newImage);
    	    	JLabel circleLabel = new JLabel(icon);
    	    	deductionBoardButtons.get(selectedTriangle).setIcon(new ImageIcon(newImage));    	
    	    	circleLabel.setBounds(100, 100, 30, 30);
    	    	deductionPanel.add(circleLabel);
    	    	setBottomCrosses();
            	
            }
        });
	    
	}
	
	private void setPlayerIngredients() {
		
        ingredientPannel = new JPanel();
        ingredientPannel.setBounds(70, 500, 653, 123);
        ingredientPannel.setOpaque(false);
        backgroundPanel.add(ingredientPannel);
        ingredientPannel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
        JLabel ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setForeground(Color.WHITE);
        ingredientsLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        ingredientsLabel.setBounds(70, 480, 85, 13);
        backgroundPanel.add(ingredientsLabel);
        
        for (Ingredient ingredient : game.currentPlayer.getIngredients()) {
        	System.out.println(ingredient.getName());
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
        artifactPannel.setBounds(70, 650, 653, 123);
        backgroundPanel.add(artifactPannel);
        artifactPannel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        artifactPannel.setOpaque(false);
        
        JLabel artifactsLabel = new JLabel("Artifacts:");
        artifactsLabel.setForeground(Color.WHITE);
        artifactsLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        artifactsLabel.setBounds(70, 630, 85, 13);
        backgroundPanel.add(artifactsLabel);
        
        for (Artifact artifact : game.currentPlayer.getArtifacts()) {
	    	Image image = new ImageIcon(this.getClass().getResource("/artifactImages/" + artifact.getName() + ".png")).getImage();
	    	Image newImage = image.getScaledInstance(60, 100, Image.SCALE_DEFAULT);
	    	ImageIcon icon = new ImageIcon(newImage);
	    	JLabel picLabel = new JLabel(icon);
	    	artifactPannel.add(picLabel);
        }
		
	}
	


	 private void setForageIngredientButton() 
	 {
	        forageIngredientButton = new ArtisticButton("/Images/forage-ingredient.png", 141, 160);
	        forageIngredientButton.setBounds(930, 50, 141, 160);
	        backgroundPanel.add(forageIngredientButton);
	        
			//addActionListener is an example of Observer Pattern in GoF
	        forageIngredientButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	PlaySong.play("ButtonClick");                      	

	                new ForageIngredientFrame(game);
	                PlayerDashboardFrame.this.dispose();
	            }
	        });
	        
	        JLabel forageLabel = new DashboardLabel("Forage for");
	        JLabel forageLabel2 = new DashboardLabel("Ingredient");
	        forageLabel.setBounds(1080, 110, 200, 30);backgroundPanel.add(forageLabel);
	        forageLabel2.setBounds(1080, 140, 200, 30);backgroundPanel.add(forageLabel2);
	    }
	 
	 private void setMakeExperimentButton()
		{
			makeExperimentButton = new ArtisticButton("/Images/make-experiment.png", 141, 160);
			makeExperimentButton.setBounds(1210, 50, 141, 160);
	        backgroundPanel.add(makeExperimentButton);

	        makeExperimentButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	PlaySong.play("ButtonClick");                      	

	                new MakeExperimentFrame(game);
	                PlayerDashboardFrame.this.dispose();
	            }
	        });
	        
	        JLabel label = new DashboardLabel("Make");
	        JLabel label2 = new DashboardLabel("Experiment");
	        label.setBounds(1360, 110, 200, 30); backgroundPanel.add(label);
	        label2.setBounds(1360, 140, 200, 30); backgroundPanel.add(label2);
		}
	 
	 private void setTransmuteIngredientButton() 
	 {
	        transmuteIngredientButton = new ArtisticButton("/Images/transmute-ingredient.png", 141, 160);
	        transmuteIngredientButton.setBounds(930, 220, 141, 160);
	        backgroundPanel.add(transmuteIngredientButton);

	        transmuteIngredientButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	PlaySong.play("ButtonClick");                      	

	                new TransmuteIngredientFrame(game);
	                PlayerDashboardFrame.this.dispose();
	            }
	        });
	        
	        JLabel label = new DashboardLabel("Transmute");
	        JLabel label2 = new DashboardLabel("Ingredient ");
	        label.setBounds(1080, 280, 200, 30);backgroundPanel.add(label);
	        label2.setBounds(1080, 310, 200, 30);backgroundPanel.add(label2);
	        
	    }

		
		private void setBuyArtifactButton() 
		{
			buyArtifactButton = new ArtisticButton("/Images/buy-artifact.png", 141, 160);
			buyArtifactButton.setBounds(1210, 220, 141, 160);
	        backgroundPanel.add(buyArtifactButton);

	        buyArtifactButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	PlaySong.play("ButtonClick");                      	

	            	new BuyArtifactFrame(game);
	            	PlayerDashboardFrame.this.dispose();
	            }
	        });
	        
	        JLabel label = new DashboardLabel("Buy Artifact");
	        label.setBounds(1360, 280, 200, 30); 
	        backgroundPanel.add(label);
		}
		


		
		private void setDebunkTheoryButton()
		{
			debunkTheoryButton = new ArtisticButton("/Images/debunk-theory.png", 141, 160);
			debunkTheoryButton.setBounds(930, 390, 141, 160);
	        backgroundPanel.add(debunkTheoryButton);

	        debunkTheoryButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	/*if (game.getRound() < 2) {
	                  	 JOptionPane.showMessageDialog(new JFrame(), "You can debunk theories after the third round. "
	                  	 		+ "Please wait for round 3 for this action.",
	                             "Action Denied", JOptionPane.ERROR_MESSAGE);
	            	}
	            	
	            	else {
		            	new DebunkTheoryFrame(game, player);
		            	PlayerDashboardFrame.this.dispose();
	            	}*/
	            	PlaySong.play("ButtonClick");                      	

	            	new DebunkTheoryFrame(game);
	            	PlayerDashboardFrame.this.dispose();
	            }
	        });
	        

			JLabel label = new DashboardLabel("Debunk Theory");
			label.setBounds(1080, 450, 200, 30); // Adjust the position as needed
			backgroundPanel.add(label);
			
		}
		

		private void setSellPotionButton()
		{
			sellPotionButton = new ArtisticButton("/Images/sell-potion.png", 141, 160);
			sellPotionButton.setBounds(1210, 390, 141, 160);
	        backgroundPanel.add(sellPotionButton);

	        sellPotionButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {

	            	/*if (game.getRound() < 2) {
	                  	 JOptionPane.showMessageDialog(new JFrame(), "You can sell potions after the second round. "
	                  	 		+ "Please wait for round 2 for this action.",
	                             "Action Denied", JOptionPane.ERROR_MESSAGE);
	            	}
	            	else {
		            	new SellPotionFrame(game, player);
		            	PlayerDashboardFrame.this.dispose();
	            	}*/
	            	PlaySong.play("ButtonClick");                      	
	            	
	            	new SellPotionFrame(game);
	            	PlayerDashboardFrame.this.dispose();

	            }
	        });
	        
	        JLabel label = new DashboardLabel("Sell Potion");
	        label.setBounds(1360, 450, 200, 30);
	        backgroundPanel.add(label);
		}

		

		private void setPublishTheoryButton()
		{
			publishTheoryButton = new ArtisticButton("/Images/publish-theory.png", 141, 160);
			publishTheoryButton.setBounds(930, 560, 141, 160);
	        backgroundPanel.add(publishTheoryButton);

	        publishTheoryButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	/*if (game.getRound() < 2) {
	                  	 JOptionPane.showMessageDialog(new JFrame(), "You can publish theories after the second round. "
	                  	 		+ "Please wait for round 2 for this action.",
	                             "Action Denied", JOptionPane.ERROR_MESSAGE);
	            	}
	            	else {
		            	new PublishTheoryFrame(game, player);
		            	PlayerDashboardFrame.this.dispose();
	            	}*/
	            	PlaySong.play("ButtonClick");                      	

	            	new PublishTheoryFrame(game);
	            	PlayerDashboardFrame.this.dispose();
	
	            }
	        });
	        
	        JLabel label = new DashboardLabel("Publish Theory");
	        label.setBounds(1080, 620, 200, 30); 
	        backgroundPanel.add(label);
		}
		
		private void testElixir() {
	        testElixir = new JButton("Test Elixir of Insight");
	        testElixir.setBounds(1210, 560, 123, 30);
	        backgroundPanel.add(testElixir);
	        
	        testElixir.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new ElixirOfInsightFrame(game);
	                PlayerDashboardFrame.this.dispose();
	            }
	        });
		}
		
		private void sendDebunkWarning() {
			
			Map<Theory, Boolean> debunkedTheories = Theory.getDebunkedTheories();
			
			Artifact art = game.currentPlayer.getArtifacts().stream().filter(each -> each.getName().equals("WisdomIdolArtifact")).findFirst().orElse(null);
			
			if(art == null) {
				return;
			}
			
			for(Entry<Theory , Boolean> eachTheory: debunkedTheories.entrySet()) {
				
				if(eachTheory.getKey().getOwner().getUserName().equals(game.currentPlayer.getUserName()) && !eachTheory.getValue()) {
					
					int result = JOptionPane.showConfirmDialog(
			                null,
			                "Your theory was debunked :((( Do you want to use your Wisdom Idol Artifact to keep your reputation points ?",
			                "Warning",
			                JOptionPane.YES_NO_OPTION
			        );

			        if (result == JOptionPane.YES_OPTION) {
			            game.currentPlayer.getArtifacts().remove(art);
			            game.currentPlayer.setReputationPoints(game.currentPlayer.getReputationPoints() + 1);
			            eachTheory.setValue(true);
			            this.dispose();
			            new PlayerDashboardFrame(game);
			        } else {
			            eachTheory.setValue(true);
			        }
					
				}
			}
		}
}