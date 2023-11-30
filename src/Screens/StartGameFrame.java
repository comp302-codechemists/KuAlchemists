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
    private JTextField player1name;
    private JTextField player2name;

    public StartGameFrame() {
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null); 
        backgroundPanel.setLocation(0, 0);
        backgroundPanel.setSize(new Dimension(1540, 820));
        getContentPane().add(backgroundPanel);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        startGameButton.setBounds(1123, 524, 150, 30);

        backgroundPanel.add(startGameButton);
        


        JButton exitGameButton = new JButton("Exit Game");
        exitGameButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        exitGameButton.setBounds(1123, 606, 150, 30);
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
        backgroundPanel.add(exitGameButton);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(223, 170, 337, 466);
        backgroundPanel.add(panel);
        panel.setLayout(null);
        
                JLabel firstUserLabel = new JLabel("Player 1");
                firstUserLabel.setBounds(55, 54, 150, 30);
                panel.add(firstUserLabel);
                firstUserLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
                
                        player1name = new JTextField();
                        player1name.setBounds(43, 94, 200, 30);
                        panel.add(player1name);
                        player1name.setFont(new Font("Tahoma", Font.PLAIN, 15));
                        
                        JLabel lblNewLabel = new JLabel("Choose your avatar");
                        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
                        lblNewLabel.setBounds(48, 169, 157, 13);
                        panel.add(lblNewLabel);
                        
                        JPanel panel_1 = new JPanel();
                        panel_1.setBounds(48, 246, 81, 56);
                        panel.add(panel_1);
                        
                        JPanel panel_1_1 = new JPanel();
                        panel_1_1.setBounds(48, 380, 81, 56);
                        panel.add(panel_1_1);
                        
                        JPanel panel_1_2 = new JPanel();
                        panel_1_2.setBounds(218, 246, 81, 56);
                        panel.add(panel_1_2);
                        
                        JPanel panel_1_1_1 = new JPanel();
                        panel_1_1_1.setBounds(218, 380, 81, 56);
                        panel.add(panel_1_1_1);
                        
                        JRadioButton rdbtnNewRadioButton = new JRadioButton("Avatar 1");
                        rdbtnNewRadioButton.setBounds(48, 213, 103, 21);
                        panel.add(rdbtnNewRadioButton);
                        
                        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Avatar 2");
                        rdbtnNewRadioButton_1.setBounds(218, 213, 103, 21);
                        panel.add(rdbtnNewRadioButton_1);
                        
                        JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Avatar 3");
                        rdbtnNewRadioButton_2.setBounds(48, 345, 103, 21);
                        panel.add(rdbtnNewRadioButton_2);
                        
                        JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Avatar 4");
                        rdbtnNewRadioButton_3.setBounds(218, 345, 103, 21);
                        panel.add(rdbtnNewRadioButton_3);
                        
                        JPanel panel_2 = new JPanel();
                        panel_2.setBackground(Color.GRAY);
                        panel_2.setLayout(null);
                        panel_2.setBounds(685, 170, 337, 466);
                        backgroundPanel.add(panel_2);
                        
                        JLabel lblPlayer = new JLabel("Player 2");
                        lblPlayer.setFont(new Font("Tahoma", Font.PLAIN, 15));
                        lblPlayer.setBounds(55, 54, 150, 30);
                        panel_2.add(lblPlayer);
                        
                        player2name = new JTextField();
                        player2name.setFont(new Font("Tahoma", Font.PLAIN, 15));
                        player2name.setBounds(43, 94, 200, 30);
                        panel_2.add(player2name);
                        
                        JLabel lblNewLabel_1 = new JLabel("Choose your avatar");
                        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
                        lblNewLabel_1.setBounds(48, 169, 157, 13);
                        panel_2.add(lblNewLabel_1);
                        
                        JPanel panel_1_3 = new JPanel();
                        panel_1_3.setBounds(48, 246, 81, 56);
                        panel_2.add(panel_1_3);
                        
                        JPanel panel_1_1_2 = new JPanel();
                        panel_1_1_2.setBounds(48, 380, 81, 56);
                        panel_2.add(panel_1_1_2);
                        
                        JPanel panel_1_2_1 = new JPanel();
                        panel_1_2_1.setBounds(218, 246, 81, 56);
                        panel_2.add(panel_1_2_1);
                        
                        JPanel panel_1_1_1_1 = new JPanel();
                        panel_1_1_1_1.setBounds(218, 380, 81, 56);
                        panel_2.add(panel_1_1_1_1);
                        
                        JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Avatar 1");
                        rdbtnNewRadioButton_4.setBounds(48, 213, 103, 21);
                        panel_2.add(rdbtnNewRadioButton_4);
                        
                        JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("Avatar 2");
                        rdbtnNewRadioButton_1_1.setBounds(218, 213, 103, 21);
                        panel_2.add(rdbtnNewRadioButton_1_1);
                        
                        JRadioButton rdbtnNewRadioButton_2_1 = new JRadioButton("Avatar 3");
                        rdbtnNewRadioButton_2_1.setBounds(48, 345, 103, 21);
                        panel_2.add(rdbtnNewRadioButton_2_1);
                        
                        JRadioButton rdbtnNewRadioButton_3_1 = new JRadioButton("Avatar 4");
                        rdbtnNewRadioButton_3_1.setBounds(218, 345, 103, 21);
                        panel_2.add(rdbtnNewRadioButton_3_1);
                        
                        JButton btnHowToPlay = new JButton("How to play?");
                        btnHowToPlay.setFont(new Font("Tahoma", Font.PLAIN, 15));
                        btnHowToPlay.setBounds(1123, 566, 150, 30);
                        backgroundPanel.add(btnHowToPlay);
                        
                        JLabel lblNewLabel_2 = new JLabel("Welcome to KUAlchemist!");
                        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
                        lblNewLabel_2.setBounds(470, 53, 277, 81);
                        backgroundPanel.add(lblNewLabel_2);
                        
                        startGameButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                 String p1name = player1name.getText();
                                 String p2name = player2name.getText();
                                 StartGameController sgc = new StartGameController();
                                 sgc.handleStartGame(p1name, p2name, null, null);
                                 
                            }
                        });
    }
    
    
    public static void main(String[] args) {
        // Create and display the frame   
    	//Player player = new Player("Simge", "Path", null, null, 10, 0, null);
        new StartGameFrame().setVisible(true);
    }



}
