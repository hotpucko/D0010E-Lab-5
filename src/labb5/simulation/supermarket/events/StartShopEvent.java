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

	private StartEvent startEvent;
	
	/**
	 * Constructor for StartShopEvent.
	 *
	 * @param simState       Reference variable to the SimState which will be casted
	 *                       as a SupermarketState.
	 * @param eventQueue     Reference variable for EventQueue, to add new events to
	 *                       the queue.
	 * @param time           The absolute time for the event, set to 0.
	 */
	public StartShopEvent(SimState simState, EventQueue eventQueue) {
		super(simState, eventQueue, 0);
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
}
