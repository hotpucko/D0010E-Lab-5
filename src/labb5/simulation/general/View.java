package labb5.simulation.general;

import java.util.Observer;

public abstract class View implements Observer{

	public View(SimState state) {
		state.addObserver(this);
	}
}
