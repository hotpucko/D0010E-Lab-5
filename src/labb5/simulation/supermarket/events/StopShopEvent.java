package labb5.simulation.supermarket.events;

import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;
import labb5.simulation.general.StopEvent;

/**
 * Specific implementation of the final event to be put in the eventqueue which
 * puts an end to the simulation.
 * 
 * @author Arvid From, Stefan Jonsson, Dino Lolic, William Kiwanuka
 * 
 */
public class StopShopEvent extends StopEvent {

	/**
	 * Constructor which inherits the general parameters of the simulation
	 */
	public StopShopEvent(SimState simState, EventQueue eventQueue, double time) {
		super(simState, eventQueue, time);

	}

	/**
	 * Stops the simulation
	 */
	public void run() {
		super.run(simState);
	}

	/**
	 * toString method that produces relevant data for representing the sim
	 * 
	 * @return string representation of finished sim
	 */
	public String toString() {
		return super.toString();
	}

}
