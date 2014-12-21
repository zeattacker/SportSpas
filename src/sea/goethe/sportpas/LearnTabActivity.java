package sea.goethe.sportpas;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class LearnTabActivity extends FragmentActivity {
	private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_learn_tab);
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(),
				android.R.id.tabcontent);

		mTabHost.addTab(
				setIndicator(LearnTabActivity.this,
						mTabHost.newTabSpec("darat"),
						R.drawable.tab_indicator_gen, "Land",
						R.drawable.icon_od), LearnSportFragment.class, null);
		mTabHost.addTab(
				setIndicator(LearnTabActivity.this, mTabHost.newTabSpec("air"),
						R.drawable.tab_indicator_gen, "Wasser", R.drawable.icon_oa),
				LearnSportFragment.class, null);
		mTabHost.addTab(
				setIndicator(LearnTabActivity.this,
						mTabHost.newTabSpec("udara"),
						R.drawable.tab_indicator_gen, "Luft",
						R.drawable.icon_ou), LearnSportFragment.class, null);
	}

	private TabSpec setIndicator(Context ctx, TabSpec spec, int resid,
			String string, int genresIcon) {
		View v = LayoutInflater.from(ctx).inflate(R.layout.tab_item, null);
		v.setBackgroundResource(resid);
		TextView tv = (TextView) v.findViewById(R.id.txt_tabtxt);
		ImageView img = (ImageView) v.findViewById(R.id.img_tabtxt);

		tv.setText(string);
		img.setBackgroundResource(genresIcon);
		return spec.setIndicator(v);
	}
}