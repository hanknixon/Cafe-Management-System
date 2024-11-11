package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class RegisterPage extends JFrame implements ActionListener {
    JPanel panel;
    JTextField fullnametf, phonenotf;
    JPasswordField passwordtf, passwordretf;
    JButton register,goback,showhide;
    JLabel regicon,background,eye;
    boolean passvis = false;

    RegisterPage() {
    			
        setSize(800, 600);
        setLocation(100, 100);

        getContentPane().setBackground(Color.WHITE);
        

        panel = new JPanel();
        panel.setBounds(140, 70, 500, 400);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        panel.setBorder(BorderFactory.createTitledBorder(border, "Register Page"));
        panel.setLayout(null);
        add(panel);

        JLabel fullname = new JLabel("Full Name:");
        fullname.setBounds(70, 45, 100, 30);
        fullname.setFont(new Font("serif", Font.PLAIN, 18));
        panel.add(fullname);

        fullnametf = new JTextField();
        fullnametf.setBounds(180, 45, 200, 30);
        panel.add(fullnametf);

        JLabel phoneno = new JLabel("Phone no:");
        phoneno.setBounds(70, 90, 100, 30);
        phoneno.setFont(new Font("serif", Font.PLAIN, 18));
        panel.add(phoneno);

        phonenotf = new JTextField();
        phonenotf.setBounds(180, 90, 200, 30);
        panel.add(phonenotf);

        JLabel password = new JLabel("Password:");
        password.setBounds(70, 135, 100, 30);
        password.setFont(new Font("serif", Font.PLAIN, 18));
        panel.add(password);

        passwordtf = new JPasswordField();
        passwordtf.setBounds(180, 135, 200, 30);
        panel.add(passwordtf);

        JLabel passwordre = new JLabel("Confirm Password:");
        passwordre.setBounds(70, 180, 150, 30);
        passwordre.setFont(new Font("serif", Font.PLAIN, 18));
        panel.add(passwordre);

        passwordretf = new JPasswordField();
        passwordretf.setBounds(220, 180, 200, 30);
        panel.add(passwordretf);

        register = new JButton("REGISTER");
        register.setBounds(250, 270, 120, 30);
        register.setBackground(Color.BLACK);
        register.setForeground(Color.WHITE);
        panel.add(register);
        
        goback = new JButton("GO BACK");
        goback.setBounds(130,270,100,30);
        goback.setBackground(Color.BLACK);
        goback.setForeground(Color.WHITE);
        goback.addActionListener(this);
        panel.add(goback);
        
        showhide = new JButton("Show");
		showhide.setBounds(390,136,70,27);
		showhide.setBackground(Color.BLACK);
		showhide.setForeground(Color.WHITE);
		showhide.addActionListener(this);
		panel.add(showhide);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/background2.jpg"));
        background = new JLabel(i1);
        background.setSize(800,600);
        add(background);
        
         
        register.addActionListener(this);

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
    	
        if (e.getSource() == register) {
        	
        	String fullname = fullnametf.getText().trim();
        	String phoneno = phonenotf.getText().trim();
        	String password = String.valueOf(passwordtf.getPassword());
        	String confirmPassword = String.valueOf(passwordretf.getPassword());
        	
        	if(fullname.isEmpty() || phoneno.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() )
        	{
        		JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	if(!fullname.matches("[a-zA-z ]+"))
        	{
        		JOptionPane.showMessageDialog(this,"Full Name cannot contain Numbers!","Error",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	if(!phoneno.matches("\\d+"))
        	{
        		JOptionPane.showMessageDialog(this,"Invalid Phone no, Please provide a valid Phone no","Error",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	if(!password.equals(confirmPassword))
        	{
        		JOptionPane.showMessageDialog(this,"Passwords do not match!","Error",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
            
            try
            {
            	Conn conn = new Conn();
            	
            	String query = "insert into users (fname, phoneno, password) values('"+fullname+"', '"+phoneno+"', '"+password+"')";

            	conn.s.executeUpdate(query);
            	
            	JOptionPane.showMessageDialog(null,"User Added Successfully!, Reverting back to main menu");
            	
            	setVisible(false);
                new MainPage();
            }
            catch (Exception ex)
            {
            	ex.printStackTrace();
            }
            
        }
        else if (e.getSource() == goback)
        {
        	setVisible(false);
        	new LoginCustomer();
        }
    }

    public static void main(String args[]) {
        new RegisterPage();
    }
}
