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
    private JButton hostButton;
    private JButton joinButton;
    private JLabel loadingLabel;
    private JProgressBar progressBar;
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
    	this.setHostButton();
    	this.setJoinButton();
    	
    	// set progress bar
    	this.setProgressBar();
    	
    	
        
        JLabel introduction = new JLabel("Choose how many player will play and let's get started...");
        introduction.setForeground(Color.WHITE);
        introduction.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        introduction.setBounds(120, 110, 560, 66);
        backgroundPanel.add(introduction);
     }
    
 
    
    private void setHostButton()
    {
    	hostButton = new JButton("Host Game");
    	hostButton.setHorizontalTextPosition(SwingConstants.CENTER);
    	hostButton.setVerticalTextPosition(SwingConstants.BOTTOM);
    	hostButton.setBackground(Color.green); 
    	hostButton.setForeground(Color.WHITE); 
    	hostButton.setFont(new Font("Arial", Font.BOLD, 12)); 
    	hostButton.setBounds(220, 230, 100, 30);


    	hostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hostButtonClicked();
            }
        });
        
        backgroundPanel.add(hostButton);
        
    }
    
    
    private void setJoinButton()
    {
    	joinButton = new JButton("Join Game");
    	joinButton.setHorizontalTextPosition(SwingConstants.CENTER);
    	joinButton.setVerticalTextPosition(SwingConstants.BOTTOM);
    	joinButton.setBackground(Color.green); 
    	joinButton.setForeground(Color.WHITE); 
    	joinButton.setFont(new Font("Arial", Font.BOLD, 12)); 
    	joinButton.setBounds(420, 230, 100, 30);
    	
    	


    	joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	joinButtonClicked();
            }
        });
        
        backgroundPanel.add(joinButton);
        
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
    
    private void hostButtonClicked() {
    	
   
    	PlaySong.play("ButtonClick");
    	
    	new HostGameFrame();
    	this.dispose();
    	
    }
    
    private void joinButtonClicked() {
    	PlaySong.play("ButtonClick");
    	new JoinGameFrame();
    	this.dispose();


    }
}