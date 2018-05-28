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
public class ViewSwapRequestsController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@FXML
	private Button cancel = new Button();
	private DBConnect db = new DBConnect();
	private User currentUser;
	private static ArrayList<String> yourBookings = new ArrayList<String>();
	private static ArrayList<SwapRequest> reqs = new ArrayList<SwapRequest>();
	private static ArrayList<RadioButton> list = new ArrayList<RadioButton>();
	@FXML private VBox container = new VBox();
	@FXML private Button approve = new Button();
	@FXML private Button decline = new Button();
	
	public void initUser(User cU) {
		currentUser = cU;
		ResultSet res = db.getResult("SELECT * FROM bookings WHERE userID="+currentUser.getID());
		try {
			while(res.next()) {
				String id = res.getString("bookingID");
				yourBookings.add(id);
			}
			for (int i=0; i<yourBookings.size(); i++) {
				res = db.getResult("SELECT * FROM swaprequests WHERE REQUESTEDBOOKINGID="+yourBookings.get(i));
				int requesterBookingID = -1;
				while(res.next()) {
                                            JOptionPane.showMessageDialog(null, "Stuff be happenign");
					int id = res.getInt("ID");
					requesterBookingID = res.getInt("REQUESTERBOOKINGID");
					int requestedBookingID = res.getInt("REQUESTEDBOOKINGID");
					reqs.add(new SwapRequest(id, requesterBookingID, requestedBookingID));
				}
			}
			for (int i=0; i<reqs.size();i++) {
				list.add(new RadioButton(reqs.get(i).getDetails2()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i=0; i<reqs.size(); i++) {
			container.setAlignment(Pos.CENTER);
			container.getChildren().add(list.get(i));
		}
	}
	
	public void approve() {
		for (int i=0; i<reqs.size(); i++) {
			if (list.get(i).isSelected()) {
//				JOptionPane.showMessageDialog(null, "Accept " + DisplaySwapRequests.this.reqs.get(i).getDetails());
				try {
					currentUser.replySwapRequest(reqs.get(i));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		}
		container.getChildren().clear();
		init();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	public void init() {
		reqs.clear();
		list.clear();
		try {
			ResultSet res = db.getResult("SELECT * FROM bookings WHERE userID="+currentUser.getID());
			while(res.next()) {
				String id = res.getString("bookingID");
				yourBookings.add(id);
			}
			for (int i=0; i<yourBookings.size(); i++) {
				res = db.getResult("SELECT * FROM swaprequests WHERE REQUESTEDBOOKINGID="+yourBookings.get(i));
				int requesterBookingID = -1;
				while(res.next()) {
                                            JOptionPane.showMessageDialog(null, "Stuff be happenign");
					int id = res.getInt("ID");
					requesterBookingID = res.getInt("REQUESTERBOOKINGID");
					int requestedBookingID = res.getInt("REQUESTEDBOOKINGID");
					reqs.add(new SwapRequest(id, requesterBookingID, requestedBookingID));
				}
			}
			for (int i=0; i<reqs.size();i++) {
				list.add(new RadioButton(reqs.get(i).getDetails2()));
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
