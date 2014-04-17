package munjeti.testing.androidtest5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// text fields and buttons from activity_main.xml
		final EditText eventNameText = (EditText) findViewById(R.id.editTextName);//event name
		final EditText eventDateText = (EditText) findViewById(R.id.editTextDate);//event date
		final EditText eventTimeText = (EditText) findViewById(R.id.editTextTime);//event time
		final EditText eventLocationText = (EditText) findViewById(R.id.editTextLocation);//event location
		final EditText eventDescriptionText = (EditText) findViewById(R.id.editTextDescription);//event location

		final Button launchButton = (Button) findViewById(R.id.button1);//create event
		final Button eventButton = (Button) findViewById(R.id.button2);//go to EventHub

		// deals with time and dates for each event
		final Calendar myCalendar = Calendar.getInstance();

		// brings up the date picker and sets field text to whatever was input
		final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// sets day, month, and year
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

				// formatting for displaying entered date in text field
				String myFormat = "MM/dd/yyyy";
				SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
				eventDateText.setText(sdf.format(myCalendar.getTime()));
			}
		};

		// brings up the time picker and sets field text to whatever was input
		final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// sets hour and minute.
				myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				myCalendar.set(Calendar.MINUTE, minute);

				// (!) Note: hour of day is always in 24hr time, so am/pm string
				// was created separately
				String AM_PM;
				if (hourOfDay < 12) {
					AM_PM = "AM";
				} else {
					AM_PM = "PM";
				}

				// formatting for displaying entered time in text field
				String myFormat = "hh:mm";
				SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
				eventTimeText.setText(sdf.format(myCalendar.getTime()) + " "
						+ AM_PM);

			}
		};

		//actually makes the date picker show up when clicked
		eventDateText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new DatePickerDialog(MainActivity.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

		//actually makes the time picker show up when clicked
		eventTimeText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new TimePickerDialog(MainActivity.this, time, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar
						.get(Calendar.MINUTE), false).show();
			}
		});

		//creates the event and stores it in memory
		//Note: as of now, the events disappear when the app is wiped from phones RAM.
		//So we need to fix the events to stay in memory until actually deleted
		launchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				//this is where the event's name that was entered in the text field is stored
				String eventName = eventNameText.getText().toString();
				String eventLocation = eventLocationText.getText().toString();
				String eventDescription = eventDescriptionText.getText().toString();

				//Event objects' parameters are string (name) and calendar (date/time)
				//myCalendar is all values that were input from user earlier
				Event.createEvent(eventName, myCalendar, eventLocation, eventDescription);

				//creates a pop up message (toast) notifying that an event has been created
				Toast.makeText(MainActivity.this, "Event created.",	Toast.LENGTH_LONG).show();

				/**
				 * intent.putExtra("interval", durationBox.getText().toString()); 
				 * startActivity(intent);
				 */
				// }
			}
		});

		//Intent for going to EventHub screen (activity_event_hub)
		final Intent EventHubIntent = new Intent(this, EventHub.class);
		
		// when this button is pressed, it goes to the EventHub screen
		eventButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(EventHubIntent);
			}
		});

		/**
		 * if (savedInstanceState == null) {
		 * getFragmentManager().beginTransaction() .add(R.id.container, new PlaceholderFragment()).commit(); }
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
