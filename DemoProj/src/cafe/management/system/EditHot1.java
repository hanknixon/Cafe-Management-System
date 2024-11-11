package cafe.management.system;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.*;

public class EditHot1 extends JFrame implements ActionListener {

    JTable table;
    JLabel background,hot;
    JButton goback;

    EditHot1() {
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

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/hotdrinks.jpg"));
        Image img = i1.getImage().getScaledInstance(1024, 768, Image.SCALE_SMOOTH);
        i1 = new ImageIcon(img);
        background = new JLabel(i1);
        background.setBounds(0, 0, 1024, 768);
        add(background);
        
        hot = new JLabel("||HOT DRINKS||");
		hot.setBounds(340,100,320,75);
		hot.setFont(new Font("serif", Font.BOLD, 40));
		hot.setBorder(border);
		hot.setOpaque(true);
		hot.setBackground(Color.WHITE);
		background.add(hot);
		
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(90, 200, 820, 400); 
        background.add(scrollPane);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from hotdrinksmenu");
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
    		new EditHot();
    	}
    }
    
    public static void main(String args[]) {
        new EditHot1();
    }
}
