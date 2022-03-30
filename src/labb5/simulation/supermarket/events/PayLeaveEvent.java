package labb5.simulation.supermarket.events;

import labb5.simulation.general.Event;
import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.state.SupermarketState;
import labb5.simulation.supermarket.state.utilities.FIFO;

/**
 * PayLeaveEvent handles the customers which are placed in the FIFO-queue. Looks
 * if the FIFO-queue is empty or not. Then selects the first Event-element in
 * the FIFO-queue and places it into the eventQueue so that it can be processed.
 * 
 * And later increment Register as well as processed customers and decrement
 * customers in the shop.
 * 
 * @author Dino Lolic, William Kiwanuka, Stefan Jonsson, Arvid From
 */
public class PayLeaveEvent extends Event {

	private int customerNumber;

	/**
	 * Constructor for PayLeaveEvent.
	 *
	 * @param simState       Reference variable to the SimState which will be casted
	 *                       as a SupermarketState.
	 * @param eventQueue     Reference variable for EventQueue, to add new events to
	 *                       the queue.
	 * @param time           The absolute time for the event.
	 * @param customerNumber Unique number for the customer.
	 */
	public PayLeaveEvent(SimState simState, EventQueue eventQueue, double time, int customerNumber) {
		super(simState, eventQueue, time);

		this.customerNumber = customerNumber;

	}

	/**
	 * Checks if the FIFO-queue is empty or not. If the FIFO-queue has any Event
	 * elements, it will select the first element in the queue and add it to
	 * eventQueue.
	 * 
	 * There after, it will remove the first element, increment the register by 1,
	 * decrement customers by 1 and increment processed customers by 1.
	 */
	@Override
	public void run(SimState simState) {

		SupermarketState state = ((SupermarketState) this.simState);
		state.update(this);

		FIFO queue = ((SupermarketState) state).getShopQueue();

		if (!queue.isEmpty()) {
			PayLeaveEvent payLeave = queue.removeFirst();
			state.decrementRegisters();
			payLeave = new PayLeaveEvent(state, eventQueue, time + payLeave.time, payLeave.getCustomerNumber());
			eventQueue.add(payLeave);
		}

		state.incrementRegisters();
		state.decrementCustomersInShop();
		state.incrementProcessedCustomerCount();
	}

	/**
	 * Prepares a string for the view
	 * 
	 * @return a string to represent that the customer is paying.
	 */
	@Override
	public String toString() {
		return "Betalning";
	}

	/**
	 * Fetching the customerNumber.
	 * 
	 * @return the customerNumber of the specific event.
	 */
	public int getCustomerNumber() {
		return this.customerNumber;
	}
}