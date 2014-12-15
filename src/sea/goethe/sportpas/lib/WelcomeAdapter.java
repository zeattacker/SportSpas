package sea.goethe.sportpas.lib;

import sea.goethe.sportpas.MenuActivity;
import sea.goethe.sportpas.R;
import sea.goethe.sportspas.model.HorenQuizModel;
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
			"Durch den Quiz �bst dein Deutsch!",
			""};
	private int[] gambarnya = { R.drawable.welcome_3, R.drawable.welcome_2,
			R.drawable.welcome_1};
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
			
			//Multiple Choice Gambar
			dh.addMP(new MultipleQuizModel("Fu�ball", "10", "img_sport_4",
					"img_alat_5", "img_alat_2", "img_sport_7", 3, "gambar"));
			dh.addMP(new MultipleQuizModel("Surfbrett", "10", "img_alat_3",
					"img_sport_1", "img_sport_4", "img_sport_3", 2, "gambar"));
			dh.addMP(new MultipleQuizModel("Laufen", "10", "img_alat_3",
					"img_alat_5", "img_alat_7", "img_sport_2", 3, "gambar"));
			dh.addMP(new MultipleQuizModel("Schwimmen", "10", "img_alat_3",
					"img_sport_5", "img_sport_3", "img_alat_8", 1, "gambar"));
			dh.addMP(new MultipleQuizModel("Tennis", "10", "img_sport_6",
					"img_alat_5", "img_sport_1", "img_alat_3", 0, "gambar"));
			dh.addMP(new MultipleQuizModel("Schrimmbrille", "10", "img_sport_7",
					"img_sport_8", "img_alat_5", "img_alat_6", 2, "gambar"));
			dh.addMP(new MultipleQuizModel("Schach", "10", "img_alat_4",
					"img_alat_7", "img_alat_2", "img_sport_3", 3, "gambar"));
			dh.addMP(new MultipleQuizModel("Surfen", "10", "img_alat_3",
					"img_alat_1", "img_sport_4", "img_sport_3", 2, "gambar"));
			dh.addMP(new MultipleQuizModel("Federballschl�ger", "10", "img_sport_5",
					"img_alat_8", "img_alat_7", "img_sport_2", 1, "gambar"));
			dh.addMP(new MultipleQuizModel("Lauf-schuhe", "10", "img_alat_2",
					"img_sport_1", "img_sport_8", "img_alat_5", 0, "gambar"));
			
			//Multiple Choice Text
			dh.addMP(new MultipleQuizModel("img_sport_4","10","Schach","Laufschuhe","Surfen","Surfbretter",2,"text"));
			dh.addMP(new MultipleQuizModel("img_sport_4","10","Schach","Laufschuhe","Surfen","Surfbretter",2,"text"));
			dh.addMP(new MultipleQuizModel("img_sport_4","10","Schach","Laufschuhe","Surfen","Surfbretter",2,"text"));
			dh.addMP(new MultipleQuizModel("img_sport_6","10","Laufschuhe","Basketball","Laufen","Tennis",3,"text"));
			dh.addMP(new MultipleQuizModel("img_sport_6","10","Laufschuhe","Basketball","Laufen","Tennis",3,"text"));
			dh.addMP(new MultipleQuizModel("img_sport_6","10","Laufschuhe","Basketball","Laufen","Tennis",3,"text"));
			dh.addMP(new MultipleQuizModel("img_alat_1","10","Federball","Rennfahren","Ball","Laufen",0,"text"));
			dh.addMP(new MultipleQuizModel("img_alat_1","10","Federball","Rennfahren","Ball","Laufen",0,"text"));
			dh.addMP(new MultipleQuizModel("img_alat_1","10","Federball","Rennfahren","Ball","Laufen",0,"text"));
			dh.addMP(new MultipleQuizModel("img_sport_4","10","Schach","Laufschuhe","Surfen","Surfbretter",2,"text"));
			
			//True Or False Text
			dh.addTF(new TFQuizModel("img_alat_1", "Federball is used for Tennis", 1));
			dh.addTF(new TFQuizModel("img_alat_1", "Federball is used for Tennis", 1));
			dh.addTF(new TFQuizModel("img_alat_1", "Federball is used for Tennis", 1));
			dh.addTF(new TFQuizModel("img_alat_1", "Federball is used for Tennis", 1));
			dh.addTF(new TFQuizModel("img_alat_1", "Federball is used for Tennis", 1));
			dh.addTF(new TFQuizModel("img_alat_1", "Federball is used for Tennis", 1));
			dh.addTF(new TFQuizModel("img_alat_1", "Federball is used for Tennis", 1));
			dh.addTF(new TFQuizModel("img_alat_1", "Federball is used for Tennis", 1));
			dh.addTF(new TFQuizModel("img_alat_1", "Federball is used for Tennis", 1));
			dh.addTF(new TFQuizModel("img_alat_1", "Federball is used for Tennis", 1));
			
			// Horen Text
			dh.addHoren(new HorenQuizModel("soal_horen_1", "Lorem"));
			dh.addHoren(new HorenQuizModel("soal_horen_1", "Lorem"));
			dh.addHoren(new HorenQuizModel("soal_horen_1", "Lorem"));
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
			
			//create session
			sh.createRegisSession("sudahdigenerate");
			
			//throw to menu
			Intent i = new Intent(activity, MenuActivity.class);
			activity.startActivity(i);
			activity.finish();
		}
	}
}
