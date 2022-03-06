package labb5.simulation.supermarket.state;

import labb5.simulation.general.Event;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.state.utilities.CustomerFactory;
import labb5.simulation.supermarket.state.utilities.FIFO;
import labb5.simulation.supermarket.state.utilities.random.ExponentialRandomStream;
import labb5.simulation.supermarket.state.utilities.random.UniformRandomStream;

public class SupermarketState extends SimState {

	private int seed;
	private int currentRegisters;
	private int maxRegisters;
	private int customerMax;
	private int customersInShop;
	private int customersRejected;
	private int customersProcessed;
	private int totalCustomersQueued;
	private boolean isShopOpen;
	private double totalTimeQueued;
	private double totalTimeRegistersIdled;
	private double lastEventTime;
	private double lambda;
	private double kMin;
	private double kMax;
	private double pMin;
	private double pMax;
	private CustomerFactory customerFactory;
	private FIFO shopQueue;
	private UniformRandomStream shoppingTimeRandomStream;
	private UniformRandomStream payLeaveTimeRandomStream;
	private ExponentialRandomStream arrivalTimeRandomStream;

	public SupermarketState(int seed, int customerMax, double lambda, double kMin, double kMax, double pMin,
			double pMax) {
		super();
		this.seed = seed;
		this.customerMax = customerMax;
		this.lambda = lambda;
		this.kMin = kMin;
		this.kMax = kMax;
		this.pMin = pMin;
		this.pMax = pMax;
		this.customerFactory = new CustomerFactory();
		this.shopQueue = new FIFO();
		this.shoppingTimeRandomStream = new UniformRandomStream(pMin, pMax, seed);
		this.payLeaveTimeRandomStream = new UniformRandomStream(kMin, kMax, seed);
		this.arrivalTimeRandomStream = new ExponentialRandomStream(lambda, seed);
	}

	@Override
	public void update(Event e) {

		if (this.isRunning()) {
			totalTimeRegistersIdled += (e.getTime() - lastEventTime) * currentRegisters;
			totalTimeQueued += (e.getTime() - lastEvent) * getShopQueue().getSize();
		}

		super.update(e);
	}

	public boolean isOpen() {
		return isShopOpen;
	}

	public boolean isMaxCapacity() {
		return customersInShop >= customerMax;
	}

	public void incrementRejected() {
		customersRejected++;
	}

	public void incrementProcessedCustomerCount() {
		customersProcessed++;
	}

	public void incrementCustomersInShop() {
		customersInShop++;
	}

	public void decrementCustomersInShop() {
		customersInShop--;
	}

	public void incrementRegisters() {
		currentRegisters++;
	}

	public void decrementRegisters() {
		currentRegisters--;
	}

	public double generateArrivalTime() {
		return this.arrivalTimeRandomStream.next();
	}
	
	public double generateShoppingTime() {
		return this.shoppingTimeRandomStream.next();
	}

	public double generatePayLeaveTime() {
		return this.payLeaveTimeRandomStream.next();
	}

	public CustomerFactory getCustomerFactory() {
		return customerFactory;
	}

	public FIFO getShopQueue() {
		return shopQueue;
	}

	public int getFreeRegisters() {
		return currentRegisters;
	}

	// view exclusive getters
	
	public double getTimeRegistersIdled() {
		return this.totalTimeRegistersIdled;
	}

	public int getCustomersInShop() {
		return this.customersInShop;
	}

	public int getCustomersProcessed() {
		return this.customersProcessed;
	}

	public int getCustomersRejected() {
		return this.customersRejected;
	}

	public int getTotalCustomersQueued() {
		return this.totalCustomersQueued;
	}

	public double getTotalTimeQueued() {
		return this.totalTimeQueued;
	}
	
	public int getMaxRegistersCount() {
		return this.maxRegisters;
	}

	public int getMaxCustomerCount() {
		return this.customerMax;
	}

	public double getLambda() {
		return this.lambda;
	}

	public double getPMin() {
		return this.pMin;
	}

	public double getPMax() {
		return this.pMax;
	}

	public double getKMin() {
		return this.kMin;
	}

	public double getKMax() {
		return this.kMax;
	}

	public int getSeed() {
		return this.seed;
	}
}
