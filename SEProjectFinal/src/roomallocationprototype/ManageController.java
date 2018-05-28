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
public class ManageController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@FXML
	private Button viewBookings = new Button();
	@FXML
	private Button viewSwapRequests = new Button();
	@FXML
	private Button viewQueues = new Button();
	@FXML
	private Button viewInviteRequests = new Button();
	@FXML
	private Button viewBookingRequest = new Button();
	@FXML
	private Button cancel = new Button();
	private DBConnect db = new DBConnect();
	private User currentUser;

	public void initUser(User cU) {
		currentUser = cU;
		if (currentUser instanceof Admin) {
			viewBookingRequest.setVisible(true);
		} else {
			viewBookingRequest.setVisible(false);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb){

	}
	
	public void openViewBookings(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ViewBookings.fxml"));
		Parent viewBookingsParent = loader.load();
		Scene viewBookingsScene = new Scene(viewBookingsParent);
		// Can call methods from scene controller
		ViewBookingsController controller = loader.getController();
		controller.initUser(currentUser);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(viewBookingsScene);
		window.show();
	}
		
	public void openSwapRequests(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ViewSwapRequests.fxml"));
		Parent swapReqParent = loader.load();
		Scene swapReqScene = new Scene(swapReqParent);
		// Can call methods from scene controller
		ViewSwapRequestsController controller = loader.getController();
		controller.initUser(currentUser);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(swapReqScene);
		window.show();
	}
	
	public void openBookingRequests(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ViewBookingRequests.fxml"));
		Parent bookingReqsParent = loader.load();
		Scene bookingReqsScene = new Scene(bookingReqsParent);
		// Can call methods from scene controller
		ViewBookingRequestsController controller = loader.getController();
		controller.initUser((Admin)currentUser);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(bookingReqsScene);
		window.show();
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
