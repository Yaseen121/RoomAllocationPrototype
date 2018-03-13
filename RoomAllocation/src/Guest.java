

public class Guest extends User implements nonAdmin {
	
	public Guest(int id) {
		//Same as staff or student?
		super(id, 1);
	}
	
	@Override
	public void requestBooking() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinQueue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manageQueues() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leaveQueue() {
		// TODO Auto-generated method stub
		
	}
}