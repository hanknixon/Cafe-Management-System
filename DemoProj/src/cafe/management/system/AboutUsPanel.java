package cafe.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutUsPanel extends JFrame implements ActionListener 
{
    private JButton closeButton;

    public AboutUsPanel() {
    	
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
		setIconImage(icon.getImage());
        
        setTitle("About JVM CAFE");
        setSize(500, 400); 
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("About JVM CAFE", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea aboutTextArea = new JTextArea();
        aboutTextArea.setText("Welcome to JVM CAFE!\n\nEstablished in 2021, JVM CAFE has been serving the community with the finest coffee and pastries. Our commitment to quality and service has made us a beloved spot among locals and visitors alike.\n\nFeatures:\n- Gourmet Coffee and Tea\n- Fresh Pastries and Snacks\n- Cozy Ambiance\n- Free Wi-Fi\n\nCome visit us to enjoy a warm cup of coffee in a friendly atmosphere!");
        aboutTextArea.setWrapStyleWord(true);
        aboutTextArea.setLineWrap(true);
        aboutTextArea.setEditable(false);
        aboutTextArea.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(aboutTextArea); 
        add(scrollPane, BorderLayout.CENTER);

        closeButton = new JButton("Close");
        closeButton.addActionListener(this);
        add(closeButton, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) 
        {
            dispose();
        }
    }

    public static void main(String[] args) {

            new AboutUsPanel();
    }
}
