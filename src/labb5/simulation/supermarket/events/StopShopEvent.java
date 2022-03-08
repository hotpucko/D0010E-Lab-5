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
int b=2;
	private StopEvent myStopEvent;

	/**
	 * Constructor which inherits the general parameters of the simulation
	 */
	public StopShopEvent(SimState simState, EventQueue eventQueue) {
		super(simState, eventQueue, 99999);

	}

	/**
	 * Stops the simulation
	 */
	public void run() {
		this.myStopEvent.run(simState);
	}

	/**
	 * toString method that produces relevant data for representing the sim
	 * 
	 * @return string representation of finished sim
	 */
	public String toString() {
		return this.myStopEvent.toString();
	}

}
