package DesignSystem;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ArtisticButton extends JButton{

	public ArtisticButton(String buttonImagePath)
	{
		setBorderPainted(false); // Remove border
        setContentAreaFilled(false); // Remove default background


        // Load the original image
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(buttonImagePath));
        Image originalImage = originalIcon.getImage();

        // Resize the image
        Image resizedImage = originalImage.getScaledInstance(141, 160, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        setIcon(resizedIcon);

	}
}
