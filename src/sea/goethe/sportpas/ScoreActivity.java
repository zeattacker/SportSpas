package sea.goethe.sportpas;

import java.sql.Timestamp;
import java.util.Date;

import sea.goethe.sportpas.lib.DatabaseHandler;
import sea.goethe.sportspas.model.ScoreModel;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreActivity extends ActionBarActivity {
	private ImageView imgScore;
	private TextView textScore;
	private Button btnLearn,btnProgress;
	public static MediaPlayer soundWin,soundLose;
	private DatabaseHandler dh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		
		imgScore = (ImageView)findViewById(R.id.imgScore);
		textScore = (TextView)findViewById(R.id.txtScoreProgress);
		btnLearn = (Button)findViewById(R.id.btnLearn);
		btnProgress = (Button)findViewById(R.id.btnProgress);
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		dh = new DatabaseHandler(getApplicationContext());
		toolbar.setTitle("Gratulation!");
		soundWin = MediaPlayer.create(this, R.raw.sound_win);
		soundLose = MediaPlayer.create(this, R.raw.sound_lose);
		
		int score = getIntent().getIntExtra("SCORE", 10);
		String time = getIntent().getStringExtra("TIME");
		
		if(TipePertama.soundBenar.isPlaying()){
			TipePertama.soundBenar.stop();
		} else {
			TipePertama.soundSalah.stop();
		}
		
		Date date = new Date();
		
		dh.addScore(new ScoreModel(score, new Timestamp(date.getTime()).toString(), time, ""));
		
		if(score <= 75){
			soundLose.start();
			textScore.setText(String.valueOf(score));
			imgScore.setBackgroundResource(R.drawable.piala_kalah);
		} else {
			soundWin.start();
			textScore.setText(String.valueOf(score));
			imgScore.setBackgroundResource(R.drawable.piala_menang);
		}
		
		btnLearn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ScoreActivity.this, MainFrameActivity.class);
				i.putExtra("MENU", "learn");
				startActivity(i);
				finish();
			}
		});
		
		btnProgress.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ScoreActivity.this, ReportActivity.class);
				startActivity(i);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
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

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch (which) {
				case DialogInterface.BUTTON_POSITIVE:
					Intent i = new Intent(ScoreActivity.this, MenuActivity.class);
					startActivity(i);
					break;
					
				case DialogInterface.BUTTON_NEGATIVE:
					break;

				default:
					break;
				}
			}
		};
		
		AlertDialog.Builder build = new AlertDialog.Builder(this);
		build.setMessage("Surrender ?")
		.setPositiveButton("Ja", dialogClickListener)
		.setNegativeButton("Nein", dialogClickListener);
	}
	
	
}
