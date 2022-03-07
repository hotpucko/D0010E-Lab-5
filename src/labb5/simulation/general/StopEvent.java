package labb5.simulation.general;

import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.state.SupermarketState;

public class StopEvent extends Event {

	/**
	 * This class represents the event of the simulation being fully completed.
	 * 
	 * @param simState
	 * @param eventQueue
	 * @param time
	 * 
	 * @author Dino Lolic, William Kiwanuka, Stefan Jonsson, Arvid From
	 */
	public StopEvent(SimState simState, EventQueue eventQueue, double time) {
		super(simState, eventQueue, 999);
	}

	@Override
	public String toString() {
		return "Stop";
	}

	@Override
	public void run(SimState simState) {
		SupermarketState state = (SupermarketState) this.simState;
		state.emergencyBreak(); // Signal that the simulation is done.
		state.update();
	}
}
