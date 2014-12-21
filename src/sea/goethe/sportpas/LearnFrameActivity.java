package sea.goethe.sportpas;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LearnFrameActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learn_frame);

		ImageButton btnSport = (ImageButton) findViewById(R.id.btnPlayHoren);
		ImageButton btnAlat = (ImageButton) findViewById(R.id.imageButton2);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("Lernen SportSpaﬂ");

		btnSport.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(LearnFrameActivity.this,
						LearnTabActivity.class);
				i.putExtra("TIPE", "sport");
				startActivity(i);
				overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
			}
		});

		btnAlat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(LearnFrameActivity.this,
						LearnTabActivity.class);
				i.putExtra("TIPE", "alat");
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
