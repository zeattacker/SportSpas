package sea.goethe.sportpas;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuActivity extends ActionBarActivity {
	private ImageButton btnLearn, btnGame, btnProgress,btnReward;
	private Button btnAbout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		btnLearn = (ImageButton) findViewById(R.id.btnLearn);
		btnGame = (ImageButton) findViewById(R.id.btnGame);
		btnAbout = (Button) findViewById(R.id.btnAbout);
		btnProgress = (ImageButton) findViewById(R.id.btnProgress);
		btnReward = (ImageButton)findViewById(R.id.btnReward);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		toolbar.setTitle("Wilkommen zu SportSpaﬂ");

		btnLearn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MenuActivity.this,
						LearnFrameActivity.class);
				i.putExtra("MENU", "learn");
				startActivity(i);
				overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
			}
		});

		btnGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MenuActivity.this, SelectQuiz.class);
				startActivity(i);
				overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
			}
		});

		btnAbout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MenuActivity.this,
						AboutActivity.class);
				i.putExtra("MENU", "about");
				startActivity(i);
				overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
			}
		});

		btnProgress.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MenuActivity.this, ReportActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
			}
		});
		
		btnReward.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MenuActivity.this, RewardActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
	}
	
	
}
