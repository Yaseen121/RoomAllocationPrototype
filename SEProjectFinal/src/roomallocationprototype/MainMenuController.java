/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomallocationprototype;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yaseen
 */
public class MainMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private User currentUser;
    @FXML private Button search;
    @FXML private Button manage;
    @FXML private Button admin;
    
    public void initUser(User cU) {
        currentUser = cU;
        //JOptionPane.showMessageDialog(null, cU.getID());
        if (currentUser instanceof Admin) {
    		admin.setVisible(true);
    	} else {
    		admin.setVisible(false);
    	}
    }
    
    public void openManagePage(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Manage.fxml"));
        Parent manageParent = loader.load();
        
        Scene manageScene = new Scene(manageParent);
        //Can call methods from scene controller
        ManageController controller = loader.getController();
        controller.initUser(currentUser);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(manageScene);
        window.show();
    }
    
    public void openAdminPage(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminPage.fxml"));
        Parent adminPageParent = loader.load();
        
        Scene adminPageScene = new Scene(adminPageParent);
        //Can call methods from scene controller
        AdminPageController controller = loader.getController();
        controller.initUser((Admin)currentUser);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(adminPageScene);
        window.show();
    }
    
    public void openSearch(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Search.fxml"));
        Parent searchParent = loader.load();
        
        Scene searchScene = new Scene(searchParent);
        //Can call methods from scene controller
        SearchController controller = loader.getController();
        controller.initUser(currentUser);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(searchScene);
        window.show();
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
