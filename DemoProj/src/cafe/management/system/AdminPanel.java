package cafe.management.system;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class AdminPanel extends JFrame implements ActionListener
{
	JLabel image;
	JButton goback;
	AdminPanel()
	{
		setSize(800,600);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/adminbackground.jpg"));
		image = new JLabel(i1);
		image.setBounds(0,0,800,600);
		add(image);
		
		goback = new JButton("SIGN OUT");
		goback.setBounds(320,200,120,30);
		goback.setBackground(Color.BLACK);
		goback.addActionListener(this);
		goback.setForeground(Color.WHITE);
		image.add(goback);
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
		setIconImage(icon.getImage());
		
		JLabel adminpanel = new JLabel("JVM CAFE ADMIN PANEL");
		adminpanel.setBounds(200,90,1000,50);
		adminpanel.setFont(new Font("Tahoma",Font.BOLD,30));
		adminpanel.setForeground(Color.WHITE);
		image.add(adminpanel);
		
		JMenuBar adminmb = new JMenuBar();
		adminmb.setBounds(0,0,800,30);
		adminmb.setForeground(Color.WHITE);
		image.add(adminmb);
		
		JMenu menu = new JMenu("MODIFY MENU");
		adminmb.add(menu);
		
		JMenuItem view = new JMenuItem("View Menu");
		view.addActionListener(this);
		menu.add(view);
		
		JMenuItem hdrinks = new JMenuItem("Edit Hot Drinks");
		hdrinks.addActionListener(this);
		menu.add(hdrinks);
		
		JMenuItem cdrinks = new JMenuItem("Edit Cold Drinks");
		cdrinks.addActionListener(this);
		menu.add(cdrinks);
		
		JMenuItem food = new JMenuItem("Edit Eatables");
		food.addActionListener(this);
		menu.add(food);
		
		JMenu customers = new JMenu("MODIFY USERS");
		adminmb.add(customers);
		
		JMenuItem addusers = new JMenuItem("Add Users");
		addusers.addActionListener(this);
		customers.add(addusers);
		
		JMenuItem viewusers = new JMenuItem("View Users");
		viewusers.addActionListener(this);
		customers.add(viewusers);
		
		JMenuItem deleteusers = new JMenuItem("Delete Users");
		deleteusers.addActionListener(this);
		customers.add(deleteusers);
		
		
		JMenu orders = new JMenu("ORDERS");
		adminmb.add(orders);
		
		JMenuItem viewhistory = new JMenuItem("View History");
		orders.add(viewhistory);
		
		JMenu stock = new JMenu("STOCK MANAGEMENT");
		adminmb.add(stock);
		
		JMenuItem cstock = new JMenuItem("View Current Stocks");
		cstock.addActionListener(this);
		stock.add(cstock);
		
		JMenuItem astock = new JMenuItem("Buy New Stocks");
		astock.addActionListener(this);
		stock.add(astock);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Add Users"))
		{
			setVisible(false);
			new AddUsers();
		}
		
		if(e.getActionCommand().equals("View Menu"))
		{
			setVisible(false);
			new MenuPage();
		}
		
		if (e.getSource() == goback)
		{
			setVisible(false);
			new LoginAdmin();
		}
		
		if (e.getActionCommand().equals("Delete Users"))
		{
			setVisible(false);
			new DeleteUsers();
		}
		
		if (e.getActionCommand().equals("View Current Stocks"))
		{
			setVisible(false);
			new ViewStocks();
		}
		
		if (e.getActionCommand().equals("Buy New Stocks"))
		{
			setVisible(false);
			new BuyStocks();
		}
		
		if (e.getActionCommand().equals("View Users"))
		{
			setVisible(false);
			new ViewUsers();
		}
		
		if (e.getActionCommand().equals("Edit Hot Drinks"))
		{
			setVisible(false);
			new EditHot();
		}
		
		if (e.getActionCommand().equals("Edit Cold Drinks"))
		{
			setVisible(false);
			new EditCold();
		}
		
		if (e.getActionCommand().equals("Edit Eatables"))
		{
			setVisible(false);
			new EditFood();
		}
	}
	public static void main(String args[])
	{
		new AdminPanel();
	}
}
