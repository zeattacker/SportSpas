package sea.goethe.sportpas.lib;

import sea.goethe.sportpas.R;
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

public class ListLearnAdapter extends BaseAdapter{
	private Activity activity;
	private LayoutInflater inflater;
	String tipeLearn = "";
	private int soundNya;
	private String[] sportItem = {"der Basketball","Laufen","der Schach","Surfen","Schwimmen","Tennis","der Fuﬂball","der Volleyball"};
	private int[] sportImg = {R.drawable.img_sport_1,R.drawable.img_sport_2,R.drawable.img_sport_3,R.drawable.img_sport_4, R.drawable.img_sport_5, R.drawable.img_sport_6, R.drawable.img_sport_7, R.drawable.img_sport_8};
	private int[] sportRaw = {R.raw.sound_sport_1,R.raw.sound_sport_2,R.raw.sound_sport_3,R.raw.sound_sport_4,R.raw.sound_sport_5,R.raw.sound_sport_6,R.raw.sound_sport_7,R.raw.sound_sport_8};
	private String[] alatItem = {"der Federball","die Laufschuhe","das Schachbrett","das Surfbrett","die Schrimmbrille","der Tennisschl‰ger","der Ball","der Federballschl‰ger"};
	private int[] alatImg = {R.drawable.img_alat_1,R.drawable.img_alat_2,R.drawable.img_alat_3,R.drawable.img_alat_4,R.drawable.img_alat_5,R.drawable.img_alat_6,R.drawable.img_alat_7,R.drawable.img_alat_8};
	private int[] alatRaw = {R.raw.sport_alat_1,R.raw.sport_alat_2,R.raw.sport_alat_3,R.raw.sport_alat_4,R.raw.sport_alat_5,R.raw.sport_alat_6,R.raw.sport_alat_7,R.raw.sport_alat_8};
	
	public ListLearnAdapter(Activity a,String tipeLearn) {
		// TODO Auto-generated constructor stub
		activity = a;
		this.tipeLearn = tipeLearn;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sportItem.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return sportItem[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView == null)
			convertView = inflater.inflate(R.layout.layout_listlearn, null);
		
		ImageView imgItem = (ImageView)convertView.findViewById(R.id.imgItem);
		TextView titleItem = (TextView)convertView.findViewById(R.id.titleItem);
		ImageButton playNormal = (ImageButton)convertView.findViewById(R.id.playNormal);
		ImageButton playSlow = (ImageButton)convertView.findViewById(R.id.playSlow);
		
		if(tipeLearn.equalsIgnoreCase("sport")){
			imgItem.setBackgroundResource(sportImg[position]);
			titleItem.setText(sportItem[position]);
			playNormal.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					@SuppressWarnings("deprecation")
					SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
					
					soundNya = sp.load(activity, sportRaw[position], 1);
					
					
					
					sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
						
						@Override
						public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
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
					
					soundNya = sp.load(activity, sportRaw[position], 1);
					
					
					
					sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
						
						@Override
						public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
							// TODO Auto-generated method stub
							soundPool.play(soundNya, 1, 1, 0, 0, (float)0.7);
						}
					});
				}
			});
		} else if(tipeLearn.equalsIgnoreCase("alat")){
			imgItem.setBackgroundResource(alatImg[position]);
			titleItem.setText(alatItem[position]);
			playNormal.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					@SuppressWarnings("deprecation")
					SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
					
					soundNya = sp.load(activity, alatRaw[position], 1);
					
					sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
						
						@Override
						public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
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
					
					soundNya = sp.load(activity, alatRaw[position], 1);
					
					
					
					sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
						
						@Override
						public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
							// TODO Auto-generated method stub
							soundPool.play(soundNya, 1, 1, 0, 0, (float)0.7);
						}
					});
				}
			});
		}
		
		
		return convertView;
	}

}
