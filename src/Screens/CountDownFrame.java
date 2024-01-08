package Screens;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountDownFrame extends JFrame {
    private JLabel countdownLabel;
    private int count = 3;

    public CountDownFrame() {
        // Set up the JFrame
        setTitle("Countdown Frame");
        setSize(1540, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add a JLabel to display the countdown
        countdownLabel = new JLabel("THE GAME IS STARTING, PREPARE YOUR ALCHEMY SET!      " + Integer.toString(count), SwingConstants.CENTER);
        countdownLabel.setFont(countdownLabel.getFont().deriveFont(48.0f)); // Increase font size for visibility
        add(countdownLabel);
        setLocationRelativeTo(null);
        setResizable(false);

        // Use Timer for countdown
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count--;

                if (count >= 1) {
                    // Update the label with the current count
                    countdownLabel.setText("THE GAME IS STARTING, PREPARE YOUR ALCHEMY SET!     "+ Integer.toString(count));
                } else {
                    // When countdown reaches 1, close the JFrame
                    ((Timer) e.getSource()).stop(); // Stop the timer
                    dispose(); // Close the JFrame
                }
            }
        });

        // Start the timer
        timer.start();
    }

    public static void main(String[] args) {
        
    }
}