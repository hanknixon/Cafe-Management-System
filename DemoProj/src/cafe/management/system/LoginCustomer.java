package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginCustomer extends JFrame implements ActionListener, MouseListener
{
	JPanel panel;
    JTextField phoneno;
    JButton login,cancel;
    JPasswordField passwordtf;
    JLabel background;
    JButton showhide;
    boolean passvis = false;
    
	LoginCustomer()
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
        panel.setBorder(BorderFactory.createTitledBorder(border, "Login (Customer)")); 
        panel.setLayout(null); 
        add(panel);
		
		JLabel username = new JLabel("Phone no");
		username.setBounds(70,45,400,75);
		username.setFont(new Font("serif", Font.PLAIN, 22));
		panel.add(username);
		
		phoneno = new JTextField();
		phoneno.setBounds(175,69,150,30);
		panel.add(phoneno);
		
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
		login.setForeground(Color.WHITE);
		login.addActionListener(this);
		panel.add(login);
		
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
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/background2.jpg"));
        background = new JLabel(i1);
        background.setSize(800,600);
        add(background);
		
		JLabel dont = new JLabel("No account?");
		dont.setBounds(133,260,120,30);
		dont.setForeground(Color.BLACK);
		panel.add(dont);
		
		
		JLabel register = new JLabel("SIGN UP");
        register.setBounds(210, 260, 120, 30);
        register.setForeground(Color.BLUE);
        register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        register.addMouseListener(this);
        panel.add(register);

		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void mouseClicked(MouseEvent e) 
	{
        if (e.getSource() instanceof JLabel) 
        {
            setVisible(false);
            new RegisterPage();
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
		
		if (e.getSource() == login)
		{
			String loginphone = phoneno.getText();
			String loginpass = new String(passwordtf.getPassword());
			
			try
			{
				Conn conn = new Conn();
				
				String query = "select * from users where phoneno = '" +loginphone+ "' and password = '" +loginpass+ "'";
				
				ResultSet rs = conn.s.executeQuery(query);
	            
				if (rs.next()) {
				    JOptionPane.showMessageDialog(null, "Logged in Successfully!");
				    setVisible(false);
				    new LoginPanel();
				} else {
				    JOptionPane.showMessageDialog(null, "Invalid Phone No or Password, No Such User Found", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    } catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }        
		}
		
	}
	public static void main(String args[])
	{
		new LoginCustomer();
	}
}
