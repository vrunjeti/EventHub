package munjeti.testing.androidtest5;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.service.textservice.SpellCheckerService.Session;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.facebook.Session;
import com.facebook.*;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;
import com.firebase.client.Firebase;
import com.firebase.simplelogin.FirebaseSimpleLoginError;
import com.firebase.simplelogin.SimpleLogin;

public class MainFragment extends Fragment {

	/**
	
	private MainFragment mainFragment;
	private static final String TAG = "MainFragment";
	private UiLifecycleHelper uiHelper;

	private static final String FIREBASE_EVENTS_URL = "https://blinding-fire-5881.firebaseio.com/eventlist";
	private Firebase ref = new Firebase(FIREBASE_EVENTS_URL);

	SimpleLogin authClient = new SimpleLogin(ref);

	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_event_hub, container,
				false);

		LoginButton authButton = (LoginButton) view
				.findViewById(R.id.authButton);
		authButton.setFragment(this);

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(getActivity(), callback);
		uiHelper.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			// Add the fragment on initial activity setup
			mainFragment = new MainFragment();
			// getSupportFragmentManager()
			getChildFragmentManager().beginTransaction()
					.add(android.R.id.content, mainFragment).commit();
		} else {
			// Or set the fragment from restored state info
			// getSupportFragmentManager()
			mainFragment = (MainFragment) getChildFragmentManager()
					.findFragmentById(android.R.id.content);
		}
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {
			Log.i(TAG, "Logged in...");
		} else if (state.isClosed()) {
			Log.i(TAG, "Logged out...");
		}

		if (state.isOpened()) {
			final String accessToken = session.getAccessToken();
			authClient.loginWithFacebook("1504369446459024",
					session.getAccessToken(),
					new SimpleLoginAuthenticatedHandler() {
						public void authenticated(
								FirebaseSimpleLoginError error,
								FirebaseSimpleLoginUser user) {
							if (error != null) {
								// There was an error
							} else {
								// Logged in with Facebook
							}
						}
					});
		} else if (state.isClosed()) {
			// Logged out of Facebook
			authClient.logout();
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();

		Session session = Session.getActiveSession();
		if (session != null && (session.isOpened() || session.isClosed())) {
			onSessionStateChange(session, session.getState(), null);
		}

		uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

	*/

}
