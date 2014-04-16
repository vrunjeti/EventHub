package munjeti.testing.androidtest5;
import java.util.ArrayList;
import java.util.Calendar;
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
	//private Date date;
	private Calendar cal;
	private static int eventCount = 0;
	public static ArrayList<Event> allEvents = new ArrayList<Event>();
	
	//constructor for Event objects
	public Event(String title, Calendar cal){
		this.setTitle(title);
		this.setCal(cal);
		eventCount++;
		allEvents.add(this);
	}
	
	public static Event createEvent(String title, Calendar cal){
		return new Event(title, cal);
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getCal(){
		return this.cal;
	}
	public void setCal(Calendar cal){
		this.cal = cal;
	}

	public static int getEventCount() {
		return eventCount;
	}

	
}
