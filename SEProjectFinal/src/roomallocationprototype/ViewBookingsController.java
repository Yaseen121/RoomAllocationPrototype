/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomallocationprototype;

import javafx.event.ActionEvent;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yaseen
 */
public class ViewBookingsController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@FXML
	private Button cancel = new Button();
	@FXML
	private Button cancelBooking = new Button();
	private DBConnect db = new DBConnect();
	private User currentUser;
	private static ArrayList<Booking> yourBookings = new ArrayList<Booking>();
	private static ArrayList<RadioButton> list = new ArrayList<RadioButton>();
	@FXML
	private VBox container = new VBox();

	public void initUser(User cU) {
		currentUser = cU;
		container.getChildren().clear();
		yourBookings.clear();
		list.clear();
		ResultSet res = db.getResult("SELECT * FROM bookings WHERE userID=" + currentUser.getID());
		try {
			while (res.next()) {
				int id = res.getInt("bookingID");
				int rID = res.getInt("roomID");
				int uID = res.getInt("userID");
				String time = res.getString("startTime");
				int open = res.getInt("OPEN");
				// Add if statement for open
				if (open == 1) {
					yourBookings.add(new Booking(id, rID, uID, time, true));
				} else {
					yourBookings.add(new Booking(id, rID, uID, time, false));
				}
			}
			for (int i = 0; i < yourBookings.size(); i++) {
				list.add(new RadioButton(yourBookings.get(i).getDetails2()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			container.setAlignment(Pos.CENTER);
			container.getChildren().add(list.get(i));
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	public void init() {
		container.getChildren().clear();
		yourBookings.clear();
		list.clear();
		ResultSet res = db.getResult("SELECT * FROM bookings WHERE userID=" + currentUser.getID());
		try {
			while (res.next()) {
				int id = res.getInt("bookingID");
				int rID = res.getInt("roomID");
				int uID = res.getInt("userID");
				String time = res.getString("startTime");
				int open = res.getInt("OPEN");
				// Add if statement for open
				if (open == 1) {
					yourBookings.add(new Booking(id, rID, uID, time, true));
				} else {
					yourBookings.add(new Booking(id, rID, uID, time, false));
				}
			}
			for (int i = 0; i < yourBookings.size(); i++) {
				list.add(new RadioButton(yourBookings.get(i).getDetails2()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			container.setAlignment(Pos.CENTER);
			container.getChildren().add(list.get(i));
		}
	}

	public void cancel(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Manage.fxml"));
		Parent manageParent = loader.load();
		Scene manageScene = new Scene(manageParent);
		// Can call methods from scene controller
		ManageController controller = loader.getController();
		controller.initUser(currentUser);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(manageScene);
		window.show();
	}

	public void cancelBooking() throws IOException, SQLException {
		for (int i = 0; i < yourBookings.size(); i++) {
			if (list.get(i).isSelected()) {
				currentUser.cancelBooking(yourBookings.get(i));
			}
		}
		init();
	}

}
