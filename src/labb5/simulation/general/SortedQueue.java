package labb5.simulation.general;

import java.util.ArrayList;

/**
 * implements a queue
 * 
 * @author Arvid From, Stefan Jonsson, William Kiwanuka, Dino Lolic
 *
 */
public class SortedQueue {

	private ArrayList<Event> queue;

	public SortedQueue() {
		queue = new ArrayList<>();
	}

	/**
	 * adds new event to the waiting queue
	 * 
	 * @param e
	 *            Events from the specific simulator
	 */
	public void add(Event e) {
		int indexToInput = 0;

		for (; indexToInput < queue.size();) {
			if (e.getTime() <= queue.get(indexToInput).getTime())
				break;
			indexToInput++;
		}

		queue.add(indexToInput, e);
	}

	/**
	 * Checks if the eventQueue is empty
	 * 
	 * @return boolean, true if the queue is empty, false if not
	 */
	public Event poll() {
		if (isEmpty()) {
			return null;
		}
		return queue.remove(0);
	}

	/**
	 * checks if the queue is empty
	 * 
	 * @return boolean true if the queue is empty
	 */
	boolean isEmpty() {
		return queue.size() <= 0;
	}
}
