package labb5.simulation.supermarket.events;

import labb5.simulation.general.Event;
import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.state.SupermarketState;

/**
 * PayLeaveEvent handles the customers which are placed in the FIFO-queue. Looks
 * if the FIFO-queue is empty or not. Then selects the first Event-element in
 * the FIFO-queue and places it into the eventQueue so that it can be processed.
 * 
 * 
 * And later increment Register as well as processed customers and decrement
 * customers in the shop.
 * 
 * @author Dino Lolic, William Kiwanuka, Stefan Jonsson, Arvid From
 * 
 */

public class PayLeaveEvent extends Event {

	/**
	 * Constructor for PayLeaveEvent.
	 *
	 * @param simState       Reference variable to the SimState which will be casted
	 *                       as a SupermarketState.
	 * @param eventQueue     Reference variable for EventQueue, to add new events to
	 *                       the queue.
	 * @param time           The absolute time for the event.
	 * @param customerNumber Unique number for the customer.
	 * 
	 */

	int customerNumber;

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
	 *
	 */

	public void run(Simstate simState) {

		SupermarketState state = (SupermarketState) this.simState;
		state.update();

		FIFO queue = state.getShopQueue();

		if (!queue.isEmpty()) {
			eventQueue.add(queue.getFirst());
			queue.removeFirst();
			state.incrementRegister();
			state.decrementCustomerInShop();
			state.incrementProcessedCustomerount();
		}
	}

	@Override
	public String toString() {
		return "Betalning";
	}

}