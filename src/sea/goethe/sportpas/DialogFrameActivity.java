package sea.goethe.sportpas;

import sea.goethe.sportpas.lib.ListDialogAdapter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class DialogFrameActivity extends ActionBarActivity {
	private ListView listDialog;
	private ListDialogAdapter listDialogAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
		
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		toolbar.setTitle("Dialog : Federball Spielen");
		
		listDialog = (ListView)findViewById(R.id.listSkor);
		listDialogAdapter = new ListDialogAdapter(this);
		
		listDialog.setAdapter(listDialogAdapter);
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
	}
	
	
}
