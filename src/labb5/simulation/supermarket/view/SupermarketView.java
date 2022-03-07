package labb5.simulation.supermarket.view;

import java.util.Observable;

import labb5.simulation.general.Event;
import labb5.simulation.general.StartEvent;
import labb5.simulation.general.StopEvent;
import labb5.simulation.general.View;
import labb5.simulation.supermarket.events.ArrivalEvent;
import labb5.simulation.supermarket.events.ClosingEvent;
import labb5.simulation.supermarket.events.PayLeaveEvent;
import labb5.simulation.supermarket.events.ShoppingEvent;
import labb5.simulation.supermarket.state.SupermarketState;

public class SupermarketView extends View{

	public SupermarketView(SupermarketState state) {
		super(state);
	}

	@Override
	public void update(Observable stateObject, Object event) {
		//SimState state = (SimState)stateObject;
		SupermarketState state = (SupermarketState)stateObject;
		Event e = (Event)event;
		printEvent(state, e);
	}

	private void printEvent(SupermarketState state, Event e) {
		if(e instanceof StartEvent) {
			
			printStartEvent(state, e);
		} else if (e instanceof StopEvent) {
			printStopEvent(state, e);
		} else if (e instanceof ClosingEvent) {
			printClosingEvent(e);
		}else {
			printCustomerEvents(state, e);
		}
	}

	private void printCustomerEvents(SupermarketState state, Event e) {
		String time = String.format("%.2f", state.getCurrentTime());//String.valueOf(format(state.getCurrentTime()));
		String eventName = e.toString();
		String customerNumber = eventToCustomerNumberString(e);
		String open = state.isOpen() ? "ö" : "s";
		String registers = String.valueOf(state.getFreeRegisters());
		String timeRegistersIdle = String.format("%.2f", state.getTimeRegistersIdled());
		String customersInStore = String.valueOf(state.getCustomersInShop());
		String customersProcessed = String.valueOf(state.getCustomersProcessed());
		String customersRejected = String.valueOf(state.getCustomersRejected());
		String customersQueued = String.valueOf(state.getTotalCustomersQueued());
		String timeQueued = String.format("%.2f", state.getTotalTimeQueued());
		String customersInQueue = String.valueOf(state.getShopQueue().getSize());
		String registerQueue = String.valueOf(state.getShopQueue().toString());
		
		System.out.println(String.format("%s\t%-8s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s", 
				time, eventName, customerNumber, open, registers, 
				timeRegistersIdle, customersInStore, customersProcessed, 
				customersRejected, customersQueued, timeQueued, customersInQueue,
				registerQueue
				));
	}

	private void printClosingEvent(Event e) {
		System.out.println(String.format("%.2f\t%-8s\t%s", e.getEventTime(), e.toString(), this.eventToCustomerNumberString(e)));
	}

	private void printStopEvent(SupermarketState state, Event e) {
		System.out.println(String.format("%.2f\t%s", e.getEventTime(), e.toString()));
		System.out.println("RESULTAT\n========\n");
		System.out.println(String.format("1)\tav %d kunder handlade %d medan %d missades\n", state.getCustomersProcessed() + state.getCustomersRejected(), state.getCustomersProcessed(), state.getCustomersRejected()));
		System.out.println(String.format("2)\tTotal tid %d kassor varit lediga: %.2f te.", state.getMaxRegistersCount(), state.getTimeRegistersIdled()));
		System.out.println(String.format("\tGenomsnittlig ledig kassatid: %.2f te (dvs %.2f%% av tiden från öppning tills sista kunden betalat)\n", state.getTimeRegistersIdled() / state.getMaxRegistersCount(), ((state.getTimeRegistersIdled() / state.getMaxRegistersCount()) / state.getCurrentTime() * 100)));
		System.out.println(String.format("3)\tTotal tid %d kunder tvingats köa: %.2f te.", state.getTotalCustomersQueued(), state.getTotalTimeQueued()));
		System.out.println(String.format("\tGenomsnittlig kötid: %.2f te.", state.getTotalTimeQueued() / state.getTotalCustomersQueued()));
	}

	private void printStartEvent(SupermarketState state, Event e) {
		System.out.println("PARAMETRAR");
		System.out.println("==========");
		System.out.println(String.format("Antal kassor, N..........: %d", state.getMaxRegistersCount()));
		System.out.println(String.format("Max som ryms, M..........: %d", state.getMaxCustomerCount()));
		System.out.println(String.format("Ankomsthastighet, lambda.: %f", state.getLambda()));
		System.out.println(String.format("Plocktider, [P_min..Pmax]: [%f..%f]", state.getPMin(), state.getPMax()));
		System.out.println(String.format("Betaltider, [K_min..Kmax]: [%s..%s]", state.getKMin(), state.getKMax()));
		System.out.println(String.format("Frö, f...................: %d", state.getSeed()));
		System.out.println("\nFÖRLOPP");
		System.out.println("=======");
		System.out.println("Tid\tHändelse\tKund\t?\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[KassaKö]");
		System.out.println(String.format("%.2f\t%s", e.getEventTime(), e.toString()));
	}
	
	private String eventToCustomerNumberString(Event e)
	{
		if(e instanceof StartEvent || e instanceof StopEvent) {
			return "";
		} else if (e instanceof ClosingEvent) {
			return "---";
		} else if (e instanceof ArrivalEvent) {
			return String.valueOf(((ArrivalEvent)e).getCustomerNumber());
		} else if (e instanceof ShoppingEvent) {
			return String.valueOf(((ShoppingEvent)e).getCustomerNumber());
		} else {
			return String.valueOf(((PayLeaveEvent)e).getCustomerNumber());
		}
	}
}

