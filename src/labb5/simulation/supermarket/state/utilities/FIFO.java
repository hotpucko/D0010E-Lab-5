package labb5.simulation.supermarket.state.utilities;

import java.util.ArrayList;

import labb5.simulation.supermarket.events.PayLeaveEvent;


/*
 * Describes the queue that appears when there are more people
 *  waiting to make their purchases than  there are available registers
 *  in the supermarket simulator
 *  
 *  All the  registers share a common queue
 *  
 * @ author Arvid From, Stefan Jonsson, Dino Lolic, William Kiwanuka
 * 
 */
public class FIFO {

	private ArrayList<PayLeaveEvent> shopQueue = new ArrayList<PayLeaveEvent>();

	/*
	 * Adds a new event representing the future payment and subsequent exit of a
	 * customer to the queue.
	 */
	public void add(PayLeaveEvent e) {
		
		shopQueue.add(e);
	}

	/*
	 * Represents the customer finally paying and leaving both the queue and the
	 * supermarket.
	 * 
	 * @return PayLeaveEvent if there is a customer able to pay and leave, the
	 * method returns said event. Elsewise it returns null.
	 */
	public PayLeaveEvent removeFirst() {
		if (isEmpty() == true) {
			return null;
		} else {
			PayLeaveEvent x = shopQueue.get(0);
			shopQueue.remove(0);
			return x;
		}
	}

	/*
	 * Checks if the queue is empty.
	 * 
	 * @return true if the queue is empty, elsewise false.
	 */
	public boolean isEmpty() {
		return shopQueue.isEmpty();
	}

	/*
	 * Outputs the current amount of people in the queue
	 * 
	 * @return int the amount of people in the queue
	 */
	public int getSize() {
		return shopQueue.size();
	}

	/*
	 * Outputs String to represent the queues size and which customers are present
	 * in it.
	 * 
	 * @ return String, size of queue and customers in queue
	 */
	public String toString() {
		String x = String.valueOf(shopQueue.get(0).getCustomerNumber());	
		for (int i =1; i == shopQueue.size(); i++){
			x += ", "  + shopQueue.get(i); 
		}
		return  "["+ x +"]";
	}
}

