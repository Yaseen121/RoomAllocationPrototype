import java.sql.Date;

public class Booking {

	private int bookingID;
	private Room room;
	private User user;
	private Date startTime;
	private boolean openBooking;
	
	public Booking(int iD, Room r, User u, Date sT, boolean oB) {
		bookingID = iD;
		room = r;
		user = u;
		startTime = sT;
		openBooking = oB;
	}
}