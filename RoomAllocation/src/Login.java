import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.*;

public class Login {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	public static DBConnect dbCon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dbCon = new DBConnect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Could not connect to database");
		}
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

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 241);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(131, 0, 193, 33);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(61, 83, 65, 14);
		frame.getContentPane().add(lblNewLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(131, 80, 193, 20);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Password:");
		lblUsername.setBounds(61, 108, 65, 14);
		frame.getContentPane().add(lblUsername);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(131, 105, 193, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String userID = usernameField.getText();
				String password = passwordField.getText();
				boolean loggedIn=false;
				try {
					ResultSet myRes = dbCon.getResult("SELECT * FROM users");
					while(myRes.next()) {
						if (myRes.getString("USERID").equals(userID) && myRes.getString("PASSWORD").equals(password)) {
							//Login successful
							loggedIn=true;
							//MainMenu menu = new MainMenu();
							String userType = myRes.getString("USERTYPE");
							User thisUser;
							if (userType.equals("Admin")) {
								thisUser =  new Admin(Integer.parseInt(userID));
							} else if (userType.equals("Staff")){
								thisUser = new Staff(Integer.parseInt(userID));
							} else if (userType.equals("Student")){
								thisUser = new Student(Integer.parseInt(userID));
							} else {
								thisUser = new Guest(Integer.parseInt(userID));
							}
							JOptionPane.showMessageDialog(null, "New window");
							//menu.main(null, thisUser, myCon);
							frame.dispose();
							break;
						} 
					}
					if (!loggedIn) {
						JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(169, 169, 95, 23);
		frame.getContentPane().add(btnLogin);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(276, 169, -186, -16);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 149, 414, 2);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 56, 414, 2);
		frame.getContentPane().add(separator_2);
	}
}
