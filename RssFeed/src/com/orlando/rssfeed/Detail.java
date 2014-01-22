package com.orlando.rssfeed;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail extends Activity {
	String valueLink;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
		    return;
		    }

		String valueTitle = extras.getString("title");
		String valuedescription = extras.getString("description");
		String img = extras.getString("imgthumb");
		valueLink = extras.getString("link");
		
		TextView titleDetail = (TextView)  findViewById(R.id.itemTitleDetail);
		titleDetail.setText(valueTitle);
		TextView descriptionDetail = (TextView)  findViewById(R.id.itemDescriptionDetail);
		descriptionDetail.setText(valuedescription);
		 ImageView itemThumb = (ImageView) findViewById(R.id.iconDetail);
		 Drawable d = null;
		 try {
				InputStream is = (InputStream) new URL(img).getContent();
				 d = Drawable.createFromStream(is, "src name");
			} catch (Exception e) {
				System.out.println("Exc=" + e);

			}
		 if (d!=null)
			 itemThumb.setImageDrawable(d);
	}
	public void goToLink(View v){
		
		Uri uri = Uri.parse(valueLink);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
		

	}


}
