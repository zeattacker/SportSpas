package sea.goethe.sportpas.lib;

import sea.goethe.sportpas.R;
import sea.goethe.sportpas.TipePertama;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SelectQuizAdapter extends PagerAdapter {
	private LayoutInflater inflater;
	private Activity activity;
	private int[] gambarQuiz = { R.drawable.img_quiz_land,
			R.drawable.img_quiz_wasser, R.drawable.img_quiz_luft };
	private int[] gambarlockQuiz = { R.drawable.land_gembok,
			R.drawable.wasser_lock, R.drawable.luft_lock };
	private String[] judulQuiz = { "Land Sportarten", "Wasser Sportarten",
			"Luft Sportarten" };
	private SessionHandler sh;

	public SelectQuizAdapter(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return gambarQuiz.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == ((RelativeLayout) object);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((RelativeLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		// TODO Auto-generated method stub
		if(inflater == null){
			inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		View convertView = inflater.inflate(R.layout.layout_select_quiz, null);
		
		ImageView imgQuiz = (ImageView)convertView.findViewById(R.id.imgQuiz);
		TextView textJudulQuiz = (TextView)convertView.findViewById(R.id.textJudulQuiz);
		Button btnPlay = (Button)convertView.findViewById(R.id.btnPlay);
		sh = new SessionHandler(activity);
		
		
		if(position == 0){
			imgQuiz.setBackgroundResource(gambarQuiz[position]);
			btnPlay.setBackgroundResource(R.drawable.bg_btn_select_quiz);
			btnPlay.setEnabled(true);
		} else {
			if(sh.isLandDone()){
				imgQuiz.setBackgroundResource(gambarQuiz[position]);
				btnPlay.setBackgroundResource(R.drawable.bg_btn_select_quiz_disabled);
				btnPlay.setEnabled(false);
			} else if(sh.isWasserDone()){
				imgQuiz.setBackgroundResource(gambarQuiz[position]);
				btnPlay.setBackgroundResource(R.drawable.bg_btn_select_quiz_disabled);
				btnPlay.setEnabled(false);
			} else {
				imgQuiz.setBackgroundResource(gambarlockQuiz[position]);
				btnPlay.setBackgroundResource(R.drawable.bg_btn_select_quiz);
				btnPlay.setEnabled(true);
			}
		}
		textJudulQuiz.setText(judulQuiz[position]);
		
		btnPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(position == 0){
					Intent i = new Intent(activity, TipePertama.class);
					activity.startActivity(i);
				} else if(position == 1){
					//pindah ke laut
					Toast.makeText(activity, "Coming Soon", Toast.LENGTH_SHORT).show();
				} else {
					// pindah ke udara
					Toast.makeText(activity, "Coming Soon", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		((ViewPager) container).addView(convertView);

		return convertView;
	}
}
