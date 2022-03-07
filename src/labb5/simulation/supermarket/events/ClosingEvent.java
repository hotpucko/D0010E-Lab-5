package labb5.simulation.supermarket.events;

import labb5.simulation.general.Event;
import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.state.SupermarketState;

/**
 * 
 * Closes the shop for further customers
 * 
 * @author Arvid From, Stefan Jonsson, William Kiwanuka, Dino Lolic
 *
 */
public class ClosingEvent extends Event {
	
	/**
	 * Constructor which relates the individual event to the event queue and the
	 * simulator at large Stop event triggers once every customer has left
	 */
	public ClosingEvent(SimState simState, EventQueue eventQueue, double time) {
		super(simState, eventQueue, time);
	}

	/**
	 * Runs the specific ClosingEvent at the given closing time Upon running, the
	 * shop closes, barring future customers from entry.
	 */
	public void run(SimState simState) {
		((SupermarketState) this.simState).update();
		((SupermarketState) this.simState).close();
	}

	/**
	 * Prepares a string for the view
	 * 
	 * @return a string to represent that the shop is closing
	 */
	public String toString() {
		return "Stänger";
	}


		
}
