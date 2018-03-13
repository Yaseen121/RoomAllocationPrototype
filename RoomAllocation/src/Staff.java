

public class Staff extends User implements nonAdmin {

	public Staff(int id) {
		super(id, 5);
	}
	public void requestBookingExt(int number) {
		// TODO - implement Staff.requestBookingExt
		throw new UnsupportedOperationException();
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