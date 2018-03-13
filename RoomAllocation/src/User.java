import java.util.ArrayList;


import java.sql.*;
public abstract class User {

	private int userID;
	private ArrayList<Booking> bookingList;
	private int maxCount;
	private ArrayList<Booking> inviteList;
	
	//Added 
	//private Connection myCon;
	
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

	public void cancelBooking() {
		// TODO - implement User.cancelBooking
		throw new UnsupportedOperationException();
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

	public void sendSwapRequest() {
		// TODO - implement User.sendSwapRequest
		throw new UnsupportedOperationException();
	}

	public void replySwapRequest() {
		// TODO - implement User.replySwapRequest
		throw new UnsupportedOperationException();
	}

	public void viewSwapRequest() {
		// TODO - implement User.viewSwapRequest
		throw new UnsupportedOperationException();
	}

	public void searchForRooms() {
		// TODO - implement User.searchForRooms
		throw new UnsupportedOperationException();
	}

}