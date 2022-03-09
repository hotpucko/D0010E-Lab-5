package labb5;
import labb5.optimize.Optimize;
import labb5.simulation.general.RunSim;
import labb5.simulation.general.View;
import labb5.simulation.supermarket.view.SupermarketView;

public class MainSim {
	private static Optimize optimize = new Optimize();
	
	public static void main(String[] args) {
		double lambda = 1.0;
		double kMin	= 2.0;
		double kMax = 3.0;
		double pMin = 0.5;
		double pMax = 1.0;
		double closingTime = 10.0;
		
		int registers = 2;
		int customerMax = 5;
		int seed = 1234;
		
		
		RunSim sim = new RunSim(customerMax, lambda, kMin, kMax, pMin, pMax, seed, registers);

		View v = new SupermarketView(sim.getState());

		sim.run(closingTime);
		//optimize.metod1(lambda, kMin, kMax, pMin, pMax, seed, registers, customerMax, closingTime);
	}
}
