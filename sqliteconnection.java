import java.sql.*;
import javax.swing.*;
public class sqliteconnection {
	Connection conn=null;
	public static Connection dbConnector()
	{
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\USER\\Documents\\project.sqlite");
			JOptionPane.showMessageDialog(null, "Connected");
			return conn;
		
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
