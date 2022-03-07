package labb5.simulation.general;
import labb5.simulation.general.Event;

/**
 * @author Arvid From, Stefan Jonsson, Dino Lolic, William Kiwanuka
 */
public class EventQueue {
	
	SortedSequence<Event> eventQueue = new SortedSequence<Event>();	
	
	/**
	 * adds new event  to the waiting queue		 
	 * @param Events from the specific simulator
	*/
	void add(Event e) {
		eventQueue.add(e);
		}
	
	/**
	 * Checks if the eventQueue is empty
	 * @return  boolean, true if the queue is empty, false if not
	 */
	public boolean isEmpty() {
		return eventQueue.isEmpty();
	}
	/** 
	 * returns  the event which is next to occur from the list, and removes it from the queue
	 * 
	 * @return  The next event in the queue
	 */
	Event poll() {
		Event e = eventQueue.smallest();
		eventQueue.removeSmallest();
		return e;
	}
		
	}


