package labb5.simulation.general;

import labb5.simulation.supermarket.events.ClosingEvent;
import labb5.simulation.supermarket.events.StartShopEvent;
import labb5.simulation.supermarket.events.StopShopEvent;
import labb5.simulation.supermarket.state.SupermarketState;
import labb5.simulation.supermarket.view.SupermarketView;

/*
 * General class which is responsible for bringing all elements of a supermarket simulation together.
 * Running the only method in the class  with desired parameters results in a printout
 *  of relevant data for how a supermarket would perform in such a situation.  
 * 
 *  @ author Arvid From, Stefan Jonsson, Dino Lolic, William Kiwanuka
 */

public class RunSim {

	private SupermarketView view;
	private SupermarketState simState;
	private EventQueue queue;

	/*
	 * Prints the state of a supermarket throughout a simulation.
	 * 
	 * @ return SimState returns the state of the simulation at the end of said simulation 
	 */
	
	public SupermarketState run(int maxRegisters, int customerMax, double lambda, double kMin, double kMax, double pMin,
			double pMax, double closingTime, int seed) {

		simState = new SupermarketState(seed, customerMax, lambda, kMin, kMax, pMin, pMax);
		view = new SupermarketView(simState);
		queue = new EventQueue();

		Event startEvent = new StartShopEvent(simState, queue);
		Event closingEvent = new ClosingEvent(simState, queue, closingTime);
		Event stopEvent = new StopShopEvent(simState, queue);

		queue.add(startEvent);
		queue.add(closingEvent);
		queue.add(stopEvent);

		do {
			Event x = queue.poll();
			x.run(simState);
		} while (!queue.isEmpty());

		return simState;
	}
}
