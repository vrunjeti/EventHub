package munjeti.testing.androidtest5;


import java.util.Date;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
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
		final DatePicker eventDatePick = (DatePicker) findViewById(R.id.datePicker1);
		final TimePicker eventTimePick = (TimePicker) findViewById(R.id.timePicker1);
		
		final Button launchButton = (Button) findViewById(R.id.button1);
		final Button eventButton = (Button) findViewById(R.id.button2);//create intent to launch other 

		launchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/**
				if (eventNameText.getText().toString().equals("")) {
					Toast.makeText(MainActivity.this,"Please enter an event name.",Toast.LENGTH_LONG).show();
				} else {
				*/
				
				String eventName = eventNameText.getText().toString();
					
				int year = eventDatePick.getYear();
				int month = eventDatePick.getMonth();
				int day = eventDatePick.getDayOfMonth();
					
				int hour = eventTimePick.getCurrentHour();
				int minute = eventTimePick.getCurrentMinute();
					
				@SuppressWarnings("deprecation")
				Date eventDate = new Date(year, month, day, hour, minute);
					
				//Event event1 = new Event(eventName, eventDate);
				Event.createEvent(eventName, eventDate);
				
				Toast.makeText(MainActivity.this,"Event created.",Toast.LENGTH_LONG).show();
								
				/**
				intent.putExtra("interval", durationBox.getText()
						.toString());
				startActivity(intent);
				*/
			//	}
			}
		});
		
		final Intent intent = new Intent(this, EventHub.class);
		
		eventButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intent);
			}
		});
		
		/**
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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
