package roomallocationprototype;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SwapRequest {
	private int id;
	private int requesterBookingID;
	private int requestedBookingID;
	private DBConnect db;
	private ResultSet res;

	public SwapRequest(int id, int rrbid, int rdbid) {
		this.id = id;
		requesterBookingID = rrbid;
		requestedBookingID = rdbid;
		db = new DBConnect();
	}

	public int getID() {
		return id;
	}

	public int getRequesterBookingID() {
		return requesterBookingID;
	}

	public int getRequesterUserID() throws SQLException {
		res = db.getResult("SELECT * FROM bookings WHERE bookingID="+requesterBookingID);
		if (res.next()) {
			return res.getInt("userID");
		}
		return -1;
	}

	public int getRequesterRoomID() throws SQLException {
		res = db.getResult("SELECT * FROM bookings WHERE bookingID="+requesterBookingID);
		if (res.next()) {
			return res.getInt("roomID");
		}
		return -1;
	}

	public int getRequestedBookingID() {
		return requestedBookingID;
	}

	public int getRequestedUserID() throws SQLException {
		res = db.getResult("SELECT * FROM bookings WHERE bookingID="+requestedBookingID);
		if (res.next()) {
			return res.getInt("userID");
		}
		return -1;
	}

	public int getRequestedRoomID() throws SQLException {
		res = db.getResult("SELECT * FROM bookings WHERE bookingID="+requestedBookingID);
		if (res.next()) {
			return res.getInt("roomID");
		}
		return -1;
	}
	
	public String getDetails() {
		return id + " " + requesterBookingID + " " + requestedBookingID;
	}
	
	public String getDetails2() {
		return "Swap ID: "+id + " \t Requester Booking ID: " + requesterBookingID + " \t Requested Booking ID: " + requestedBookingID;
	}
}
