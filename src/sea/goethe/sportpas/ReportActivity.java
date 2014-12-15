package sea.goethe.sportpas;

import java.util.List;

import sea.goethe.sportpas.lib.DatabaseHandler;
import sea.goethe.sportpas.lib.ListSkorAdapter;
import sea.goethe.sportspas.model.ScoreModel;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ReportActivity extends ActionBarActivity {
	private ListSkorAdapter lsAdapter;
	private ListView listSkor;
	private List<ScoreModel> smList;
	private DatabaseHandler dh;
	private TextView textScore, textTime;
	private LinearLayout bagianShare;
	private Button btnUlang, btnMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);

		listSkor = (ListView) findViewById(R.id.listSkor);
		textScore = (TextView) findViewById(R.id.txtScore);
		textTime = (TextView) findViewById(R.id.txtTime);
		btnUlang = (Button) findViewById(R.id.btnUlang);
		btnMenu = (Button) findViewById(R.id.btnMenu);
		bagianShare = (LinearLayout) findViewById(R.id.btnShare);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("T‰tigkeitsbericht");

		bagianShare.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent sharingIntent = new Intent(
						android.content.Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				String shareBody = "Learn Germany A1 with SportSpaﬂ #SportSpaﬂ http://bit.ly/SportSpasApp";
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						"Learn Germany A1 With SportSpaﬂ");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
						shareBody);
				startActivity(Intent.createChooser(sharingIntent,
						"Share SportSpas"));
			}
		});

		btnMenu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ReportActivity.this, MenuActivity.class);
				startActivity(i);
			}
		});

		btnUlang.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ReportActivity.this, TipePertama.class);
				startActivity(i);
				finish();
			}
		});

		dh = new DatabaseHandler(getApplicationContext());
		smList = dh.getAllScore("");
		if (dh.getMaxScore() <= 0) {
			textScore.setText("0");
		} else {
			textScore.setText(dh.getMaxScore() + "");
		}

		if (dh.getMinTime() == null) {
			textTime.setText("00:00");
		} else {
			String detik = dh.getMinTime().length() > 2 ? dh.getMinTime()
					.substring(dh.getMinTime().length() - 2) : dh.getMinTime();
			String menit = dh.getMinTime().substring(0, 2);
			textTime.setText(menit + ":" + detik);
		}

		lsAdapter = new ListSkorAdapter(this, smList);

		listSkor.setAdapter(lsAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report, menu);
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
