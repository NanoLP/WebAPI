package com.battleplugins.webapi.event;

import java.net.URL;


/**
 * @author lDucks
 */

public class UrlCloseEvent extends WebEvent{
	final URL url;
	final StringBuilder data;
	final long time;

	public UrlCloseEvent(URL url, StringBuilder data) {
		this.url = url;
		this.data = data;
		this.time = System.currentTimeMillis();
	}

	public URL getUrl(){
		return url;
	}
	
	public StringBuilder getData(){
		return data;
	}
	
	public long getTime() {
		return time;
	}
}