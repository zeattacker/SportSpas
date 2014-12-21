package sea.goethe.sportpas;

import sea.goethe.sportpas.lib.DatabaseHandler;
import sea.goethe.sportpas.lib.ListLearnAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class LearnSportFragment extends Fragment {
	private ListView listLearn;
	private ListLearnAdapter learnAdapter;
	private DatabaseHandler dh;
	public LearnSportFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_learn_sport, container,
				false);
		listLearn = (ListView) v.findViewById(R.id.listSkor);
		Button btnDialog = (Button)v.findViewById(R.id.btnDialog);
		dh = new DatabaseHandler(getActivity());

		Intent i = getActivity().getIntent();
		String tipe = i.getStringExtra("TIPE");

		learnAdapter = new ListLearnAdapter(getActivity(), dh.getAllLearn(getTag(), tipe));

		listLearn.setAdapter(learnAdapter);
		
		btnDialog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(),DialogFrameActivity.class);
				startActivity(i);
				getActivity().overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
			}
		});
		return v;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		getActivity().overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
	}
	
	
}
