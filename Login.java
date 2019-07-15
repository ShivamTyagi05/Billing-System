import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection=null;
	private JTextField textField;
	private JPasswordField passwordField;
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection=sqliteconnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User name");
		lblNewLabel.setBounds(31, 37, 84, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(31, 70, 71, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(180, 34, 141, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(180, 67, 141, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="select * from loginTable where username =? and password =?";
					PreparedStatement prp=connection.prepareStatement(query);
					prp.setString(1,textField.getText());
					prp.setString(2,passwordField.getText());
					ResultSet rs=prp.executeQuery();
					int count=0;
					while(rs.next())
					{
						count++;
					}
					if(count == 1)
					{
						JOptionPane.showMessageDialog(null, "Username & password is correct.");
						ItemEntry it= new ItemEntry();
						it.setVisible(true);
						frame.dispose();
					}
					else if(count>1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username & password is incorrect.");
					}
					rs.close();
					prp.close();
				
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnLogIn.setBounds(180, 98, 89, 23);
		frame.getContentPane().add(btnLogIn);
	}
}
