package munjeti.testing.androidtest5;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//import java.sql.Date;

public class Event {

	/**
	 * This class contains the components of event objects
	 * 
	 * @param String
	 *            title: name of event
	 * @param Calendar
	 *            cal: the day and time event is set to
	 * 
	 */

	private String title;
	private Calendar cal;
	private String location;
	private String description;
	// private String[] group; <-- used for handling groups that event is shared
	// to
	// probably not use string, but people objects or something...

	private static int eventCount = 0;
	public static ArrayList<Event> allEvents = new ArrayList<Event>();

	// constructor for Event objects
	public Event(String title, Calendar cal, String location, String description) {
		this.setTitle(title);
		this.setCal(cal);
		this.setLocation(location);
		this.setDescription(description);
		eventCount++;
		allEvents.add(this);
	}

	public static Event createEvent(String title, Calendar cal,
			String location, String description) {
		return new Event(title, cal, location, description);
	}

	public String printTD() {
		/**
		int hour = this.cal.get(Calendar.HOUR_OF_DAY);
		int minute = this.cal.get(Calendar.MINUTE);
		int day = this.cal.get(Calendar.DATE);
		int month = this.cal.get(Calendar.MONTH) + 1;
		int year = this.cal.get(Calendar.YEAR);
		*/
		String AM_PM;
		if (this.cal.get(Calendar.HOUR_OF_DAY) < 12)
			AM_PM = "AM";
		else
			AM_PM = "PM";
		
		//int hourDisplayed = (hour < 12) ? ((hour == 0) ? 12 : hour) : hour - 12;
		
		String timeFormat = "hh:mm";
		SimpleDateFormat tF = new SimpleDateFormat(timeFormat, Locale.US);
		
		String dateFormat = "MM/dd/yyyy";
		SimpleDateFormat dF = new SimpleDateFormat(dateFormat, Locale.US);

		//return hourDisplayed + ":" + minute + " " + AM_PM + "\n" + month + "/"
		//		+ day + "/" + year;
		
		return tF.format(this.cal.getTime()) + " " + AM_PM + "\n" + dF.format(this.cal.getTime());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getCal() {
		return this.cal;
	}

	public void setCal(Calendar cal) {
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
