package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import net.proteanit.sql.*;

public class EditCold extends JFrame implements ActionListener 
{
	JPanel panel;
	JLabel background;
	JButton view,add,del,goback;
	
	EditCold()
	{
		setSize(800,600);
		setLocation(100,100);
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
		setIconImage(icon.getImage());
		
		goback = new JButton("GO BACK");
		goback.setBounds(500,410,120,30);
		goback.setBackground(Color.BLACK);
		goback.addActionListener(this);
		goback.setForeground(Color.WHITE);
		add(goback);
		
		panel = new JPanel();
        panel.setBounds(200, 100, 400, 300);
        Border border = BorderFactory.createLineBorder(Color.WHITE); 
        panel.setBorder(BorderFactory.createTitledBorder(border, "COLD DRINKS"));
        panel.setOpaque(false);
        panel.setLayout(null); 
        add(panel);
        
        view = new JButton("VIEW MENU");
		view.setBounds(137,70,120,30);
		view.setBackground(Color.BLACK);
		view.addActionListener(this);
		view.setForeground(Color.WHITE);
		panel.add(view);
		
		add = new JButton("ADD MENU ITEMS");
		add.setBounds(126,145,140,30);
		add.setBackground(Color.BLACK);
		add.addActionListener(this);
		add.setForeground(Color.WHITE);
		panel.add(add);
		
		del = new JButton("REMOVE MENU ITEMS");
		del.setBounds(117,220,160,30);
		del.setBackground(Color.BLACK);
		del.addActionListener(this);
		del.setForeground(Color.WHITE);
		panel.add(del);
        
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/coldrinks.jpg"));
        Image img = i1.getImage().getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
        i1 = new ImageIcon(img);
        background = new JLabel(i1);
        background.setBounds(0, 0, 1024, 768);
        add(background);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == view)
		{
			setVisible(false);
			new EditCold1();
		}
		if (e.getSource() ==  add)
		{
			setVisible(false);
			new EditCold2();
		}
		if (e.getSource() == del)
		{
			setVisible(false);
			new EditCold3();
		}
		if (e.getSource() == goback)
		{
			setVisible(false);
			new AdminPanel();
		}
	}
	public static void main(String args[])
	{
		new EditCold();
	}
}