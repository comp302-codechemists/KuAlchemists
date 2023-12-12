package DesignSystem;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class ArtisticButton extends JButton {

    public ArtisticButton(String buttonImagePath, int width, int height) {
        setBorderPainted(false); // Remove border
        setContentAreaFilled(false); // Remove default background

        // Load the original image
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(buttonImagePath));
        Image originalImage = originalIcon.getImage();

        // Resize the image
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        setIcon(resizedIcon);
    }
    
}
