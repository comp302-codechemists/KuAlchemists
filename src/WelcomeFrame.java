import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class WelcomeFrame extends JFrame {

    private JButton startButton;
    private JLabel loadingLabel;
    private JProgressBar progressBar;

    public WelcomeFrame() {
        setTitle("Welcome to the Game");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
           
                ImageIcon imageIcon = new ImageIcon("C:\\KuAlchemists\\KuAlchemists\\src\\bck.png");
                Image image = imageIcon.getImage();

               
                int x = (getWidth() - image.getWidth(null)) / 2;
                int y = (getHeight() - image.getHeight(null)) / 2;
                g.drawImage(image, x, y, this);
            }
        };
        backgroundPanel.setLocation(0, 0);
        backgroundPanel.setSize(new Dimension(586, 363));
        backgroundPanel.setLayout(null);
        getContentPane().add(backgroundPanel);
        
        startButton = new JButton("Start Game");
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        startButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        startButton.setBackground(new Color(0, 150, 136)); 
        startButton.setForeground(Color.WHITE); 
        startButton.setFont(new Font("Arial", Font.BOLD, 12)); 

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButtonClicked();
            }
        });
        

        loadingLabel = new JLabel("   Codechemists is loading, this may take a while...");
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
        loadingLabel.setBounds(127, 199, 354, 30);
        loadingLabel.setVisible(false);
        startButton.setBounds(248, 200, 100, 30); 
        loadingLabel.setBackground(Color.GRAY);
        loadingLabel.setOpaque(true);
        backgroundPanel.add(loadingLabel);
        backgroundPanel.add(startButton);
        backgroundPanel.setOpaque(false);
        
        progressBar = new JProgressBar();
        progressBar.setBounds(229, 240, 160, 11);
        progressBar.setValue(20);
        progressBar.setVisible(false);
        backgroundPanel.add(progressBar);
        
    }

    private void startButtonClicked() {
        startButton.setVisible(false);
        loadingLabel.setVisible(true);
        progressBar.setVisible(true);

    }
}
