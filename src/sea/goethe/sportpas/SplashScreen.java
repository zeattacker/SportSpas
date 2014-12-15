package sea.goethe.sportpas;

import sea.goethe.sportpas.lib.SessionHandler;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class SplashScreen extends ActionBarActivity {
	private SessionHandler sh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		sh = new SessionHandler(getApplicationContext());
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(sh.isFirstTime()){
					Intent i = new Intent(SplashScreen.this, MenuActivity.class);
					startActivity(i);
					finish();
				} else {
					Intent i = new Intent(SplashScreen.this, WelcomeActivity.class);
					startActivity(i);
					finish();
				}
			}
		}, 1500);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
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
}
