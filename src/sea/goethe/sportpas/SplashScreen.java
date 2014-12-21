package sea.goethe.sportpas;

import sea.goethe.sportpas.lib.SessionHandler;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
					overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
					finish();
				} else {
					Intent i = new Intent(SplashScreen.this, WelcomeActivity.class);
					startActivity(i);
					overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
					finish();
				}
			}
		}, 1500);
	}
}
