package labb5.simulation.general;

import java.util.Observer;

/**
 * <code>View</code> is the abstract implementation for handling all contexts
 * related to analysis of the SimState object
 * 
 * @author Stefan Jonsson, Dino Lolic, William Kiwanuka, Arvid From
 * @version %I%, %G%
 *
 */
public abstract class View implements Observer {

	/**
	 * The view constructor handles setting up the Observer pattern
	 * 
	 * @param state The state object for the view to observe
	 */
	public View(SimState state) {
		state.addObserver(this);
	}
}
