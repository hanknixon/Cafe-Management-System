package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import net.proteanit.sql.*;

public class ColdDrinks extends JFrame implements ActionListener {

    JTable table;
    JLabel background,cold;
    JButton goback;

    ColdDrinks() {
        setSize(1024, 768);
        setLocation(100, 100);
        
        Border border = BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(Color.BLACK, 3), 
        	    BorderFactory.createEmptyBorder(10, 10, 10, 10)
        		);
        
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/icon.jpg"));
        setIconImage(icon.getImage());
        
        goback = new JButton("GO BACK");
		goback.setBounds(800,610,120,30);
		goback.setBackground(Color.BLACK);
		goback.addActionListener(this);
		goback.setForeground(Color.WHITE);
		add(goback);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/coldrinks.jpg"));
        Image img = i1.getImage().getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
        i1 = new ImageIcon(img);
        background = new JLabel(i1);
        background.setBounds(0, 0, 1024, 768);
        add(background);
        
        
        cold = new JLabel("||COLD DRINKS||");
		cold.setBounds(320,100,350,75);
		cold.setFont(new Font("serif", Font.BOLD, 40));
		cold.setBorder(border);
		cold.setOpaque(true);
		cold.setBackground(Color.WHITE);
		background.add(cold);
		
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(90, 200, 820, 400); 
        background.add(scrollPane);

        try {
            Conn c = new Conn();
            
            ResultSet rs = c.s.executeQuery("select * from colddrinksmenu");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	if (e.getSource() == goback)
    	{
    		setVisible(false);
    		new MenuPage();
    	}
    }

    public static void main(String args[]) {
        new ColdDrinks();
    }
}
