package sea.goethe.sportpas;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends ActionBarActivity {
	private ImageButton btnLearn, btnGame, btnAbout, btnProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		btnLearn = (ImageButton) findViewById(R.id.btnLearn);
		btnGame = (ImageButton) findViewById(R.id.btnGame);
		btnAbout = (ImageButton) findViewById(R.id.btnAbout);
		btnProgress = (ImageButton) findViewById(R.id.btnProgress);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("Wilkommen zu SportSpaﬂ");

		btnLearn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MenuActivity.this,
						MainFrameActivity.class);
				i.putExtra("MENU", "learn");
				startActivity(i);
			}
		});

		btnGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MenuActivity.this, TipePertama.class);
				startActivity(i);
			}
		});

		btnAbout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MenuActivity.this,
						MainFrameActivity.class);
				i.putExtra("MENU", "about");
				startActivity(i);
			}
		});

		btnProgress.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MenuActivity.this, ReportActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
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
