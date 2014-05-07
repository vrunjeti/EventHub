package munjeti.testing.androidtest5;


import com.firebase.client.DataSnapshot;
import com.firebase.client.*;
//import ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class EventHub extends Activity {
	
	// TODO: change this to your own Firebase URL
    private static final String FIREBASE_URL = "https://blinding-fire-5881.firebaseio.com";

    private String username;
    private Firebase ref;
    private ValueEventListener connectedListener;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		  connectedListener = ref.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
	            @Override
	            public void onDataChange(DataSnapshot dataSnapshot) {
	                boolean connected = (Boolean)dataSnapshot.getValue();
	                if (connected) {
	                    Toast.makeText(EventHub.this, "Connected to Firebase", Toast.LENGTH_SHORT).show();
	                } else {
	                    Toast.makeText(EventHub.this, "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
	                }
	            }

	            @Override
	            public void onCancelled() {
	                // No-op
	            }
	        });
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_hub);

		final ArrayList<Event> eventList = Event.allEvents;

		// events are in a listView
		ListView listview = (ListView) findViewById(R.id.listview);

		List<ListViewItem> items = new ArrayList<EventHub.ListViewItem>();
		
		ref = new Firebase(FIREBASE_URL);

		
		for (final Event e : eventList) {
			items.add(new ListViewItem() {
				{
					Title = e.getTitle();
					Location = e.getLocation();
					Time = e.printTD();
				}
			});
		}
		
		ref.push().setValue(items);

		CustomListViewAdapter adapter = new CustomListViewAdapter(this, items);
		listview.setAdapter(adapter);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	class ListViewItem {
		public String Title;
		public String Location;
		public String Time;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_hub_actions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
        case R.id.action_new:
            Intent createEventIntent = new Intent(this, CreateEvent.class);
            startActivity(createEventIntent);
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
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
			View rootView = inflater.inflate(R.layout.fragment_event_hub,
					container, false);
			return rootView;
		}
	}

}
