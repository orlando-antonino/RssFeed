package com.orlando.rssfeed;

import java.io.Serializable;

public class ItemFeed implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String title;
	private final String link;
	private final String description;
	private final String imgThumbUrl;


	public ItemFeed(String _title, 
			String _link, 
			String _description, 
			String _imgThumbUrl) {
		
		this.title = _title;
		this.link = _link;
		this.description = _description;
		this.imgThumbUrl = _imgThumbUrl;
		
	}

	public String getTitle() {
		return title;
	}
	public String getLink() {
		return link;
	}
	public String getDescription() {
		return description;
	}
	public String getImgThumbUrl() {
		return imgThumbUrl;
	}


}
