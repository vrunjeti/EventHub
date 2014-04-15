package munjeti.testing.androidtest5;
import java.util.ArrayList;
import java.util.Date;
//import java.sql.Date;

public class Event {

	/**
	 * This class contains the components of event objects
	 * @param String title: name of event
	 * @param Date date: the day and time event is set to
	 * 
	 * Constructor for Date objects:
	 * Date(int year, int month, int date, int hrs, int min)
	 * 
	 */

	private String title;
	private Date date;
	private static int eventCount = 0;
	public static ArrayList<Event> allEvents = new ArrayList<Event>();
	
	//constructor for Event objects
	public Event(String title, Date date){
		this.setTitle(title);
		this.setDate(date);
		eventCount++;
		allEvents.add(this);
	}
	
	public static Event createEvent(String title, Date date){
		return new Event(title, date);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static int getEventCount() {
		return eventCount;
	}
	
}
