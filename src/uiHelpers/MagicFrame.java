package uiHelpers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Business.KUAlchemistsGame;

public class MagicFrame extends JFrame{

	public KUAlchemistsGame game;
	public JPanel backgroundPanel;
	private final int width;
	private final int height;
	
	public MagicFrame(KUAlchemistsGame game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
	}
	
	public void setBackground(String backgroundSource) {
		setAppearance(backgroundSource);
	}
	

	private void setAppearance(String backgroundSource)
    {
    	// As soon as the constructor is called,
    	// set visibility of the frame to true.
    	this.setVisible(true);
    	this.setResizable(false);
    	this.setTitle("KU Alchemists Game");
    	this.setSize(width, height);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.getContentPane().setLayout(null);
    	

    	backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                
           	 super.paintComponent(g);
                
                Image image = new ImageIcon(this.getClass().getResource(backgroundSource)).getImage();
                
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
        backgroundPanel.setSize(new Dimension(width, height));
        backgroundPanel.setLayout(null);
        backgroundPanel.setOpaque(false);
        getContentPane().add(backgroundPanel);
    }
	
}
