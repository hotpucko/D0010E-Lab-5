package labb5;
import javax.print.attribute.standard.PrinterLocation;

import labb5.optimize.Optimize;
import labb5.simulation.general.RunSim;
import labb5.simulation.general.View;
import labb5.simulation.supermarket.state.SupermarketState;
import labb5.simulation.supermarket.view.SupermarketView;

public class MainSim {
	private static Optimize optimize = new Optimize();
	
	public static void main(String[] args) {
		double lambda = 2000;
		double kMin	= 0.2;
		double kMax = 0.3;
		double pMin = 0.45;
		double pMax = 0.65;
		double closingTime = 20;
		
		int registers = 1;
		int customerMax = 1400;
		int seed = 1234;
		
		//RunSim sim = new RunSim(customerMax, lambda, kMin, kMax, pMin, pMax, seed, registers);

		//View v = new SupermarketView(sim.getState());

		//sim.run(closingTime);
		//System.out.println(String.valueOf(optimize.metod3(lambda, kMin, kMax, pMin, pMax, seed, customerMax, closingTime)));
	
		
		
		long startTime = System.currentTimeMillis();
		
		//SupermarketState state = optimize.metod3(2000,  0.2, 0.3, 0.45, 0.65, 42,  1400, 20.0d);
		SupermarketState state = optimize.metod3(2000,  0.2, 0.3, 0.45, 0.65, 42,  1400, 20.0d);
		long endTime = System.currentTimeMillis();
		System.out.println(String.format("metod3: registers: %d | RejectedCustomers: %d", state.getMaxRegistersCount(), state.getCustomersRejected()));
		System.out.println(String.format("That took %f milliseconds", new Double(endTime - startTime)));
		
		//SupermarketState ex1 = optimize.metod3(1, 2, 3, 0.5d, 1d, 1234, 5, 10);
		/*System.out.println(String.format("ex1: %s", String.valueOf(ex1)));
		int ex2 = optimize.metod3(2, 2, 3, 0.5d, 1, 1234, 7, 10);
		System.out.println(String.format("ex2: %s", String.valueOf(ex2)));
		int ex3 = optimize.metod3(3, 0.35d, 0.65d, 0.6d, 0.9d, 13, 7, 8);
		System.out.println(String.format("ex3: %s", String.valueOf(ex3)));
		int ex4 = optimize.metod3(50, 0.2, 0.3, 0.45d, 0.65, 42, 100, 20);
		System.out.println(String.format("ex4: %s", String.valueOf(ex4)));
		int ex5 = optimize.metod3(100, 0.2d, 0.3d, 0.45d, 0.65d, 42, 1400, 20);
		System.out.println(String.format("ex5: %s", String.valueOf(ex5)));
		int ex6 = optimize.metod3(700, 0.2d, 0.3d, 0.45d, 0.65d, 42, 1400, 20);
		System.out.println(String.format("ex6: %s", String.valueOf(ex6)));
		int ex7 = optimize.metod3(2000,  0.2, 0.3, 0.45, 0.65, 42,  1400, 20.0d);
		System.out.println(String.format("ex7: %s", String.valueOf(ex7)));*/
				
	}
}
