package labb5.simulation.general;

import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.events.ArrivalEvent;
import labb5.simulation.supermarket.state.SupermarketState;
import labb5.simulation.supermarket.state.utilities.CustomerFactory;

/**
 * This class marks the start of the simulation. The first customer is generated
 * and an ArrivalEvent is created.
 * 
 * @author Dino Lolic, Stefan Jonsson, William Kiwanuka, Arvid From
 */

public class StartEvent extends Event {

	/**
	 * Constructor.
	 * 
	 * @param simState
	 *            The simstate object on which to run the event
	 * @param eventQueue
	 *            The eventqueue on which to add the firstArrival
	 * @param time
	 *            The current absolute time.
	 */
	public StartEvent(SimState simState, EventQueue eventQueue, double time) {
		super(simState, eventQueue, time);
	}

	/**
	 * Runs the startevent for the simulation. Generated an arrivalevent for the
	 * first arrival. The following arrivalevent takes care of subsequent arrival
	 * events.
	 * 
	 * @param simState
	 *            The simstate object for which this event applies to.
	 */
	@Override
	public void run(SimState simState) {
		SupermarketState state = (SupermarketState) this.simState;
		state.run();
		((SupermarketState) this.simState).open();
		((SupermarketState) this.simState).update(this);

		ArrivalEvent firstArrival = new ArrivalEvent(simState, eventQueue, time,
				state.getCustomerFactory().generateCustomer());
		this.eventQueue.add(firstArrival);
	}

	@Override
	public String toString() {
		return "Start";
	}
}