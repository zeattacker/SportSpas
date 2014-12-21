package sea.goethe.sportpas.lib;

import sea.goethe.sportpas.MenuActivity;
import sea.goethe.sportpas.R;
import sea.goethe.sportspas.model.HorenQuizModel;
import sea.goethe.sportspas.model.LearnModel;
import sea.goethe.sportspas.model.MultipleQuizModel;
import sea.goethe.sportspas.model.TFQuizModel;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WelcomeAdapter extends PagerAdapter {
	private Activity activity;
	private String[] textnya = { "Jetzt lernt man Deutsch einfacher",
			"Durch den Quiz übst dein Deutsch!", "" };
	private int[] gambarnya = { R.drawable.welcome_3, R.drawable.welcome_2,
			R.drawable.welcome_1 };
	private LayoutInflater inflater;
	TextView txtWelcome;
	ImageView imgWelcome;
	Button btnLanjut;
	private ProgressDialog pDialog;
	private DatabaseHandler dh;
	private SessionHandler sh;

	public WelcomeAdapter(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return textnya.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == ((RelativeLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub

		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rootView = inflater.inflate(R.layout.layout_welcome, container,
				false);

		txtWelcome = (TextView) rootView.findViewById(R.id.txtWelcome);
		imgWelcome = (ImageView) rootView.findViewById(R.id.imgWelcome);
		btnLanjut = (Button) rootView.findViewById(R.id.btnLanjut);
		sh = new SessionHandler(activity);
		dh = new DatabaseHandler(activity);

		txtWelcome.setText(textnya[position]);
		imgWelcome.setBackgroundResource(gambarnya[position]);

		if (position == 2) {
			txtWelcome.setVisibility(View.INVISIBLE);
			btnLanjut.setVisibility(View.VISIBLE);

			btnLanjut.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					new generateData().execute();
				}
			});
		}

		((ViewPager) container).addView(rootView);

		return rootView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((RelativeLayout) object);
	}

	class generateData extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			// Multiple Choice Gambar
			dh.addMP(new MultipleQuizModel("Fußball", "10", "img_sport_7",
					"img_alat_5", "img_alat_2", "img_sport_4", 0, "gambar"));
			dh.addMP(new MultipleQuizModel("Surfbrett", "10", "img_alat_3",
					"img_sport_3", "img_sport_1", "img_sport_4", 3, "gambar"));
			dh.addMP(new MultipleQuizModel("Laufen", "10", "img_alat_3",
					"img_sport_2", "img_alat_7", "img_alat_5", 1, "gambar"));
			dh.addMP(new MultipleQuizModel("Schwimmen", "10", "img_alat_3",
					"img_sport_5", "img_sport_3", "img_alat_8", 1, "gambar"));
			dh.addMP(new MultipleQuizModel("Tennis", "10", "img_sport_6",
					"img_alat_5", "img_sport_1", "img_alat_3", 0, "gambar"));
			dh.addMP(new MultipleQuizModel("Schrimmbrille", "10",
					"img_sport_7", "img_sport_8", "img_alat_5", "img_alat_6",
					2, "gambar"));
			dh.addMP(new MultipleQuizModel("Schach", "10", "img_alat_4",
					"img_alat_7", "img_alat_2", "img_sport_3", 3, "gambar"));
			dh.addMP(new MultipleQuizModel("Surfen", "10", "img_alat_3",
					"img_alat_1", "img_sport_4", "img_sport_3", 2, "gambar"));
			dh.addMP(new MultipleQuizModel("Federballschläger", "10",
					"img_sport_5", "img_alat_8", "img_alat_7", "img_sport_2",
					1, "gambar"));
			dh.addMP(new MultipleQuizModel("Lauf-schuhe", "10", "img_alat_2",
					"img_sport_1", "img_sport_8", "img_alat_5", 0, "gambar"));

			// Multiple Choice Text
			dh.addMP(new MultipleQuizModel("img_sport_4", "10", "Schach",
					"Laufschuhe", "Surfen", "Surfbretter", 2, "text"));
			dh.addMP(new MultipleQuizModel("img_sport_4", "10", "Schach",
					"Laufschuhe", "Surfen", "Surfbretter", 2, "text"));
			dh.addMP(new MultipleQuizModel("img_sport_4", "10", "Schach",
					"Laufschuhe", "Surfen", "Surfbretter", 2, "text"));
			dh.addMP(new MultipleQuizModel("img_sport_6", "10", "Laufschuhe",
					"Basketball", "Laufen", "Tennis", 3, "text"));
			dh.addMP(new MultipleQuizModel("img_sport_6", "10", "Laufschuhe",
					"Basketball", "Laufen", "Tennis", 3, "text"));
			dh.addMP(new MultipleQuizModel("img_sport_6", "10", "Laufschuhe",
					"Basketball", "Laufen", "Tennis", 3, "text"));
			dh.addMP(new MultipleQuizModel("img_alat_1", "10", "Federball",
					"Rennfahren", "Ball", "Laufen", 0, "text"));
			dh.addMP(new MultipleQuizModel("img_alat_1", "10", "Federball",
					"Rennfahren", "Ball", "Laufen", 0, "text"));
			dh.addMP(new MultipleQuizModel("img_alat_1", "10", "Federball",
					"Rennfahren", "Ball", "Laufen", 0, "text"));
			dh.addMP(new MultipleQuizModel("img_sport_4", "10", "Schach",
					"Laufschuhe", "Surfen", "Surfbretter", 2, "text"));

			// True Or False Text
			dh.addTF(new TFQuizModel("img_alat_7", "Ball für Fußball genutzt",
					0));
			dh.addTF(new TFQuizModel("img_alat_7",
					"Ball für Fußball genutzt 2", 0));
			dh.addTF(new TFQuizModel("img_sport_5", "Das Spiel Fußball 3", 1));
			dh.addTF(new TFQuizModel("img_sport_5", "Das Spiel Fußball 4", 1));
			dh.addTF(new TFQuizModel("img_alat_7",
					"Ball für Fußball genutzt 5", 0));
			dh.addTF(new TFQuizModel("img_alat_7",
					"Ball für Fußball genutzt 6", 0));
			dh.addTF(new TFQuizModel("img_sport_5", "Das Spiel Fußball", 1));
			dh.addTF(new TFQuizModel("img_sport_5", "Das Spiel Fußball 8", 1));
			dh.addTF(new TFQuizModel("img_alat_7",
					"Ball für Fußball genutzt 9", 0));
			dh.addTF(new TFQuizModel("img_sport_5", "Das Spiel Fußball 10", 1));

			// Horen Text
			dh.addHoren(new HorenQuizModel("soal_horen_1", "A"));
			dh.addHoren(new HorenQuizModel("soal_horen_1", "B"));
			dh.addHoren(new HorenQuizModel("soal_horen_1", "C"));

			// Add learn Sport
			dh.addLearn(new LearnModel("der Basketball", "Ich Spiele Basketball", "img_sport_1",
					"sound_sport_1", "darat", "sport"));
			dh.addLearn(new LearnModel("Laufen", "Ich Laufe", "img_sport_2",
					"sound_sport_2", "darat", "sport"));
			dh.addLearn(new LearnModel("der Schach", "Ich Spiele Schach", "img_sport_3",
					"sound_sport_3", "darat", "sport"));
			dh.addLearn(new LearnModel("Surfen", "Ich Surfe", "img_sport_4",
					"sound_sport_4", "air", "sport"));
			dh.addLearn(new LearnModel("Schwimmen", "Ich Schwimme", "img_sport_5",
					"sound_sport_5", "air", "sport"));
			dh.addLearn(new LearnModel("der Tennis", "Ich Spiele Tennis", "img_sport_6",
					"sound_sport_6", "darat", "sport"));
			dh.addLearn(new LearnModel("der Fubßall", "Ich Spiele Fubßall", "img_sport_7",
					"sound_sport_7", "darat", "sport"));
			dh.addLearn(new LearnModel("der Volleyball", "Ich Spiele Volleyball", "img_sport_8",
					"sound_sport_8", "darat", "sport"));
			dh.addLearn(new LearnModel("das Jet Ski", "Ich Spiele Jet Ski", "img_sport_9",
					"sound_sport_9", "air", "sport"));
			dh.addLearn(new LearnModel("das Flößerei", "Ich bin Flößerei", "img_sport_10",
					"sound_sport_10", "air", "sport"));
			dh.addLearn(new LearnModel("das Schnorcheln", "Ich bin Schnorcheln", "img_sport_11",
					"sound_sport_11", "air", "sport"));
			dh.addLearn(new LearnModel("das Luftballon", "", "img_sport_12",
					"sound_sport_12", "udara", "sport"));
			dh.addLearn(new LearnModel("das Gleitschirmfliegen", "", "img_sport_13",
					"sound_sport_13", "udara", "sport"));

			// Add learn Alat
			dh.addLearn(new LearnModel("der Federball", "Ich leihe der Federball", "img_alat_1",
					"sound_alat_1", "darat", "alat"));
			dh.addLearn(new LearnModel("die Laufschuhe", "Ich benutze Laufschuhe", "img_alat_2",
					"sound_alat_2", "darat", "alat"));
			dh.addLearn(new LearnModel("das Schachbrett", "Ich kaufe Schachbrett", "img_alat_3",
					"sound_alat_3", "darat", "alat"));
			dh.addLearn(new LearnModel("das Surfbrett", "Ich leihe Surfbrett", "img_alat_4",
					"sound_alat_4", "air", "alat"));
			dh.addLearn(new LearnModel("die Schrimbrille", "Ich kaufe Schrimbrille", "img_alat_5",
					"sound_alat_5", "air", "alat"));
			dh.addLearn(new LearnModel("der Tennisschläger", "Ich leihe Tennisschläger", "img_alat_6",
					"sound_alat_6", "darat", "alat"));
			dh.addLearn(new LearnModel("der Ball", "Ich kaufe Ball", "img_alat_7",
					"sound_alat_7", "darat", "alat"));
			dh.addLearn(new LearnModel("der Federballschläger", "Ich leihe Federballschläger",
					"img_alat_8", "sound_alat_8", "darat", "alat"));
			dh.addLearn(new LearnModel("das Ruder", "ich kaufe Ruder", "img_alat_9",
					"sound_alat_9", "air", "alat"));
			dh.addLearn(new LearnModel("der Badeanzug", "Ich kaufe Badeanzug", "img_alat_10",
					"sound_alat_10", "air", "alat"));
			dh.addLearn(new LearnModel("der Schwimmgürtel", "Ich leihe Schwimmer", "img_alat_11",
					"sound_alat_11", "air", "alat"));

			return null;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			pDialog = new ProgressDialog(activity);
			pDialog.setMessage("Generating Data");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();

			// create session
			sh.createRegisSession("sudahdigenerate");

			// throw to menu
			Intent i = new Intent(activity, MenuActivity.class);
			activity.startActivity(i);
			activity.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
			activity.finish();
		}
	}
}
