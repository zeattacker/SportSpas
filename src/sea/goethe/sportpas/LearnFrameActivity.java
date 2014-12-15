package sea.goethe.sportpas;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class LearnFrameActivity extends Fragment {
	
	public LearnFrameActivity(){
		//kosong
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.activity_learn_frame, null);
		
		ImageButton btnSport = (ImageButton)rootView.findViewById(R.id.btnPlayHoren);
		ImageButton btnAlat = (ImageButton)rootView.findViewById(R.id.imageButton2);
		ImageButton btnDialog = (ImageButton)rootView.findViewById(R.id.imageButton3);
		
		btnSport.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity() , LearnSportFragment.class);
				i.putExtra("TIPE", "sport");
				startActivity(i);
			}
		});
		
		btnAlat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), LearnSportFragment.class);
				i.putExtra("TIPE", "alat");
				startActivity(i);
			}
		});

		btnDialog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), DialogFrameActivity.class);
				startActivity(i);
			}
		});
		return rootView;
	}
	
	
}
