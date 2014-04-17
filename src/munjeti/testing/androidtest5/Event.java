package munjeti.testing.androidtest5;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
//import java.sql.Date;

public class Event {

	/**
	 * This class contains the components of event objects
	 * @param String title: name of event
	 * @param Calendar cal: the day and time event is set to
	 *  
	 */

	private String title;
	private Calendar cal;
	private String location;
	private String description;
	//private String[] group;  <-- used for handling groups that event is shared to
	//probably not use string, but people objects or something...
	
	private static int eventCount = 0;
	public static ArrayList<Event> allEvents = new ArrayList<Event>();
	
	//constructor for Event objects
	public Event(String title, Calendar cal, String location, String description){
		this.setTitle(title);
		this.setCal(cal);
		this.setLocation(location);
		this.setDescription(description);
		eventCount++;
		allEvents.add(this);
	}
	
	public static Event createEvent(String title, Calendar cal, String location, String description){
		return new Event(title, cal, location, description);
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
