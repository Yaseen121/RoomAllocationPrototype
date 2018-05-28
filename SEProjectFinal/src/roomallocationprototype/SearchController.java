/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomallocationprototype;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yaseen
 */
public class SearchController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private User currentUser;
    
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private TextField roomName;
    @FXML private TextField roomCapacity;
    @FXML private TextField roomType;
    @FXML private Button search;
    @FXML private Button cancel;
    
    public void initUser(User cU) {
        currentUser = cU;
    }
    
    public void cancel(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainMenu.fxml"));
        Parent mainMenuParent = loader.load();
        Scene mainMenuScene = new Scene(mainMenuParent);
        //Can call methods from scene controller
        MainMenuController controller = loader.getController();
        controller.initUser(currentUser);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);
        window.show();
    }
    
    public void search(ActionEvent event) throws IOException {
    	Date dNow = new Date();
    	Date sDate = Date.from(startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	Date eDate = Date.from(endDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	if (dNow.compareTo(sDate)>0|| dNow.compareTo(eDate)>0) {
			JOptionPane.showMessageDialog(null, "Dates not valid.");
		} else if (eDate.compareTo(sDate)<0) {
			JOptionPane.showMessageDialog(null, "Dates not valid.");
		} else {
			String rN = roomName.getText();
	    	String rT = roomType.getText();
	    	String rC = roomCapacity.getText();
	    	//Open search results
	    	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("SearchResults.fxml"));
	        Parent searchResultsParent = loader.load();
	        Scene searchResultsScene = new Scene(searchResultsParent);
	        //Can call methods from scene controller
	        SearchResultsController controller = loader.getController();
	        controller.initUser(currentUser, rN, rT, rC, sDate, eDate);
	        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	        window.setScene(searchResultsScene);
	        window.show();
		}
    }
    
    public void logout(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent loginParent = loader.load();
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    	
    }

}
