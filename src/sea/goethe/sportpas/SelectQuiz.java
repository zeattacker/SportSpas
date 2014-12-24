package sea.goethe.sportpas;

import sea.goethe.sportpas.lib.SelectQuizAdapter;

import com.viewpagerindicator.LinePageIndicator;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;

public class SelectQuiz extends ActionBarActivity {
	private SelectQuizAdapter squizAdapter;
	private ViewPager viewPager;
	private LinePageIndicator lIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_quiz);

		viewPager = (ViewPager) findViewById(R.id.pager);
		Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
		
		toolBar.setTitle("Quiz");

		squizAdapter = new SelectQuizAdapter(this);

		viewPager.setAdapter(squizAdapter);
		viewPager.setCurrentItem(0);
		
		lIndicator = (LinePageIndicator)findViewById(R.id.indicator);
		lIndicator.setViewPager(viewPager);
	}
}
