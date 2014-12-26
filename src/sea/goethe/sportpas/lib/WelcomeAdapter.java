package sea.goethe.sportpas.lib;

import sea.goethe.sportpas.MenuActivity;
import sea.goethe.sportpas.R;
import sea.goethe.sportspas.model.LearnModel;
import sea.goethe.sportspas.model.MultipleQuizModel;
import sea.goethe.sportspas.model.ProgressModel;
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
			"Durch den Quiz �bst dein Deutsch!", "" };
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
			dh.addMP(new MultipleQuizModel("das Radfahren", "10", "img_sport_1",
					"img_sport_6", "img_sport_15", "img_alat_7", 2, "darat"));
			dh.addMP(new MultipleQuizModel("das Schach", "10", "img_sport_7",
					"img_sport_3", "img_alat_2", "img_alat_1", 1, "darat"));
			dh.addMP(new MultipleQuizModel("der Tennisschl�ger", "10", "img_alat_4",
					"img_sport_8", "img_sport_2", "img_alat_6", 3, "darat"));
			dh.addMP(new MultipleQuizModel("der Volleyball", "10", "img_alat_15",
					"img_alat_2", "img_sport_7", "img_sport_8", 3, "darat"));
			dh.addMP(new MultipleQuizModel("der Basketball", "10", "img_sport_1",
					"img_alat_16", "img_alat_17", "img_alat_18", 0, "darat"));
			dh.addMP(new MultipleQuizModel("img_sport_6", "10", "der Tennischl�ger",
					"das Tennis", "der Tennisplatz", "das Surfbrett", 1, "darat"));
			dh.addMP(new MultipleQuizModel("img_alat_2", "10", "Stick Golf",
					"Schwimmen", "Laufschuhe", "das Laufen", 2, "darat"));
			dh.addMP(new MultipleQuizModel("img_alat_1", "10", "der Federball",
					"der Ball", "der Federballschl�ger", "Rennhelm", 0, "darat"));
			dh.addMP(new MultipleQuizModel("img_alat_3", "10", "Fu�ballplatz",
					"das Schachbrett", "das Surfbrett", "dass Schach", 1, "darat"));
			dh.addMP(new MultipleQuizModel("img_alat_18", "10", "Basketball",
					"Basketballkorb", "Lauffeld", "Tennischl�ger", 1, "darat"));
			
			//air
			dh.addMP(new MultipleQuizModel("die Fl��erei", "10", "img_sport_9",
					"img_sport_4", "img_sport_11", "img_sport_10", 3, "air"));
			dh.addMP(new MultipleQuizModel("das Schwimmen", "10", "img_sport_5",
					"img_alat_9", "img_alat_5", "img_alat_11", 0, "air"));
			dh.addMP(new MultipleQuizModel("das Surfen", "10", "img_alat_4",
					"img_sport_4", "img_alat_10", "img_sport_9", 1, "air"));
			dh.addMP(new MultipleQuizModel("img_alat_10", "10", "Schnorcheln",
					"Badeanzug", "Paddel", "Surfbrett", 1, "air"));
			dh.addMP(new MultipleQuizModel("img_sport_9", "10", "Rettungsweste",
					"Schrimbrille", "Jetski", "Schwimmen", 2, "air"));
			dh.addMP(new MultipleQuizModel("img_alat_11", "10", "die Rettungsweste",
					"der Rettungsweste", "das Rettungsweste", "du Rettungsweste", 0, "air"));
			dh.addMP(new MultipleQuizModel("img_alat_9", "10", "du Paddle",
					"der Paddle", "die Paddle", "das Paddle", 3, "air"));
			dh.addMP(new MultipleQuizModel("img_alat_11", "10", "ob Rettungsweste zum Schwimmen ?",
					"", "", "", 0, "air"));
			dh.addMP(new MultipleQuizModel("img_alat_9", "10", "ob Paddle zum Surfen ?",
					"", "", "", 1, "air"));
			dh.addMP(new MultipleQuizModel("img_alat_5", "10", "ob Schrimbrille zum Schach ?",
					"", "", "", 1, "air"));
			
			//udara
			dh.addMP(new MultipleQuizModel("das Gleitschirmfliegen", "20", "img_sport_3",
					"img_alat_13", "img_sport_13", "img_alat_14", 2, "udara"));
			dh.addMP(new MultipleQuizModel("img_alat_12", "20", "das Gleitschirmfliegen",
					"der Hei�luftballon", "der Flugzeug", "das Segelflugzeug", 0, "udara"));
			dh.addMP(new MultipleQuizModel("img_alat_14", "20", "ob Hei�luftballon zum Luft ?",
					"", "", "", 0, "udara"));
			dh.addMP(new MultipleQuizModel("das Segelflugzeug", "10", "Ich bin ein Pilot",
					"Ich Spiele Luftballon", "", "", 0, "udara"));
			dh.addMP(new MultipleQuizModel("der Flugzeug", "10", "",
					"", "", "", 0, "udara"));
			dh.addMP(new MultipleQuizModel("Ich bestieg das Flugzeug", "10", "",
					"", "", "", 0, "udara"));

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
			dh.addLearn(new LearnModel("der Fub�all", "Ich Spiele Fub�all", "img_sport_7",
					"sound_sport_7", "darat", "sport"));
			dh.addLearn(new LearnModel("der Volleyball", "Ich Spiele Volleyball", "img_sport_8",
					"sound_sport_8", "darat", "sport"));
			dh.addLearn(new LearnModel("das Jet Ski", "Ich Spiele Jet Ski", "img_sport_9",
					"sound_sport_9", "air", "sport"));
			dh.addLearn(new LearnModel("die Fl��erei", "Ich bin Fl��erei", "img_sport_10",
					"sound_sport_10", "air", "sport"));
			dh.addLearn(new LearnModel("das Schnorcheln", "Ich bin Schnorcheln", "img_sport_11",
					"sound_sport_11", "air", "sport"));
			dh.addLearn(new LearnModel("das Luftballon", "Ich spiele Luftballon", "img_sport_12",
					"sound_sport_12", "udara", "sport"));
			dh.addLearn(new LearnModel("das Gleitschirmfliegen", "Ich spiele Gleitschirmfliegen", "img_sport_13",
					"sound_sport_13", "udara", "sport"));
			dh.addLearn(new LearnModel("das Segelflugzeug", "Ich spiele Segelflugzeug", "img_sport_14",
					"sound_sport_14", "udara", "sport"));

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
			dh.addLearn(new LearnModel("der Tennisschl�ger", "Ich leihe Tennisschl�ger", "img_alat_6",
					"sound_alat_6", "darat", "alat"));
			dh.addLearn(new LearnModel("der Ball", "Ich kaufe Ball", "img_alat_7",
					"sound_alat_7", "darat", "alat"));
			dh.addLearn(new LearnModel("der Federballschl�ger", "Ich leihe Federballschl�ger",
					"img_alat_8", "sound_alat_8", "darat", "alat"));
			dh.addLearn(new LearnModel("das Paddel", "ich kaufe Paddel", "img_alat_9",
					"sound_alat_9", "air", "alat"));
			dh.addLearn(new LearnModel("der Badeanzug", "Ich kaufe Badeanzug", "img_alat_10",
					"sound_alat_10", "air", "alat"));
			dh.addLearn(new LearnModel("die Rettungsweste", "Ich leihe Rettungsweste", "img_alat_11",
					"sound_alat_11", "air", "alat"));
			dh.addLearn(new LearnModel("das Segelflugzeug", "ich kaufe Segelflugzeug", "img_alat_12",
					"sound_alat_12", "udara", "alat"));
			dh.addLearn(new LearnModel("der Flugzeug", "Ich kaufe Flugzeug", "img_alat_13",
					"sound_alat_13", "udara", "alat"));
			dh.addLearn(new LearnModel("der Hei�luftballon", "Ich leihe Hei�luftballon", "img_alat_14",
					"sound_alat_14", "udara", "alat"));
			
			//inisialisasi progress
			dh.addProgress(new ProgressModel(0, 0, 0, 0));

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
