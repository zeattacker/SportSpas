package sea.goethe.sportpas.lib;

import java.util.Locale;

import sea.goethe.sportpas.R;
import android.app.Activity;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class ListDialogAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private TextToSpeech tts;
	private String[] dialogCowok = {"Hallo Angela","Möchtest du mit mir spielen ?","Kein Problem. Treffen wir uns heute Nachmittag auf dem Schulhof?","Tschüss!"};
	private String[] dialogCewek = {"Hallo Rama","Gerne, aber ich habe noch nie Federball gespielt.","Okay, Rama","Tschüss!"};
	
	public ListDialogAdapter(Activity act) {
		// TODO Auto-generated constructor stub
		activity = act;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dialogCowok.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return dialogCowok[position];
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
			convertView = inflater.inflate(R.layout.layout_dialog, null);
		
		TextView textCewek = (TextView)convertView.findViewById(R.id.textCewek);
		TextView textCowok = (TextView)convertView.findViewById(R.id.textCowok);
		ImageButton playCewek = (ImageButton)convertView.findViewById(R.id.playCewek);
		ImageButton playCowok = (ImageButton)convertView.findViewById(R.id.playCowok);
		
		tts = new TextToSpeech(activity, new TextToSpeech.OnInitListener() {

			@Override
			public void onInit(int status) {
				// TODO Auto-generated method stub
				if (status == TextToSpeech.SUCCESS) {

					int result = tts.setLanguage(Locale.GERMANY);

					if (result == TextToSpeech.LANG_MISSING_DATA
							|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
						Log.e("TTS", "This Language is not supported");
					}
				} else {
					Log.e("TTS", "Initilization Failed!");
				}
			}
		});
		
		textCowok.setText(dialogCowok[position]);
		textCewek.setText(dialogCewek[position]);
		
		playCewek.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				playNormal(dialogCewek[position]);
			}
		});
		
		playCowok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				playNormal(dialogCowok[position]);
			}
		});
		return convertView;
	}
	
	private void playNormal(String text) {
		tts.setSpeechRate((float) 1.0);
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"");
	}

}
