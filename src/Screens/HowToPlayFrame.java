package Screens;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ScrollPaneConstants;

public class HowToPlayFrame extends GeneralFrame {
	
	private JTextArea txtgameOverview;
	private JPanel backgroundPanel;
	
	public HowToPlayFrame() {
				
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground();
		
		txtgameOverview = new JTextArea("1. Game Overview:\r\n"
				+ "   - KU Alchemist is a 2-player card game centered around brewing potions, testing, selling, and utilizing artifacts. Players forage ingredients, \ntransmute cards for gold, and experiment with unique potion combinations.\r\n"
				+ "\r\n"
				+ "2. Ingredient Cards:\r\n"
				+ "   - Create potions using a variety of ingredient cards. The resulting potion's effects depend on the molecules, which consist of three colors, \ntwo signs, and two sizes.\r\n"
				+ "\r\n"
				+ "3. Potion Brewing:\r\n"
				+ "   - Brew potions in the Potion Brewing Area by combining two ingredient cards. The dominant molecule, based on size and matching signs, determines \nthe potion's effect.\r\n"
				+ "\r\n"
				+ "4. Foraging and Transmuting:\r\n"
				+ "   - Forage (draw) one ingredient card from the deck or transmute (sell) one card to the deck for one gold.\r\n"
				+ "\r\n"
				+ "5. Artifact Cards:\r\n"
				+ "   - Purchase artifact cards for 3 gold, gaining various abilities. Drawn from a faced-down deck, these cards enhance gameplay.\r\n"
				+ "\r\n"
				+ "6. Experimentation:\r\n"
				+ "   - Experiment with created potions, revealing outcomes to other players. Sell potions for gold (1-3 coins based on quality).\r\n"
				+ "\r\n"
				+ "7. Debunking Theories:\r\n"
				+ "   - Debunk another player's theory for a gain of 2 reputation points. Unsuccessful attempts result in a loss of 1 reputation point.\r\n"
				+ "\r\n"
				+ "8. Theory Publishing:\r\n"
				+ "   - Publish a theory about an ingredient, taking the risk that the molecules' information may be incorrect.\r\n"
				+ "\r\n"
				+ "9. Marker Usage:\r\n"
				+ "   - Publish theories only on ingredients lacking alchemy markers. Use markers not assigned to any ingredient, with each molecule sign corresponding to\na single ingredient");
		txtgameOverview.setOpaque(false);
		txtgameOverview.setEditable(false);
		txtgameOverview.setBounds(200, 120, 1120, 600);
		txtgameOverview.setFont(new Font("MV Boli", Font.PLAIN, 15));
		txtgameOverview.setForeground(Color.white);
		
		backgroundPanel.add(txtgameOverview);
	}
	
	private void setBackground()
	{
		backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                
           	 super.paintComponent(g);
                
                Image image = new ImageIcon(this.getClass().getResource("/BackgroundImages/howToPlayBackground.png")).getImage();
                
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
	
    public static void main(String[] args) {
        // Create and display the frame   
    	//Player player = new Player("Simge", "Path", null, null, 10, 0, null);
        new HowToPlayFrame();
    }
}
