package Screens;


import javax.swing.*;
import javax.swing.border.Border;

import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.StartGameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class StartGameFrame extends GeneralFrame {

	/*
	 * Screen displayed after start game is clicked.
	 * Lets the users enter names and avatar, 
	 * then starts the game with the given info.
	 * It takes a game instance as a parameter.
	 */
	
	private KUAlchemistsGame game;
    private JPanel backgroundPanel;
    
    private JLabel usernameLabel;
    private JLabel avatarLabel;
    
	private JTextField player1UsernameTexfField =  new JTextField();
	private JTextField player2UsernameTexfField =  new JTextField();
	
    private JLabel player1Label = new JLabel("Player 1");
    private JLabel player2Label = new JLabel("Player 2");
    
    ButtonGroup player1avatarButtonGroup = new ButtonGroup();
    ButtonGroup player2avatarButtonGroup = new ButtonGroup();
    private JRadioButton player1Avatar1Button = new JRadioButton("Avatar 1");
    private JRadioButton player1Avatar2Button = new JRadioButton("Avatar 2");
    private JRadioButton player1Avatar3Button = new JRadioButton("Avatar 3");
    private JRadioButton player1Avatar4Button = new JRadioButton("Avatar 4");
    private JRadioButton player2Avatar1Button = new JRadioButton("Avatar 1");
    private JRadioButton player2Avatar2Button = new JRadioButton("Avatar 2");
    private JRadioButton player2Avatar3Button = new JRadioButton("Avatar 3");
    private JRadioButton player2Avatar4Button = new JRadioButton("Avatar 4");
    
    private JButton startGameButton = new JButton("Start Game");
    private JButton howToPlayButton = new JButton("How to Play");
    private JButton exitGameButton = new JButton("Exit Game");
    
    String p1avatar;
    String p2avatar;
    String p1name;
    String p2name;
    
    public StartGameFrame(KUAlchemistsGame game) {
    	
    	super();
    	this.setBackground();
    	this.setPlayer1Components();
    	this.setPlayer2Components();
    	this.setStartGameButton();
    	this.setHowToPlayButton();
    	this.setExitGameButton();
                        
    }
    
    private void setBackground() {
    	
    	backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                
           	 super.paintComponent(g);
                
                Image image = new ImageIcon(this.getClass().getResource("/Images/startGameBack.png")).getImage();
                
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

    private void setPlayer1Components()
    {
    	player1Label.setBounds(340, 180, 120, 30);
    	player1Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Label.setForeground(Color.white);
    	
    	usernameLabel = new JLabel("Enter a username.");
    	usernameLabel.setBounds(270, 240, 200, 30);
    	usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	usernameLabel.setForeground(Color.white);
    	
    	player1UsernameTexfField.setBounds(270, 290, 200, 30);
    	
    	avatarLabel = new JLabel("Select an avatar.");
    	avatarLabel.setBounds(270, 350, 200, 30);
    	avatarLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	avatarLabel.setForeground(Color.white);
    	
    	player1Avatar1Button.setBounds(270, 400, 150, 30);
    	player1Avatar1Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Avatar1Button.setForeground(Color.white);
    	
    	player1Avatar2Button.setBounds(450, 400, 150, 30);
    	player1Avatar2Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Avatar2Button.setForeground(Color.white);
    	
    	player1Avatar3Button.setBounds(270, 500, 150, 30);
    	player1Avatar3Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Avatar3Button.setForeground(Color.white);
    	
    	player1Avatar4Button.setBounds(450, 500, 150, 30);
    	player1Avatar4Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Avatar4Button.setForeground(Color.white);
    	
    	// Add the radio buttons to the ButtonGroup
    	player1avatarButtonGroup.add(player1Avatar1Button);
    	player1avatarButtonGroup.add(player1Avatar2Button);
    	player1avatarButtonGroup.add(player1Avatar3Button);
    	player1avatarButtonGroup.add(player1Avatar4Button);
    	
    	backgroundPanel.add(player1Label);
    	backgroundPanel.add(usernameLabel);
    	backgroundPanel.add(player1UsernameTexfField);
    	backgroundPanel.add(avatarLabel);
    	backgroundPanel.add(player1Avatar1Button);
    	backgroundPanel.add(player1Avatar2Button);
    	backgroundPanel.add(player1Avatar3Button);
    	backgroundPanel.add(player1Avatar4Button);
    	
    	
    }
    
    private void setPlayer2Components()
    {
    	player2Label.setBounds(1020, 180, 120, 30);
    	player2Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Label.setForeground(Color.white);
    	
    	usernameLabel = new JLabel("Enter a username.");
    	usernameLabel.setBounds(950, 240, 200, 30);
    	usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	usernameLabel.setForeground(Color.white);
    	
    	player2UsernameTexfField.setBounds(950, 290, 200, 30);
    	
    	avatarLabel = new JLabel("Select an avatar.");
    	avatarLabel.setBounds(950, 350, 200, 30);
    	avatarLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	avatarLabel.setForeground(Color.white);
    	
    	player2Avatar1Button.setBounds(950, 400, 150, 30);
    	player2Avatar1Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Avatar1Button.setForeground(Color.white);
    	
    	player2Avatar2Button.setBounds(1130, 400, 150, 30);
    	player2Avatar2Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Avatar2Button.setForeground(Color.white);
    	
    	player2Avatar3Button.setBounds(950, 500, 150, 30);
    	player2Avatar3Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Avatar3Button.setForeground(Color.white);
    	
    	player2Avatar4Button.setBounds(1130, 500, 150, 30);
    	player2Avatar4Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Avatar4Button.setForeground(Color.white);
    	

    	// Add the radio buttons to the ButtonGroup
    	player2avatarButtonGroup.add(player2Avatar1Button);
    	player2avatarButtonGroup.add(player2Avatar2Button);
    	player2avatarButtonGroup.add(player2Avatar3Button);
    	player2avatarButtonGroup.add(player2Avatar4Button);
    	
    	backgroundPanel.add(player2Label);
    	backgroundPanel.add(usernameLabel);
    	backgroundPanel.add(player2UsernameTexfField);
    	backgroundPanel.add(avatarLabel);
    	backgroundPanel.add(player2Avatar1Button);
    	backgroundPanel.add(player2Avatar2Button);
    	backgroundPanel.add(player2Avatar3Button);
    	backgroundPanel.add(player2Avatar4Button);
    }
  	
    private void setStartGameButton()
    {
    	backgroundPanel.add(startGameButton);
    	startGameButton.setBounds(650, 550, 200, 30);
    	startGameButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	startGameButton.setForeground(Color.white);
    	startGameButton.setOpaque(false);
    	startGameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
 
    	/*
    	 * We need to handle empty text field or radius button cases!!!!
    	 * */
    	
    	startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                 p1name = player1UsernameTexfField.getText();
                 p2name = player2UsernameTexfField.getText();
                 p1avatar = getSelectedButtonText(player1avatarButtonGroup);
                 p2avatar = getSelectedButtonText(player2avatarButtonGroup);
                 boolean startOk = true;
                 
                 if (p1avatar == null || p2avatar == null) {
                	 JOptionPane.showMessageDialog(new JFrame(), "Please select avatars!",
                             "Avatar Error", JOptionPane.ERROR_MESSAGE);
                	 startOk = false;
                 }
                 
                 else if (p1avatar == p2avatar) {
                	 JOptionPane.showMessageDialog(new JFrame(), "Please choose different avatars!",
                             "Avatar Error", JOptionPane.ERROR_MESSAGE);
                	 startOk = false;
                 }
                 
                 if ((p1name.isEmpty()) || p2name.isEmpty()) {
                	 JOptionPane.showMessageDialog(new JFrame(), "Please enter usernames!",
                             "Name Error", JOptionPane.ERROR_MESSAGE);
                	 startOk = false;
                 }    

                 if (startOk) {
                     StartGameController sgc = new StartGameController();
                     sgc.handleStartGame(p1name, p2name, p1avatar, p2avatar);
                     startGamePressed();
                 }

            }
        });    	
    }
    
    
    
    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
    
    private void setHowToPlayButton()
    {
    	backgroundPanel.add(howToPlayButton);
    	howToPlayButton.setBounds(650, 600, 200, 30);
    	howToPlayButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	howToPlayButton.setForeground(Color.white);
    	howToPlayButton.setOpaque(false);
    	howToPlayButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

    	howToPlayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				howToPlayButtonPressed();
			}
		});
    }
    
    private void setExitGameButton()
    {
    	backgroundPanel.add(exitGameButton);
    	exitGameButton.setBounds(650, 650, 200, 30);
    	exitGameButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	exitGameButton.setForeground(Color.white);
    	exitGameButton.setOpaque(false);
    	exitGameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

    	exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
    }
    
    private void startGamePressed() {
    	new MainGameFrame();
    	this.dispose();
    }
    
    private void howToPlayButtonPressed()
    {
    	new HowToPlayFrame();
    	this.dispose();
    }
    
    public static void main(String[] args) {
        // Create and display the frame   
        new StartGameFrame(new KUAlchemistsGame(2));
    }



}