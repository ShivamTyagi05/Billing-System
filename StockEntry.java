import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StockEntry extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockEntry frame = new StockEntry();
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
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	 * Create the frame.
	 */
	public StockEntry() {
		connection=sqliteconnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(130, 51, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(130, 96, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(130, 141, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(130, 187, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnSaveData = new JButton("Save Item");
		btnSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="insert into ItemInfo (ItemNo,ItemName,StockCount,Cost) values(?,?,?,?)";
					PreparedStatement prp=connection.prepareStatement(query);
					prp.setString(1,textField.getText());
					prp.setString(2,textField_1.getText());
					prp.setString(3,textField_2.getText());
					prp.setString(4,textField_3.getText());
					prp.execute();
					
					JOptionPane.showMessageDialog(null, "Item Saved");
					
					prp.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSaveData.setBounds(291, 51, 104, 30);
		contentPane.add(btnSaveData);
		
		JLabel lblItemNumber = new JLabel("Item Number");
		lblItemNumber.setBounds(10, 54, 82, 14);
		contentPane.add(lblItemNumber);
		
		JLabel lblNewLabel = new JLabel("Item Name");
		lblNewLabel.setBounds(10, 99, 82, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("No of Items");
		lblNewLabel_1.setBounds(10, 144, 82, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cost of Item");
		lblNewLabel_2.setBounds(10, 190, 82, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Update Item");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="Update ItemInfo set ItemNo='"+textField.getText()+"', ItemName='"+textField_1.getText()+"', StockCount='"+textField_2.getText()+"', Cost='"+textField_3.getText()+"' where ItemNo='"+textField.getText()+"' ";
					PreparedStatement prp=connection.prepareStatement(query);
					prp.execute();
					
					JOptionPane.showMessageDialog(null, "Item Update");
					
					prp.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(291, 109, 104, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete Item");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="Delete from ItemInfo where ItemNo='"+textField.getText()+"' ";
					PreparedStatement prp=connection.prepareStatement(query);
					prp.execute();
					
					JOptionPane.showMessageDialog(null, "Item Deleted");
					
					prp.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(291, 177, 104, 30);
		contentPane.add(btnNewButton_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ItemEntry ie=new ItemEntry();
				ie.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(10, 11, 65, 23);
		contentPane.add(btnBack);
	}
}
