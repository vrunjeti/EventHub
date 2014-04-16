package munjeti.testing.androidtest5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

		final EditText eventNameText = (EditText) findViewById(R.id.editText1);
		final EditText eventDateText = (EditText) findViewById(R.id.editText2);
		final EditText eventTimeText = (EditText) findViewById(R.id.editText3);

		final Button launchButton = (Button) findViewById(R.id.button1);
		final Button eventButton = (Button) findViewById(R.id.button2);

		final Calendar myCalendar = Calendar.getInstance();

		final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

				String myFormat = "MM/dd/yyyy";
				SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
				eventDateText.setText(sdf.format(myCalendar.getTime()));
			}
		};

		final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				myCalendar.set(Calendar.MINUTE, minute);

				String ampm;
				if(hourOfDay < 12){
					ampm = "AM";
				} else {
					ampm = "PM";
				}
				
				String myFormat = "hh:mm";
				
				SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
				eventTimeText.setText(sdf.format(myCalendar.getTime()) + " " + ampm);
				
			}
		};

		eventDateText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new DatePickerDialog(MainActivity.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

		eventTimeText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new TimePickerDialog(MainActivity.this, time, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar
						.get(Calendar.MINUTE), false).show();
			}
		});

		launchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/**
				 * if (eventNameText.getText().toString().equals("")) {
				 * Toast.makeText
				 * (MainActivity.this,"Please enter an event name."
				 * ,Toast.LENGTH_LONG).show(); } else {
				 */

				String eventName = eventNameText.getText().toString();

				// int year = eventDatePick.getYear();
				// int month = eventDatePick.getMonth();
				// int day = eventDatePick.getDayOfMonth();

				// int hour = eventTimePick.getCurrentHour();
				// int minute = eventTimePick.getCurrentMinute();

				/**
				 * final Calendar cal = Calendar.getInstance();
				 * 
				 * int year = cal.get(Calendar.YEAR); int month =
				 * cal.get(Calendar.MONTH); int day =
				 * cal.get(Calendar.DAY_OF_MONTH); int hour =
				 * cal.get(Calendar.HOUR_OF_DAY); int minute =
				 * cal.get(Calendar.MINUTE);
				 */

				// Event event1 = new Event(eventName, eventDate);
				Event.createEvent(eventName, myCalendar);

				Toast.makeText(MainActivity.this, "Event created.",
						Toast.LENGTH_LONG).show();

				/**
				 * intent.putExtra("interval", durationBox.getText()
				 * .toString()); startActivity(intent);
				 */
				// }
			}
		});

		final Intent intent = new Intent(this, EventHub.class);
		// final Intent i = new Intent(this, MyAndroidAppActivity.class);

		eventButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intent);
			}
		});

		/**
		 * testCalButton.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { startActivity(i); } });
		 */

		/**
		 * if (savedInstanceState == null) {
		 * getFragmentManager().beginTransaction() .add(R.id.container, new
		 * PlaceholderFragment()).commit(); }
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
