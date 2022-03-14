package labb5.simulation.supermarket.events;

import labb5.simulation.general.Event;

import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.state.SupermarketState;
import labb5.simulation.supermarket.state.utilities.FIFO;

/**
 * ShoppingEvent, the simulation of a customer selecting goods to purchase. Once
 * finished, (time is given by superMarketState), the customer is put into a
 * FIFO queue if all registers are full, if there are free registers, close a
 * register and create a payLeaveEvent to the eventQueue.
 * 
 * @author Dino Lolic, William Kiwanuka, Stefan Jonsson, Arvid From
 *
 */
public class ShoppingEvent extends Event {

	private int localCustNum;

	/**
	 * Constructor for shoppingEvents.
	 * 
	 * @param simState
	 *            From this var the specific supermarketState is acquired.
	 * @param eventQueue
	 *            An eventqueue on which to add new events.
	 * @param time
	 *            The current absolute time.
	 * @param customerNumber
	 *            A specific customer who does these acts.
	 */
	public ShoppingEvent(SimState simState, EventQueue eventQueue, double time, int customerNumber) {
		super(simState, eventQueue, time);
		localCustNum = customerNumber;
	}

	/**
	 * Go to pay once a specific time (given from the specific state) has been
	 * reached, If there are available queues, make payment happen immediately and
	 * reserve a register for this customer, else add the Pay event to a FIFO queue
	 * to reach the registers.
	 */
	public void run(SimState simState) {
		SupermarketState state = (SupermarketState) this.simState;
		((SupermarketState) this.simState).update(this);

		double payLeaveTime = state.generatePayLeaveTime();

		double goPayTime = payLeaveTime + time;

		PayLeaveEvent payLeave = new PayLeaveEvent(simState, eventQueue, goPayTime, localCustNum);

		if (state.getFreeRegisters() > 0) {
			eventQueue.add(payLeave);
			state.decrementRegisters();

		} else {

			state.getShopQueue().add(payLeave);
			state.IncrementCustomersQueued();
		}
	}

	/**
	 * Fetching the customerNumber.
	 * 
	 * @return the customerNumber of the specific event.
	 */
	public int getCustomerNumber() {
		return this.localCustNum;
	}

	/**
	 * Prepares a string for the view
	 * 
	 * @return a string to represent that the customer is picks goods.
	 */
	@Override
	public String toString() {
		return "Plock";
	}
}