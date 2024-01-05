package Screens;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Business.KUAlchemistsGame;
import Business.*;
import DesignSystem.GameFrame;
import networking.Client;
import networking.ClientHandler;
import networking.Server;
import soundEffects.PlaySong;
import uiHelpers.MagicFrame;

public class OnlineOptionFrame extends MagicFrame {
	private JButton hostButton;
    private JButton joinButton;
    private JTextArea playerName;
	 public OnlineOptionFrame()
	    {
	    	this(null); 
	    }
	    
	    
	    public OnlineOptionFrame(KUAlchemistsGame game) 
	    {
	    	super(game, GameFrame.welcomeFrameWidth, GameFrame.welcomeFrameHeight);
	    	setBackground("/BackgroundImages/welcomeBackground.png");
	    	
	    	this.playerName = new JTextArea();
	    	playerName.setBounds(320, 190, 100, 30);
	    	playerName.setLineWrap(true);
	    	playerName.setWrapStyleWord(true);
	    	playerName.setEditable(true);
	    	playerName.setText("Enter your playername");
	    	backgroundPanel.add(playerName);
	    	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	this.setHostButton();
	    	this.setJoinButton();
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
	                try {
						hostButtonClicked();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
	    
	    
	    private void hostButtonClicked() throws IOException {

	    	Player.players.add(new Player("Zort" ,"avatar1"));
	    	PlaySong.play("ButtonClick");
	        HostGameFrame hostFrame = new HostGameFrame();

	        new Thread(() -> {
	            try {
	                Server server = new Server();
	                // Perform any other server-related logic here

	                SwingUtilities.invokeLater(() -> {
	                    this.dispose();
	                });

	                SwingUtilities.invokeLater(() -> {
	                    hostFrame.setVisible(true);
	                });
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }).start();
	        
	        new Thread(() -> {
	            try {
	                Client newClient = new Client("172.16.126.0",hostFrame);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }).start();
	        

	    }
	    
	    private void joinButtonClicked() {
	    	
	    	PlaySong.play("ButtonClick");
	    	new JoinGameFrame();
	    	this.dispose();


	    }
}
