package labb5.simulation.general;

import general.EventQueue;
import general.SimState;

public class StopEvent extends Event {

	public 	StopEvent(SimState simState, EventQueue eventQueue, double time) {
		super(simState, eventQueue, time);
	}

}
