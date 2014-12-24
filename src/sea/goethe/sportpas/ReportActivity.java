package sea.goethe.sportpas;

import sea.goethe.sportpas.lib.DatabaseHandler;
import sea.goethe.sportspas.model.ProgressModel;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ReportActivity extends ActionBarActivity {
	private DatabaseHandler dh;
	private TextView textScore, textTime,txtPbBaca,txtPbTulis,txtPbDengar,txtPbSpeak;
	private LinearLayout bagianShare;
	private Button btnUlang, btnMenu;
	private ProgressBar pbBaca,pbTulis,pbDengar,pbSpeak;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);
		
		textScore = (TextView) findViewById(R.id.txtScore);
		textTime = (TextView) findViewById(R.id.txtTime);
		btnUlang = (Button) findViewById(R.id.btnUlang);
		btnMenu = (Button) findViewById(R.id.btnMenu);
		bagianShare = (LinearLayout) findViewById(R.id.btnShare);
		dh = new DatabaseHandler(getApplicationContext());
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("T‰tigkeitsbericht");
		
		//bagian skill
		txtPbBaca = (TextView)findViewById(R.id.txtPbBaca);
		txtPbTulis = (TextView)findViewById(R.id.txtPbTulis);
		txtPbDengar = (TextView)findViewById(R.id.txtPbDengar);
		txtPbSpeak = (TextView)findViewById(R.id.txtPbSpeak);
		pbBaca = (ProgressBar)findViewById(R.id.pbBaca);
		pbBaca.setMax(100);
		pbTulis = (ProgressBar)findViewById(R.id.pbTulis);
		pbTulis.setMax(100);
		pbDengar = (ProgressBar)findViewById(R.id.pbDengar);
		pbDengar.setMax(100);
		pbSpeak = (ProgressBar)findViewById(R.id.pbSpeak);
		pbSpeak.setMax(100);
		
		ProgressModel pm = dh.getProgress(1);
		txtPbBaca.setText(checkProgress(pm.getRead()));
		txtPbTulis.setText(checkProgress(pm.getWrite()));
		txtPbDengar.setText(checkProgress(pm.getListen()));
		txtPbSpeak.setText(checkProgress(pm.getSpeak()));
		pbBaca.setProgress(pm.getRead());
		pbTulis.setProgress(pm.getWrite());
		pbDengar.setProgress(pm.getListen());
		pbSpeak.setProgress(pm.getSpeak());

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
	}
	
	private String checkProgress(int angka){
		String hasil = "";
		if(angka > 100){
			hasil = "100%";
		} else {
			hasil = angka + "%";
		}
		
		return hasil;
	}
}
