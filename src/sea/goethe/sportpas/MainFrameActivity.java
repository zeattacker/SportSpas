package sea.goethe.sportpas;


import sea.goethe.sportpas.lib.NavDrawerListAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainFrameActivity extends ActionBarActivity {

	// inisialisasi toolbar
    private Toolbar toolbar;

    // navigation drawer
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToogle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    private ListView drawerList;
    private NavDrawerListAdapter adapter;
    private String[] navMenuTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        mTitle = mDrawerTitle = getTitle();

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.list_slidermenu);

        drawerList.setOnItemClickListener(new SlideMenuClickListener());

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer);
        toolbar.setTitle(mTitle);

        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

        adapter = new NavDrawerListAdapter(getApplicationContext(), navMenuTitles);

        drawerList.setAdapter(adapter);

        initDrawer();
        
        if (savedInstanceState == null) {
            // on first time display view for first nav item
        	Intent is = getIntent();
        	String menunya = is.getStringExtra("MENU");
        	
        	if(menunya.equalsIgnoreCase("learn"))
        		displayView(0);	
        	else if(menunya.equalsIgnoreCase("about"))
        		displayView(3);
        }
    }


    private void initDrawer(){
        drawerToogle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.txt_open, R.string.txt_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                toolbar.setTitle(mDrawerTitle);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle(mTitle);
            }
        };

        drawerLayout.setDrawerListener(drawerToogle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToogle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToogle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private class SlideMenuClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            displayView(position);
        }
    }

    private void displayView(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
            	fragment = new LearnFrameActivity();
                break;
            case 1:
                break;
            case 2:
            	break;
            case 3:
            	fragment = new AboutActivity();
            	break;
            default:
                break;
        }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

            drawerList.setItemChecked(position, true);
            drawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            drawerLayout.closeDrawer(drawerList);
        } else {
            Log.e("MainActivity","Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        toolbar.setTitle(mTitle);
    }
}
