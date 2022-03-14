package labb5.simulation.supermarket.state.utilities;

/**
 * 
 * @param customerNumber,  the given number for
 * initializing a single customer through arrivalEvent.
 * the parameter increases every time the only method in the class is
 * called.
 * 
 * @author Arvid From, Stefan Jonsson, Dino Lolic, William Kiwanuka
 */
public class CustomerFactory {
	private int customerNumber = 0 ;

	/**
	 * increases the customer number and returns it.
	 * this keeps track of which customer is enacting events in 
	 * the eventQueue
	 * 
	 * @return customerNumber
	 */
	public int generateCustomer() {
		customerNumber++;
		return customerNumber;
			
		}
}
