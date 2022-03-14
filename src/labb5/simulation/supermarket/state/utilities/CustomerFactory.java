package labb5.simulation.supermarket.state.utilities;

/**
 * keeps track of which customer belongs to which event
 * 
 * @author Arvid From, Stefan Jonsson, Dino Lolic, William Kiwanuka
 */
public class CustomerFactory {
	private int customerNumber = 0;

	/**
	 * increases the customer number and returns it. this keeps track of which
	 * customer is enacting events in the eventQueue
	 * 
	 * @return customerNumber
	 */
	public int generateCustomer() {
		customerNumber++;
		return customerNumber;

	}
}
