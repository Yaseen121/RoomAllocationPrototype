public abstract interface nonAdmin {

	int queueLimit = 5;
	Room[] queues = null;

	void requestBooking();

	void joinQueue();

	void manageQueues();

	void leaveQueue();

}