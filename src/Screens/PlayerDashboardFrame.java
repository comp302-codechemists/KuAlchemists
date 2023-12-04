package Screens;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

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
    	
        backgroundPanel.setLocation(0, 0);
        backgroundPanel.setLayout(null);
        backgroundPanel.setOpaque(false);
        backgroundPanel.setSize(new Dimension(964, 552));
        
        // add every component to background panel

        getContentPane().add(backgroundPanel);
        
        
        // How to do this? We should know which button is clicked.
        // But implementing every 28 button one by one doesn't sound good.
        /*
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(10, 10, 944, 532);
        backgroundPanel.add(buttonPanel);
        ButtonGroup buttonGroup = new ButtonGroup();
        int pyramidHeight = 7; 
        int buttonWidth = 100;
        int buttonHeight = 110;
        int startX = 150;
        int startY = 50;
        for (int i = 0; i < pyramidHeight; i++) {
            int buttonsInRow = i + 1;
            int rowWidth = buttonsInRow * buttonWidth;

            for (int j = 0; j < buttonsInRow; j++) {
                int x = startX + (rowWidth - (buttonsInRow * buttonWidth)) / 2 + j * buttonWidth;
                int y = startY + i * buttonHeight;

                JRadioButton radioButton = new JRadioButton();
                radioButton.setBounds(x, y, buttonWidth, buttonHeight);
                buttonGroup.add(radioButton);
                buttonPanel.add(radioButton);
            }
        }
        */
        
        
        
        
		
	}
	
	
	
    public static void main(String[] args) {
        // Create and display the frame   
    	Player player = new Player("Simge", "Path", null, null, 10, 0, null);
        new PlayerDashboardFrame(player).setVisible(true);
    }
}
