package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.buyArtifactController;
import DesignSystem.ArtisticButton;
import artifactScreens.ArtifactFrame;

import javax.swing.JLabel;

public class BuyArtifactFrame extends FunctionalFrame{

	JButton button;
	private JButton returnBtn;
	
	public BuyArtifactFrame(KUAlchemistsGame game, Player player) {
		super(game, player);
		this.setBackground("/FunctionalBackgroundImages/buyArtifactBackground.png");
		this.setButton();
		this.setReturnBtn();
	}
	
	private void setReturnBtn() {
		
		returnBtn = new ArtisticButton("/Images/return.png", 60, 60);	
    	returnBtn.setBounds(1100, 30, 60, 60);
		backgroundPanel.add(returnBtn);
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayerDashboardFrame(game, player);
                BuyArtifactFrame.this.dispose();
            }
        });	
		
	}
	
	private void setButton()
	{
		button = new JButton();
		button.setText("Buy");
		button.setBounds(500, 500, 150, 30);
		button.setForeground(Color.white);
		button.setFont(new Font("Tahoma", Font.ITALIC, 15));
		button.setOpaque(false);
		button.setBorder(new LineBorder(Color.white, 2));
		backgroundPanel.add(button);
		button.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buyArtifactController controller = new buyArtifactController(game);
                String boughtArtifact = controller.buyArtifactHandler();
                
			    if (boughtArtifact != null)
			    {
			    	ArtifactFrame artifactFrame = controller.getArtifactFrame(boughtArtifact);

			    	artifactFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set close operation

			    	artifactFrame.addWindowListener(new WindowAdapter() {
			    	    @Override
			    	    public void windowClosing(WindowEvent e) {
			    	        // Perform actions when the window is closing
			    	        // You can choose to hide the frame instead of disposing it
			    	        artifactFrame.setVisible(false); // Hide the frame
			    	        // Close the frame
						    BuyArtifactFrame.this.dispose();
						    new MainGameFrame(game);
			    	    }
			    	});

			    	SwingUtilities.invokeLater(() -> {
			    	    artifactFrame.setVisible(true);
			    	    artifactFrame.toFront();
			    	    artifactFrame.requestFocus();
			    	});

			    }
			    else
			    {
			    	String message = "Balance is unsufficient, come back when you have more gold :D";
			    	JOptionPane.showMessageDialog(null, message, "Unsufficient Balance", JOptionPane.WARNING_MESSAGE);
			    	 // Close the frame
				    BuyArtifactFrame.this.dispose();
				    new MainGameFrame(game);
			    }
			}


	            @Override
	            public void mousePressed(MouseEvent e) {
	                // Handle mouse press event if needed
	            }

	            @Override
	            public void mouseReleased(MouseEvent e) {
	                // Handle mouse release event if needed
	            }

	            @Override
	            public void mouseEntered(MouseEvent e) {
	                // Change button appearance on mouse enter (hover effect)
	            	button.setBorder(new LineBorder(Color.yellow, 2));
	            	button.setForeground(Color.yellow);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                // Restore button appearance on mouse exit
	            	button.setBorder(new LineBorder(Color.white, 2));
	            	button.setForeground(Color.white);
	            }
	        });
		
		backgroundPanel.add(button);
	}
}
