package roomallocationprototype;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.*;
public abstract class User {

	private int userID;
	private ArrayList<Booking> bookingList;
	private int maxCount;
	private ArrayList<Booking> inviteList;
	private DBConnect db = new DBConnect();
	
	//Added 
	//private Connection myCon;
	
	public int getID () {
		return userID;
	}
	
	public void requestBooking(String startDateTime, int roomid) {
		db.execute("INSERT INTO bookingrequests (ID, ROOMID, USERID, startTime, OPEN) VALUES (NULL, \'" + roomid + "\', \'"+this.getID() + "\', \'"+startDateTime+"\', \'1\')");
	}
		
	public User(int id, int mC) {
		userID = id;
		maxCount = mC;
		//myCon = myC;
	}
	
	//Removed login method
	
	public void logout() {
		// TODO - implement User.logout
		throw new UnsupportedOperationException();
	}

	public void cancelBooking(Booking bk) {
		db.execute("DELETE FROM bookings WHERE bookingID="+bk.getbookingID());
	}

	public void viewBookings() {
		// TODO - implement User.viewBookings
		throw new UnsupportedOperationException();
	}

	public void requestInvite() {
		// TODO - implement User.requestInvite
		throw new UnsupportedOperationException();
	}

	public void viewInvites() {
		// TODO - implement User.viewInvites
		throw new UnsupportedOperationException();
	}

	public void respondToInvite() {
		// TODO - implement User.respondToInvite
		throw new UnsupportedOperationException();
	}

	public void sendInvites() {
		// TODO - implement User.sendInvites
		throw new UnsupportedOperationException();
	}

	public void sendSwapRequest(int requestedBookingID, int yourBookingID) throws SQLException {
		if (requestedBookingID!=-1 ) {
			db.execute("INSERT INTO swaprequests (ID, REQUESTERBOOKINGID, REQUESTEDBOOKINGID) VALUES (NULL, \'"+yourBookingID+"\', \'"+requestedBookingID+"\')");
		} else {
			JOptionPane.showMessageDialog(null, "There was an error ");
		}
		
	}

	public void replySwapRequest(SwapRequest acceptedSwap) throws SQLException {
		int yourNewRoom = acceptedSwap.getRequesterRoomID();
		int thierNewRoom = acceptedSwap.getRequestedBookingID();
                int yourBooking = acceptedSwap.getRequestedBookingID();
                int theirBooking = acceptedSwap.getRequesterBookingID();
		db.execute("UPDATE bookings SET roomID=" + yourNewRoom + " WHERE bookingID=" + yourBooking);
		db.execute("UPDATE bookings SET roomID=" + thierNewRoom + " WHERE bookingID=" + theirBooking);
		//DELETE ALL Requests with either booking id
		db.execute("DELETE FROM swaprequests WHERE REQUESTERBOOKINGID="+acceptedSwap.getRequesterBookingID());
                db.execute("DELETE FROM swaprequests WHERE REQUESTEDBOOKINGID="+acceptedSwap.getRequestedBookingID());
	}

	//View Swap request changed from method to page

	public void searchForRooms() {
		// TODO - implement User.searchForRooms
		throw new UnsupportedOperationException();
	}
	
	public DBConnect getDB () {
		return db;
	}

}