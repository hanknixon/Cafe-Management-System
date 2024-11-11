package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import net.proteanit.sql.*;

public class EditCold3 extends JFrame implements ActionListener
{
	JPanel panel;
	JLabel background;
	JButton delete,goback;
	JTextField prodnametf;
	JLabel prodname;
	
	EditCold3()
	{
		setSize(800, 600);
        setLocation(100, 100);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
        setIconImage(icon.getImage());
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/coldrinks.jpg"));
        Image img = i1.getImage().getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
        i1 = new ImageIcon(img);
        background = new JLabel(i1);
        background.setBounds(0, 0, 1024, 768);
        add(background);
        
        panel = new JPanel();
        panel.setBounds(140, 100, 500, 300);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,2), "DELETE ITEMS"));
        panel.setLayout(null);
        background.add(panel);
        
        delete = new JButton("DELETE ITEM");
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
        
        prodnametf = new JTextField();
        prodnametf.setBounds(280, 103, 180, 30);
        panel.add(prodnametf);
        
        prodname = new JLabel("ENTER PRODUCT ID");
        prodname.setBounds(35,100,250,35);
        prodname.setFont(new Font("serif", Font.BOLD, 22));
        prodname.setOpaque(false);
        prodname.setForeground(new Color(123,63,0));
        prodname.setBackground(new Color(123,63,0));
      	panel.add(prodname);
        
        
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == delete)
		{
			String productName = prodnametf.getText();
			
			try {
		        Conn conn = new Conn();
		        
		        String query = "delete from colddrinksmenu where PRODUCT_ID = '" + productName + "'";
		        
		        int rowsAffected = conn.s.executeUpdate(query);
		        
		        if (rowsAffected > 0) {
		            JOptionPane.showMessageDialog(null, "Item Deleted Successfully!");
		        } else {
		            JOptionPane.showMessageDialog(null, "Invalid Product ID: No item found with this product ID.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    } catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }        
		}
		if (e.getSource() == goback)
		{
			setVisible(false);
			new EditCold();
		}
	}
	
	public static void main(String args[])
	{
		new EditCold3();
	}
}
