package com.orlando.rssfeed;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Settings extends Activity {
	static final String ME ="com.orlando.rssfeed.Settings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
        final Context con = this.getApplicationContext();
        EditText etextUrl = (EditText)  findViewById(R.id.urlText);
        
        SharedPreferences appSharedPrefs = PreferenceManager
        		  .getDefaultSharedPreferences(con);
        
        String urlFeed = appSharedPrefs.getString("urlFeed",Const.FEED_URL);
        etextUrl.setText(urlFeed);
    }
    public void changeUrl(View v){
    	EditText etextUrl = (EditText)  findViewById(R.id.urlText);
		
    	
     	SharedPreferences appSharedPrefs = PreferenceManager
      		  .getDefaultSharedPreferences(getApplicationContext());
              Editor prefsEditor = appSharedPrefs.edit();

              prefsEditor.putString("urlFeed", etextUrl.getText().toString());
              prefsEditor.commit();
              Log.i(ME,etextUrl.getText().toString() );
              

	}



    
}
