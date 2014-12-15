package sea.goethe.sportpas;

import com.viewpagerindicator.CirclePageIndicator;

import sea.goethe.sportpas.lib.WelcomeAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class WelcomeActivity extends ActionBarActivity {

	private WelcomeAdapter welcomeAdapter;
	private ViewPager viewPager;
	private RelativeLayout linWelcome;
	private CirclePageIndicator cIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		viewPager = (ViewPager)findViewById(R.id.pager);
		
		welcomeAdapter = new WelcomeAdapter(WelcomeActivity.this);
		linWelcome = (RelativeLayout)findViewById(R.id.linWelcome);
		
		viewPager.setAdapter(welcomeAdapter);
		viewPager.setCurrentItem(0);
		
		cIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
		cIndicator.setViewPager(viewPager);
		
		linWelcome.setBackgroundColor(getResources().getColor(R.color.blueWelcomeColor));
		linWelcome.invalidate();
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
