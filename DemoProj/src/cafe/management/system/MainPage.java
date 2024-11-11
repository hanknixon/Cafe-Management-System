package cafe.management.system;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;


public class MainPage extends JFrame implements ActionListener
{
	JButton prev,loginc,logina;
	
	MainPage()
	{
		setSize(1366,768);
		setLocation(100,100);
		
		
		ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("icons/background1.jpg"));
		JLabel image = new JLabel(i2);
		add(image);
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
		setIconImage(icon.getImage());
		
		JLabel text = new JLabel("|| JVM CAFE ||");
		text.setBounds(530,100,1000,90);
		text.setForeground(Color.WHITE);
		text.setFont(new Font("serif",Font.BOLD,40));
		image.add(text);
		
		loginc = new JButton("LOGIN AS CUSTOMER");
		loginc.setBounds(565,200,190,40);
		loginc.setBackground(Color.WHITE);
		loginc.addActionListener(this);
		loginc.setFont(new Font("serif", Font.PLAIN, 15));
		image.add(loginc);
		
		logina = new JButton("LOGIN AS ADMIN");
		logina.setBounds(565,250,190,40);
		logina.setBackground(Color.WHITE);
		logina.addActionListener(this);
		logina.setFont(new Font("serif", Font.PLAIN, 15));
		image.add(logina);
		
		prev = new JButton("GO BACK");
		prev.setBounds(565,300,190,40);
		prev.setBackground(Color.WHITE);
		prev.addActionListener(this);
		prev.setFont(new Font("serif", Font.PLAIN, 15));
		image.add(prev);

		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == loginc)
		{
			setVisible(false);
			new LoginCustomer();
		}
		if(e.getSource() == logina)
		{
			setVisible(false);
			new LoginAdmin();
		}
		if(e.getSource() == prev)
		{
			setVisible(false);
			new CafeManagementSystem();
		}
	}
	
	public static void main(String args[])
	{
		new MainPage();
	}
}
