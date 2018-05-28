package roomallocationprototype;


import java.sql.Connection;

import javax.swing.JOptionPane;

public class Admin extends User {
	
	public Admin(int id) {
		super(id, -1);
	}
	
	public void addRoom(String rN, int c, String d, String rT, int pU) {
		this.getDB().execute("INSERT INTO rooms (ROOMID, ROOMNAME, ROOMTYPE, CAPACITY, DEPT, PERMITTEDUSERS) VALUES (NULL, \'" + rN  + "\', \'" + rT  + "\', \'" + c  + "\', \'" + d + "\', \'" + pU + "\')");
		//JOptionPane.showMessageDialog(null, "INSERT INTO \'rooms\' (\'ROOMID\', \'ROOMNAME\', \'ROOMTYPE\', \'CAPACITY\', \'DEPT\', \'PERMITTEDUSERS\') VALUES (NULL, \'" + rN  + "\', \'" + rT  + "\', \'" + c  + "\', \'" + d + "\', \'" + pU + "\')");
	}

	public void editRoom() {
		// TODO - implement Admin.editRoom
		throw new UnsupportedOperationException();
	}

	public void removeRoom(int id) {
		this.getDB().execute("DELETE FROM rooms WHERE ROOMID="+id);
	}

	public void addUser(String p, String uT) {
		this.getDB().execute("INSERT INTO users (USERID, PASSWORD, USERTYPE) VALUES (NULL, \'" + p + "\', \'" + uT + "\')");
	}

	public void editUser() {
		// TODO - implement Admin.editUser
		throw new UnsupportedOperationException();
	}

	public void removeUser(int id) {
		this.getDB().execute("DELETE FROM users WHERE USERID="+id);
	}

	public void bookRoom() {
		// TODO - implement Admin.bookRoom
		throw new UnsupportedOperationException();
	}

	public void overrideBooking() {
		// TODO - implement Admin.overrideBooking
		throw new UnsupportedOperationException();
	}

	public void approveBooking(Booking bk) {
		this.getDB().execute("DELETE FROM bookingrequests WHERE ID="+bk.getbookingID());
		int rID = bk.getRoomID();
		int uID = bk.getUserID();
		String time = bk.getStartTime();
		int open = bk.getOpen();
		this.getDB().execute("INSERT INTO bookings (bookingID, roomID, userID, startTime, OPEN, Queue1, Queue2, Queue3, Queue4, Queue5) VALUES (NULL, \'"+rID+"\', \'"+uID+"\', \'"+time+"\', \'"+open+"\', NULL, NULL, NULL, NULL, NULL)");
	}
	
	public void declineBooking(Booking bk) {
		this.getDB().execute("DELETE FROM bookingrequests WHERE ID="+bk.getbookingID());
	}

	public void approveExtRequest() {
		// TODO - implement Admin.approveExtRequest
		throw new UnsupportedOperationException();
	}

	public void setWorkingHours() {
		// TODO - implement Admin.setWorkingHours
		throw new UnsupportedOperationException();
	}

	public void getWorkingHours() {
		// TODO - implement Admin.getWorkingHours
		throw new UnsupportedOperationException();
	}
	
	public void makeBooking(String startDateTime, int roomid) {
		this.getDB().execute("INSERT INTO bookings (bookingID, ROOMID, USERID, startTime, OPEN) VALUES (NULL, \'" + roomid + "\', \'"+this.getID() + "\', \'"+startDateTime+"\', \'1\')");
	}

}

