import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MakeBill extends JFrame {

	private JPanel contentPane;
	int c,a,total=0,count=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeBill frame = new MakeBill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textFieldTotal;
	
	DefaultTableModel model=new DefaultTableModel();
	private JButton btnBack;
    /**
	 * Create the frame.
	 */
	public MakeBill() {
		connection=sqliteconnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 603, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item Number");
		lblNewLabel.setBounds(45, 139, 112, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Item Count");
		lblNewLabel_1.setBounds(45, 212, 112, 31);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(45, 169, 112, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(45, 242, 112, 31);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Add In Bill");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int t=0;
					String query4="Select ItemName from ItemInfo where ItemNo = '"+textField.getText()+"' ";
					PreparedStatement prp4=connection.prepareStatement(query4);
					ResultSet rs4=prp4.executeQuery();
					
					String query1="Select Cost from ItemInfo where ItemNo = '"+textField.getText()+"' ";
					PreparedStatement prp=connection.prepareStatement(query1);
					ResultSet rs=prp.executeQuery();
					
					int ct = Integer.parseInt(textField_1.getText());
					
					String query2="select StockCount from ItemInfo where ItemNo = '"+textField.getText()+"' ";
					PreparedStatement prp2=connection.prepareStatement(query2);
					ResultSet rs2=prp2.executeQuery();
					
					while(rs2.next())
					{
						a=rs2.getInt(1);
						if(a>0)
						{
							a-=ct;
							if(a>0)
							{
								String query3="Update ItemInfo set StockCount = ? where ItemNo = '"+textField.getText()+"' ";
								PreparedStatement prp3=connection.prepareStatement(query3);
								prp3.setInt(1, a);
								prp3.execute();
								
								c=rs.getInt(1);
								c=c*ct;
								t=c;
							    total+=c;
							}
							else{
								JOptionPane.showMessageDialog(null, "Stock not available");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Stock not available");
						}
					}
					if(count == 0)
					{
						table.setModel(model);
						model.addColumn("Item Name");
						model.addColumn("No of Items");
						model.addColumn("Cost");
						model.addColumn("Total");
						count++;
					}
					String str=Integer.toString(t);
					model.addRow(new Object[]{rs4.getString(1),textField_1.getText(),rs.getString(1),str});
					
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		btnNewButton.setBounds(45, 284, 112, 31);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 24, 363, 291);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButtonTotal = new JButton("Total");
		btnNewButtonTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str=Integer.toString(total);
				textFieldTotal.setText(str);
			}
		});
		btnNewButtonTotal.setBounds(214, 326, 112, 34);
		contentPane.add(btnNewButtonTotal);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setBounds(362, 326, 139, 34);
		contentPane.add(textFieldTotal);
		textFieldTotal.setColumns(10);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ItemEntry ie=new ItemEntry();
				ie.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(45, 11, 89, 23);
		contentPane.add(btnBack);
	}
}
