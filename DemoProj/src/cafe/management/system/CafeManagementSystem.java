package cafe.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CafeManagementSystem extends JFrame implements ActionListener, MouseListener
{
	JLabel aboutus;
	CafeManagementSystem()
	{
		setSize(1366,768);
		setLocation(100,100);
		
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/background1.jpg"));
		JLabel image = new JLabel(i1);
		add(image);
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
		setIconImage(icon.getImage());
		
		JLabel text = new JLabel("    WELCOME TO JVM CAFE ");
		text.setBounds(450,100,1000,90);
		text.setForeground(Color.WHITE);
		text.setFont(new Font("serif", Font.PLAIN, 30));
		image.add(text);
		
		JButton next = new JButton("Proceed to Next Page");
		next.setBounds(565,200,190,50);
		next.setBackground(Color.WHITE);
		next.addActionListener(this);
		next.setFont(new Font("serif", Font.PLAIN, 15));
		image.add(next);
		
		aboutus = new JLabel("About Us");
        aboutus.setBounds(640, 260, 120, 30);
        aboutus.setForeground(Color.BLUE);
        aboutus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aboutus.addMouseListener(this);
        image.add(aboutus);
		
		setVisible(true);   
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	public void mouseClicked(MouseEvent e) 
	{
        if (e.getSource() == aboutus) 
        {
            new AboutUsPanel();
        }
    }
	public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
	public void actionPerformed(ActionEvent e)
	{
		setVisible(false);
		new MainPage();
	}
	public static void main(String args[])
	{
		new CafeManagementSystem();
	}
}
