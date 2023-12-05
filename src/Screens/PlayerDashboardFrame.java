package Screens;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Business.Player;

public class PlayerDashboardFrame extends GeneralFrame{
	
	/*
	 * A dedicated panel that showcases the current player's avatar, available resources, and actions.
	 * Offers interactive buttons for actions like collecting ingredients, brewing potions, and
	 * submitting publications.
	 */
	private JPanel backgroundPanel;
	private JPanel bottom;
	
	public PlayerDashboardFrame(Player player) {
		
    	backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Image originalImage = new ImageIcon(this.getClass().getResource("/Images/deduction_board_up.png")).getImage();

                // Calculate the scaled width and height while maintaining the aspect ratio
                int originalWidth = originalImage.getWidth(null);
                int originalHeight = originalImage.getHeight(null);
                int maxWidth = getWidth();
                int maxHeight = getHeight();

                double scale = Math.min((double) maxWidth / originalWidth, (double) maxHeight / originalHeight);
                int scaledWidth = (int) (originalWidth * scale);
                int scaledHeight = (int) (originalHeight * scale);

                // Calculate the position to center the scaled image
                int x = (getWidth() - scaledWidth) / 2;
                int y = (getHeight() - scaledHeight) / 2;

                // Draw the scaled image
                g.drawImage(originalImage, x, y, scaledWidth, scaledHeight, this);
            }
    	};
    	
        backgroundPanel.setLocation(10, 0);
        backgroundPanel.setLayout(null);
        backgroundPanel.setOpaque(false);
        backgroundPanel.setSize(new Dimension(789, 466));       
        getContentPane().add(backgroundPanel);
        
        
        
        // Buttons for the deduction board:
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(10, 10, 769, 456);
        
        ButtonGroup buttonGroup = new ButtonGroup();
        List<JRadioButton> buttons = new ArrayList<JRadioButton>();

        int rows = 7;
        int startX = 372;
        int startY = 20; 

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col <= row; col++) {
                JRadioButton btn = new JRadioButton();
                buttonGroup.add(btn);
                buttons.add(btn);
                int x = startX - row * 65 / 2 + col * 65;
                int y = startY + row * 57;
                btn.setBounds(x, y, 20, 20);
                buttonPanel.add(btn);
            }
        }   
        
        backgroundPanel.add(buttonPanel);
    
    
    bottom = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Image originalImage = new ImageIcon(this.getClass().getResource("/Images/deduction_board_bottom.png")).getImage();

            // Calculate the scaled width and height while maintaining the aspect ratio
            int originalWidth = originalImage.getWidth(null);
            int originalHeight = originalImage.getHeight(null);
            int maxWidth = getWidth();
            int maxHeight = getHeight();

            double scale = Math.min((double) maxWidth / originalWidth, (double) maxHeight / originalHeight);
            int scaledWidth = (int) (originalWidth * scale);
            int scaledHeight = (int) (originalHeight * scale);

            // Calculate the position to center the scaled image
            int x = (getWidth() - scaledWidth) / 2;
            int y = (getHeight() - scaledHeight) / 2;

            // Draw the scaled image
            g.drawImage(originalImage, x, y, scaledWidth, scaledHeight, this);
        }
    };
    bottom.setBounds(10, 476, 789, 297);
    getContentPane().add(bottom);
    bottom.setLayout(null);
    bottom.setOpaque(false);
	
	}
	
    public static void main(String[] args) {
        // Create and display the frame   
    	Player player = new Player("Simge", "Path", null, null, 10, 0, null);
        new PlayerDashboardFrame(player).setVisible(true);
    }
}
