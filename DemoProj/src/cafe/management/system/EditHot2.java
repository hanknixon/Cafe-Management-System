package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import net.proteanit.sql.*;

public class EditHot2 extends JFrame implements ActionListener
{
	JLabel background,pid,name,desc,price;
	JPanel panel;
	JButton add,goback;
	JTextField pidtf,nametf,desctf,pricetf;
	
	EditHot2()
	{
		setSize(1024, 768);
        setLocation(100, 100);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
        setIconImage(icon.getImage());
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/hotdrinks.jpg"));
        Image img = i1.getImage().getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
        i1 = new ImageIcon(img);
        background = new JLabel(i1);
        background.setBounds(0, 0, 1024, 768);
        add(background);
        
        panel = new JPanel();
        panel.setBounds(150, 100, 700, 500);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); 
        panel.setBorder(BorderFactory.createTitledBorder(border, "ADD DRINKS"));
        panel.setLayout(null); 
        background.add(panel);
        
        add = new JButton("ADD ITEM");
		add.setBounds(200,375,120,30);
		add.setBackground(Color.BLACK);
		add.addActionListener(this);
		add.setForeground(Color.WHITE);
		panel.add(add);
		
		goback = new JButton("GO BACK");
		goback.setBounds(350,375,120,30);
		goback.setBackground(Color.BLACK);
		goback.addActionListener(this);
		goback.setForeground(Color.WHITE);
		panel.add(goback);
        
        
        pid = new JLabel("ENTER PRODUCT ID");
        pid.setBounds(90,70,250,30);
        pid.setFont(new Font("serif", Font.BOLD, 25));
		panel.add(pid);
		
		pidtf = new JTextField();
		pidtf.setBounds(400,72,150,30);
		panel.add(pidtf);
		
		name = new JLabel("ENTER PRODUCT NAME");
        name.setBounds(45,150,310,30);
        name.setFont(new Font("serif", Font.BOLD, 25));
		panel.add(name);
		
		nametf = new JTextField();
		nametf.setBounds(400,152,180,30);
		panel.add(nametf);
		
		desc = new JLabel("ENTER PRODUCT DETAILS");
        desc.setBounds(40,235,310,30);
        desc.setFont(new Font("serif", Font.BOLD, 23));
		panel.add(desc);
		
		desctf = new JTextField();
		desctf.setBounds(400,237,250,27);
		panel.add(desctf);
		
		price = new JLabel("      ENTER PRICE DETAILS");
        price.setBounds(40,319,310,30);
        price.setFont(new Font("serif", Font.BOLD, 23));
		panel.add(price);
		
		pricetf = new JTextField();
		pricetf.setBounds(400,320,250,27);
		panel.add(pricetf);
		
		setLocationRelativeTo(null);
        setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == add) {
	        String productId = pidtf.getText();
	        String productName = nametf.getText();
	        String productDesc = desctf.getText();
	        String productPrice = pricetf.getText();
	        
	        if (productId.isEmpty() || productName.isEmpty() || productDesc.isEmpty() || productPrice.isEmpty() || !productId.startsWith("HID")) {
	            JOptionPane.showMessageDialog(null, "Please fill all fields correctly.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        try {
	            Conn conn = new Conn();

	            String query = "insert into hotdrinksmenu values('" + productId + "', '" + productName + "', '" + productDesc + "', '" + productPrice + "')";

	            conn.s.executeUpdate(query);

	            JOptionPane.showMessageDialog(null, "Item Added Successfully!");
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Failed to add item. Please check your input.", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    if (e.getSource() == goback) {
	        setVisible(false);
	        new EditHot();
	    }
	}

	
	public static void main(String args[])
	{
		new EditHot2();
	}
}
