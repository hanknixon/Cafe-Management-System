package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import net.proteanit.sql.DbUtils;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;


public class ViewUsers extends JFrame implements ActionListener 
{
	JTable table;
    JLabel background,userse;
    JButton goback;
    
	ViewUsers()
	{
		setSize(1024, 768);
        setLocation(100, 100);
        Border border = BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(Color.BLACK, 3), 
        	    BorderFactory.createEmptyBorder(10, 10, 10, 10)
        		);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
        setIconImage(icon.getImage());
        
        goback = new JButton("GO BACK");
		goback.setBounds(800,610,120,30);
		goback.setBackground(Color.BLACK);
		goback.addActionListener(this);
		goback.setForeground(Color.WHITE);
		add(goback);
		
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/addusers.jpg"));
        Image img = i1.getImage().getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
        i1 = new ImageIcon(img);
        background = new JLabel(i1);
        background.setBounds(0, 0, 1024, 768);
        add(background);
        
        userse = new JLabel("|| USERS ENLISTED ||");
		userse.setBounds(325,100,330,75);
		userse.setFont(new Font("serif", Font.BOLD, 30));
		userse.setBorder(border);
		userse.setOpaque(true);
		userse.setBackground(Color.WHITE);
		background.add(userse);
		
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(90, 200, 820, 400); 
        background.add(scrollPane);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from users");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == goback)
		{
			setVisible(false);
    		new AdminPanel();
		}
	}
	
	public static void main(String args[])
	{
		new ViewUsers();
	}
}
