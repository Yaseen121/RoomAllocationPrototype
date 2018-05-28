package roomallocationprototype;

import java.sql.Date;

public class Booking {

	private int bookingID;
	private int roomID;
	private int userID;
	private String startTime;
	private boolean openBooking;
	
	public Booking(int iD, int r, int u, String sT, boolean oB) {
		bookingID = iD;
		roomID = r;
		userID = u;
		startTime = sT;
		openBooking = oB;
	}
	
	public String getDetails() {
		return bookingID + " " + roomID + " " + userID + " " + startTime + " " + openBooking;
	}
	
	public String getDetails2() {
		return "Booking ID: " + bookingID + " \t Room ID: " + roomID +  " \t User ID: " + userID + " \t Start Time: " + startTime + " \t Open: " + openBooking;
	}
	
	public int getbookingID() {
		return bookingID;
	}
	
	public int getRoomID() {
		return roomID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public int getOpen() {
		if (openBooking) {
			return 1;
		} else {
			return 0;
		}
	}
}