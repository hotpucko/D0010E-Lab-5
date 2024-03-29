package labb5.simulation.supermarket.events;

import labb5.simulation.general.StartEvent;
import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;

/**
 * StartShopEvent will be called and initialize the ArrivalEvent with the first
 * customer arrival time of 0.
 * 
 * @author Dino Lolic, William Kiwanuka, Stefan Jonsson, Arvid From
 */
public class StartShopEvent extends StartEvent {

	/**
	 * Constructor for StartShopEvent.
	 *
	 * @param simState
	 *            Reference variable to the SimState which will be casted as a
	 *            SupermarketState.
	 * @param eventQueue
	 *            Reference variable for EventQueue, to add new events to the queue.
	 */
	public StartShopEvent(SimState simState, EventQueue eventQueue) {
		super(simState, eventQueue, 0.00);
	}

	/**
	 * Runs the run method in StartEvent to initialize the ArrivalEvent and prints
	 * out the string in StartEvent.
	 */
	@Override
	public void run(SimState simState) {
		super.run(simState);
	}

	/**
	 * Prepares a string for the view
	 * 
	 * @return a string to represent that the shop is opening.
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
