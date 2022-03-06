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
 * @param simState
 * @param eventQueue
 * @param time,           a time when the event will occur.
 * @param customerNumber, a certain customer.
 * 
 */
public class ArrivalEvent extends Event {

	int customerNumber;

	/**
	 * Constructor for arrival event. Takes time, customer number, current
	 * eventQueue, and a simState object as parameters.
	 * 
	 * @param simState
	 * @param eventQueue
	 * @param time        Absolute time.
	 * @param customerNum Customer number of a specific customer.
	 * 
	 * @author William Kiwanuka, Dino Lolic, Stefan Jonsson, Arvid From
	 */
	public ArrivalEvent(SimState simState, EventQueue eventQueue, double time, int customerNum) {
		super(simState, eventQueue, time);
		customerNumber = customerNum;
	}

	public void run(SimState simState) {

		SupermarketState state = (SupermarketState) this.simState;
		state.update();

		double absTime = this.getTime();
		double arrTime = state.generateArrivalTime();
		double shopTime = state.generateShoppingTime();

		double nextArrTime = absTime + arrTime;
		double nextShopTime = absTime + shopTime;

		if (state.isOpen()) {
			ArrivalEvent nextArrivalEvent = new ArrivalEvent(this.simState, this.eventQueue, nextTime,
					state.getCustomerFactory().generateCustomer());
			if (state.isMaxCapacity()) {
				state.incrementRejected();
				EventQueue.add(nextArrivalEvent);
			} else {
				state.incrementCustomersInShop();
				ShoppingEvent goShopping = new ShoppingEvent(this.simState, this.eventQueue, nextShopTime,
						customerNumber);

				EventQueue.add(nextArrivalEvent);
				EventQueue.add(goShopping);
			}
		}
	}

	@Override
	public String toString() {
		return ("Ankomst");
	}
}
