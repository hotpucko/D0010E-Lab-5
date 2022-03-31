package labb5.simulation.general;

import labb5.simulation.general.EventQueue;
import labb5.simulation.general.SimState;

/**
 * The abstract base class for all general state contexts. The absolute time
 * from the simulation start when the event should occur is stored in this
 * class, along with methods for run, getTime and compareTo that compares the
 * time it should occur with the current Time.
 * 
 * @author Dino Lolic, Stefan Jonsson, William Kiwanuka, Arvid From
 * 
 */
public abstract class Event {

	protected double time;
	protected EventQueue eventQueue;
	protected SimState simState;

	/**
	 * Constructor for the abstract Event class
	 * 
	 * @param simState
	 * @param eventQueue
	 * @param time
	 */
	public Event(SimState simState, EventQueue eventQueue, double time) {

		this.time = time;
		this.simState = simState;
		this.eventQueue = eventQueue;

	}

	/**
	 * Performs appropriate changes to the specific state. Runs next event(s).
	 * 
	 * @param simState
	 *            The current simState object that is running.
	 */
	public abstract void run(SimState simState);

	/**
	 * Getter for this objects time
	 * 
	 * @return The absolute time.
	 */
	public double getTime() {
		return this.time;
	}

	/**
	 * A method used for handling string outputs. Is later used for relevant
	 * printouts from the events.
	 * 
	 * @return String outputs for Arrival time, start time, stop time, closing time,
	 *         how much time was spent shopping and how much time was spent to Pay
	 *         and leave.
	 * 
	 */
	abstract public String toString();

}