package sea.goethe.sportpas;

import sea.goethe.sportpas.lib.DatabaseHandler;
import sea.goethe.sportpas.lib.SessionHandler;
import sea.goethe.sportspas.model.ProgressModel;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class RewardActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reward);
		RelativeLayout bgReward = (RelativeLayout) findViewById(R.id.bgReward);
		SessionHandler sh = new SessionHandler(getApplicationContext());
		DatabaseHandler dh = new DatabaseHandler(getApplicationContext());
		ProgressModel pm = dh.getProgress(1);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("Aufkleber");

		if (sh.isLandDone() && !sh.isWasserDone() && !sh.isLuftDone()) {
			bgReward.setBackgroundResource(R.drawable.img_reward_2);
		} else if (sh.isLandDone() && sh.isWasserDone() && !sh.isLuftDone()) {
			bgReward.setBackgroundResource(R.drawable.img_reward_3);
		} else if (sh.isLandDone() && sh.isWasserDone() && sh.isLuftDone()) {
			bgReward.setBackgroundResource(R.drawable.img_reward_4);
		} else if (sh.isLandDone() && sh.isWasserDone() && sh.isLuftDone()
				&& pm.getListen() >= 75 && pm.getRead() >= 75
				&& pm.getWrite() >= 75 && pm.getSpeak() >= 75) {
			bgReward.setBackgroundResource(R.drawable.img_reward_5);
		} else {
			bgReward.setBackgroundResource(R.drawable.img_reward_1);
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
	}
}
