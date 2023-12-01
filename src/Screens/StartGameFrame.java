package Screens;


import javax.swing.*;

import Business.Player;
import Controllers.StartGameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameFrame extends GeneralFrame {
	
	/*
	 * Screen displayed after start game is clicked.
	 * Lets the users enter names and avatar, then starts the game with the given info.
	 */
    private JTextField playerOneNameTextField;
    private JTextField player2name;
    JPanel backgroundPanel;
    JPanel playerOnePanel;
    JPanel playerTwoPanel;
    JPanel buttonsPanel;
    
//    JButton startGameButton;
//    JButton exitGameButton;
//    JPanel panel;

    public StartGameFrame() {
    	
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
    	this.setBackgroundPanel();
    	// this.setButtonsPanel();
    	this.setPlayerPanel(1);
    	
                        
                        
    }
    
    private void setPlayerPanel(int playerNumber)
    {
    	JPanel playerPanel = new JPanel();
    	playerPanel.setSize(new Dimension(200, 400));
    	playerPanel.setBounds(200, 100, 400, 500);
    	playerPanel.setBackground(Color.blue);
    	backgroundPanel.add(playerPanel);
    	
    	JLabel playerLabel = new JLabel("Player " + playerNumber);
    	playerPanel.add(playerLabel);
    	playerLabel.setBounds(10, 20, 150, 30);
    	playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
    	JTextField playerTextField = new JTextField();
    	playerPanel.add(playerTextField);
        playerTextField.setBounds(10, 50, 200, 30);
        playerTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JLabel avatarLabel = new JLabel("Choose your avatar");
        playerPanel.add(avatarLabel);
        avatarLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        avatarLabel.setBounds(10, 100, 157, 30);
        
        JRadioButton avatarRatioButton1 = new JRadioButton("Avatar 1");
        avatarRatioButton1.setBounds(48, 213, 103, 21);
        
        JRadioButton avatarRatioButton2 = new JRadioButton("Avatar 2");
        avatarRatioButton2.setBounds(218, 213, 103, 21);
        
        JRadioButton avatarRatioButton3 = new JRadioButton("Avatar 3");
        avatarRatioButton3.setBounds(48, 345, 103, 21);
        
        JRadioButton avatarRatioButton4 = new JRadioButton("Avatar 4");
        avatarRatioButton4.setBounds(218, 345, 103, 21);
        
    }
    
    private void setButtonsPanel()
    {
    	// startGameButton
    	JButton startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        startGameButton.setBounds(1123, 524, 150, 30);
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 String p1name = playerOneNameTextField.getText();
                 String p2name = player2name.getText();
                 StartGameController sgc = new StartGameController();
                 sgc.handleStartGame(p1name, p2name, null, null);
                 
            }
        });
        
        // exitGameButton
        JButton exitGameButton = new JButton("Exit Game");
        exitGameButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        exitGameButton.setBounds(1123, 606, 150, 30);
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
        
        // howToPlayButton
        JButton howToPlayButton = new JButton("How to play?");
        howToPlayButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        howToPlayButton.setBounds(1123, 566, 150, 30);
        howToPlayButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HowToPlayFrame frame = new HowToPlayFrame();
				frame.setVisible(true);
				
			}
		});
        
        
        backgroundPanel.add(startGameButton);
        backgroundPanel.add(exitGameButton);
        backgroundPanel.add(howToPlayButton);
    }
    
    
    private void setBackgroundPanel()
    {
    	backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setSize(new Dimension(1540, 820));
        getContentPane().add(backgroundPanel);
    }
    
    
    public static void main(String[] args) {
        // Create and display the frame   
    	//Player player = new Player("Simge", "Path", null, null, 10, 0, null);
        new StartGameFrame();
    }



}
