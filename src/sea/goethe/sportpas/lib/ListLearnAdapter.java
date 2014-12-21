package sea.goethe.sportpas.lib;

import java.util.List;

import sea.goethe.sportpas.R;
import sea.goethe.sportspas.model.LearnModel;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ListLearnAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	String tipeLearn = "";
	private int soundNya;
	private List<LearnModel> learnList;

	public ListLearnAdapter(Activity a, List<LearnModel> learnList) {
		// TODO Auto-generated constructor stub
		activity = a;
		this.learnList = learnList;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return learnList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return learnList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		if (convertView == null)
			convertView = inflater.inflate(R.layout.layout_listlearn, null);

		ImageView imgItem = (ImageView) convertView.findViewById(R.id.imgItem);
		TextView titleItem = (TextView) convertView
				.findViewById(R.id.titleItem);
		TextView titleKalimat = (TextView)convertView.findViewById(R.id.titleKalimat);
		ImageButton playNormal = (ImageButton) convertView
				.findViewById(R.id.playNormal);
		ImageButton playSlow = (ImageButton) convertView
				.findViewById(R.id.playSlow);

		final LearnModel lm = learnList.get(position);
		imgItem.setBackgroundResource(activity.getResources().getIdentifier(
				lm.getGambar(), "drawable", activity.getPackageName()));
		titleItem.setText(lm.getKata());
		titleKalimat.setText(lm.getKalimat());
		playNormal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				@SuppressWarnings("deprecation")
				SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

				soundNya = sp.load(
						activity,
						activity.getResources().getIdentifier(lm.getMusic(),
								"raw", activity.getPackageName()), 1);

				sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

					@Override
					public void onLoadComplete(SoundPool soundPool,
							int sampleId, int status) {
						// TODO Auto-generated method stub
						soundPool.play(soundNya, 1, 1, 0, 0, 1);
					}
				});
			}
		});

		playSlow.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				@SuppressWarnings("deprecation")
				SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

				soundNya = sp.load(
						activity,
						activity.getResources().getIdentifier(lm.getMusic(),
								"raw", activity.getPackageName()), 1);

				sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

					@Override
					public void onLoadComplete(SoundPool soundPool,
							int sampleId, int status) {
						// TODO Auto-generated method stub
						soundPool.play(soundNya, 1, 1, 0, 0, (float) 0.7);
					}
				});
			}
		});

		return convertView;
	}

}
