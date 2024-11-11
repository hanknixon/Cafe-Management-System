package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import net.proteanit.sql.*;

public class LoginPanel extends JFrame implements ActionListener, MouseListener 
{
	JLabel background,welcome,edit;
	JPanel panel;
	JButton view,order,sign;
	
	LoginPanel()
	{
		setSize(800,600);
		setLocation(100,100);
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
        setIconImage(icon.getImage());
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/panel.jpg"));
        Image img = i1.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        i1 = new ImageIcon(img);
        background = new JLabel(i1);
        background.setBounds(0, 0, 800, 600);
        add(background);
        
        welcome = new JLabel("Welcome to JVM Cafe");
        welcome.setBounds(200,30,400,50);
        welcome.setFont(new Font("serif", Font.BOLD, 40));
        welcome.setForeground(Color.WHITE);
        background.add(welcome);
        
        panel = new JPanel();
        panel.setBounds(200, 100, 400, 350);
        panel.setBackground(new Color(0,0,0,150));
        Border border = BorderFactory.createLineBorder(new Color(184, 115, 51),2); 
        panel.setBorder(BorderFactory.createTitledBorder(border));
        panel.setLayout(null); 
        background.add(panel);
        
        view = new JButton("VIEW MENU");
		view.setBounds(137,70,120,30);
		view.setBackground(Color.BLACK);
		view.addActionListener(this);
		view.setForeground(Color.WHITE);
		panel.add(view);
		
		order = new JButton("ORDER");
		order.setBounds(137,145,120,30);
		order.setBackground(Color.BLACK);
		order.addActionListener(this);
		order.setForeground(Color.WHITE);
		panel.add(order);
		
		sign = new JButton("SIGN OUT");
		sign.setBounds(137,220,120,30);
		sign.setBackground(Color.BLACK);
		sign.addActionListener(this);
		sign.setForeground(Color.WHITE);
		panel.add(sign);
		
		edit = new JLabel("EDIT PROFILE");
        edit.setBounds(290, 300, 120, 30);
        edit.setForeground(Color.BLUE);
        edit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        edit.addMouseListener(this);
        panel.add(edit);
    
		
        JOptionPane.showMessageDialog(this,"Welcome To JVM Coffee Shop\n" + 
        "We're Glad to have you here, Enjoy our variety of snacks and drinks!");
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == view)
		{
			setVisible(false);
			new MenuPage1();
		}
		
		if(e.getSource() == sign)
		{
			setVisible(false);
			new LoginCustomer();
		}
		
		if (e.getSource() == order)
		{
			setVisible(false);
			new OrderPanel();
		}
	}
	
	public void mouseClicked(MouseEvent e) 
	{
        if (e.getSource() == edit) 
        {
            setVisible(false);
            new EditProfile();
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
	
	
	public static void main(String args[])
	{
		new LoginPanel();
	}
}
