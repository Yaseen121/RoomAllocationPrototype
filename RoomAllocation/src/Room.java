public class Room {

	private int roomID;
	private String roomName;
	private int capacity;
	//Remove this private boolean booked;
	private String dept;
	private String roomType;
	private int permittedUsers;
	
	public Room(int rID, String rN, int c, String d, String rT, int pU) {
		roomID = rID;
		roomName = rN;
		capacity = c;
		dept = d;
		roomType = rT;
		permittedUsers = pU;
	}
	
	public String getRoomName() {
		return roomName;
	}

	public int getRoomID() {
		return roomID;
	}
}