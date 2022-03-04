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
	
	public SupermarketState() {
		// TODO Auto-generated constructor stub
	}

}
