package labb5.simulation.supermarket.state;

import labb5.simulation.general.SimState;

public class SupermarketState extends SimState {

	private int seed;
	private int currentRegister;
	private int maxRegisters;
	private int customerMax;
	private int customersInShop;
	private int customersRejected;
	private int customersProcessed;
	private int totalCustomersQueued;
	private boolean isShopOpen;
	private double totalTimeQueued;
	private double totalTimeRegistersIdled;
	private double lambda;
	private double kMin;
	private double kMax;
	private double pMin;
	private double pMax;
	
	public SupermarketState(int seed, int customerMax, double lambda, double kMin, double kMax, double pMin, double pMax) {
		super();
		this.seed = seed;
		this.customerMax = customerMax;
		this.lambda = lambda;
		this.kMin = kMin;
		this.kMax = kMax;
		this.pMin = pMin;
		this.pMax = pMax;
	}

}
