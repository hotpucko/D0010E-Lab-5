package labb5.simulation.general;

import java.util.ArrayList;

import labb5.simulation.general.Event;

/**
 * General Event queue for running events
 * 
 * @author Arvid From, Stefan Jonsson, Dino Lolic, William Kiwanuka
 */
public class EventQueue {

	SortedQueue eventQueue = new SortedQueue();

	// SortedSequence<Event> eventQueue = new SortedSequence<Event>();

	/**
	 * adds new event to the waiting queue
	 * 
	 * @param e Event 
	 *            from the specific simulator
	 */
	public void add(Event e) {
		eventQueue.add(e);
	}

	/**
	 * Checks if the eventQueue is empty
	 * 
	 * @return boolean, true if the queue is empty, false if not
	 */
	public Event poll() {
		return eventQueue.poll();
	}

	boolean isEmpty() {
		return eventQueue.isEmpty();
	}
}
