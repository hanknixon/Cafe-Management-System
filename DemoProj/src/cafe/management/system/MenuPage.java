package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.awt.*;

public class MenuPage extends JFrame implements ActionListener 
{
	JPanel panel;
	JLabel background;
	JButton cdmenu,hdmenu,fdmenu,goback;
	
	MenuPage()
	{
		setSize(800,600);
		setLocation(100,100);
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
		setIconImage(icon.getImage());
		
		goback = new JButton("GO BACK");
		goback.setBounds(500,400,120,30);
		goback.setBackground(Color.BLACK);
		goback.addActionListener(this);
		goback.setForeground(Color.WHITE);
		add(goback);
		
		panel = new JPanel();
        panel.setBounds(200, 100, 400, 300);
        Border border = BorderFactory.createLineBorder(Color.BLACK); 
        panel.setBorder(BorderFactory.createTitledBorder(border, "MENU SECTION"));
        panel.setOpaque(false);
        panel.setLayout(null); 
        add(panel);
        
        cdmenu = new JButton("COLD DRINKS");
		cdmenu.setBounds(137,70,120,30);
		cdmenu.setBackground(Color.BLACK);
		cdmenu.addActionListener(this);
		cdmenu.setForeground(Color.WHITE);
		panel.add(cdmenu);
		
		hdmenu = new JButton("HOT DRINKS");
		hdmenu.setBounds(137,145,120,30);
		hdmenu.setBackground(Color.BLACK);
		hdmenu.addActionListener(this);
		hdmenu.setForeground(Color.WHITE);
		panel.add(hdmenu);
		
		fdmenu = new JButton("FOOD ITEMS");
		fdmenu.setBounds(137,220,120,30);
		fdmenu.setBackground(Color.BLACK);
		fdmenu.addActionListener(this);
		fdmenu.setForeground(Color.WHITE);
		panel.add(fdmenu);
        
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/menu.jpg"));
        background = new JLabel(i1);
        background.setSize(800,600);
        add(background);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == cdmenu)
		{
			setVisible(false);
			new ColdDrinks();
		}
		if (e.getSource() ==  hdmenu)
		{
			setVisible(false);
			new HotDrinks();
		}
		if (e.getSource() == fdmenu)
		{
			setVisible(false);
			new FoodItems();
		}
		if (e.getSource() == goback)
		{
			setVisible(false);
			new AdminPanel();
		}
	}
	public static void main(String args[])
	{
		new MenuPage();
	}
}
