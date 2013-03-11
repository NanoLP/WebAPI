package com.battleplugins.webapi.event;

import java.net.URL;


/**
 * @author lDucks
 */

public class UrlOpenEvent extends WebEvent{
	final URL url;
	final StringBuilder data;
	final long start;

	public UrlOpenEvent(URL url, StringBuilder data) {
		this.url = url;
		this.data = data;
		this.start = System.currentTimeMillis();
	}

	public URL getUrl(){
		return url;
	}
	
	public StringBuilder getData(){
		return data;
	}
	
	public long getStart() {
		return start;
	}
}