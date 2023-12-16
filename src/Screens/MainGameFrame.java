package Screens;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Business.KUAlchemistsGame;
import DesignSystem.GameButton;
import DesignSystem.GameText;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MainGameFrame extends GeneralFrame{
	
	/* 
	 * Displays the entire game board, including all game objects (Board, Tokens,
	 * ingredients, potions, Publication Cards, and Deduction Board).
	 * Provides comprehensive information about each player's resources, scores, and
	 * progress during the game.
	 */
	private KUAlchemistsGame game;
	private JPanel backgroundPanel;
	private JTable playersInfoTable;
	private JButton endGameButton;
	private JButton exitGameButton;
	private JButton howToPlayButton;
	private JButton pauseGameButton;
	private JButton takeTurnButton;
	
	public MainGameFrame(KUAlchemistsGame game) 
	{
		
		super();
		this.game = game;
		
		this.setBackground();
		this.setPlayersInfoTable();
		this.setButtons();
		this.setTheoriesPanel();
		this.setGameLog();
		this.setDirections();
		
	}
	
	private void setDirections()
	{
		String enthusiastizmText = """
					Welcome to the mesmerizing world of alchemy, 
				where magic and science intertwine to create wonders beyond imagination! 
				Prepare to embark on an enchanting journey where you'll concoct mystical 
				potions, conduct daring experiments, and unveil the secrets of the arcane arts.

					As an aspiring alchemist, the power of creation lies within your 
				grasp. Blend rare ingredients, harness the elements, and stir the 
				cauldron of possibility to produce potions of extraordinary effects. 
				From elixirs that grant incredible strength to brews that bestow 
				wisdom beyond measure, your mastery over alchemy knows no bounds.
				""";
		
		JTextArea enthusiastizmTextArea = new JTextArea(enthusiastizmText);
		enthusiastizmTextArea.setBounds(500, 80, 520, 200);
		enthusiastizmTextArea.setForeground(Color.white);
		enthusiastizmTextArea.setFont(GameText.normalText);
		enthusiastizmTextArea.setOpaque(false);
		backgroundPanel.add(enthusiastizmTextArea);
		
		String labelText = String.format("\n\tIt is %s's turn.", game.currentPlayer.getUserName());
		JLabel directionsLabel = new JLabel(labelText);
		directionsLabel.setBounds(650, 280, 330, 30);
		directionsLabel.setForeground(Color.white);
		directionsLabel.setFont(GameText.normalText);
		directionsLabel.setOpaque(false);
		setTakeTurnButton();
		backgroundPanel.add(directionsLabel);
		
	}
	
	private void setGameLog() {
	    JTextArea gameLogArea = new JTextArea(10, 30);
	    gameLogArea.setForeground(Color.white);
	    gameLogArea.setFont(GameText.normalText);
	    gameLogArea.setEditable(false);
	    gameLogArea.setBackground(new Color(0, 0, 0, 0)); 

	    JScrollPane scrollPane = new JScrollPane(gameLogArea);
	    scrollPane.setBounds(520, 490, 490, 230);
	    scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	    scrollPane.setOpaque(false);
	    scrollPane.getViewport().setOpaque(false); 

	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 

	    backgroundPanel.setOpaque(false); 

	    gameLogArea.append("Game log entry 1\n");
	    gameLogArea.append("Game log entry 2\n");

	    backgroundPanel.add(scrollPane); 
	}



	

	private void setTheoriesPanel() {
	    
		JPanel theoryPanel = new JPanel();
		theoryPanel.setLayout(new GridLayout(4, 2, 5, 5)); 
		theoryPanel.setBounds(10, 42, 485, 698);
	    theoryPanel.setOpaque(false);

	    // Create an EmptyBorder with desired spacing
	    Border spacingBorder = BorderFactory.createEmptyBorder(1,1,1,1);

	    for (int i = 0; i < 8; i++) {
	        JLabel theoryLabel = new JLabel();
	        theoryLabel.setPreferredSize(new Dimension(200, 150));
	        theoryLabel.setBorder(spacingBorder); // Apply the spacing border to each label
	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/theory" + (i + 1) + ".png"));
	        Image image = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
	        theoryLabel.setIcon(new ImageIcon(image));
	        theoryPanel.add(theoryLabel);
	    }

	    backgroundPanel.add(theoryPanel);
	}
	
	private void setBackground() {
    	
    	backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                
           	 super.paintComponent(g);
                
                Image image = new ImageIcon(this.getClass().getResource("/BackgroundImages/mainGameBackground.png")).getImage();
                
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
	
	      

	private void setPlayersInfoTable()
	{
		playersInfoTable = new JTable();
		DefaultTableModel model = new DefaultTableModel(5, 3);

		model.setValueAt(" Information", 0, 0);
		model.setValueAt(" Avatar", 1, 0);
		model.setValueAt(" Reputation Points", 2, 0);
		model.setValueAt(" Ingredient Cards", 3, 0);
		model.setValueAt(" Artifact Cards", 4, 0);

		for (int i = 0; i < this.game.getNumberOfPlayers(); i++) {
	        String avatarPath = game.getPlayers().get(i).getAvatarPath();
	        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/Images/" + avatarPath + ".png"));
	        Image originalImage1 = originalIcon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	        ImageIcon avatar = new ImageIcon(originalImage1);
			
			model.setValueAt(this.game.getPlayers().get(i).getUserName(), 0, i+1);
			model.setValueAt(avatar, 1, i + 1);
			model.setValueAt(this.game.getPlayers().get(i).getReputationPoints(), 2, i+1);
			model.setValueAt(this.game.getPlayers().get(i).getIngredients().size(), 3, i+1);
			model.setValueAt(this.game.getPlayers().get(i).getArtifacts().size(), 4, i+1);
		}
		
		playersInfoTable = new JTable(model);
        

		
		playersInfoTable.setFont(new Font("Tahoma", Font.ITALIC, 17));
		playersInfoTable.setForeground(Color.white);
		playersInfoTable.setBorder(new LineBorder(Color.white, 2));
		playersInfoTable.setOpaque(false);
		playersInfoTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		playersInfoTable.setRowHeight(50);
		playersInfoTable.setSurrendersFocusOnKeystroke(true);
		playersInfoTable.setBounds(1100, 85, 370, 255);
		
		// Set transparent background for the table and cells
        playersInfoTable.setOpaque(false);
        playersInfoTable.setBackground(new Color(0, 0, 0, 0)); // Transparent black (adjust alpha as needed)
        playersInfoTable.getColumnModel().getColumn(1).setCellRenderer(new AvatarRenderer());
        playersInfoTable.getColumnModel().getColumn(2).setCellRenderer(new AvatarRenderer());

        
		backgroundPanel.add(playersInfoTable);
		
		JLabel round = new JLabel("Round %d");
		JLabel turn = new JLabel("%s's turn");
		backgroundPanel.add(round);
		backgroundPanel.add(turn);
		
	}
	
	private static class AvatarRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        if (value instanceof ImageIcon) {
	            setIcon((ImageIcon) value);
	            setText("");  // Set an empty string to avoid displaying text
	            setHorizontalAlignment(CENTER);
	            setVerticalAlignment(CENTER);
	        } else {
	            setIcon(null); // Clear the icon if the value is not an ImageIcon
	            setText(value != null ? value.toString() : "");
	            setHorizontalAlignment(LEFT);
	            setVerticalAlignment(CENTER);
	        }

	        return component;
	    }
	}
	
	private void setButtons()
	{
		 setExitGameButton();
		 setPauseGameButton();
		 setHowToPlayButton();
		 setEndGameButton();
	}
	private void setExitGameButton() 
	{
		exitGameButton = new GameButton("Exit Game");
		exitGameButton.setBounds(1320, 500, 150, 30);
		backgroundPanel.add(exitGameButton);

	}
	private void setPauseGameButton() 
	{
		pauseGameButton = new GameButton("Pause Game");
		pauseGameButton.setBounds(1320, 560, 150, 30);
		backgroundPanel.add(pauseGameButton);
	}
	private void setHowToPlayButton()
	{
		howToPlayButton = new GameButton("How to Play?");
		howToPlayButton.setBounds(1100, 560, 150, 30);
		howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new HowToPlayFrame();
            }
        });
		backgroundPanel.add(howToPlayButton);
		
	}
	private void setEndGameButton() 
	{
		
		endGameButton = new GameButton("End Game");
		endGameButton.setBounds(1100, 500, 150, 30);
		backgroundPanel.add(endGameButton);
	
	}

	private void setTakeTurnButton() 
	{
		
		takeTurnButton = new GameButton("Take Turn");
		takeTurnButton.setBounds(650, 330, 150, 30);
		backgroundPanel.add(takeTurnButton);
		takeTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new PlayerDashboardFrame(game, game.currentPlayer);
            	MainGameFrame.this.dispose();
            }
        });
		
	}
}


	
	
	
	
	
	