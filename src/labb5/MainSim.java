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
		
		//if run with view
		boolean runWithView = true;
		//vilket exempel?
		int ex = 7;
		//which optimize method?
		int selectMethod = 2;
		
		int verbosity = 0;

		long startTime = System.currentTimeMillis();
		
		if(runWithView) {
			RunSim sim = new RunSim(customerMax, lambda, kMin, kMax, pMin, pMax, seed, registers);
					  
			View v = new SupermarketView(sim.getState());
					  
			sim.run(closingTime);
		} else {
			if(selectMethod == 3) {
				switch (ex) {
					case 1:
						//lambda, kMin, kMax, pMin, pMax, seed, customerMax, closingTime, Verbosity
						state = optimize.metod3(1, 2, 3, 0.5, 1, 1234, 5, 10, verbosity);
						break;
					case 2:
						state = optimize.metod3(2, 2, 3, 0.5, 1, 1234, 7, 10, verbosity);
						break;
					case 3:
						state = optimize.metod3(3, 0.35, 0.6, 0.6, 0.9, 13, 7, 8, verbosity);
						break;
					case 4:
						state = optimize.metod3(50, 0.2, 0.3, 0.45, 0.65, 42, 100, 20, verbosity);
						break;
					case 5:
						state = optimize.metod3(100, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20, verbosity);
						break;
					case 6:
						state = optimize.metod3(700, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20, verbosity);
						break;
					case 7:
						state = optimize.metod3(2000, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20, verbosity);
						break;
					default:
						break;
				}
			} else if (selectMethod == 2) {
				switch (ex) {
					case 1:
						//lambda, kMin, kMax, pMin, pMax, seed, customerMax, closingTime, 0, customerMax, Verbosity
						state = optimize.metod2(1, 2, 3, 0.5, 1, 1234, 5, 10, 0, 5, verbosity);
						break;
					case 2:
						state = optimize.metod2(2, 2, 3, 0.5, 1, 1234, 7, 10, 0, 7, verbosity);
						break;
					case 3:
						state = optimize.metod2(3, 0.35, 0.6, 0.6, 0.9, 13, 7, 8, 0, 7, verbosity);
						break;
					case 4:
						state = optimize.metod2(50, 0.2, 0.3, 0.45, 0.65, 42, 100, 20, 0, 100, verbosity);
						break;
					case 5:
						state = optimize.metod2(100, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20, 0, 1400, verbosity);
						break;
					case 6:
						state = optimize.metod2(700, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20, 0, 1400, verbosity);
						break;
					case 7:
						state = optimize.metod2(2000, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20, 0, 1400, verbosity);
						break;
					default:
						break;
				}
			}
			
			System.out.println(String.format("metod3: registers: %d | RejectedCustomers: %d", state.getMaxRegistersCount(),
					state.getCustomersRejected()));
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(String.format("That took %f milliseconds", new Double(endTime - startTime)));

		/*
		 * RunSim sim = new RunSim(customerMax, lambda, kMin, kMax, pMin, pMax, seed,
		 * registers);
		 * 
		 * View v = new SupermarketView(sim.getState());
		 * 
		 * sim.run(closingTime);
		 * //System.out.println(String.valueOf(optimize.metod3(lambda, kMin, kMax, pMin,
		 * pMax, seed, customerMax, closingTime)));
		 */


		
		
		// SupermarketState ex1 = optimize.metod3(1, 2, 3, 0.5d, 1d, 1234, 5, 10);
		/*
		 * System.out.println(String.format("ex1: %s", String.valueOf(ex1))); int ex2 =
		 * optimize.metod3(2, 2, 3, 0.5d, 1, 1234, 7, 10);
		 * System.out.println(String.format("ex2: %s", String.valueOf(ex2))); int ex3 =
		 * optimize.metod3(3, 0.35d, 0.65d, 0.6d, 0.9d, 13, 7, 8);
		 * System.out.println(String.format("ex3: %s", String.valueOf(ex3))); int ex4 =
		 * optimize.metod3(50, 0.2, 0.3, 0.45d, 0.65, 42, 100, 20);
		 * System.out.println(String.format("ex4: %s", String.valueOf(ex4))); int ex5 =
		 * optimize.metod3(100, 0.2d, 0.3d, 0.45d, 0.65d, 42, 1400, 20);
		 * System.out.println(String.format("ex5: %s", String.valueOf(ex5))); int ex6 =
		 * optimize.metod3(700, 0.2d, 0.3d, 0.45d, 0.65d, 42, 1400, 20);
		 * System.out.println(String.format("ex6: %s", String.valueOf(ex6))); int ex7 =
		 * optimize.metod3(2000, 0.2, 0.3, 0.45, 0.65, 42, 1400, 20.0d);
		 * System.out.println(String.format("ex7: %s", String.valueOf(ex7)));
		 */

	}
}
