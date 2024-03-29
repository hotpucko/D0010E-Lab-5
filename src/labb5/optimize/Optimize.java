package labb5.optimize;

import java.util.Random;

import labb5.simulation.general.RunSim;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.state.SupermarketState;

/**
 * Class that implements runSim. The class Minimizes the output of missed
 * customers as a function of open registers in a given supermarket. The class
 * consists of three functions, one which just runs a given simulation, one that
 * controls for least amount of customers missed, and one which massively
 * implements that simulation to find the correct register given that most other
 * variables change. The execution is layered, which is to say that: The third
 * method implements the second, which implements the first.
 *
 * 
 * @author Stefan Jonsson, Arvid From, William Kiwanuka, Dino Lolic
 *
 * 
 */
public class Optimize {

	Random rnd;

	public Optimize() {

	}

	/**
	 * Runs single simulation
	 * 
	 * @param lambda
	 *            1/lambda is the rate of customers entering the supermarket with 1
	 *            representing a single unit of time
	 * @param kMin
	 *            The minimum payment time for a single customer
	 * @param kMax
	 *            The maximum payment time for a single customer
	 * @param pMin
	 *            The minimum shopping time for a single customer
	 * @param pMax
	 *            The maximum shopping time for a single customer
	 * @param seed
	 *            The seed that always executes the simulation in exactly the same
	 *            way given a randomiser
	 * @param registers
	 *            The amount of Registers available throughout the simulation
	 * @param customerMax
	 *            The maximum amount of customers allowed within the supermarket
	 * @param closingTime
	 *            After how many units of time does the shop being simulated close
	 * 
	 * @return returns the Supermarket state after a single regular simulation of a
	 *         supermarket with given parameters
	 */
	public SupermarketState rejectedCustomers(double lambda, double kMin, double kMax, double pMin, double pMax,
			int seed, int registers, int customerMax, double closingTime) {
		RunSim sim = new RunSim(customerMax, lambda, kMin, kMax, pMin, pMax, seed, registers);
		sim.run(closingTime);
		return sim.getState();
	}

	/**
	 * Locates the least amount of registers needed for a supermarket to miss the
	 * least amount of customers, all other things equal. Uses repeated iterations
	 * of metod1() and a recursive binary search to achieve this
	 * 
	 * @param lambda
	 *            1/lambda is the rate of customers entering the supermarket with 1
	 *            representing a single unit of time
	 * @param kMin
	 *            The minimum payment time for a single customer
	 * @param kMax
	 *            The maximum payment time for a single customer
	 * @param pMin
	 *            The minimum shopping time for a single customer
	 * @param pMax
	 *            The maximum shopping time for a single customer
	 * @param seed
	 *            The seed that always executes the simulation in exactly the same
	 *            way given a randomiser
	 * @param customerMax
	 *            The max amount of customers allowed within a single shop within
	 *            the customerSimulator
	 * @param closingTime
	 *            After how many units of time does the shop being simulated close
	 * @param min
	 *            Low bound for binary search of the results for all the simulations
	 *            during runtime
	 * @param max
	 *            High bound for binary search of the results for all the
	 *            simulations during runtime
	 * @return A Supermarket-state which produces the least amount of missed
	 *         customers as a function of the amount of open registers
	 */
	public SupermarketState optimizeRegisters(double lambda, double kMin, double kMax, double pMin, double pMax,
			int seed, int customerMax, double closingTime, int min, int max, int verbosity) {

		// base case
		if (min == max) {
			return rejectedCustomers(lambda, kMin, kMax, pMin, pMax, seed, min, customerMax, closingTime);
		}

		SupermarketState middle = rejectedCustomers(lambda, kMin, kMax, pMin, pMax, seed, Math.floorDiv(min + max, 2),
				customerMax, closingTime);
		SupermarketState upper = rejectedCustomers(lambda, kMin, kMax, pMin, pMax, seed, max, customerMax, closingTime);

		// recursive case
		if (verbosity >= 2)
			System.out.println(String.format("Middle: %d | Upper: %d | min: %d | max: %d",
					middle.getCustomersRejected(), upper.getCustomersRejected(), min, max));
		if (middle.getCustomersRejected() <= upper.getCustomersRejected()) {
			return optimizeRegisters(lambda, kMin, kMax, pMin, pMax, seed, customerMax, closingTime, min,
					Math.floorDiv(min + max, 2), verbosity);
		}
		return optimizeRegisters(lambda, kMin, kMax, pMin, pMax, seed, customerMax, closingTime,
				(int) Math.ceil(new Double(min + max) / 2d), max, verbosity);
	}

	/**
	 * Identifies a particularly bad case for a supermarket with the given
	 * parameters. if a worse case can not be found within 100 random iterations of
	 * optimizeRegisters(), it returns said scenario.
	 * 
	 * @param lambda
	 *            1/lambda is the rate of customers entering the supermarket with 1
	 *            representing a single unit of time
	 * @param kMin
	 *            The minimum payment time for a single customer
	 * @param kMax
	 *            The maximum payment time for a single customer
	 * @param pMin
	 *            The minimum shopping time for a single customer
	 * @param pMax
	 *            The maximum shopping time for a single customer
	 * @param seed
	 *            The seed that always executes the simulation in exactly the same
	 *            way given a randomiser
	 * @param customerMax
	 *            The max amount of customers allowed within a single shop within
	 *            the customerSimulator
	 * @param closingTime
	 *            After how many units of time does the shop being simulated close
	 * @return for a given seed of random values, the method returns the smallest
	 *         amount of registers needed to reduce the amount of missed customers
	 *         to its smallest possible value.
	 */
	public SupermarketState variability(double lambda, double kMin, double kMax, double pMin, double pMax, int seed,
			int customerMax, double closingTime, int verbosity) {
		rnd = new Random(seed);
		int iterationsSinceLastChange = 0;
		SupermarketState worstRegistersAmount = null;

		while (iterationsSinceLastChange <= 100) {
			SupermarketState currentRegistersAmount = optimizeRegisters(lambda, kMin, kMax, pMin, pMax, rnd.nextInt(),
					customerMax, closingTime, 1, customerMax, verbosity);
			if (verbosity >= 1)
				System.out.println(String.format("Method2 returned %d registers with %d customers rejected",
						currentRegistersAmount.getMaxRegistersCount(), currentRegistersAmount.getCustomersRejected()));
			if (worstRegistersAmount == null
					|| currentRegistersAmount.getMaxRegistersCount() > worstRegistersAmount.getMaxRegistersCount()) {
				worstRegistersAmount = currentRegistersAmount;
				iterationsSinceLastChange = 0;
				continue;
			}
			iterationsSinceLastChange++;
		}

		return worstRegistersAmount;
	}
}
