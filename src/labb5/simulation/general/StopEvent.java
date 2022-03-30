package labb5.simulation.general;

import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.state.SupermarketState;

/**
 * This class represents the event of the simulation being fully completed.
 * 
 * @author Dino Lolic, William Kiwanuka, Stefan Jonsson, Arvid From
 */
public class StopEvent extends Event {

	/**
	 * The constructor for the simulation stop Note that time is set to 999.
	 * 
	 * @param simState   The simulation to stop
	 * @param eventQueue Needed for general Event class
	 * @param time       The current absolute time, set to 999.
	 */
	public StopEvent(SimState simState, EventQueue eventQueue, double time) {
		super(simState, eventQueue, 999);
	}

	/**
	 * Print stop.
	 */
	@Override
	public String toString() {
		return "Stop";
	}

	/**
	 * Runs the stopEvent, which essentially stops the simulation.
	 */
	@Override
	public void run(SimState simState) {
		SupermarketState state = (SupermarketState) this.simState;
		state.emergencyBreak(); // Signal that the simulation is done.
		((SupermarketState) this.simState).update(this);
	}
}