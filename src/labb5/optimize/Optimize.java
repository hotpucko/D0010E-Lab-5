package labb5.optimize;

import java.util.Random;

import labb5.simulation.general.RunSim;
import labb5.simulation.supermarket.state.SupermarketState;

public class Optimize {

	Random rnd;
	SupermarketState simState;
	
	public Optimize() {
		
	}
	
	//detta �r mitt f�rs�k med optimize metoden, se om ni l�ser det p� n�got b�ttre s�tt :shrug:
	
	public int metod1(double lambda, double kMin, double kMax, double pMin, double pMax, int seed, int registers, int customerMax, double closingTime) {
		RunSim sim = new RunSim(customerMax, lambda, kMin, kMax, pMin, pMax, seed, registers);
		sim.run(closingTime);
		this.simState = sim.getState();
		return simState.getCustomersRejected(); //sim.state beh�ver visst vara public, sorry arvid, eller g�r en getter f�r simstate :shrug:
	}
	
	public int metod2(double lambda, double kMin, double kMax, double pMin, double pMax, int seed, int customerMax, double closingTime) {
		int registers = customerMax;
		
		int missedCustomers = metod1(lambda, kMin, kMax, pMin, pMax, seed, registers, customerMax, closingTime);

		while (registers > 0) {
			int newMissedCustommers = metod1(lambda, kMin, kMax, pMin, pMax, seed, registers, customerMax, closingTime);

			if (newMissedCustommers != missedCustomers)
				return registers + 1;
			registers--;
		}
		return customerMax;
	}
	
	public int metod3(double lambda, double kMin, double kMax, double pMin, double pMax, int seed, int customerMax, double closingTime) {
		rnd = new Random(seed);
		int iterationsSinceLastChange = 0;
		int worstRegistersAmount = 0;

		while (iterationsSinceLastChange <= 100) {
			int currentRegistersAmount = metod2(lambda, kMin, kMax, pMin, pMax, rnd.nextInt(), customerMax, closingTime);
			if (currentRegistersAmount > worstRegistersAmount) {
				worstRegistersAmount = currentRegistersAmount;
				iterationsSinceLastChange = 0;
				continue;
			}
			iterationsSinceLastChange++;
		}

		return worstRegistersAmount;
	}
}
