import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.sql.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserMain {

	JFrame frame;
	private JTextField txtbname;
	private JTextField txtprice;
	private JTextField txtedition;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMain window = new UserMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;

	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javabookshop", "root","");
//	            if(con!=null){System.out.println("lol");}
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	               ex.printStackTrace();
	        }

	    }
	 
	 
	public UserMain() {
		initialize();
		Connect();
		table_load();
	}
	
	public void table_load() {
	    try {
	        pst = con.prepareStatement("SELECT * FROM books");
	        rs = pst.executeQuery();

	        // to get column names
	        ResultSetMetaData metaData = rs.getMetaData();
	        int count = metaData.getColumnCount();

	        String[] columnNames = new String[count];
	        for (int i = 1; i <= count; i++) {
	            columnNames[i - 1] = metaData.getColumnName(i);
	        }
	        
	        // DefaultTableModel 
	        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

	        // Add rows
	        while (rs.next()) {
	            Object[] row = new Object[count];
	            for (int i = 1; i <= count; i++) {
	                row[i - 1] = rs.getObject(i);
	            }
	            model.addRow(row);
	        }
	        
	        
	        table.setModel(model);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1036, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBookShop = new JLabel("BOOK SHOP");
		lblBookShop.setForeground(new Color(0, 191, 255));
		lblBookShop.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblBookShop.setBackground(new Color(255, 255, 255));
		lblBookShop.setBounds(415, 23, 228, 51);
		frame.getContentPane().add(lblBookShop);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.RED);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(30, 110, 453, 281);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(36, 49, 107, 37);
		panel.add(lblNewLabel);
		
		JLabel lblEdition = new JLabel("Quantity");
		lblEdition.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEdition.setBounds(36, 117, 107, 37);
		panel.add(lblEdition);
		
		JLabel lblPrice = new JLabel("Cost");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(36, 182, 107, 37);
		panel.add(lblPrice);
		
		txtbname = new JTextField();
		txtbname.setBounds(175, 59, 208, 22);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(175, 192, 208, 22);
		panel.add(txtprice);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(175, 127, 208, 22);
		panel.add(txtedition);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(45, 420, 435, 92);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
                
                try {
                     
                       String Id = txtbid.getText();

                           pst = con.prepareStatement("select Name,Edition,Cost from books where Id = ?");
                           pst.setString(1, Id);
                           ResultSet rs = pst.executeQuery();

                       if(rs.next()==true)
                       {
                         
                           String name = rs.getString(1);
                           String edition = rs.getString(2);
                           String price = rs.getString(3);
                           
                           txtbname.setText(name);
                           txtedition.setText(edition);
                           txtprice.setText(price);
   
                       }   
                       else
                       {
                           txtbname.setText("");
                           txtedition.setText("");
                           txtprice.setText("");
                            
                       }
                   } 
               
                catch (SQLException ex) {
                      
                   }
           }
		});
		txtbid.setColumns(10);
		txtbid.setBounds(177, 31, 208, 22);
		panel_1.add(txtbid);
		
		JLabel lblBookId = new JLabel("Book Id");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBookId.setBounds(33, 21, 107, 37);
		panel_1.add(lblBookId);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		table.setBounds(539, 134, 453, 321);
		frame.getContentPane().add(table);
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setBackground(new Color(230, 230, 250));
		txtpnId.setText("          ID");
		txtpnId.setBounds(539, 113, 104, 22);
		frame.getContentPane().add(txtpnId);
		
		JTextPane txtpnName = new JTextPane();
		txtpnName.setForeground(new Color(0, 0, 0));
		txtpnName.setText("          NAME");
		txtpnName.setBackground(new Color(230, 230, 250));
		txtpnName.setBounds(638, 113, 139, 22);
		frame.getContentPane().add(txtpnName);
		
		JTextPane txtpnEdition = new JTextPane();
		txtpnEdition.setText("    QUANTITY");
		txtpnEdition.setBackground(new Color(230, 230, 250));
		txtpnEdition.setBounds(776, 113, 133, 22);
		frame.getContentPane().add(txtpnEdition);
		
		JTextPane txtpnPrice = new JTextPane();
		txtpnPrice.setText("    PRICE");
		txtpnPrice.setBackground(new Color(230, 230, 250));
		txtpnPrice.setBounds(904, 113, 88, 22);
		frame.getContentPane().add(txtpnPrice);
	}
}
