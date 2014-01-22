package com.orlando.rssfeed;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class ParsRss {

	private final String ns = null;

	public List<ItemFeed> parse(InputStream _inputStream)
			throws XmlPullParserException, IOException {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(_inputStream, null);
			parser.nextTag();
			return readFeed(parser);
		} finally {
			_inputStream.close();
		}
	}

	private List<ItemFeed> readFeed(XmlPullParser _parser)
			throws XmlPullParserException, IOException {
		List<ItemFeed> items = new ArrayList<ItemFeed>();
		_parser.require(XmlPullParser.START_TAG, null, "rss");

		String title = null;
		String link = null;
		String description = null;
		String thumb = null;

		while (_parser.next() != XmlPullParser.END_DOCUMENT) {
			if (_parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = _parser.getName();
			if (name.equals("title")) {
				title = readTitle(_parser);
			} else if (name.equals("link")) {
				link = readLink(_parser);
			} else if (name.equals("description")) {
				description = readdescription(_parser);
			} else if (name.equals("media:thumbnail")) {
				thumb = readThumb(_parser);
			}
			if (title != null && link != null && description != null
					&& thumb != null) {

				ItemFeed item = new ItemFeed(title, link, description, thumb);

				items.add(item);
				title = null;
				link = null;
				thumb = null;
			}
		}
		return items;
	}

	private String readParamUrl(XmlPullParser parser) throws IOException,
			XmlPullParserException {
		String result = "";

		result = parser.getAttributeValue(ns, "url");
		parser.nextTag();

		return result;
	}

	private String readLink(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "link");
		String link = txtRead(parser);
		parser.require(XmlPullParser.END_TAG, ns, "link");
		return link;
	}

	private String readdescription(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "description");
		String description = txtRead(parser);
		parser.require(XmlPullParser.END_TAG, ns, "description");
		return description;
	}

	private String readTitle(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "title");
		String title = txtRead(parser);
		parser.require(XmlPullParser.END_TAG, ns, "title");
		return title;
	}

	private String readThumb(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, "media:thumbnail");
		String thumb = readParamUrl(parser);

		parser.require(XmlPullParser.END_TAG, ns, "media:thumbnail");
		return thumb;
	}

	private String txtRead(XmlPullParser parser) throws IOException,
			XmlPullParserException {
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			parser.nextTag();
		}
		return result;
	}

}
