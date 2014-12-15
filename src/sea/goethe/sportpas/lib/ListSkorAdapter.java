package sea.goethe.sportpas.lib;


import java.util.List;

import sea.goethe.sportpas.R;
import sea.goethe.sportspas.model.ScoreModel;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListSkorAdapter extends BaseAdapter{
	private Activity activity;
	private LayoutInflater inflater;
	private List<ScoreModel> scoreModel;
	
	public ListSkorAdapter(Activity a,List<ScoreModel> scoreModel) {
		// TODO Auto-generated constructor stub
		activity = a;
		this.scoreModel = scoreModel;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return scoreModel.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return scoreModel.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null)
			convertView = inflater.inflate(R.layout.layout_listprogress, null);
		
		ImageView imgScore = (ImageView)convertView.findViewById(R.id.imgFinal);
		TextView txtScore = (TextView)convertView.findViewById(R.id.txtScoreProgress);
		TextView txtTime = (TextView)convertView.findViewById(R.id.txtScoreTime);
		TextView txtTgl = (TextView)convertView.findViewById(R.id.txtTanggalProgress);
		
		
		
		ScoreModel sm = scoreModel.get(position);
		
		if(sm.getScore() > 75){
			imgScore.setBackgroundResource(R.drawable.piala_menang);
		} else {
			imgScore.setBackgroundResource(R.drawable.piala_kalah);
		}
		
		txtScore.setText("Score : " + sm.getScore());
		String detik = sm.getTime().length() > 2 ? sm.getTime().substring(sm.getTime().length() - 2) : sm.getTime();
		String menit = sm.getTime().substring(0,2);
		txtTime.setText("Waktu : " + menit + ":" + detik);
		txtTgl.setText("Tanggal : " + sm.getTGL());
		
		return convertView;
	}

}
