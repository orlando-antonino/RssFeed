package com.orlando.rssfeed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class RssFeed extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


	}

	@Override
	protected void onResume() {
		
		super.onResume();
		
//			addFragRss();
		FragmentManager mng = getSupportFragmentManager();
//		FragmentTransaction acttrans = mng.beginTransaction();
		FragmentFeed fragment = new FragmentFeed();
		replaceFragment(mng, "first", fragment, R.id.fragment_cnt);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch (item.getItemId())
    	{
    	case R.id.action_settings:
			startActivity(new Intent(this, Settings.class));
    		return true;
    	
		case R.id.action_about:
    		Toast.makeText(this, "Made by Antonino Orlando", 
    			Toast.LENGTH_LONG).show();
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }
	@Override
	protected void onSaveInstanceState(Bundle _outState) {
		super.onSaveInstanceState(_outState);
		_outState.putBoolean("addedFrg", true);
	}
	private void addFragRss() {
		FragmentManager mng = getSupportFragmentManager();
		FragmentTransaction acttrans = mng.beginTransaction();
		FragmentFeed fragment = new FragmentFeed();
		if(!mng.findFragmentById(R.id.fragment_cnt).isVisible()){
			
			
			acttrans.add(R.id.fragment_cnt, fragment);
			acttrans.commit();
		}
		else{
			replaceFragment(mng, "first", fragment, R.id.fragment_cnt);

		}
			
		
	}
	public static boolean replaceFragment(FragmentManager fragmentManager, String tag, Fragment fragment, int fragCointeinerResId){

//		if(fragmentManager.findFragmentByTag(tag) == null || (fragmentManager.findFragmentByTag(tag) != null && !fragmentManager.findFragmentByTag(tag).isVisible())){
			FragmentTransaction ft = null;
			ft = fragmentManager.beginTransaction();
			ft.replace(fragCointeinerResId, fragment, tag);
			ft.commit();
			return true;
//		}
//		return false;
	}
}