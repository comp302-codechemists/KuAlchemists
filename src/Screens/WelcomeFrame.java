package Screens;
import javax.swing.*;

import Business.KUAlchemistsGame;
import Controllers.PlayGameController;
import DesignSystem.GameFrame;
import soundEffects.PlaySong;
import uiHelpers.MagicFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class WelcomeFrame extends MagicFrame {

	private PlayGameController playGameController;
    private JButton onlineButton;
    private JButton offlineButton;
    private JLabel loadingLabel;
    private JProgressBar progressBar;
    private JComboBox<String> playerCount;
    public WelcomeFrame()
    {
    	this(null); 
    }
    
    
    public WelcomeFrame(KUAlchemistsGame game) 
    {
    	super(game, GameFrame.welcomeFrameWidth, GameFrame.welcomeFrameHeight);
    	setBackground("/BackgroundImages/welcomeBackground.png");
    	
    	// set loading label
    	this.setLoadingLabel();
    	
    	// set start button
    	this.setOnlineButton();
    	this.setJoinButton();
    	
    	// set progress bar
    	this.setProgressBar();
    	
    	this.setPlayerCount();

        
        JLabel introduction = new JLabel("Choose how many player will play and let's get started...");
        introduction.setForeground(Color.WHITE);
        introduction.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        introduction.setBounds(120, 110, 560, 66);
        backgroundPanel.add(introduction);
     }
    
    private void setPlayerCount() {
    	String[] options = {"2", "3", "4"};
        playerCount = new JComboBox<String>(options);
        playerCount.setMaximumRowCount(3);
        playerCount.setSelectedIndex(0);
        playerCount.setBounds(320, 273, 100, 30);
        backgroundPanel.add(playerCount);
    }
    
    private void setOnlineButton()
    {
    	onlineButton = new JButton("Online");
    	onlineButton.setHorizontalTextPosition(SwingConstants.CENTER);
    	onlineButton.setVerticalTextPosition(SwingConstants.BOTTOM);
    	onlineButton.setBackground(Color.green); 
    	onlineButton.setForeground(Color.WHITE); 
    	onlineButton.setFont(new Font("Arial", Font.BOLD, 12)); 
    	onlineButton.setBounds(220, 230, 100, 30);


    	onlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onlineButtonClicked();
            }
        });
        
        backgroundPanel.add(onlineButton);
        
    }
    
    
    private void setJoinButton()
    {
    	offlineButton = new JButton("Offline");
    	offlineButton.setHorizontalTextPosition(SwingConstants.CENTER);
    	offlineButton.setVerticalTextPosition(SwingConstants.BOTTOM);
    	offlineButton.setBackground(Color.green); 
    	offlineButton.setForeground(Color.WHITE); 
    	offlineButton.setFont(new Font("Arial", Font.BOLD, 12)); 
    	offlineButton.setBounds(420, 230, 100, 30);
    	
    	


    	offlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	offlineButtonClicked();
            }
        });
        
        backgroundPanel.add(offlineButton);
        
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
        backgroundPanel.add(loadingLabel);
    }
    
    private void setProgressBar() 
    {
    	 progressBar = new JProgressBar();
         progressBar.setBounds(229, 240, 160, 11);
         progressBar.setValue(20);
         progressBar.setVisible(false);
         backgroundPanel.add(progressBar);
    }
    
    private void onlineButtonClicked() {
    	
   
    	PlaySong.play("ButtonClick");
    	
    	new OnlineOptionFrame();
    	this.dispose();
    	
    }
    
    private void offlineButtonClicked() {
    	PlaySong.play("ButtonClick");
    	playGameController = new PlayGameController();
    	int selected = Integer.parseInt((String) playerCount.getSelectedItem());
    	KUAlchemistsGame game = playGameController.playGame(selected);
        new StartGameFrame(game);
        this.dispose();


    }
}