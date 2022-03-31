package labb5.simulation.general;

import labb5.simulation.supermarket.events.ClosingEvent;
import labb5.simulation.supermarket.events.StartShopEvent;
import labb5.simulation.supermarket.events.StopShopEvent;
import labb5.simulation.supermarket.state.SupermarketState;
import labb5.simulation.supermarket.view.SupermarketView;

/**
 * General class which is responsible for bringing all elements of a supermarket
 * simulation together. Running the only method in the class with desired
 * parameters results in a printout of relevant data for how a supermarket would
 * perform in such a situation.
 * 
 * @author Arvid From, Stefan Jonsson, Dino Lolic, William Kiwanuka
 */
public class RunSim {

	private SupermarketState simState;
	private EventQueue queue;

	/**
	 * 
	 * @param customerMax  The maximum amount of customers allowed within the
	 *                     supermarket
	 * @param lambda       1/lambda is the rate of customers entering the
	 *                     supermarket with 1 representing a single unit of time
	 * @param kMin         The minimum payment time for a single customer
	 * @param kMax         The maximum payment time for a single customer
	 * @param pMin         The minimum shopping time for a single customer
	 * @param pMax         The maximum shopping time for a single customer
	 * @param seed         The seed that always executes the simulation in exactly
	 *                     the same way given a randomiser
	 * @param maxRegisters The amount of Registers available throughout the
	 *                     simulation
	 */
	public RunSim(int customerMax, double lambda, double kMin, double kMax, double pMin, double pMax, int seed,
			int maxRegisters) {
		simState = new SupermarketState(seed, customerMax, lambda, kMin, kMax, pMin, pMax, maxRegisters);
		queue = new EventQueue();

	}

	/**
	 * Returns the simstate for external program functionality
	 * 
	 * @return SupermarketState, the state
	 */

	public SupermarketState getState() {
		return this.simState;
	}

	/**
	 * Runs the simulation. Individual events affect the state of the simulation
	 * according to given parameters in runSim() as well as rules for how the events
	 * behave(described in said events) Changes may be observed if one adds a view
	 * to the implementation.
	 * 
	 * @param closingTime Time at which a simulation is meant to close its generic
	 *                    doors so to speak,NOT to stop the simulation entirely.
	 */
	public void run(double closingTime) {

		Event startEvent = new StartShopEvent(simState, queue);
		Event closingEvent = new ClosingEvent(simState, queue, closingTime);
		Event stopEvent = new StopShopEvent(simState, queue, 999.00);

		queue.add(startEvent);
		queue.add(closingEvent);
		queue.add(stopEvent);

		do {
			Event x = queue.poll();
			x.run(simState);
		} while (!queue.isEmpty());
	}
}
