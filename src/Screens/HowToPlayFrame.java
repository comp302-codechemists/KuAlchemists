package Screens;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;

public class HowToPlayFrame extends GeneralFrame {
	private JTextArea txtgameOverview;
	public HowToPlayFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		getContentPane().setLayout(null);
		txtgameOverview = new JTextArea("1. *Game Overview:*\r\n"
				+ "   - KU Alchemist is a 2-player card game centered around brewing potions, testing, selling, and \r\nutilizing artifacts. Players forage ingredients, transmute cards for gold, and experiment with unique \r\npotion combinations.\r\n"
				+ "\r\n"
				+ "2. *Ingredient Cards:*\r\n"
				+ "   - Create potions using a variety of ingredient cards. The resulting potion's effects depend on the molecules, which consist of three colors, two signs, and two sizes.\r\n"
				+ "\r\n"
				+ "3. *Potion Brewing:*\r\n"
				+ "   - Brew potions in the Potion Brewing Area by combining two ingredient cards. The dominant molecule, based on size and matching signs, determines the potion's effect.\r\n"
				+ "\r\n"
				+ "4. *Foraging and Transmuting:*\r\n"
				+ "   - Forage (draw) one ingredient card from the deck or transmute (sell) one card to the deck for one gold.\r\n"
				+ "\r\n"
				+ "5. *Artifact Cards:*\r\n"
				+ "   - Purchase artifact cards for 3 gold, gaining various abilities. Drawn from a faced-down deck, these cards enhance gameplay.\r\n"
				+ "\r\n"
				+ "6. *Experimentation:*\r\n"
				+ "   - Experiment with created potions, revealing outcomes to other players. Sell potions for gold (1-3 coins based on quality).\r\n"
				+ "\r\n"
				+ "7. *Debunking Theories:*\r\n"
				+ "   - Debunk another player's theory for a gain of 2 reputation points. Unsuccessful attempts result in a loss of 1 reputation point.\r\n"
				+ "\r\n"
				+ "8. *Theory Publishing:*\r\n"
				+ "   - Publish a theory about an ingredient, taking the risk that the molecules' information may be incorrect.\r\n"
				+ "\r\n"
				+ "9. *Marker Usage:*\r\n"
				+ "   - Publish theories only on ingredients lacking alchemy markers. Use markers not assigned to any ingredient, with each molecule sign corresponding to a single ingredient");
		txtgameOverview.setEditable(false);
		txtgameOverview.setBounds(94, 25, 1317, 737);
		txtgameOverview.setFont(new Font("MV Boli", Font.PLAIN, 15));
		
		getContentPane().add(txtgameOverview);
	}
	
	
    public static void main(String[] args) {
        // Create and display the frame   
    	//Player player = new Player("Simge", "Path", null, null, 10, 0, null);
        new HowToPlayFrame().setVisible(true);
    }
}
