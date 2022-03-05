package labb5.simulation.general;
import labb5.simulation.supermarket.events.ClosingEvent;
import labb5.simulation.supermarket.events.StartShopEvent;
import labb5.simulation.supermarket.events.StopShopEvent;
import labb5.simulation.supermarket.state.SupermarketState;
import labb5.simulation.supermarket.view.SupermarketView;

public class RunSim {
	
 private SupermarketView view;
 private SupermarketState simState;
 private EventQueue queue;
 
 public SupermarketState run(int maxRegisters, int customerMax, double lambda , double kMin, double kMax, double pMin, double pMax, double closingTime, int seed) {
	 
	 simState = new SupermarketState(seed, customerMax, lambda, kMin, kMax, pMin,pMax);
	 view = new SupermarketView(simState);
	 queue = new EventQueue();
	 
	 Event startEvent = new StartShopEvent(simState, queue);
	 Event closingEvent = new ClosingEvent(simState, queue, closingTime);
	 Event stopEvent = new StopShopEvent(simState, queue);
	 
	 queue.add(startEvent);
	 queue.add(closingEvent);
	 queue.add(stopEvent);
	 
	 
 do {
	 Event x =queue.poll();
	 x.run(simState);
 }while (!queue.isEmpty());
 
 return simState;
}
}
