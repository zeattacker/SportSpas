package sea.goethe.sportpas;

import sea.goethe.sportpas.lib.ListDialogAdapter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
