package com.orlando.rssfeed;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.androidquery.AQuery;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterFeed extends BaseAdapter {

	private final List<ItemFeed> items;
	private final Context context;
	HolderV holder;

	public AdapterFeed(Context context, List<ItemFeed> items) {
		this.items = items;
		this.context = context;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int _position, View _cnvView, ViewGroup _parent) {

		if (_cnvView == null) {
			_cnvView = View.inflate(context, R.layout.feed_item, null);
			holder = new HolderV();
			holder.itemTitle = (TextView) _cnvView.findViewById(R.id.itemTitle);

			holder.itemThumb = (ImageView) _cnvView.findViewById(R.id.icon);
			_cnvView.setTag(holder);

			holder.itemTitle.setText(items.get(_position).getTitle());

		} else {
			holder = (HolderV) _cnvView.getTag();
		}
		String url = items.get(_position).getImgThumbUrl();
		AQuery aq = new AQuery(_cnvView);
		aq.id(R.id.icon).image(url);

		return _cnvView;
	}

	static class HolderV {
		TextView itemTitle;
		ImageView itemThumb;
		TextView itemDescription;

	}
}
