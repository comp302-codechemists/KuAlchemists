package Screens;


import javax.swing.*;
import javax.swing.border.Border;

import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.StartGameController;
import DesignSystem.GameButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
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
    private JRadioButton player1Avatar1Button = new JRadioButton("avatar1");
    private JRadioButton player1Avatar2Button = new JRadioButton("avatar2");
    private JRadioButton player1Avatar3Button = new JRadioButton("avatar3");
    private JRadioButton player1Avatar4Button = new JRadioButton("avatar4");
    private JRadioButton player2Avatar1Button = new JRadioButton("avatar1");
    private JRadioButton player2Avatar2Button = new JRadioButton("avatar2");
    private JRadioButton player2Avatar3Button = new JRadioButton("avatar3");
    private JRadioButton player2Avatar4Button = new JRadioButton("avatar4");
    
    private JButton startGameButton;
    private JButton howToPlayButton;
    private JButton exitGameButton;
    
    String p1avatar;
    String p2avatar;
    String p1name;
    String p2name;
    
    ImageIcon avatar1;
    ImageIcon avatar2;
    ImageIcon avatar3;
    ImageIcon avatar4;
    
    public StartGameFrame(KUAlchemistsGame game) {
    	
    	super();
    	this.game = game;
    	this.getAvatarImages();
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
                
                Image image = new ImageIcon(this.getClass().getResource("/BackgroundImages/startGameBackground.png")).getImage();
                
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
    
    private ImageIcon makeCircularAvatar(ImageIcon originalIcon) 
    {
        int diameter = Math.min(originalIcon.getIconWidth(), originalIcon.getIconHeight());
        BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        // Create a circle and clip the original image to the circle
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, diameter, diameter);
        g2.setClip(circle);
        g2.drawImage(originalIcon.getImage(), 0, 0, diameter, diameter, null);
        g2.dispose();

        return new ImageIcon(image);
    }

    private void getAvatarImages() 
    {
        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/Images/avatar1.png"));
        Image originalImage1 = originalIcon1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        avatar1 = makeCircularAvatar(new ImageIcon(originalImage1));

        ImageIcon originalIcon2 = new ImageIcon(getClass().getResource("/Images/avatar2.png"));
        Image originalImage2 = originalIcon2.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        avatar2 = makeCircularAvatar(new ImageIcon(originalImage2));

        ImageIcon originalIcon3 = new ImageIcon(getClass().getResource("/Images/avatar3.png"));
        Image originalImage3 = originalIcon3.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        avatar3 = makeCircularAvatar(new ImageIcon(originalImage3));

        ImageIcon originalIcon4 = new ImageIcon(getClass().getResource("/Images/avatar4.png"));
        Image originalImage4 = originalIcon4.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        avatar4 = makeCircularAvatar(new ImageIcon(originalImage4));
    }


    private void setPlayer1Components()
    {
    	player1Label.setBounds(252, 180, 120, 30);
    	player1Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Label.setForeground(Color.white);
    	
    	usernameLabel = new JLabel("Enter a username.");
    	usernameLabel.setBounds(182, 240, 200, 30);
    	usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	usernameLabel.setForeground(Color.white);
    	
    	player1UsernameTexfField.setBounds(182, 290, 200, 30);
    	
    	avatarLabel = new JLabel("Select an avatar.");
    	avatarLabel.setBounds(182, 350, 200, 30);
    	avatarLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	avatarLabel.setForeground(Color.white);
    	
    	player1Avatar1Button.setBounds(182, 421, 89, 89);
    	player1Avatar1Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Avatar1Button.setForeground(Color.white);
    	player1Avatar1Button.setIcon(avatar1);
    	
    	player1Avatar2Button.setBounds(296, 421, 89, 89);
    	player1Avatar2Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Avatar2Button.setForeground(Color.white);
    	player1Avatar2Button.setIcon(avatar2);
    	
    	player1Avatar3Button.setBounds(182, 521, 89, 89);
    	player1Avatar3Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Avatar3Button.setForeground(Color.white);
    	player1Avatar3Button.setIcon(avatar3);
    	
    	player1Avatar4Button.setBounds(296, 521, 89, 89);
    	player1Avatar4Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Avatar4Button.setForeground(Color.white);
    	player1Avatar4Button.setIcon(avatar4);
    	
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
    	player2Label.setBounds(1211, 180, 120, 30);
    	player2Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Label.setForeground(Color.white);
    	
    	usernameLabel = new JLabel("Enter a username.");
    	usernameLabel.setBounds(1141, 240, 200, 30);
    	usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	usernameLabel.setForeground(Color.white);
    	
    	player2UsernameTexfField.setBounds(1141, 290, 200, 30);
    	
    	avatarLabel = new JLabel("Select an avatar.");
    	avatarLabel.setBounds(1141, 350, 200, 30);
    	avatarLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	avatarLabel.setForeground(Color.white);
    	
    	player2Avatar1Button.setBounds(1144, 421, 89, 89);
    	player2Avatar1Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Avatar1Button.setForeground(Color.white);
    	player2Avatar1Button.setIcon(avatar1);
    	
    	player2Avatar2Button.setBounds(1262, 421, 89, 89);
    	player2Avatar2Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Avatar2Button.setForeground(Color.white);
    	player2Avatar2Button.setIcon(avatar2);
    	
    	player2Avatar3Button.setBounds(1144, 521, 89, 89);
    	player2Avatar3Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Avatar3Button.setForeground(Color.white);
    	player2Avatar3Button.setIcon(avatar3);
    	
    	player2Avatar4Button.setBounds(1262, 521, 89, 89);
    	player2Avatar4Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Avatar4Button.setForeground(Color.white);
    	player2Avatar4Button.setIcon(avatar4);

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
    	startGameButton = new GameButton("Start Game");
    	backgroundPanel.add(startGameButton);
    	startGameButton.setBounds(650, 550, 200, 30);
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
                 
                 else if ((p1name.isEmpty()) || p2name.isEmpty()) {
                	 JOptionPane.showMessageDialog(new JFrame(), "Please enter usernames!",
                             "Name Error", JOptionPane.ERROR_MESSAGE);
                	 startOk = false;
                 }    
                
                 if (startOk) {
                	 System.out.printf("Avatar path: %s", p1avatar);             	 

                     StartGameController startGameController = new StartGameController(game);
                     startGameController.handleStartGame(p1name, p2name, p1avatar, p2avatar);
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
    	howToPlayButton = new GameButton("How to Play?");
    	backgroundPanel.add(howToPlayButton);
    	howToPlayButton.setBounds(650, 600, 200, 30);
    	howToPlayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				howToPlayButtonPressed();
			}
		});
    }
    
    private void setExitGameButton()
    {
    	exitGameButton = new GameButton("Exit Game");
    	backgroundPanel.add(exitGameButton);
    	exitGameButton.setBounds(650, 650, 200, 30);
    	exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
    }
    
    private void startGamePressed() {
    	new MainGameFrame(this.game);
    	this.dispose();
    }
    
    private void howToPlayButtonPressed()
    {
    	new HowToPlayFrame();
    }
}