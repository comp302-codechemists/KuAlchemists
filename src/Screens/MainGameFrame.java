package Screens;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Business.KUAlchemistsGame;
import Controllers.StartGameController;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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
	
	public MainGameFrame(KUAlchemistsGame game) {
		
		super();
		this.game = game;
		
		this.setBackground();
		this.setPlayersInfoTable();
		this.setButtons();
		this.setIngredientPanel();
		this.setArtifactPanel();
		this.setGameLog();
		
	}
	
	private void setGameLog() {
	    JTextArea gameLogArea = new JTextArea(10, 30);
	    gameLogArea.setForeground(Color.white);
	    gameLogArea.setFont(new Font("Tahoma", Font.ITALIC, 17));
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



	private void setIngredientPanel() 
	{
	    JPanel ingredientPanel = new JPanel();
	    ingredientPanel.setLayout(new GridLayout(2, 4, 5, 5));
	    ingredientPanel.setBounds(25, 45, 420, 320);
	    ingredientPanel.setOpaque(false);

	    for (int i = 0; i < 8; i++) {
	        JLabel ingredientLabel = new JLabel();
	        ingredientLabel.setPreferredSize(new Dimension(100, 150));
	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/ingredient" + (i + 1) + ".png"));
	        Image image = imageIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
	        ingredientLabel.setIcon(new ImageIcon(image));
	        ingredientPanel.add(ingredientLabel);
	    }

	    backgroundPanel.add(ingredientPanel);
	}
	

	private void setArtifactPanel() {
	    
		JPanel artifactPanel = new JPanel();
	    artifactPanel.setLayout(new GridLayout(4, 2, 5, 5)); 
	    artifactPanel.setBounds(55, 400, 440, 340);
	    artifactPanel.setOpaque(false);

	    // Create an EmptyBorder with desired spacing
	    Border spacingBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);

	    for (int i = 0; i < 8; i++) {
	        JLabel artifactLabel = new JLabel();
	        artifactLabel.setPreferredSize(new Dimension(140, 80));
	        artifactLabel.setBorder(spacingBorder); // Apply the spacing border to each label
	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/artifact" + (i + 1) + ".png"));
	        Image image = imageIcon.getImage().getScaledInstance(140, 80, Image.SCALE_SMOOTH);
	        artifactLabel.setIcon(new ImageIcon(image));
	        artifactPanel.add(artifactLabel);
	    }

	    backgroundPanel.add(artifactPanel);
	}
	
	private void setBackground() {
    	
    	backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                
           	 super.paintComponent(g);
                
                Image image = new ImageIcon(this.getClass().getResource("/Images/mainGameFrame.png")).getImage();
                
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
		DefaultTableModel model = new DefaultTableModel(4, 3);

		model.setValueAt(" Information", 0, 0);
		model.setValueAt(" Reputation Points", 1, 0);
		model.setValueAt(" Ingredient Cards", 2, 0);
		model.setValueAt(" Artifact Cards", 3, 0);

		for (int i = 0; i < this.game.getNumberOfPlayers(); i++) {
			model.setValueAt(this.game.getPlayers().get(i).getUserName(), 0, i+1);
			model.setValueAt(this.game.getPlayers().get(i).getReputationPoints(), 1, i+1);
			model.setValueAt(this.game.getPlayers().get(i).getIngredients().size(), 2, i+1);
			model.setValueAt(this.game.getPlayers().get(i).getArtifacts().size(), 3, i+1);
		}
		
		playersInfoTable = new JTable(model);
		playersInfoTable.setFont(new Font("Tahoma", Font.ITALIC, 17));
		playersInfoTable.setForeground(Color.white);
		playersInfoTable.setBorder(new LineBorder(Color.white, 2));
		playersInfoTable.setOpaque(false);
		playersInfoTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		playersInfoTable.setRowHeight(50);
		playersInfoTable.setSurrendersFocusOnKeystroke(true);
		playersInfoTable.setBounds(1100, 85, 370, 200);
		
		// Set transparent background for the table and cells
        playersInfoTable.setOpaque(false);
        playersInfoTable.setBackground(new Color(0, 0, 0, 0)); // Transparent black (adjust alpha as needed)
        playersInfoTable.setDefaultRenderer(Object.class, new TransparentTableCellRenderer());

        
		backgroundPanel.add(playersInfoTable);
		
		JLabel round = new JLabel("Round %d");
		JLabel turn = new JLabel("%s's turn");
		backgroundPanel.add(round);
		backgroundPanel.add(turn);
		
	}
	
	private class TransparentTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground(new Color(0, 0, 0, 0)); // Transparent black (adjust alpha as needed)
            return c;
        }
    }
	
	private void setButtons()
	{
		 setExitGameButton();
		 setPauseGameButton();
		 setHowToPlayButton();
		 setEndGameButton();
		 setTakeTurnButton();
	}
	private void setExitGameButton() 
	{
		exitGameButton = new JButton("Exit Game");
		exitGameButton.setBounds(1320, 500, 150, 30);
		exitGameButton.setForeground(Color.white);
		exitGameButton.setFont(new Font("Tahoma", Font.ITALIC, 15));
		exitGameButton.setOpaque(false);
		exitGameButton.setBorder(new LineBorder(Color.white, 2));
		backgroundPanel.add(exitGameButton);
		exitGameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse click event if needed
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Handle mouse press event if needed
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Handle mouse release event if needed
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Change button appearance on mouse enter (hover effect)
            	exitGameButton.setBorder(new LineBorder(Color.yellow, 2));
            	exitGameButton.setForeground(Color.yellow);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore button appearance on mouse exit
            	exitGameButton.setBorder(new LineBorder(Color.white, 2));
                exitGameButton.setForeground(Color.white);
            }
        });
	}
	private void setPauseGameButton() 
	{
		pauseGameButton = new JButton("Pause Game");
		pauseGameButton.setBounds(1320, 560, 150, 30);
		pauseGameButton.setForeground(Color.white);
		pauseGameButton.setFont(new Font("Tahoma",Font.ITALIC, 15));
		pauseGameButton.setOpaque(false);
		pauseGameButton.setBorder(new LineBorder(Color.white, 2));
		backgroundPanel.add(pauseGameButton);
		pauseGameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse click event if needed
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Handle mouse press event if needed
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Handle mouse release event if needed
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Change button appearance on mouse enter (hover effect)
                pauseGameButton.setBorder(new LineBorder(Color.yellow, 2));
                pauseGameButton.setForeground(Color.yellow);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore button appearance on mouse exit
                pauseGameButton.setBorder(new LineBorder(Color.white, 2));
                pauseGameButton.setForeground(Color.white);
            }
        });
	}
	private void setHowToPlayButton()
	{
		howToPlayButton = new JButton("How to Play?");
		howToPlayButton.setBounds(1100, 560, 150, 30);
		howToPlayButton.setForeground(Color.white);
		howToPlayButton.setFont(new Font("Tahoma", Font.ITALIC, 15));
		howToPlayButton.setOpaque(false);
		howToPlayButton.setBorder(new LineBorder(Color.white, 2));
		backgroundPanel.add(howToPlayButton);
		howToPlayButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse click event if needed
            	new HowToPlayFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Handle mouse press event if needed
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Handle mouse release event if needed
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Change button appearance on mouse enter (hover effect)
            	howToPlayButton.setBorder(new LineBorder(Color.yellow, 2));
            	howToPlayButton.setForeground(Color.yellow);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore button appearance on mouse exit
            	howToPlayButton.setBorder(new LineBorder(Color.white, 2));
            	howToPlayButton.setForeground(Color.white);
            }
        });
	}
	private void setEndGameButton() 
	{
		
		endGameButton = new JButton("End Game");
		endGameButton.setBounds(1100, 500, 150, 30);
		endGameButton.setForeground(Color.white);
		endGameButton.setFont(new Font("Tahoma", Font.ITALIC, 15));
		endGameButton.setOpaque(false);
		endGameButton.setBorder(new LineBorder(Color.white, 2));
		backgroundPanel.add(endGameButton);
		
		endGameButton.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                // Handle mouse click event if needed
	            }

	            @Override
	            public void mousePressed(MouseEvent e) {
	                // Handle mouse press event if needed
	            }

	            @Override
	            public void mouseReleased(MouseEvent e) {
	                // Handle mouse release event if needed
	            }

	            @Override
	            public void mouseEntered(MouseEvent e) {
	                // Change button appearance on mouse enter (hover effect)
	            	endGameButton.setBorder(new LineBorder(Color.yellow, 2));
	            	endGameButton.setForeground(Color.yellow);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                // Restore button appearance on mouse exit
	            	endGameButton.setBorder(new LineBorder(Color.white, 2));
	            	endGameButton.setForeground(Color.white);
	            }
	        });
	}
	private void setTakeTurnButton() 
	{
		
		takeTurnButton = new JButton("Take Turn");
		takeTurnButton.setBounds(1220, 620, 150, 30);
		takeTurnButton.setForeground(Color.white);
		takeTurnButton.setFont(new Font("Tahoma", Font.ITALIC, 15));
		takeTurnButton.setOpaque(false);
		takeTurnButton.setBorder(new LineBorder(Color.white, 2));
		backgroundPanel.add(takeTurnButton);
		takeTurnButton.addMouseListener(new MouseListener() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                // Handle mouse click event if needed
	            	new PlayerDashboardFrame(KUAlchemistsGame.currentPlayer);
	            }

	            @Override
	            public void mousePressed(MouseEvent e) {
	                // Handle mouse press event if needed
	            }

	            @Override
	            public void mouseReleased(MouseEvent e) {
	                // Handle mouse release event if needed
	            }

	            @Override
	            public void mouseEntered(MouseEvent e) {
	                // Change button appearance on mouse enter (hover effect)
	            	takeTurnButton.setBorder(new LineBorder(Color.yellow, 2));
	            	takeTurnButton.setForeground(Color.yellow);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                // Restore button appearance on mouse exit
	            	takeTurnButton.setBorder(new LineBorder(Color.white, 2));
	            	takeTurnButton.setForeground(Color.white);
	            }
	        });
	}

	
	/*
    public static void main(String[] args) {
        // Create and display the frame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }*/
}


	
	
	
	
	
	