package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class LoginAdmin extends JFrame implements ActionListener
{
	JPanel panel;
    JTextField usernametf;
    JButton login,cancel,showhide;
    JPasswordField passwordtf;
    JLabel background;
    boolean passvis = false;
    
	LoginAdmin()
	{
		setSize(800,600);
		setLocation(100,100);
		
		getContentPane().setBackground(Color.WHITE);
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
		setIconImage(icon.getImage());
		
		setLayout(null);
		
		panel = new JPanel();
        panel.setBounds(200, 100, 400, 300);
        Border border = BorderFactory.createLineBorder(Color.BLACK); 
        panel.setBorder(BorderFactory.createTitledBorder(border, "Login (Admin)")); 
        panel.setLayout(null); 
        add(panel);
		
		JLabel username = new JLabel("Username");
		username.setBounds(70,45,400,75);
		username.setFont(new Font("serif", Font.PLAIN, 22));
		panel.add(username);
		
		usernametf = new JTextField();
		usernametf.setBounds(175,69,150,30);
		panel.add(usernametf);
		
		JLabel password = new JLabel("Password");
		password.setBounds(70,143,100,30);
		password.setFont(new Font("serif", Font.PLAIN, 22));
		panel.add(password);
		
		passwordtf = new JPasswordField();
		passwordtf.setBounds(175,146,150,30);
		panel.add(passwordtf);
		
		login = new JButton("LOGIN");
		login.setBounds(62,220,120,30);
		login.setBackground(Color.BLACK);
		login.addActionListener(this);
		login.setForeground(Color.WHITE);
		panel.add(login);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/background2.jpg"));
        background = new JLabel(i1);
        background.setSize(800,600);
        add(background);
		
		cancel = new JButton("CANCEL");
		cancel.setBounds(222,220,120,30);
		cancel.setBackground(Color.BLACK);
		cancel.setForeground(Color.WHITE);
		cancel.addActionListener(this);
		panel.add(cancel);
		
		showhide = new JButton("Show");
		showhide.setBounds(325,147,70,27);
		showhide.setBackground(Color.BLACK);
		showhide.setForeground(Color.WHITE);
		showhide.addActionListener(this);
		panel.add(showhide);

		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == showhide)
		{
			if(passvis)
			{
				passwordtf.setEchoChar('•');
				showhide.setText("Show");
				passvis = false;
			}
			else
			{
				passwordtf.setEchoChar((char) 0); 
				showhide.setText("Hide");
				passvis = true;
			}
		}
    	
		if(e.getSource() == cancel)
		{
			setVisible(false);
			new MainPage();
		}
		else if(e.getSource() == login)
		{
			String username = usernametf.getText();
			String password = new String(passwordtf.getPassword());
			
			if (username.equals("admin") && password.equals("admin"))
			{
				setVisible(false);
				new AdminPanel();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Invalid Username or password!");
			}
		}
	}
	public static void main(String args[])
	{
		new LoginAdmin();
	}
}
