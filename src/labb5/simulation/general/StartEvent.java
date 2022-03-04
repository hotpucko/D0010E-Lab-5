package labb5.simulation.general;

import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.events.ArrivalEvent;
import labb5.simulation.supermarket.state.SupermarketState;
import labb5.simulation.supermarket.state.utilities.CustomerFactory;

public class StartEvent extends Event {

	/**
	 * This class marks the start of the simulation. The first
	 * customer is generated and an ArrivalEvent is created.
	 * 
	 * @param simState
	 * @param eventQueue
	 * @param time
	 * 
	 * @author Dino Lolic, Stefan Jonsson, William Kiwanuka, Arvid From
	 */
	public StartEvent(SimState simState, EventQueue eventQueue, double time) {
		super(simState, eventQueue, time);
	}

	@Override
	void run(SimState simState) {
		SupermarketState state = (SupermarketState) this.simState;
		state.update();
		
		ArrivalEvent firstArrival = new ArrivalEvent(simState, eventQueue, time, state.getCustomerFactory().generateCustomer());
		eventQueue.add(firstArrival);
		// Skapa ett arrival event
		
	}

	@Override
	public String toString() {
		"Start"
	}
}
