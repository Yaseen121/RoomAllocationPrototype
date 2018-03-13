import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBConnect {
	
	private Connection con;
	private static Statement stmt;
	private static ResultSet res;
	
	public DBConnect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/roomallocation", "root", "root");
			stmt = con.createStatement();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
	
	public static ResultSet getResult (String query) {
		try {
			res = stmt.executeQuery(query);
			return res;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
		return null;
	}
	
	public static void insert (String query) {
		try {
			stmt.execute(query);
		} catch  (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
}
