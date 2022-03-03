package labb5.simulation.general;

import java.util.Observable;

public abstract class SimState extends Observable {
	
	protected double lastEventTime;
	protected boolean isRunning;
	private double timeElapsed;
	private boolean emergencyBreak;
	
	public SimState() {
		
	}
	
	void update(Event e) {
		if (!isRunning()) {
			return;
		}
		
		if (!emergencyBreak)
			this.timeElapsed += e.getTime() - lastEventTime;
		
		setChanged();
		notifyObservers(e);
		lastEventTime = e.getTime();
	}
	
	public boolean isRunning() {
		return isRunning();
	}
	
	void EmergencyBreak() {
		this.emergencyBreak = true;
		this.isRunning = false;
	}
	
	double getCurrentTime() {
		return this.timeElapsed;
	}
	
	protected boolean isEmergencyBreak() {
		return emergencyBreak;
	}
}
