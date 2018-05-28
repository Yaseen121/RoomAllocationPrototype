/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomallocationprototype;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yaseen
 */
public class AdminPageController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@FXML
	private Button addUser = new Button();
	@FXML
	private Button editUser = new Button();
	@FXML
	private Button removeUser = new Button();
	@FXML
	private Button addRoom = new Button();
	@FXML
	private Button editRoom = new Button();
	@FXML
	private Button removeRoom = new Button();
	@FXML
	private Button cancel = new Button();
	private DBConnect db = new DBConnect();
	private Admin currentUser;

	public void initUser(Admin cU) {
		currentUser = cU;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	public void addUser() {
		String pass = JOptionPane.showInputDialog("Enter a password");
		String[] userTypes = { "Student", "Staff", "Guest", "Admin" };
		String uT = (String) JOptionPane.showInputDialog(null, "Choose a user type: ", null,
				JOptionPane.QUESTION_MESSAGE, null, userTypes, userTypes[0]);
		currentUser.addUser(pass, uT);
	}

	public void removeUser() {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id of the user to remove"));
		currentUser.removeUser(id);
	}

	public void addRoom() {
		String rN = JOptionPane.showInputDialog("Enter a room name");
		String[] roomTypes = { "Classroom", "Lab", "LectureHall" };
		String rT = (String) JOptionPane.showInputDialog(null, "Choose a room type: ", null,
				JOptionPane.QUESTION_MESSAGE, null, roomTypes, roomTypes[0]);
		int c = Integer.parseInt(JOptionPane.showInputDialog("Enter a capacity: "));
		String d = JOptionPane.showInputDialog("Enter a dept: ");
		int pU = Integer.parseInt(JOptionPane.showInputDialog("Enter permitted users: "));
		currentUser.addRoom(rN, c, d, rT, pU);
	}

	public void removeRoom() {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the id of the room to remove"));
		currentUser.removeRoom(id);
	}

	public void cancel(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("MainMenu.fxml"));
		Parent mainMenuParent = loader.load();
		Scene mainMenuScene = new Scene(mainMenuParent);
		// Can call methods from scene controller
		MainMenuController controller = loader.getController();
		controller.initUser(currentUser);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(mainMenuScene);
		window.show();
	}

}
