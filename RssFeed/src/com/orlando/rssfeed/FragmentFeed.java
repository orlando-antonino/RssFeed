package com.orlando.rssfeed;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class FragmentFeed extends Fragment implements OnItemClickListener {

	private View view;
	private ListView listView;
	private ProgressBar progBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//		StrictMode.setThreadPolicy(policy);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.fragment_layout, container, false);
			progBar = (ProgressBar) view.findViewById(R.id.prgBar);
			listView = (ListView) view.findViewById(R.id.listView);
			listView.setOnItemClickListener(this);
			startService();
		} else {
			ViewGroup parent = (ViewGroup) view.getParent();
			parent.removeView(view);
		}
		return view;
	}

	private void startService() {
		
		Intent i = new Intent(getActivity(), FeedSvc.class);
		i.putExtra(FeedSvc.RECEIV, receiverResult);
		getActivity().startService(i);
	}

	

	@Override
	public void onItemClick(AdapterView<?> _parent, View _view, int _pos, long _id) {
		AdapterFeed adapter = (AdapterFeed) _parent.getAdapter();
		ItemFeed item = (ItemFeed) adapter.getItem(_pos);
		Uri uri = Uri.parse(item.getLink());
		
		Intent i = new Intent(getActivity(), Detail.class);
		i.putExtra("title", item.getTitle());
		i.putExtra("description", item.getDescription());
		i.putExtra("imgthumb", item.getImgThumbUrl());
		i.putExtra("link", uri);

		
		startActivity(i);

	}
	private final ResultReceiver receiverResult = new ResultReceiver(new Handler()) {
		@SuppressWarnings("unchecked")
		@Override
		protected void onReceiveResult(int _resultCd, Bundle _rsltData) {
			progBar.setVisibility(View.GONE);
			List<ItemFeed> items = (List<ItemFeed>) _rsltData.getSerializable(FeedSvc.RSSITEMS);
			
			if (items != null) {
				AdapterFeed adapter = new AdapterFeed(getActivity(), items);
				listView.setAdapter(adapter);
			} 
			else {
				
				Toast.makeText(getActivity(), "Feed download error",
						Toast.LENGTH_LONG).show();
			}
		};
	};
}
