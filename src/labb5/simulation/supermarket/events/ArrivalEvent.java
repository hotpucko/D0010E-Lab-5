package labb5.simulation.supermarket.events;

import labb5.simulation.general.Event;
import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.state.SupermarketState;

/**
 * The arrival event is used to create new arrival events based on the current
 * absolute time, and a generated arrival time for the next customer. It creates
 * a new arrival event, and after a certain time it starts a Shopping Event for
 * the current customer.
 * 
 * @author Dino Lolic, Stefan Jonsson, Arvid From, William Kiwanuka
 * 
 */
public class ArrivalEvent extends Event {

	private int customerNumber;

	/**
	 * Constructor for arrival event. Takes time, customer number, current
	 * eventQueue, and a simState object as parameters.
	 * 
	 * @param simState
	 *            From this var the specific supermarketState is acquired.
	 * @param eventQueue
	 *            An eventqueue on which to add new events.
	 * @param time
	 *            Absolute time.
	 * @param customerNum
	 *            Customer number of a specific customer.
	 */
	public ArrivalEvent(SimState simState, EventQueue eventQueue, double time, int customerNum) {
		super(simState, eventQueue, time);
		customerNumber = customerNum;
	}

	/**
	 * Runs the specific ArrivalEvent. Upon running, a new ArrivalEvent is created
	 * at an appropriate time for a new customer. If the store is open and at max
	 * capacity, the rejected tracker in supermarketstate is incremented, else a
	 * ShoppingEvent is created for this customer at an appropriate time.
	 */
	public void run(SimState simState) {

		SupermarketState state = (SupermarketState) this.simState;
		((SupermarketState) this.simState).update(this);

		double absTime = this.getTime();
		// double arrTime = state.generateArrivalTime();
		// double shopTime = state.generateShoppingTime();

		// double nextArrTime = absTime + arrTime;
		// double nextShopTime = absTime + shopTime;

		if (state.isOpen()) {
			ArrivalEvent nextArrivalEvent = new ArrivalEvent(this.simState, this.eventQueue,
					absTime + state.generateArrivalTime(), state.getCustomerFactory().generateCustomer());
			if (state.isMaxCapacity()) {
				state.incrementRejected();
				eventQueue.add(nextArrivalEvent);
			} else {
				state.incrementCustomersInShop();
				ShoppingEvent goShopping = new ShoppingEvent(this.simState, this.eventQueue,
						absTime + state.generateShoppingTime(), customerNumber);

				this.eventQueue.add(goShopping);
				this.eventQueue.add(nextArrivalEvent);

			}
		}
	}

	/**
	 * Getter for customerNumber.
	 * 
	 * @return customerNumber
	 */
	public int getCustomerNumber() {
		return this.customerNumber;
	}

	/**
	 * Prepares a string for the view
	 * 
	 * @return a string to represent that the customer have arrived to the shop.
	 */
	@Override
	public String toString() {
		return ("Ankomst");
	}
}
