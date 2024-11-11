package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import net.proteanit.sql.*;

public class OrderPanel extends JFrame implements ActionListener 
{
	private double priceCalc1;
	JButton addtocart,goback,purchase,clear;
	JLabel background;
	JTextArea receipt;
	JTextField result1,result2,result3;
	JPanel panel;
	JSpinner spinner;
	JComboBox<String> hotdmenu;
	JComboBox<String> coldmenu;
	JComboBox<String> foodmenu;
	
	OrderPanel()
	{
		setSize(800,600);
		setLocation(100,100);
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
        setIconImage(icon.getImage());
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/orderpanel.jpg"));
        Image img = i1.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        i1 = new ImageIcon(img);
        background = new JLabel(i1);
        background.setBounds(0, 0, 800, 600);
        add(background);
        
        
        addtocart = new JButton("ADD TO CART");
        addtocart.setBounds(550, 400, 150, 30);
        addtocart.setBackground(Color.BLACK);
        addtocart.addActionListener(this);
        addtocart.setForeground(Color.WHITE);
        background.add(addtocart);
        
        purchase = new JButton("PURCHASE");
        purchase.setBounds(550, 460, 150, 30);
        purchase.setBackground(Color.BLACK);
        purchase.addActionListener(this);
        purchase.setForeground(Color.WHITE);
        background.add(purchase);
        
        clear = new JButton("CLEAR");
        clear.setBounds(550, 520, 150, 30);
        clear.setBackground(Color.BLACK);
        clear.addActionListener(this);
        clear.setForeground(Color.WHITE);
        background.add(clear);
        
        goback = new JButton("GO BACK");
        goback.setBounds(100, 400, 150, 30);
        goback.setBackground(Color.BLACK);
        goback.addActionListener(this);
        goback.setForeground(Color.WHITE);
        background.add(goback);
        
        receipt = new JTextArea("------------------- Welcome to JVM Cafe! -------------------\n-----------------------------------------------------------------------"
        		+ "\n                                Java Street 299\n                           Tel: +099 675 8459\n-----------------------------------------------------------------------"
        		+ "\nName                                         Quantity              Price");
        receipt.setEditable(false);
        JScrollPane scroll = new JScrollPane(receipt);
        scroll.setBounds(480,70,290,300);
        background.add(scroll);
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(30, 70, 290, 300); 
        background.add(panel);
        
        hotdmenu = new JComboBox<>();
        hotdmenu.addActionListener(this);
        hotdmenu.setBounds(10, 40, 120, 25);
        panel.add(hotdmenu);
                
        coldmenu = new JComboBox<>();
        coldmenu.addActionListener(this);
        coldmenu.setBounds(10, 90, 120, 25);
        panel.add(coldmenu);
        
        foodmenu = new JComboBox<>();
        foodmenu.addActionListener(this);
        foodmenu.setBounds(10, 140, 120, 25);
        panel.add(foodmenu);
        
        spinner= new JSpinner(); 
        spinner.setModel(new SpinnerNumberModel(0, 0, 100, 1)); 
        spinner.setBounds(80, 200, 120, 25);
        panel.add(spinner);
        
        result1 = new JTextField("");
        result1.setBounds(160, 40, 120, 25);
        panel.add(result1);
        
        result2 = new JTextField("");
        result2.setBounds(160, 90, 120, 25);
        panel.add(result2);
        
        result3 = new JTextField("");
        result3.setBounds(160, 140, 120, 25);
        panel.add(result3);
        
        try {
            Conn conn = new Conn();
            
            String query1 = "select HOT_DRINKS_AVAILABLE from hotdrinksmenu";
            
            String query2 = "select COLD_DRINKS_AVAILABLE from colddrinksmenu";
            
            String query3 = "select SNACKS_AVAILABLE from food";
            
            PreparedStatement pst1 = conn.c.prepareStatement(query1);
            
            PreparedStatement pst2 = conn.c.prepareStatement(query2);
            
            PreparedStatement pst3 = conn.c.prepareStatement(query3);
            
            ResultSet rs1 = pst1.executeQuery();
            
            ResultSet rs2 = pst2.executeQuery();
            
            ResultSet rs3 = pst3.executeQuery();
            
            while (rs1.next()) 
            {
                String nameh = rs1.getString("HOT_DRINKS_AVAILABLE");
                
                hotdmenu.addItem(nameh);
            }
            
            hotdmenu.setSelectedIndex(-1);
            
            while(rs2.next())
            {
            	String namec = rs2.getString("COLD_DRINKS_AVAILABLE");
            	
            	coldmenu.addItem(namec);
            }
            
            coldmenu.setSelectedIndex(-1);
            
            while(rs3.next())
            {
            	String namef = rs3.getString("SNACKS_AVAILABLE");
            	
            	foodmenu.addItem(namef);
            }
            
            foodmenu.setSelectedIndex(-1);
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
        
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
        
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == addtocart)
		{
			String productName = "";
			
			int quantity = (int) spinner.getValue();
			if (quantity > 0)
			{
				

				double price = 0.0;
			
				if (!result1.getText().isEmpty()) 
				{
					productName = result1.getText();
					price = getPriceOfHotDrinks(result1.getText());
				} 
			
				else if (!result2.getText().isEmpty()) 
				{
					productName = result2.getText();
					price = getPriceOfColdDrinks(result2.getText());
				} 
			
				else if (!result3.getText().isEmpty()) 
				{
					productName = result3.getText();
					price = getPriceOfFood(result3.getText());
				}
			
				double priceCalc = price * quantity;
			
				String receiptEntry = String.format("\n%-48s %d           %.2f\n", productName, quantity, priceCalc);
				receipt.append(receiptEntry);
			}
			else
				
			{
				JOptionPane.showMessageDialog(this, "Please Select a valid Quantity", "Invalid Quantity", JOptionPane.WARNING_MESSAGE);
			}

		}
		
		else if (e.getSource() == hotdmenu)
	    {
	        comboBoxActionPerformed1();
	        comboBoxActionPerformed4(hotdmenu);
	    }
		
		else if (e.getSource() == coldmenu)
		{
			comboBoxActionPerformed2();
			comboBoxActionPerformed4(coldmenu);
		}
		
		else if (e.getSource() == foodmenu)
		{
			comboBoxActionPerformed3();
			comboBoxActionPerformed4(foodmenu);
		}
		
		else if (e.getSource() == goback)
		{
			setVisible(false);
			new LoginPanel();
		}
		
		else if (e.getSource() == purchase)
		{
			String mobnum = JOptionPane.showInputDialog(this, "Please enter your mobile number for confirmation:", "Mobile Number", JOptionPane.QUESTION_MESSAGE);
			
			if (mobnum != null && !mobnum.isEmpty())
			{
				try
				{
					Conn conn = new Conn();
					
					String query = "select * from users where phoneno = ?";
					
					PreparedStatement pst1 = conn.c.prepareStatement(query);
					
					pst1.setString(1, mobnum);
					
					ResultSet rs1 = pst1.executeQuery();
					
					if (rs1.next())
					{
						int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to make this purchase?", "Confirm Purchase", JOptionPane.YES_NO_OPTION);
						
						if (confirmation == JOptionPane.YES_OPTION)
						{
							JOptionPane.showMessageDialog(this, "Your Purchase has been Successful!", "Purchase Succesful", JOptionPane.INFORMATION_MESSAGE);
							receipt.setText("------------------- Welcome to JVM Cafe! -------------------\n-----------------------------------------------------------------------\n                                Java Street 299\n                           "
									+ "Tel: +099 675 8459\n-----------------------------------------------------------------------\nName                                         Quantity              Price");
						}
					}
					
					else
					{
						JOptionPane.showMessageDialog(this, "Mobile Number not found!", "Invalid Mobile Number", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				catch (Exception ex4)
				{
					ex4.printStackTrace();
				}
			}
			
			else
			{
				JOptionPane.showMessageDialog(this, "Please Enter a valid Mobile number", "Invalid Mobile Number", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if (e.getSource() == clear)
		{
			receipt.setText("------------------- Welcome to JVM Cafe! -------------------\n-----------------------------------------------------------------------\n                                Java Street 299\n                           "
					+ "Tel: +099 675 8459\n-----------------------------------------------------------------------\nName                                         Quantity              Price");
			
		}
	}
	
	private void comboBoxActionPerformed1() 
	{
	    Object selectedValue = hotdmenu.getSelectedItem();
	    
	    if (selectedValue != null) 
	    {
	        result1.setText(selectedValue.toString());
	        updateIngredientStock(result1.getText(), (int) spinner.getValue());
	    } 
	    
	    else 
	    {
	        result1.setText(""); 
	    }
	}
	
	private void comboBoxActionPerformed2()
	{
		Object selectedValue1 = coldmenu.getSelectedItem();
		
		if (selectedValue1 != null)
		{
			result2.setText(selectedValue1.toString());
			updateIngredientStock(result2.getText(), (int) spinner.getValue());
		}
		
		else
		{
			result2.setText("");
		}
	}
	
	private void comboBoxActionPerformed3()
	{
		Object selectedValue1 = foodmenu.getSelectedItem();
		
		if (selectedValue1 != null)
		{
			result3.setText(selectedValue1.toString());
			updateIngredientStock(result3.getText(), (int) spinner.getValue());
		}
		
		else
		{
			result3.setText("");
		}
	}
	
	private void comboBoxActionPerformed4(JComboBox<String> selectedComboBox)
	{
		if (selectedComboBox == hotdmenu)
		{
			coldmenu.setSelectedIndex(-1);
			foodmenu.setSelectedIndex(-1);
		}
		
		else if (selectedComboBox == coldmenu)
		{
			hotdmenu.setSelectedIndex(-1);
			foodmenu.setSelectedIndex(-1);
		}
		
		else if (selectedComboBox == foodmenu)
		{
			hotdmenu.setSelectedIndex(-1);
			coldmenu.setSelectedIndex(-1);
		}
		
	}
	
	private double getPriceOfHotDrinks(String productName)
	{
		double price = 0.0;
		try
		{
			Conn conn = new Conn();
			
			String query = "select Price FROM hotdrinksmenu WHERE HOT_DRINKS_AVAILABLE = ?";
			
			PreparedStatement pst = conn.c.prepareStatement(query);
			
			pst.setString(1,productName);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				price = rs.getDouble("Price");
			}
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return price;
	}
	
	private double getPriceOfColdDrinks(String productName)
	{
		double price = 0.0;
		try
		{
			Conn conn = new Conn();
			
			String query = "select Price FROM colddrinksmenu WHERE COLD_DRINKS_AVAILABLE = ?";
			
			PreparedStatement pst = conn.c.prepareStatement(query);
			
			pst.setString(1,productName);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				price = rs.getDouble("Price");
			}
		}
		
		catch(Exception ex1)
		{
			ex1.printStackTrace();
		}
		
		return price;
	}
	
	private double getPriceOfFood(String productName)
	{
		double price = 0.0;
		try
		{
			Conn conn = new Conn();
			
			String query = "select Price FROM food WHERE SNACKS_AVAILABLE = ?";
			
			PreparedStatement pst = conn.c.prepareStatement(query);
			
			pst.setString(1,productName);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				price = rs.getDouble("Price");
			}
		}
		
		catch(Exception ex2)
		{
			ex2.printStackTrace();
		}
		
		return price;
	}
	
	private void updateIngredientStock(String productName, int quantity)
	{
		try
		{
			Conn conn = new Conn();
			
			String query = "";
			
			if (productName.equals("Cappuccino"))
			{
				query = "update stocks set QUANTITY = QUANTITY - ? where ITEM_NAME in ('Milk', 'Espresso Shots', 'Sugar')";
			}
			
			else if (productName.equals("Espresso"))
			{
				query = "update stocks set QUANTITY = QUANTITY - ? where ITEM_NAME in ('Espresso Shots', 'Sugar')";
			}
			
			else if (productName.equals("Spiced Vanilla Milk"))
			{
				query = "update stocks set QUANTITY = QUANTITY - ? where ITEM_NAME in ('Milk', 'Spices', 'Sugar')";
			}
			
			else if (productName.equals("Malted Milkshake"))
			{
				query = "update stocks set QUANTITY = QUANTITY - ? where ITEM_NAME in ('Milk', 'Malted Milk Powder', 'Ice Cream', 'Sugar', 'Choco Syrup')";
			}
			
			else if (productName.equals("Iced Latte"))
			{
				query = "update stocks set QUANTITY = QUANTITY - ? where ITEM_NAME in ('Ice', 'Espresso Shots', 'Milk', 'Sugar')";
			}
			
			else if (productName.equals("Choco Frappe"))
			{
				query = "update stocks set QUANTITY = QUANTITY - ? where ITEM_NAME in ('Milk', 'Ice', 'Choco Syrup', 'Coffee Powder', 'Sugar')";
			}
			
			else if (productName.equals("BBQ Chicken Wrap"))
			{
				query = "update stocks set QUANTITY = QUANTITY - ? where ITEM_NAME in ('Chicken', 'Cheese', 'Flour', 'Sauce', 'Tomato')";
			}
			
			else if (productName.equals("Pesto Panini SW"))
			{
				query = "update stocks set QUANTITY = QUANTITY - ? where ITEM_NAME in ('Bread', 'Chicken', 'Sauce', 'Tomato', 'Cheese')";
			}
			
			else if (productName.equals("Chocolate Croissant"))
			{
				query = "update stocks set QUANTITY = QUANTITY - ? where ITEM_NAME in ('Flour', 'Choco Syrup', 'Sugar', 'Eggs')";
			}
			
			PreparedStatement pst = conn.c.prepareStatement(query);
			
			pst.setInt(1,quantity);
			
			pst.executeUpdate();
		}
		
		catch (SQLException exm)
		{
			exm.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		new OrderPanel();
	}
}
