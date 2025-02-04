package Screens;

import javax.print.attribute.AttributeSet;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import networking.Client;
import networking.ClientHandler;
import Business.Ingredient;

import Business.GameEvent;
import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Theory;
import DesignSystem.GameButton;
import DesignSystem.GameText;
import soundEffects.PlaySong;
import Business.Theory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MainGameFrame extends GeneralFrame{
	
	/* 
	 * Displays the entire game board, including all game objects (Board, Tokens,
	 * ingredients, potions, Publication Cards, and Deduction Board).
	 * Provides comprehensive information about each player's resources, scores, and
	 * progress during the game.
	 */
	private JTable playersInfoTable;
	private JButton endGameButton;
	private JButton exitGameButton;
	private JButton howToPlayButton;
	private JButton pauseGameButton;
	private JButton takeTurnButton;
	private JTextArea gameLogArea;
	private JTextArea playerNameDisplayed;
	private JButton startGameButton;

	public MainGameFrame(KUAlchemistsGame game) 
	{
		super(game);
		setBackground("/BackgroundImages/mainGameBackground.png");
		this.setPlayersInfoTable();
		this.setButtons();
		this.setTheoriesPanel();
		this.setGameLog();
		this.setDirections();
	
		setExistingTheories();
		
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
		if (game.isOnline() == false) {
			setTakeTurnButton();
		}
		else {
			setStartGameButton();
		}

		backgroundPanel.add(directionsLabel);
		
	}
	
	private void setGameLog() {
	    gameLogArea = new JTextArea(10, 30);
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

	    //TODO 
	    /*
	     * I can't set game log colors separately. They all change color once I set it.
	     */
	    // BEYZA HELP!
	    for (GameEvent event : GameEvent.getEvents()) {
	        Color color = event.getColor();
	        String eventString = event.getEventString();
	        //gameLogArea.setForeground(color);       
	        gameLogArea.append(eventString + "\n");
	    }

	    backgroundPanel.add(scrollPane); 
	}

	

	private void setTheoriesPanel() {
	    
		JPanel initheoryPanel = new JPanel();
		initheoryPanel.setLayout(new GridLayout(4, 2, 5, 5)); 
		initheoryPanel.setBounds(10, 42, 485, 698);
	    initheoryPanel.setOpaque(false);

	    // Create an EmptyBorder with desired spacing
	    Border spacingBorder = BorderFactory.createEmptyBorder(1,1,1,1);

	    for (int i = 0; i < 8; i++) {
	        JLabel theoryLabel = new JLabel();
	        theoryLabel.setPreferredSize(new Dimension(200, 150));
	        theoryLabel.setBorder(spacingBorder); // Apply the spacing border to each label
	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/theory" + (i + 1) + ".png"));
	        Image image = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
	        theoryLabel.setIcon(new ImageIcon(image));
	        initheoryPanel.add(theoryLabel);
	       	              
	    }

	    backgroundPanel.add(initheoryPanel);
	}

	private void setExistingTheories() {
		JPanel theoryPanel = new JPanel();
		theoryPanel.setLayout(new GridLayout(4, 2, 5, 5)); 
		theoryPanel.setBounds(35, 65, 485, 698);
	    theoryPanel.setOpaque(false);

	    // Create an EmptyBorder with desired spacing
	    Border spacingBorder = BorderFactory.createEmptyBorder(1,1,1,100);
	    List<Theory> allTheories = Theory.getAllTheories();
	    List<JLabel> labels = new ArrayList<>();
	    
	    for (int i = 0; i < 8; i++) {
	        JLabel theoryLabel = new JLabel();
	        theoryLabel.setPreferredSize(new Dimension(60, 70));
	        theoryLabel.setBorder(spacingBorder); 
	        labels.add(theoryLabel);
	        theoryPanel.add(theoryLabel);
	       
	    }
	    
	    for (Theory theory : allTheories) {
	    	int i = Ingredient.getIngredientIndex(theory.getIngredient().getName());
	    	JLabel toSet = labels.get(i-1); // i-1th label will be set with the token.
	    	String toke = theory.getAlchemyMarker().getName();
	    	System.out.printf("Ingredient index: %d, token: %s\n", i-1, toke);
	    	ImageIcon imageIcon = new ImageIcon(getClass().getResource("/alchemyMarkerImages/" + toke + ".png"));
	        Image image = imageIcon.getImage().getScaledInstance(60, 70, Image.SCALE_SMOOTH);
	        toSet.setIcon(new ImageIcon(image));
	    	
	    }

	    backgroundPanel.add(theoryPanel);
	    backgroundPanel.setComponentZOrder(theoryPanel, 0);
        
       
		
	}


	private void setPlayersInfoTable()
	{
		playersInfoTable = new JTable();
		int cols = game.getNumberOfPlayers() + 1;
		DefaultTableModel model = new DefaultTableModel(6, cols);
		
		model.setValueAt(" Information", 0, 0);
		model.setValueAt(" Avatar", 1, 0);
		model.setValueAt(" Reputation Points", 2, 0);
		model.setValueAt(" Ingredient Cards", 3, 0);
		model.setValueAt(" Artifact Cards", 4, 0);
		model.setValueAt(" Sickness Level", 5, 0);
		
		for (int i = 0; i < this.game.getNumberOfPlayers(); i++) {
	        String avatarPath = game.getPlayers().get(i).getAvatarPath();
	        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/Images/" + avatarPath.trim() + ".png"));
	        Image originalImage1 = originalIcon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	        ImageIcon avatar = new ImageIcon(originalImage1);
			
			model.setValueAt(this.game.getPlayers().get(i).getUserName(), 0, i+1);
			model.setValueAt(avatar, 1, i + 1);
			model.setValueAt(this.game.getPlayers().get(i).getReputationPoints(), 2, i+1);
			model.setValueAt(this.game.getPlayers().get(i).getIngredients().size(), 3, i+1);
			model.setValueAt(this.game.getPlayers().get(i).getArtifacts().size(), 4, i+1);
			model.setValueAt(this.game.getPlayers().get(i).getSicknessLevel(), 5, i + 1);
		}
		
		playersInfoTable = new JTable(model);
        
		
		playersInfoTable.setFont(new Font("Tahoma", Font.ITALIC, 17));
		playersInfoTable.setForeground(Color.white);
		playersInfoTable.setBorder(new LineBorder(Color.white, 2));
		playersInfoTable.setOpaque(false);
		playersInfoTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		playersInfoTable.setRowHeight(50);
		playersInfoTable.setSurrendersFocusOnKeystroke(true);
		playersInfoTable.setBounds(1100, 85, 370, 300);
		
		// Set transparent background for the table and cells
        playersInfoTable.setOpaque(false);
        playersInfoTable.setBackground(new Color(0, 0, 0, 0)); // Transparent black (adjust alpha as needed)
        playersInfoTable.getColumnModel().getColumn(1).setCellRenderer(new AvatarRenderer());
        playersInfoTable.getColumnModel().getColumn(2).setCellRenderer(new AvatarRenderer());
        if (game.getNumberOfPlayers() > 2) {
            playersInfoTable.getColumnModel().getColumn(3).setCellRenderer(new AvatarRenderer());
            	if (game.getNumberOfPlayers() > 3) {
                    playersInfoTable.getColumnModel().getColumn(4).setCellRenderer(new AvatarRenderer());
            	}
        }
        
		backgroundPanel.add(playersInfoTable);
		
		JLabel round = new JLabel("Round %d");
		JLabel turn = new JLabel("%s's turn");
		
		playerNameDisplayed = new JTextArea();
		playerNameDisplayed.setEditable(false);
		playerNameDisplayed.setBounds(720, 10, 100, 40);
		playerNameDisplayed.setVisible(game.isOnline());
		backgroundPanel.add(playerNameDisplayed);

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
		exitGameButton.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
                PlaySong.play("ButtonClick");
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Are you sure you want to exit the game?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        new Object[]{"Yes", "No"},
                        "No");
                if (choice == JOptionPane.YES_OPTION) {
                	MainGameFrame.this.dispose();
                }	
			}
		});
		backgroundPanel.add(exitGameButton);

	}
	private void setPauseGameButton() 
	{
		pauseGameButton = new GameButton("Pause Game");
		pauseGameButton.setBounds(1320, 560, 150, 30);
		pauseGameButton.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
                PlaySong.play("ButtonClick");
                if (KUAlchemistsGame.instance.isOnline()) {
					Client.instance.sendMessage("PAUSE");
				}

				KUAlchemistsGame.instance.pause();
				/*gameLogArea.revalidate();
				gameLogArea.repaint();*/
			}
		});
		backgroundPanel.add(pauseGameButton);
	}
	private void setHowToPlayButton()
	{
		howToPlayButton = new GameButton("How to Play?");
		howToPlayButton.setBounds(1100, 560, 150, 30);
		howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlaySong.play("ButtonClick");
                new HowToPlayFrame(game);
            }
        });
		backgroundPanel.add(howToPlayButton);
		
	}
	private void setEndGameButton() 
	{
		
		endGameButton = new GameButton("End Game");
		endGameButton.setBounds(1100, 500, 150, 30);
		endGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlaySong.play("ButtonClick");
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Are you sure you want to end the current game?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        new Object[]{"Yes", "No"},
                        "No");
                if (choice == JOptionPane.YES_OPTION) {
                	MainGameFrame.this.dispose();
                	new WelcomeFrame();
                } 
            }
        });

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
                PlaySong.play("ButtonClick");
            	new PlayerDashboardFrame(game);
            	MainGameFrame.this.dispose();
            }
        });
		
	}
	
	

	public void setStartGameButton() {
		startGameButton = new GameButton("Start!");
		startGameButton.setBounds(650, 330, 150, 30);
		
		startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlaySong.play("ButtonClick");
                String userName = KUAlchemistsGame.instance.currentPlayer.getUserName();
            	KUAlchemistsGame.instance.client.sendMessage("SPESIFIC,TAKETURN," + userName);
            }
        });

		
	}
	
	public void disableStartGameButton() {
		this.startGameButton.setEnabled(false);
	}
	
	public void enableTurnButton() {
		takeTurnButton.setEnabled(true);
	}
	
	public void disableTurnButton() {
		takeTurnButton.setEnabled(false);

	}
	
	public void updatePlayerName(String playerName) {
		playerNameDisplayed.setText(playerName);
	}
}


	
	
	
	
	
	