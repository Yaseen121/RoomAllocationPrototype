import java.sql.Connection;

public class Admin extends User {
	
	public Admin(int id) {
		super(id, -1);
	}
	
	public void addRoom(int rID, String rN, int c, String d, String rT, int pU) {
		String stmt = "INSERT INTO rooms (roomID, roomName, capacity, department, roomType, permittedUsers) "
				+ "VALUES ("+rID + ", " + rN + ", " + c + ", " + d + ", " + rT + ", " + pU +")";
		DBConnect.insert(stmt);
	}

	public void editRoom() {
		// TODO - implement Admin.editRoom
		throw new UnsupportedOperationException();
	}

	public void removeRoom() {
		// TODO - implement Admin.removeRoom
		throw new UnsupportedOperationException();
	}

	public void addUser() {
		// TODO - implement Admin.addUser
		throw new UnsupportedOperationException();
	}

	public void editUser() {
		// TODO - implement Admin.editUser
		throw new UnsupportedOperationException();
	}

	public void removeUser() {
		// TODO - implement Admin.removeUser
		throw new UnsupportedOperationException();
	}

	public void bookRoom() {
		// TODO - implement Admin.bookRoom
		throw new UnsupportedOperationException();
	}

	public void overrideBooking() {
		// TODO - implement Admin.overrideBooking
		throw new UnsupportedOperationException();
	}

	public void approveBooking() {
		// TODO - implement Admin.approveBooking
		throw new UnsupportedOperationException();
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

}