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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yaseen
 */
public class ViewBookingRequestsController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@FXML
	private Button cancel = new Button();
	private DBConnect db = new DBConnect();
	private Admin currentUser;
	private static ArrayList<Booking> reqs = new ArrayList<Booking>();
	private static ArrayList<CheckBox> list = new ArrayList<CheckBox>();
	@FXML private VBox container = new VBox();
	@FXML private Button approve = new Button();
	@FXML private Button decline = new Button();
	
	public void initUser(Admin cU) {
		currentUser = cU;
	}
	
	public void decline() {
		for (int i=0; i<reqs.size(); i++) {
			if (list.get(i).isSelected()) {
				JOptionPane.showMessageDialog(null, "Declined " + reqs.get(i).getDetails());
				currentUser.declineBooking(reqs.get(i));
			}
		}
		container.getChildren().clear();
		init();
	}
	
	public void approve() {
		for (int i=0; i<reqs.size(); i++) {
			if (list.get(i).isSelected()) {
				JOptionPane.showMessageDialog(null, "Approved " + reqs.get(i).getDetails());
				currentUser.approveBooking(reqs.get(i));
			}
		}
		container.getChildren().clear();
		init();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ResultSet res = db.getResult("SELECT * FROM bookingrequests");
		try {
			while(res.next()) {
				int id = res.getInt("ID");
				int rID = res.getInt("ROOMID");
				int uID = res.getInt("USERID");
				String time = res.getString("startTime");
				int open = res.getInt("OPEN");
				//Add if statement for open
				reqs.add(new Booking(id, rID, uID, time, true));
			}
			for (int i=0; i<reqs.size();i++) {
				list.add(new CheckBox(reqs.get(i).getDetails2()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i=0; i<reqs.size(); i++) {
			container.setAlignment(Pos.CENTER);
			container.getChildren().add(list.get(i));
		}
	}
	
	public void init() {
		reqs.clear();
		list.clear();
		ResultSet res = db.getResult("SELECT * FROM bookingrequests");
		try {
			while(res.next()) {
				int id = res.getInt("ID");
				int rID = res.getInt("ROOMID");
				int uID = res.getInt("USERID");
				String time = res.getString("startTime");
				int open = res.getInt("OPEN");
				//Add if statement for open
				reqs.add(new Booking(id, rID, uID, time, true));
			}
			for (int i=0; i<reqs.size();i++) {
				list.add(new CheckBox(reqs.get(i).getDetails2()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i=0; i<reqs.size(); i++) {
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

}
