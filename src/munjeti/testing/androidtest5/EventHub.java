package munjeti.testing.androidtest5;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;
import com.firebase.simplelogin.FirebaseSimpleLoginError;
import com.firebase.simplelogin.FirebaseSimpleLoginUser;
import com.firebase.simplelogin.SimpleLogin;
import com.firebase.simplelogin.SimpleLoginAuthenticatedHandler;

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
import android.os.Build;

public class EventHub extends Activity {
	
    private static final String FIREBASE_URL = "https://blinding-fire-5881.firebaseio.com";
    private static final String FIREBASE_EVENT_LIST_URL = "blinding-fire-5881.firebaseio.com/\"Event%20List\"";
    private static final String FIREBASE_EVENTS_URL = "https://blinding-fire-5881.firebaseio.com/eventlist";
    private Firebase ref = new Firebase(FIREBASE_EVENTS_URL);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_hub);

		final ArrayList<Event> eventList = Event.allEvents;

		// events are in a listView
		ListView listview = (ListView) findViewById(R.id.listview);

		List<ListViewItem> items = new ArrayList<EventHub.ListViewItem>();

		//ref = new Firebase(FIREBASE_EVENT_LIST_URL);
		
		/**
		
		ref.child(".info/authenticated").addValueEventListener(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onCancelled() {
				// TODO Auto-generated method stub
				
			}
		});
		
		final SimpleLogin authClient = new SimpleLogin(ref, getApplicationContext());
		
		/**
		final SimpleLoginAuthenticatedHandler simpleLoginAuthenticatedHandler = new SimpleLoginAuthenticatedHandler() {                        
		        @SuppressWarnings("unused")
				public void authenticated(Error error, FirebaseSimpleLoginUser user) {
		                System.out.println("Error: " + error);
		                System.out.println("User: " + user);            
		                System.out.println("3rdP: " + user.getThirdPartyUserData());
		        }

				@Override
				public void authenticated(FirebaseSimpleLoginError arg0,
						FirebaseSimpleLoginUser arg1) {
					// TODO Auto-generated method stub
					
				}
		 };
		
		// authClient.checkAuthStatus(simpleLoginAuthenticatedHandler);
		 * 
		 * 
		 */
		
		/**
		authClient.checkAuthStatus(new SimpleLoginAuthenticatedHandler() {
			  public void authenticated(FirebaseSimpleLoginError error, FirebaseSimpleLoginUser user) {
			    if (error != null) {
			      // Oh no! There was an error performing the check
			    } else if (user == null) {
			      // No user is logged in
			    } else {
			      // There is a logged in user
			    }
			  }

			@SuppressWarnings("unused")
			public void authenticated1(FirebaseSimpleLoginError arg0,
					FirebaseSimpleLoginUser arg1) {
				// TODO Auto-generated method stub
				
			}
			});
		
		authClient.createUser("ajv.cs196@gmail.com", "ca$hmoney$wag", new SimpleLoginAuthenticatedHandler() {
			  public void authenticated(FirebaseSimpleLoginError error, FirebaseSimpleLoginUser user) {
			    if(error != null) {
			      // There was an error creating this account
			    }
			    else {
			      // We are now logged in
			    }
			  }
			});
		
		*/
		final SimpleLogin authClient = new SimpleLogin(ref, getApplicationContext());
		
		authClient.loginWithEmail("ajv.cs196@gmail.com", "ca$hmoney$wag", new SimpleLoginAuthenticatedHandler() {
			  public void authenticated(FirebaseSimpleLoginError error, FirebaseSimpleLoginUser user) {
			    if(error != null) {
			      // There was an error logging into this account
			    }
			    else {
			    	
			    }
			  }
			});
		
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
