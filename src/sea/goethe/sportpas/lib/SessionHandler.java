package sea.goethe.sportpas.lib;

import java.util.HashMap;

import sea.goethe.sportpas.MenuActivity;
import sea.goethe.sportpas.WelcomeActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionHandler {
	// Shared Preferences
	SharedPreferences pref;

	// Editor for Shared preferences
	Editor editor;

	// Context
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREF_NAME = "sportspasPref";

	// All Shared Preferences Keys
	private static final String IS_FIRST = "isFirstTime";

	// User name (make variable public to access from outside)
	public static final String KEY_SESION = "session";

	// Constructor
	public SessionHandler(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	/**
	 * Create regis session
	 * */
	public void createRegisSession(String session) {
		// Storing login value as TRUE
		editor.putBoolean(IS_FIRST, true);

		// Storing session in pref
		editor.putString(KEY_SESION, session);

		// commit changes
		editor.commit();
	}

	/**
	 * Check login method wil check user login status If false it will redirect
	 * user to login page Else won't do anything
	 * */
	public void checkRegis() {
		// Check login status
		if (this.isFirstTime()) {

			// user is not logged in redirect him to Login Activity
			Intent i = new Intent(_context, MenuActivity.class);
			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// Staring Login Activity
			_context.startActivity(i);
		}

	}

	/**
	 * Get stored session data
	 * */
	public HashMap<String, String> getUserDetails() {
		HashMap<String, String> user = new HashMap<String, String>();
		// user session
		user.put(KEY_SESION, pref.getString(KEY_SESION, null));

		// return user
		return user;
	}

	// Clear session details
	public void logoutUser() {
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();

		// After logout redirect user to Loing Activity
		Intent i = new Intent(_context, WelcomeActivity.class);
		i.putExtra("STATUS", "logout");
		// Closing all the Activities
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		// Add new Flag to start new Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		// Staring Login Activity
		_context.startActivity(i);

		if (_context instanceof Activity) {
			((Activity) _context).finish();
		}

	}

	/**
	 * Quick check for login
	 * **/
	// Get Login State
	public boolean isFirstTime() {
		return pref.getBoolean(IS_FIRST, false);
	}
}