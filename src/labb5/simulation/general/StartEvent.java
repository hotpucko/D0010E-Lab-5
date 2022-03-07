package labb5.simulation.general;

import general.EventQueue;
import general.SimState;

public class StartEvent extends Event {

	public StartEvent(SimState simState, EventQueue eventQueue, double time) {
		super(simState, eventQueue, time);
	}

}
