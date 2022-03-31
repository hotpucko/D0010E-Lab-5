package labb5.simulation.general;

import java.util.Observable;

/**
 * State is the abstract base class for all state general state contexts. a
 * State object encapsulates all the information needed to set up a general
 * simulation state. This state information includes:
 * <ul>
 * <li>the total value of the simulation time
 * <li>the conditional running state
 * <li>the time of the most recent event processed
 * <li>the conditional emergency break
 * </ul>
 * 
 * @author Stefan Jonsson, Dino Lolic, William Kiwanuka, Arvid From
 * @version %I%, %G%
 *
 */
public abstract class SimState extends Observable {

	protected double lastEventTime;
	protected boolean isRunning;
	private double timeElapsed;
	private boolean emergencyBreak;

	/**
	 * Updates the internal time based on the argument if the state is considered
	 * active <br>
	 * Notifies observers with the event that was processed
	 * 
	 * @param e an <code>Event</code> that the simulation processes
	 */
	public void update(Event e) {
		if (isRunning()) {

			if (!emergencyBreak) {
				this.timeElapsed += e.getTime() - lastEventTime;
			}
		}
		setChanged();
		notifyObservers(e);
		lastEventTime = e.getTime();
	}

	/**
	 * Checks if the simstate is active
	 * 
	 * @return <code>true </code> if the <code>SimState</code> object is considered
	 *         active; <code>false</code> otherwise
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * Stops the simulation in its current state
	 */
	public void emergencyBreak() {
		this.emergencyBreak = true;
		this.isRunning = false;
	}

	/**
	 * Gets the internal time elapsed for the simulation
	 * 
	 * @return the current time of the simulation
	 */
	public double getCurrentTime() {
		return this.timeElapsed;
	}

	/**
	 * Checks whether the emergency break has been triggered
	 * 
	 * @return <code>true</code> if the emergency break has been triggered;
	 *         <code>false</code> otherwise
	 */
	protected boolean isEmergencyBreak() {
		return emergencyBreak;
	}

	/**
	 * sets the sim to running
	 */
	public void run() {
		isRunning = true;
	}
}
