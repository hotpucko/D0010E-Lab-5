package labb5;

import labb5.optimize.Optimize;
import labb5.simulation.general.RunSim;
import labb5.simulation.general.View;
import labb5.simulation.supermarket.state.SupermarketState;
import labb5.simulation.supermarket.view.SupermarketView;

public class MainSim {
	private static Optimize optimize = new Optimize();

	public static void main(String[] args) {

		double lambda = 1.0;
		double kMin = 2;
		double kMax = 3;
		double pMin = 0.5;
		double pMax = 1;
		double closingTime = 10;

		int registers = 2;
		int customerMax = 5;
		int seed = 1234;

		// if ! run with view
		SupermarketState state = null;

		// if run with view
		boolean runWithView = true;
		
		
		//optimize setup
		// vilket exempel?
		int ex = 1;
		// which optimize method?
		int selectMethod = 3;

		int verbosity = 0;

		if (runWithView) {
			RunSim sim = new RunSim(customerMax, lambda, kMin, kMax, pMin, pMax, seed, registers);

			View v = new SupermarketView(sim.getState());

			sim.run(closingTime);
		} else {
			if (selectMethod == 3) {
				switch (ex) {
				case 1:
					// lambda, kMin, kMax, pMin, pMax, seed, customerMax, closingTime, Verbosity
					state = optimize.variability(1, 2, 3, 0.5, 1, 1234, 5, 10, verbosity);
					break;
				case 2:
					state = optimize.variability(2, 2, 3, 0.5, 1, 1234, 7, 10, verbosity);
					break;
				case 3:
					state = optimize.variability(3, 0.35, 0.6, 0.6, 0.9, 13, 7, 8, verbosity);
					break;
				case 4:
					state = optimize.variability(50, 0.2, 0.3, 0.45, 0.65, 42, 100, 20, verbosity);
					break;
				case 5:
					state = optimize.variability(100, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20, verbosity);
					break;
				case 6:
					state = optimize.variability(700, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20, verbosity);
					break;
				case 7:
					state = optimize.variability(2000, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20, verbosity);
					break;
				default:
					break;
				}
			} else if (selectMethod == 2) {
				switch (ex) {
				case 1:
					// lambda, kMin, kMax, pMin, pMax, seed, customerMax, closingTime, 0,
					// customerMax, Verbosity
					state = optimize.optimizeRegisters(1, 2, 3, 0.5, 1, 1234, 5, 10, 0, 5, verbosity);
					break;
				case 2:
					state = optimize.optimizeRegisters(2, 2, 3, 0.5, 1, 1234, 7, 10, 0, 7, verbosity);
					break;
				case 3:
					state = optimize.optimizeRegisters(3, 0.35, 0.6, 0.6, 0.9, 13, 7, 8, 0, 7, verbosity);
					break;
				case 4:
					state = optimize.optimizeRegisters(50, 0.2, 0.3, 0.45, 0.65, 42, 100, 20, 0, 100, verbosity);
					break;
				case 5:
					state = optimize.optimizeRegisters(100, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20, 0, 1400, verbosity);
					break;
				case 6:
					state = optimize.optimizeRegisters(700, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20, 0, 1400, verbosity);
					break;
				case 7:
					state = optimize.optimizeRegisters(2000, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20, 0, 1400, verbosity);
					break;
				default:
					break;
				}
			}

			System.out.println(String.format("metod3: registers: %d | RejectedCustomers: %d",
					state.getMaxRegistersCount(), state.getCustomersRejected()));
		}

	}
}
