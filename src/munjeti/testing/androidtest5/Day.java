package munjeti.testing.androidtest5;
import java.util.ArrayList;
import java.util.Date;

public class Day {

	/**
	 * This class is used to organize all the events in a day by using an
	 * arrayList. The idea behind this is to be able to gather all the events
	 * for a given day with reference too a specific day.
	 * 
	 */

	//private Date day;
	private ArrayList<Event> dayEvents = new ArrayList<Event>();

	public Day(Date d) {
		//this.day = d;
		for (Event e : Event.allEvents) {
			if (e.getDate().equals(d)) {
				this.dayEvents.add(e);
			}
		}
	}
	
}
//Date(int year, int month, int date, int hrs, int min)