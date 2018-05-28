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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yaseen
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField userID;
    @FXML
    private PasswordField password;
    private DBConnect db;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            db = new DBConnect();
            //JOptionPane.showMessageDialog(null, "Connected to database");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not connect to database");
        }
    }

    public void login(ActionEvent event) throws IOException{
        //JOptionPane.showMessageDialog(null, userID.getText() + " " + password.getText());
        boolean loggedIn = false;
        try {
            ResultSet myRes = db.getResult("SELECT * FROM users");
            while (myRes.next()) {
                if (myRes.getString("USERID").equals(userID.getText()) && myRes.getString("PASSWORD").equals(password.getText())) {
                    //Login successful
                    loggedIn = true;
                    //MainMenu menu = new MainMenu();
                    String userType = myRes.getString("USERTYPE");
                    User thisUser;
                    if (userType.equals("Admin")) {
                        thisUser = new Admin(Integer.parseInt(userID.getText()));
                    } else if (userType.equals("Staff")) {
                        thisUser = new Staff(Integer.parseInt(userID.getText()));
                    } else if (userType.equals("Student")) {
                        thisUser = new Student(Integer.parseInt(userID.getText()));
                    } else {
                        thisUser = new Guest(Integer.parseInt(userID.getText()));
                    }
                   if (loggedIn){
                       FXMLLoader loader = new FXMLLoader();
                       loader.setLocation(getClass().getResource("MainMenu.fxml"));
                       Parent mainMenuParent = loader.load();
                       
                       Scene mainMenuScene = new Scene(mainMenuParent);
                       //Can call methods from scene controller
                       MainMenuController controller = loader.getController();
                       controller.initUser(thisUser);
                       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                       window.setScene(mainMenuScene);
                       window.show();
                   }
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

}
