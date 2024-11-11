package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import net.proteanit.sql.*;

public class EditProfile extends JFrame implements ActionListener 
{
	JLabel background;
	JPanel panel;
	JTextField userPhone;
	JPasswordField oldpasstf,passwordtf,passwordretf;
	JButton goback,update;
	
	EditProfile()
	{
		setSize(800,600);
		setLocation(100,100);
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
        setIconImage(icon.getImage());
        
        panel = new JPanel();
        panel.setBounds(140, 70, 500, 400);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        panel.setBorder(BorderFactory.createTitledBorder(border, "Register Page"));
        panel.setLayout(null);
        add(panel);
        
        JLabel phoneno = new JLabel("Confirm Phone No:");
        phoneno.setBounds(70, 90, 160, 30);
        phoneno.setFont(new Font("serif", Font.PLAIN, 18));
        panel.add(phoneno);

        userPhone = new JTextField();
        userPhone.setBounds(270, 90, 150, 30);
        panel.add(userPhone);

        JLabel oldpass = new JLabel("Old Password:");
        oldpass.setBounds(70, 135, 130, 30);
        oldpass.setFont(new Font("serif", Font.PLAIN, 18));
        panel.add(oldpass);

        oldpasstf = new JPasswordField();
        oldpasstf.setBounds(270, 135, 150, 30);
        panel.add(oldpasstf);

        JLabel password = new JLabel("New Password:");
        password.setBounds(70, 180, 130, 30);
        password.setFont(new Font("serif", Font.PLAIN, 18));
        panel.add(password);

        passwordtf = new JPasswordField();
        passwordtf.setBounds(270, 180, 150, 30);
        panel.add(passwordtf);

        JLabel passwordre = new JLabel("Confirm New Password:");
        passwordre.setBounds(70, 225, 190, 30);
        passwordre.setFont(new Font("serif", Font.PLAIN, 18));
        panel.add(passwordre);

        passwordretf = new JPasswordField();
        passwordretf.setBounds(270, 225, 150, 30);
        panel.add(passwordretf);

        update = new JButton("UPDATE");
        update.setBounds(250, 320, 120, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        panel.add(update);
        
        goback = new JButton("GO BACK");
        goback.setBounds(130,320,100,30);
        goback.setBackground(Color.BLACK);
        goback.setForeground(Color.WHITE);
        goback.addActionListener(this);
        panel.add(goback);

        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/panel.jpg"));
        Image img = i1.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        i1 = new ImageIcon(img);
        background = new JLabel(i1);
        background.setBounds(0, 0, 800, 600);
        add(background);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == goback)
		{
			setVisible(false);
			new LoginPanel();
		}
		
		else if (e.getSource() ==  update)
		{
			String userPh = userPhone.getText();
			String oldpassword = new String(oldpasstf.getPassword());
			String newpassword = new String(passwordtf.getPassword());
			String confirmpassword = new String(passwordretf.getPassword());
			
			if (userPh.isEmpty() || oldpassword.isEmpty() || newpassword.isEmpty() || confirmpassword.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please fill out all required fields","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			else if (!newpassword.equals(confirmpassword))
			{
				JOptionPane.showMessageDialog(this, "The Passwords do not match, Please recheck!","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try 
			{
				Conn conn = new Conn();
				String query = "select password from users where phoneno = ?";
				PreparedStatement pst = conn.c.prepareStatement(query);
				
				pst.setString(1, userPh);
				
				ResultSet rs = pst.executeQuery();
				
				if(rs.next())
				{
					if (rs.next())
					{
						String updateQuery = "update users set password = ? where phoneno = ?";
						
						PreparedStatement updPst = conn.c.prepareStatement(updateQuery);
						
						updPst.setString(1, newpassword);
			            updPst.setString(2, userPh);
			            int updateCount = updPst.executeUpdate();
			            
			            if (updateCount > 0)
			            {
			            	JOptionPane.showMessageDialog(this, "Password updated successfully.");
			            }
			            
			            else
			            {
			            	JOptionPane.showMessageDialog(this, "Error updating password.");
			            }
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Incorrect current password.", "Authentication Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
			
			catch (Exception ae)
			{
				ae.printStackTrace();
			}
		}
	}
	
	
	public static void main(String args[])
	{
		new EditProfile();
	}

}
