package labb5.simulation.general;
import java.util.ArrayList;

import labb5.simulation.general.Event;

/**
 * @author Arvid From, Stefan Jonsson, Dino Lolic, William Kiwanuka
 */
public class EventQueue {
	
	ArrayList<Event> eventQueue = new ArrayList<>();
	
	//SortedSequence<Event> eventQueue = new SortedSequence<Event>();	
	
	/**
	 * adds new event  to the waiting queue		 
	 * @param Events from the specific simulator
	*/
	public void add(Event e) {
		int indexToInput = 0;
		
		for(;indexToInput < eventQueue.size();)
		{
			if(e.getTime() <= eventQueue.get(indexToInput).getTime())
				break;
			indexToInput++;
		}

		eventQueue.add(indexToInput, e);
		
		//eventQueue.add(e);
		}
	
	/**
	 * Checks if the eventQueue is empty
	 * @return  boolean, true if the queue is empty, false if not
	 */
	public Event poll()
	{
		if(isEmpty())
			return null;
		return eventQueue.remove(0);
	}
	
	boolean isEmpty()
	{
		return eventQueue.size() <= 0;
	}
		
	}


