package sea.goethe.sportpas.lib;


import java.util.ArrayList;

import sea.goethe.sportpas.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChoiceGambarAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<String> jawabanQuiz = new ArrayList<String>();
	private LayoutInflater inflater;
	private RadioButton btnSelect;
	@SuppressWarnings("unused")
	private RadioGroup btnSelectGroup;
	public static int mSelectedPosition = -1;
	
	public ChoiceGambarAdapter(Context c,ArrayList<String> jawabanQuiz) {
		// TODO Auto-generated constructor stub
		this.context = c;
		this.jawabanQuiz = jawabanQuiz;
		btnSelectGroup = new RadioGroup(c);
		inflater = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return jawabanQuiz.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return jawabanQuiz.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		Holder holder;
		
		if(view == null){
			view = inflater.inflate(R.layout.layout_choicegambar, null);
			holder = new Holder();
			holder.image = (ImageView)view.findViewById(R.id.imgSoal);
			holder.radioButton = (RadioButton)view.findViewById(R.id.btnJawaban);
			view.setTag(holder);
		} else {
			holder = (Holder)view.getTag();
		}
		
		holder.radioButton.setChecked(false);
		
		holder.image.setBackgroundResource(context.getResources().getIdentifier(jawabanQuiz.get(position), "drawable", context.getPackageName()));
		
		holder.radioButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if((position != mSelectedPosition && btnSelect != null)){
					btnSelect.setChecked(false);
				}
				
				mSelectedPosition = position;
				btnSelect = (RadioButton)v;
			}
		});
		
		if(mSelectedPosition != position){
			holder.radioButton.setChecked(false);
		} else {
			holder.radioButton.setChecked(true);
			if(btnSelect != null && holder.radioButton != btnSelect){
				btnSelect = holder.radioButton;
			}
		}
		
		return view;
	}
	
	private class Holder {

	    ImageView image;
	    RadioButton radioButton;
	}
}
