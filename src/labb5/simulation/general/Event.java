package labb5.simulation.general;

import general.Event;
import general.EventQueue;
import general.SimState;
import general.Simstate;

public abstract class Event {

	protected double time;
	
	EventQueue eventQueue;
	SimState simState;
	
	public Event(Simstate simState, EventQueue eventQueue, double time) {
		
		this.time = time;
		this.simState = simState;
		this.eventQueue = eventQueue;
		
	}
	
	
	abstract void run(SimState simState);
	
	double getTime() {
		return this.time;
	}
	
	abstract public String toString();
	
	abstract int compareTo(Event event);


}
