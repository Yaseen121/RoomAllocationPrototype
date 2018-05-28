/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomallocationprototype;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Yaseen
 */
public class SearchResultsController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	private User currentUser;
	private DBConnect db = new DBConnect();
	private ResultSet res;
	private String roomName;
	private String roomType;
	private int roomCap;
	private Date start;
	private Date end;
	private int openFrom = 7;
	private int openTill = 20;
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<String> columnNames = new ArrayList<String>();
	@FXML
	private ComboBox dates = new ComboBox();
	@FXML
	private TableView<RoomBookings> table = new TableView<RoomBookings>();

	@FXML
	TableColumn<RoomBookings, String> roomNamesCol;
	@FXML
	TableColumn<RoomBookings, String> sevenAMCol;
	@FXML
	TableColumn<RoomBookings, String> eightAMCol;
	@FXML
	TableColumn<RoomBookings, String> nineAMCol;
	@FXML
	TableColumn<RoomBookings, String> tenAMCol;
	@FXML
	TableColumn<RoomBookings, String> elevenAMCol;
	@FXML
	TableColumn<RoomBookings, String> twelvePMCol;
	@FXML
	TableColumn<RoomBookings, String> onePMCol;
	@FXML
	TableColumn<RoomBookings, String> twoPMCol;
	@FXML
	TableColumn<RoomBookings, String> threePMCol;
	@FXML
	TableColumn<RoomBookings, String> fourPMCol;
	@FXML
	TableColumn<RoomBookings, String> fivePMCol;
	@FXML
	TableColumn<RoomBookings, String> sixPMCol;
	@FXML
	TableColumn<RoomBookings, String> sevenPMCol;
	@FXML
	Button request = new Button();
	@FXML
	Button make = new Button();
	@FXML
	Button joinQueue = new Button();
	@FXML
	Button cancel = new Button();
	@FXML
	Button swap = new Button();

	public void requestSwap() throws SQLException {
		ResultSet res = db.getResult("SELECT * FROM bookings WHERE userID=" + currentUser.getID());
		List<String> bookingIDs = new ArrayList<String>();
		if (res.next()) {
			bookingIDs.add(res.getString("bookingID"));
			while (res.next()) {
				bookingIDs.add(res.getString("bookingID"));
			}
			String chosenID = (String) JOptionPane.showInputDialog(null, "What booking would you like to swap",
					"Select your booking", JOptionPane.QUESTION_MESSAGE, null, bookingIDs.toArray(), bookingIDs.get(0));
			try {
				int id = Integer.parseInt(
						JOptionPane.showInputDialog("Enter the ID of the booking you would like to swap with"));
				int yourID = Integer.parseInt(chosenID);
				currentUser.sendSwapRequest(id, yourID);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error : " + e);
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have no bookings to swap.");
		}
	}

	public void requestBooking() {
		try {
			String startTime = JOptionPane.showInputDialog("Enter the desired start time");
			int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the room ID"));
			String date = String.valueOf(dates.getValue().toString());
			currentUser.requestBooking((date + " " + startTime), id);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error: Please enter correct numbers");
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Error: Please select a date");
		}
	}

	public void makeBooking() throws SQLException {
		try {
			String startTime = JOptionPane.showInputDialog("Enter the desired start time");
			int id = Integer.parseInt(JOptionPane.showInputDialog("Enter the room ID"));
			String date = String.valueOf(dates.getValue().toString());
			((Admin) currentUser).makeBooking((date + " " + startTime), id);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error: Please enter correct numbers");
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Error: Please select a date");
		}
		loadTableData();
	}

	public void fillTableHeaders() {
		int dif = openTill - openFrom;
		int hour = openFrom;
		columnNames.add("Room Names (ID)");
		for (int i = 0; i <= dif; i++) {
			if (i != 0) {
				if (hour < 10) {
					columnNames.add("0" + hour + ":00:00");
				} else {
					columnNames.add(hour + ":00:00");
				}
				hour++;
			}
			// System.out.println(columnHeaders[i] + "\t");
		}
	}

	public void loadTableData() throws SQLException {
		table.getItems().clear();
		// JOptionPane.showMessageDialog(null, "The currrent date is " +
		// dates.getValue().toString());
		for (int i = 0; i < rooms.size(); i++) {
			ArrayList<String> booked = new ArrayList<String>();
			String roomN = rooms.get(i).getRoomName() + " (" + rooms.get(i).getRoomID() + ")";
			booked.add(roomN);
			for (int j = 1; j <= openTill - openFrom; j++) {
				String date = dates.getValue().toString();
				res = db.getResult(
						"Select * FROM bookings WHERE date(startTime)=\"" + date + "\" AND time(startTime)=\""
								+ columnNames.get(j) + "\" AND roomID=\"" + rooms.get(i).getRoomID() + "\"");
				if (res.next()) {
					booked.add("Booking ID: " + res.getString("bookingID"));
				} else {
					booked.add(" ");
				}
			}

			table.getItems().add(new RoomBookings(booked));
		}
	}

	public void initUser(User cU, String rN, String rT, String rC, Date s, Date e) {
		currentUser = cU;
		if (cU instanceof Admin) {
			make.setDisable(false);
			request.setDisable(true);
		} else {
			make.setDisable(true);
			request.setDisable(false);
		}
		start = s;
		end = e;
		roomName = rN;
		roomType = rT;
		currentUser = cU;
		if (!rC.equals("")) {
			roomCap = Integer.parseInt(rC);
		}
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar startFrom = Calendar.getInstance();
		startFrom.setTime(start);

		/// End needs to be an hour after start of day
		Calendar endAt = Calendar.getInstance(); // creates calendar
		endAt.setTime(end); // sets calendar time/date
		endAt.add(Calendar.HOUR_OF_DAY, 1); // adds one hour

		for (Date date = startFrom.getTime(); startFrom.before(endAt); startFrom.add(Calendar.DATE,
				1), date = startFrom.getTime()) {
			// Do your job here with `date`.
			dates.getItems().add(formatter.format(date));
		}
		fillRooms();
	}

	public void fillRooms() {
		boolean isAWhere = false;
		try {
			ResultSet myRes;
			String whereThis = " WHERE";
			if (!roomName.equals("")) {
				whereThis = whereThis + " ROOMNAME=\"" + roomName + "\"";
				isAWhere = true;
			}
			if (!roomType.equals("")) {
				whereThis = whereThis + " ROOMTYPE=\"" + roomType + "\"";
				isAWhere = true;
			}
			if (roomCap != 0) {
				whereThis = whereThis + " CAPACITY=\"" + roomCap + "\"";
				isAWhere = true;
			}
			if (isAWhere) {
				myRes = db.getResult("SELECT * FROM rooms" + whereThis);
			} else {
				myRes = db.getResult("SELECT * FROM rooms");
			}
			while (myRes.next()) {
				// System.out.println(myRes.getString("ROOMID") + " " +
				// myRes.getString("ROOMNAME") + " " + myRes.getString("CAPACITY") + " " +
				// myRes.getString("ROOMTYPE") + " " + myRes.getString("DEPT"));
				rooms.add(new Room(Integer.parseInt(myRes.getString("ROOMID")), myRes.getString("ROOMNAME"),
						Integer.parseInt(myRes.getString("CAPACITY")), myRes.getString("DEPT"),
						myRes.getString("ROOMTYPE"), Integer.parseInt(myRes.getString("PERMITTEDUSERS"))));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL Error : " + e);
		}

	}

	public void cancel(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Search.fxml"));
		Parent searchParent = loader.load();
		Scene searchScene = new Scene(searchParent);
		// Can call methods from scene controller
		SearchController controller = loader.getController();
		controller.initUser(currentUser);
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(searchScene);
		window.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		table.setEditable(false);
		fillTableHeaders();
		roomNamesCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("roomName"));
		sevenAMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("sevenAM"));
		eightAMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("eightAM"));
		nineAMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("nineAM"));
		tenAMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("tenAM"));
		elevenAMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("elevenAM"));
		twelvePMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("twelvePM"));
		onePMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("onePM"));
		twoPMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("twoPM"));
		threePMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("threePM"));
		fourPMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("fourPM"));
		fivePMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("fivePM"));
		sixPMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("sixPM"));
		sevenPMCol.setCellValueFactory(new PropertyValueFactory<RoomBookings, String>("sevenPM"));

		// roomNamesCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// sevenAMCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// eightAMCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// nineAMCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// tenAMCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// elevenAMCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// twelvePMCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// onePMCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// twoPMCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// threePMCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// fourPMCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// fivePMCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// sixPMCol.setCellFactory(TextFieldTableCell.forTableColumn());
		// sevenPMCol.setCellFactory(TextFieldTableCell.forTableColumn());

	}

}
