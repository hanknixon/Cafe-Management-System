package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class BuyStocks extends JFrame implements ActionListener
{
	JPanel panel;
	JSpinner spinner;
	JTextField values;
	JLabel background,stocks,choose;
	JComboBox cb;
	JButton buy,goback;
	
	BuyStocks()
	{
		setSize(800,600);
		setLocation(100,100);
		
		Border border = BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(Color.BLACK, 3),BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
        setIconImage(icon.getImage());
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/stocks.jpg"));
        Image img = i1.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        i1 = new ImageIcon(img);
        background = new JLabel(i1);
        background.setBounds(0, 0, 800, 600);
        add(background);
        
        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(border, "WELCOME"));
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setBounds(170, 190, 400, 300);
        background.add(panel);
        
        stocks = new JLabel("||WELCOME TO JVM GROCERY||");
		stocks.setBounds(160,100,420,50);
		stocks.setFont(new Font("serif", Font.BOLD, 25));
		stocks.setBorder(border);
		stocks.setOpaque(true);
		stocks.setBackground(Color.WHITE);
		background.add(stocks);
		
		choose = new JLabel("Choose Products Below");
		choose.setBounds(80,20,225,50);
		choose.setFont(new Font("Times New Roman", Font.BOLD, 20));
		choose.setBorder(border);
		choose.setOpaque(true);
		choose.setBackground(Color.WHITE);
		panel.add(choose);
		
        String stock[] = {"Milk","Ice","Coffee Powder","Choco Syrup","Sugar","Espresso Shots","Chicken","Cheese","Bread","Tomato","Flour","Eggs","Sauce"};
        cb = new JComboBox(stock);
        cb.setBounds(40,100,120,20);
        panel.add(cb);
        
        values = new JTextField();
        values.setBounds(240, 100, 120, 20);
        panel.add(values);
        
        buy = new JButton("BUY PRODUCTS");
		buy.setBounds(130,150,130,30);
		buy.setBackground(Color.BLACK);
		buy.addActionListener(this);
		buy.setForeground(Color.WHITE);
		panel.add(buy);
		
		goback = new JButton("EXIT STORE");
		goback.setBounds(130,200,130,30);
		goback.setBackground(Color.BLACK);
		goback.addActionListener(this);
		goback.setForeground(Color.WHITE);
		panel.add(goback);
		
        setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
	    if (e.getSource() == buy)
	    {
	        String selectedItem = (String) cb.getSelectedItem();
	        String quantity = values.getText();
	        
	        if (quantity.isEmpty() || quantity.equals("0")) 
	        {
	            JOptionPane.showMessageDialog(null, "Please enter a valid quantity", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        if(quantity.matches("[a-zA-z]+"))
        	{
        		JOptionPane.showMessageDialog(this,"Please enter a valid quantity!","Error",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
	        
	        try
	        {
	            Conn conn = new Conn();

	            String query = "update stocks set QUANTITY = QUANTITY + "+quantity+" where ITEM_NAME = '"+selectedItem+"'";

	            conn.s.executeUpdate(query);

	            JOptionPane.showMessageDialog(null,"Successfully Purchased");
	        }

	        catch (Exception ex)
	        {
	            ex.printStackTrace();
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
		new BuyStocks();
	}
}
