package roomallocationprototype;

import javax.swing.JOptionPane;

public class Student extends NonAdmin {

	public Student(int id) {
		super(id , 1);
	}
	
	@Override
	public void requestBooking(String startDateTime, int roomid) {
		this.getDB().execute("INSERT INTO bookingrequests (ID, ROOMID, USERID, startTime, OPEN) VALUES (NULL, \'" + roomid + "\', \'"+this.getID() + "\', \'"+startDateTime+"\', \'1\')");
	}

	public void joinQueue() {
		// TODO Auto-generated method stub
		
	}

	public void manageQueues() {
		// TODO Auto-generated method stub
		
	}

	public void leaveQueue() {
		// TODO Auto-generated method stub
		
	}
}