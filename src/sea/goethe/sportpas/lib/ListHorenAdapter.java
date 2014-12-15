package sea.goethe.sportpas.lib;

import java.util.List;

import sea.goethe.sportpas.R;
import sea.goethe.sportpas.TipePertama;
import sea.goethe.sportspas.model.HorenQuizModel;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ListHorenAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<HorenQuizModel> horenQuizList;
	public static boolean isFilled = false;
	
	public ListHorenAdapter(Activity act, List<HorenQuizModel> horenQuizList) {
		// TODO Auto-generated constructor stub
		activity = act;
		this.horenQuizList = horenQuizList;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return horenQuizList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return horenQuizList.get(position);
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
			convertView = inflater.inflate(R.layout.layout_listhoren, null);
		
		ImageButton btnHorenPlay = (ImageButton)convertView.findViewById(R.id.btnPlayHoren);
		final EditText txtHorenJawab = (EditText)convertView.findViewById(R.id.textHorenJawab);
		final ImageView imgHorenStatus = (ImageView)convertView.findViewById(R.id.imgHorenStatus);
		
		final HorenQuizModel hqm = horenQuizList.get(position);
		
		txtHorenJawab.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				if (actionId == EditorInfo.IME_ACTION_SEARCH ||
			            actionId == EditorInfo.IME_ACTION_DONE ||
			            event.getAction() == KeyEvent.ACTION_DOWN &&
			            event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
			        	isFilled = true;
			        	if(txtHorenJawab.getText().toString().equalsIgnoreCase(hqm.getJawaban())){
			        		imgHorenStatus.setBackgroundResource(R.drawable.img_answer_true);
			        		TipePertama.score += 3;
			        		TipePertama.soundBenar.start();
			        	} else {
			        		imgHorenStatus.setBackgroundResource(R.drawable.img_answer_false);
			        		TipePertama.soundSalah.start();
			        	}
			        	
			        	txtHorenJawab.setEnabled(false);
			           return true; // consume.               
			    }
				return false;
			}
		});
		btnHorenPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MediaPlayer mp = MediaPlayer.create(activity, activity.getResources().getIdentifier(hqm.getMusic(), "raw", activity.getPackageName()));
				mp.start();
			}
		});
		
		return convertView;
	}

}
