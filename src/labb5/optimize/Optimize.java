package labb5.optimize;

import java.util.Random;

import labb5.simulation.general.RunSim;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.state.SupermarketState;

public class Optimize {

	Random rnd;
	
	public Optimize() {
		
	}
	
	//detta �r mitt f�rs�k med optimize metoden, se om ni l�ser det p� n�got b�ttre s�tt :shrug:
	
	public SupermarketState metod1(double lambda, double kMin, double kMax, double pMin, double pMax, int seed, int registers, int customerMax, double closingTime) {
		RunSim sim = new RunSim(customerMax, lambda, kMin, kMax, pMin, pMax, seed, registers);
		sim.run(closingTime);
		return sim.getState();
		//return simState; //sim.state beh�ver visst vara public, sorry arvid, eller g�r en getter f�r simstate :shrug:
	}
	
	public SupermarketState metod2(double lambda, double kMin, double kMax, double pMin, double pMax, int seed, int customerMax, double closingTime, int min, int max) {
		
		//base case
		if (min == max) {
			//System.out.println(String.format("%d, %d", min, max));
			return metod1(lambda, kMin, kMax, pMin, pMax, seed, min, customerMax, closingTime);
		}
		
		SupermarketState lower = metod1(lambda, kMin, kMax, pMin, pMax, seed, min, customerMax, closingTime);
		SupermarketState middle = metod1(lambda, kMin, kMax, pMin, pMax, seed, Math.floorDiv(min + max, 2), customerMax, closingTime);
		SupermarketState upper = metod1(lambda, kMin, kMax, pMin, pMax, seed, max, customerMax, closingTime);
		//recursive case
		if(middle.getCustomersRejected() < upper.getCustomersRejected())
			return metod2(lambda, kMin, kMax, pMin, pMax, seed, customerMax, closingTime, min, Math.floorDiv(min + max, 2));
		return metod2(lambda, kMin, kMax, pMin, pMax, seed, customerMax, closingTime, (int)Math.ceil(new Double(min + max)/2d), max);
		/*
		if (lowerRecursion.getCustomersRejected() <= upperRecursion.getCustomersRejected()) {
			return lowerRecursion;
		}
		return upperRecursion;
		*/
		
		
		/*int registers = customerMax;
		
		int missedCustomers = metod1(lambda, kMin, kMax, pMin, pMax, seed, registers, customerMax, closingTime);

		while (registers > 0) {
			int newMissedCustommers = metod1(lambda, kMin, kMax, pMin, pMax, seed, registers, customerMax, closingTime);

			if (newMissedCustommers != missedCustomers)
				return registers + 1;
			registers--;
		}
		return customerMax;*/
	}
	
	public SupermarketState metod3(double lambda, double kMin, double kMax, double pMin, double pMax, int seed, int customerMax, double closingTime) {
		rnd = new Random(seed);
		int iterationsSinceLastChange = 0;
		SupermarketState worstRegistersAmount = metod1(lambda, kMin, kMax, pMin, pMax, seed, customerMax, customerMax, closingTime);

		while (iterationsSinceLastChange <= 100) {
			SupermarketState currentRegistersAmount = metod2(lambda, kMin, kMax, pMin, pMax, rnd.nextInt(), customerMax, closingTime, 1, customerMax);
			if (currentRegistersAmount.getMaxRegistersCount() > worstRegistersAmount.getMaxRegistersCount()) {
				worstRegistersAmount = currentRegistersAmount;
				iterationsSinceLastChange = 0;
				continue;
			}
			iterationsSinceLastChange++;
		}

		return worstRegistersAmount;
	}
}
