package labb5.simulation.supermarket.events;

import labb5.simulation.general.StartEvent;
import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;



/**
 * StartShopEvent will be called and initialize the ArrivalEvent with
 * the first customer arrival time of 0.
 * 
 * @author Dino Lolic, William Kiwanuka, Stefan Jonsson, Arvid From
 */
public class StartShopEvent extends StartEvent {

	private int customerNumber;
	private StartEvent startEvent;
	
	/**
	 * Constructor for PayLeaveEvent.
	 *
	 * @param simState       Reference variable to the SimState which will be casted
	 *                       as a SupermarketState.
	 * @param eventQueue     Reference variable for EventQueue, to add new events to
	 *                       the queue.
	 * @param time           The absolute time for the event, set to 0.
	 * @param customerNumber Unique number for the customer.
	 */
	public StartShopEvent(SimState simState, EventQueue eventQueue, double time, int customerNumber) {
		super(simState, eventQueue, 0);
		this.customerNumber = customerNumber;
	}
	
	/**
	 * Runs the run method in StartEvent to initialize the ArrivalEvent
	 * and prints out the string in StartEvent.
	 */
	@Override
	public void run(SimState simState) {
		startEvent.run(this.simState);
		startEvent.toString();
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
