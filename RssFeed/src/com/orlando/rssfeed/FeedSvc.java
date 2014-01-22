package com.orlando.rssfeed;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.preference.PreferenceManager;
import android.util.Log;

public class FeedSvc extends IntentService {

	public static final String RSSITEMS = "items";
	public static final String RECEIV = "receiver";

	public FeedSvc() {
		super("FeedSvc");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(Const.TAG, "started");
		
		List<ItemFeed> itemsRss = null;
		try {
			ParsRss parser = new ParsRss();
			String urlfeed ="";
			 SharedPreferences appSharedPrefs = PreferenceManager
	        		  .getDefaultSharedPreferences(getApplicationContext());
	        
	        String urlFeed = appSharedPrefs.getString("urlFeed",Const.FEED_URL);
	        InputStream ip =inputStreamRet( urlFeed);
	        if (ip !=null)
	        {
	        	itemsRss = parser.parse(ip);
	    		Bundle bund = new Bundle();
	    		bund.putSerializable(RSSITEMS, (Serializable) itemsRss);
	    		ResultReceiver receiver = intent.getParcelableExtra(RECEIV);
	    		receiver.send(0, bund);
	        }
	        else{
	        	Intent i = new Intent(new Intent(getApplicationContext(), Settings.class));  
	        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	        	startActivity(i);
	        }
			
		} catch (XmlPullParserException e) {
			Log.w(e.getMessage(), e);
		} catch (IOException e) {
			Log.w(e.getMessage(), e);
		}

	}

	public InputStream inputStreamRet(String link) {
		try {
			URL url = new URL(link);
			return url.openConnection().getInputStream();
		} catch (IOException e) {
			Log.w(Const.TAG, "Exception while retrieving the input stream", e);
			
			return null;
		}
	}
}
