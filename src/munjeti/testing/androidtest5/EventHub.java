package munjeti.testing.androidtest5;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import java.util.Set;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.view.ActionMode;
import android.view.MenuInflater;


import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.firebase.client.Firebase;
import com.firebase.simplelogin.FirebaseSimpleLoginError;
import com.firebase.simplelogin.FirebaseSimpleLoginUser;
import com.firebase.simplelogin.SimpleLogin;
import com.firebase.simplelogin.SimpleLoginAuthenticatedHandler;

import com.facebook.*;
import com.facebook.model.*;

public class EventHub extends FragmentActivity {
	
    private static final String FIREBASE_URL = "https://blinding-fire-5881.firebaseio.com";
    private static final String FIREBASE_EVENT_LIST_URL = "blinding-fire-5881.firebaseio.com/\"Event%20List\"";
    private static final String FIREBASE_EVENTS_URL = "https://blinding-fire-5881.firebaseio.com/eventlist";
    private static final String FIREBASE_GROUP_URL = "https://blinding-fire-5881.firebaseio.com/group1";
    
    private SelectionAdapter adapter; 
	public Set<Integer> results;

    
    //private Firebase ref = new Firebase(FIREBASE_GROUP_URL);
    
    private Firebase ref = new Firebase(FIREBASE_URL).child("testList1");
    
    private String username;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_hub);

		setupUsername();
		
		final ArrayList<Event> eventList = Event.allEvents;

		// events are in a listView
		final ListView listview = (ListView) findViewById(R.id.listview);

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
		
		/**
		
		final SimpleLogin authClient = new SimpleLogin(ref, getApplicationContext());
		
		// start Facebook Login
		  Session.openActiveSession(this, true, new Session.StatusCallback() {

		    // callback when session changes state
		    @Override
		    public void call(Session session, SessionState state, Exception exception) {
		    	if (session.isOpened()) {
		    		// make request to the /me API
		    		Request.newMeRequest(session, new Request.GraphUserCallback() {

		    		  // callback after Graph API response with user object
		    		  @Override
		    		  public void onCompleted(GraphUser user, Response response) {
		    		  }
		    		}).executeAsync();
		    		}
		    }
		  });
		  
		  
		  
		
		//final SimpleLogin authClient = new SimpleLogin(ref, getApplicationContext());
		
		
		authClient.loginWithEmail("ajv.cs196@gmail.com", "ca$hmoney$wag", new SimpleLoginAuthenticatedHandler() {
			  public void authenticated(FirebaseSimpleLoginError error, FirebaseSimpleLoginUser user) {
			    if(error != null) {
			      // There was an error logging into this account
			    }
			    else {
			    	
			    }
			  }
			});
		
		*/
		
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
		
		

		CustomListViewAdapter listadapter = new CustomListViewAdapter(this, items);
		listview.setAdapter(listadapter);

		adapter = new SelectionAdapter(this, R.layout.item_row, R.id.textView1, items);
		listview.setAdapter(adapter);
		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		registerForContextMenu(listview);
		
		listview.setMultiChoiceModeListener(new MultiChoiceModeListener() {
		    private int nr = 0;
            
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // TODO Auto-generated method stub
                return false;
            }
             
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // TODO Auto-generated method stub
                 adapter.clearSelection();
            }
             
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // TODO Auto-generated method stub
                 
                nr = 0;
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.contextual_menu, menu);
                return true;
            }
            
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // TODO Auto-generated method stub
                switch (item.getItemId()) {
                 
                    case R.id.item_delete:
                    	nr = 0;
                    	int[] resultsArray = new int[results.size()];
                    	results.toArray();
                        for (int i=0; i<results.size(); i++) {
                        	Event.allEvents.remove(resultsArray[i]);
                        }
                        adapter.clearSelection();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
				
            }
             
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                    long id, boolean checked) {
                // TODO Auto-generated method stub
            	
                 if (checked) {
                        nr++;
                        adapter.setNewSelection(position, checked);                    
                    } else {
                        nr--;
                        adapter.removeSelection(position);                 
                    }
                 		results = adapter.getCurrentCheckedPosition();
                    mode.setTitle(nr + " selected");
                 
            }

            
		});
	
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {
	    	   
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int position, long arg3) {
                // TODO Auto-generated method stub
                 
                listview.setItemChecked(position, !adapter.isPositionChecked(position));
                return false;
            }
        });

		
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
	}
	
	 @Override
	  public void onActivityResult(int requestCode, int resultCode, Intent data) {
	      super.onActivityResult(requestCode, resultCode, data);
	      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
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
	
	private class SelectionAdapter extends ArrayAdapter<ListViewItem> {
	   	 
        private HashMap<Integer, Boolean> mSelection = new HashMap<Integer, Boolean>();
        
    	LayoutInflater inflater;
    	List<ListViewItem> items;
        
        
        public SelectionAdapter(Context context, int resource,
                int textViewResourceId, List<ListViewItem> items) {
            super(context, resource, textViewResourceId, items);
            this.items = items;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        
        @Override  
        public int getCount() {  
            // TODO Auto-generated method stub
            return items.size();  
        }  
      
          
        public void setNewSelection(int position, boolean value) {
            mSelection.put(position, value);
            notifyDataSetChanged();
        }
 
        public boolean isPositionChecked(int position) {
            Boolean result = mSelection.get(position);
            return result == null ? false : result;
        }
 
        public Set<Integer> getCurrentCheckedPosition() {
            return mSelection.keySet();
        }
 
        public void removeSelection(int position) {
            mSelection.remove(position);
            notifyDataSetChanged();
        }
 
        public void clearSelection() {
            mSelection = new HashMap<Integer, Boolean>();
            notifyDataSetChanged();
        }
 
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	ListViewItem item = items.get(position);
        	
        	View vi=convertView;
            
            if(convertView==null)
                vi = inflater.inflate(R.layout.item_row, null);
                
            TextView txtTitle = (TextView) vi.findViewById(R.id.txtTitle);
            TextView txtLocation = (TextView) vi.findViewById(R.id.txtLocation);
            TextView txtTime = (TextView) vi.findViewById(R.id.txtTime);
            
            txtTitle.setText(item.Title);
            txtLocation.setText(item.Location);
            txtTime.setText(item.Time);
        	
            vi.setBackgroundColor(getResources().getColor(android.R.color.background_light)); //default color
            
            if (mSelection.get(position) != null) {
            	vi.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            }
            return vi;
        }
    }

	
	 private void setupUsername() {
	        SharedPreferences prefs = getApplication().getSharedPreferences("EventPrefs", 0);
	        username = prefs.getString("username", null);
	        if (username == null) {
	            Random r = new Random();
	            // Assign a random user name if we don't have one saved.
	            username = "User" + r.nextInt(100000);
	            prefs.edit().putString("username", username).commit();
	        }
	    }

}
