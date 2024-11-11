package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteUsers extends JFrame implements ActionListener
{
	JButton delete,goback;
	JPanel panel;
	JTextField textfield;
	JLabel background,phoneno;
	
	DeleteUsers()
	{
		setSize(800,600);
		setLocation(100,100);
		
		panel = new JPanel();
        panel.setBounds(140, 100, 500, 300);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "DELETE USERS"));
        panel.setLayout(null);
        add(panel);
		
		Border border = BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(Color.BLACK, 3), 
        	    BorderFactory.createEmptyBorder(10, 10, 10, 10)
        		);
	
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
		setIconImage(icon.getImage());
		
		delete = new JButton("DELETE ACCOUNT");
        delete.setBounds(50, 230, 150, 30);
        delete.setBackground(Color.BLACK);
        delete.addActionListener(this);
        delete.setForeground(Color.WHITE);
        panel.add(delete);
        
        goback = new JButton("GO BACK");
        goback.setBounds(310, 230, 150, 30);
        goback.setBackground(Color.BLACK);
        goback.addActionListener(this);
        goback.setForeground(Color.WHITE);
        panel.add(goback);
        
        textfield = new JTextField();
        textfield.setBounds(280, 103, 180, 30);
        panel.add(textfield);
        
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/addusers.jpg"));
        Image img = i1.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        i1 = new ImageIcon(img);
        background = new JLabel(i1);
        background.setBounds(0, 0, 1024, 768);
        add(background);
        
        phoneno = new JLabel("ENTER PHONE NO");
      	phoneno.setBounds(35,100,250,35);
      	phoneno.setFont(new Font("serif", Font.BOLD, 25));
     	phoneno.setOpaque(false);
     	phoneno.setForeground(new Color(123,63,0));
      	phoneno.setBackground(new Color(123,63,0));
      	panel.add(phoneno);
		
		setVisible(true);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == delete)
		{
			String phone = textfield.getText();
			
			try {
		        Conn conn = new Conn();
		        
		        String query = "delete from users where phoneno = '" + phone + "'";
		        
		        int rowsAffected = conn.s.executeUpdate(query);
		        
		        if (rowsAffected > 0) 
		        {
		            JOptionPane.showMessageDialog(null, "User Deleted Successfully!");
		        }
		        
		        else 
		        {
		            JOptionPane.showMessageDialog(null, "Invalid Phone Number: No user found with this phone number.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    } 
			catch (SQLException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }       
		}
		
		if (e.getSource() == goback)
		{
			setVisible(false);
			new AdminPanel();
		}
	}

	public static void main(String args[])
	{
		new DeleteUsers();
	}
}