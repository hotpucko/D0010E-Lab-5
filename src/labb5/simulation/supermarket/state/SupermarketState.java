package labb5.simulation.supermarket.state;

import labb5.simulation.general.Event;
import labb5.simulation.general.SimState;
import labb5.simulation.supermarket.state.utilities.CustomerFactory;
import labb5.simulation.supermarket.state.utilities.FIFO;
import labb5.simulation.supermarket.state.utilities.random.ExponentialRandomStream;
import labb5.simulation.supermarket.state.utilities.random.UniformRandomStream;

/**
 * <code>SupermarketState</code> is the implemented supermarket state class for
 * all supermarket general state contexts. a <code>SupermarketState</code>
 * object encapsulates all the state information needed to set up a supermarket
 * simulation. This state information includes, but is not limited to:
 * <ul>
 * <li>customer contexts
 * <li>delta time generation
 * <li>time contexts
 * </ul>
 * 
 * @author Stefan Jonsson, Dino Lolic, William Kiwanuka, Arvid From
 * @version %I%, %G%
 * @see SimState
 */
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

	/**
	 * the class constructor setting up the initial state.
	 * 
	 * @param seed
	 *            a seed for initializing random values
	 * @param customerMax
	 *            the maximum capacity of customers in the supermarket
	 * @param lambda
	 *            the rate at which customers arrive, 1 over <code>lambda</code> per
	 *            unit of time on average
	 * @param kMin
	 * 			the minimum time to process a customer
	 * @param kMax
	 * 			the maximum time to process a customer
	 * @param pMin
	 * 			the minimum time for a customer to shop
	 * @param pMax
	 * 			the maximum time for a customer to shop
	 */
	public SupermarketState(int seed, int customerMax, double lambda, double kMin, double kMax, double pMin,
			double pMax, int maxRegisters) {
		super();
		this.seed = seed;
		this.customerMax = customerMax;
		this.lambda = lambda;
		this.kMin = kMin;
		this.kMax = kMax;
		this.pMin = pMin;
		this.pMax = pMax;
		this.customerMax = customerMax;
		this.maxRegisters = maxRegisters;
		this.customerFactory = new CustomerFactory();
		this.shopQueue = new FIFO();
		this.shoppingTimeRandomStream = new UniformRandomStream(pMin, pMax, seed);
		this.payLeaveTimeRandomStream = new UniformRandomStream(kMin, kMax, seed);
		this.arrivalTimeRandomStream = new ExponentialRandomStream(lambda, seed);
	}

	/**
	 * updates the time contexts associated with the state using an <code>Event</code>
	 * 
	 * @param e 
	 * 			the <code>Event</code> used to update the state
	 */
	@Override
	public void update(Event e) {

		if (this.isRunning()) {
			totalTimeRegistersIdled += (e.getTime() - lastEventTime) * currentRegisters;
			totalTimeQueued += (e.getTime() - lastEventTime) * getShopQueue().getSize();
		}

		super.update(e);
	}

	/**
	 * checks if the shop is open
	 * 
	 * @return <code>true </code> if the shop is considered
	 *         open; <code>false</code> otherwise
	 */
	public boolean isOpen() {
		return isShopOpen;
	}

	/**
	 * sets the internal is shop open variable to false
	 * @see #isOpen()
	 */
	public void close() {
		this.isShopOpen = false;
	}
	
	/**
	 * checks if the maximum capacity of customers is achieved
	 * 
	 * @return <code>true </code> if the shop is considered
	 *         to be at max capacity; <code>false</code> otherwise
	 */
	public boolean isMaxCapacity() {
		return customersInShop >= customerMax;
	}

	/**
	 * increments the internal customer counter
	 */
	public void incrementRejected() {
		customersRejected++;
	}

	/**
	 * increments the internal processed customer counter
	 */
	public void incrementProcessedCustomerCount() {
		customersProcessed++;
	}

	/**
	 * increments the internal customers currently in the shop counter
	 */
	public void incrementCustomersInShop() {
		customersInShop++;
	}

	/**
	 * decrements the internal customers currently in the shop counter
	 */
	public void decrementCustomersInShop() {
		customersInShop--;
	}

	/**
	 * increments the internal free registers counter 
	 */
	public void incrementRegisters() {
		currentRegisters++;
	}

	/**
	 * decrements the internal free registers counter
	 */
	public void decrementRegisters() {
		currentRegisters--;
	}

	/**
	 * generates an exponentially distributed random time based on <code>lambda</code>
	 * 
	 * @return a delta time for an <code>ArrivalEvent</code>
	 */
	public double generateArrivalTime() {
		return this.arrivalTimeRandomStream.next();
	}
	
	/**
	 * generates a uniformally distributed random time based on <code>pMin</code> and <code>pMax</code>
	 * 
	 * @return a delta time for a <code>ShoppingEvent</code>
	 */
	public double generateShoppingTime() {
		return this.shoppingTimeRandomStream.next();
	}

	/**
	 * generates a uniformally distributed random time based on <code>kMin</code> and <code>kMax</code>
	 * 
	 * @return a delta time for a <code>PayLeaveEvent</code>
	 */
	public double generatePayLeaveTime() {
		return this.payLeaveTimeRandomStream.next();
	}

	/**
	 * gets a reference to the factory for generating customers
	 * 
	 * @return the states <code>CustomerFactory</code> object
	 */
	public CustomerFactory getCustomerFactory() {
		return customerFactory;
	}

	/**
	 * gets a reference to the queue to the registers
	 * 
	 * @return the states <code>FIFO</code> object
	 */
	public FIFO getShopQueue() {
		return shopQueue;
	}

	/**
	 * fetches the internal free registers counter
	 * 
	 * @return the amount of free registers
	 */
	public int getFreeRegisters() {
		return currentRegisters;
	}

	// view exclusive getters
	
	/**
	 * fetches the current time that registers have been idle
	 * 
	 * @return how long the registers have idled
	 */
	public double getTimeRegistersIdled() {
		return this.totalTimeRegistersIdled;
	}

	/**
	 * fetches the current customer in shop count
	 * 
	 * @return the amount of customers currently in the shop
	 */
	public int getCustomersInShop() {
		return this.customersInShop;
	}

	/**
	 * fetches how many customers have paid and left the store
	 * 
	 * @return the amount of customers that have been processed
	 */
	public int getCustomersProcessed() {
		return this.customersProcessed;
	}

	/**
	 * fetches how many customers have been unable to enter the store due to it being at max capacity
	 * 
	 * @return the amount of customers rejected
	 */
	public int getCustomersRejected() {
		return this.customersRejected;
	}

	/**
	 * fetches how many customers in total that has started queuing in the shop
	 * 
	 * @return the total amount of customers queued
	 */
	public int getTotalCustomersQueued() {
		return this.totalCustomersQueued;
	}

	/**
	 * fetches the current time that has been spent waiting for registers to become free
	 * 
	 * @return the total time customers have spent queuing
	 */
	public double getTotalTimeQueued() {
		return this.totalTimeQueued;
	}
	
	/**
	 * fetches the amount of registers that exist in the shop
	 * 
	 * @return the total amount of registers
	 */
	public int getMaxRegistersCount() {
		return this.maxRegisters;
	}

	/**
	 * fetches the maximum capacity of customers that can be at the store at any given time
	 * 
	 * @return the shop capacity
	 */
	public int getMaxCustomerCount() {
		return this.customerMax;
	}

	/**
	 * fetches the amount of customers that on average can arrive per time unit
	 * 
	 * @return the arrival rate of customers
	 */
	public double getLambda() {
		return this.lambda;
	}

	/**
	 * fetches the minimum time that a customer can spend shopping in the shop
	 * 
	 * @return the minimum shopping time
	 */
	public double getPMin() {
		return this.pMin;
	}

	/**
	 * fetches the maximum time that a customer can spend shopping in the shop
	 * 
	 * @return the maximum shopping time
	 */
	public double getPMax() {
		return this.pMax;
	}

	/**
	 * fetches the minimum time that it takes to process a customer at a register
	 * 
	 * @return the minimum customer processing time
	 */
	public double getKMin() {
		return this.kMin;
	}

	/**
	 * fetches the maximum time that it takes to process a customer at a register
	 * 
	 * @return the maximum customer processing time
	 */
	public double getKMax() {
		return this.kMax;
	}

	/**
	 * fetches the seed used to generate random numbers for the simulation
	 * 
	 * @return the seed for randomness
	 */
	public int getSeed() {
		return this.seed;
	}
}
