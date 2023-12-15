package Screens;
import javax.swing.*;

import Business.KUAlchemistsGame;
import Controllers.PlayGameController;
import Controllers.StartGameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class WelcomeFrame extends JFrame {

	private PlayGameController playGameController;
	private JPanel backgroundPanel;
    private JButton startButton;
    private JLabel loadingLabel;
    private JProgressBar progressBar;

    public WelcomeFrame() 
    {
    	// set general appearance
    	this.setAppearance();
    	
    	// set loading label
    	this.setLoadingLabel();
    	
    	// set start button
    	this.setStartButton();
    	
    	// set progress bar
    	this.setProgressBar();
    	
    	// create JPnale and set background 
    	this.setBackground();
    	
    }
    
    private void setAppearance()
    {
    	// As soon as the constructor is called,
    	// set visibility of the frame to true.
    	this.setVisible(true);
    	this.setResizable(false);
    	this.setTitle("Welcome to the Game");
    	this.setSize(770, 410);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.getContentPane().setLayout(null);
    }

    private void setBackground() {
    	 
    	backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                
           	 super.paintComponent(g);
                
                Image image = new ImageIcon(this.getClass().getResource("/BackgroundImages/welcomeBackground.png")).getImage();
                
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
         backgroundPanel.setSize(new Dimension(770, 410));
         backgroundPanel.setLayout(null);
         backgroundPanel.setOpaque(false);
         
         // add every component to background panel
         backgroundPanel.add(loadingLabel);
         backgroundPanel.add(startButton);
         backgroundPanel.add(progressBar);
         getContentPane().add(backgroundPanel);
    }
    
    private void setStartButton()
    {
    	startButton = new JButton("Start Game");
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        startButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        startButton.setBackground(Color.green); 
        startButton.setForeground(Color.WHITE); 
        startButton.setFont(new Font("Arial", Font.BOLD, 12)); 
        startButton.setBounds(320, 230, 100, 30); 

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButtonClicked();
            }
        });
        
    }
    
    private void setLoadingLabel()
    {
    	loadingLabel = new JLabel("   Codechemists is loading, this may take a while...");
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
        loadingLabel.setBounds(127, 199, 354, 30);
        loadingLabel.setVisible(false);
        loadingLabel.setBackground(Color.GRAY);
        loadingLabel.setOpaque(true);
    }
    
    private void setProgressBar() 
    {
    	 progressBar = new JProgressBar();
         progressBar.setBounds(229, 240, 160, 11);
         progressBar.setValue(20);
         progressBar.setVisible(false);
    }
    
    private void startButtonClicked() {
    	
    	/*
    	 * When the user clicks on the start button 
    	 * The game is created and
    	 * the game instance is sent to the new frame
    	 * */
    	
    	playGameController = new PlayGameController();
    	KUAlchemistsGame game = playGameController.playGame(2);
        new StartGameFrame(game);
        this.dispose();
    }
    
    
 
    public static void main(String[] args) {
        
    	// Create and display the frame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeFrame();
            }
        });
    }
}
