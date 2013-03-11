package mc.battleplugins.webapi.event;

import java.net.URL;

import mc.battleplugins.webapi.object.URLData;



/**
 * @author lDucks
 */

public class UrlOpenEvent extends WebEvent{
	final URL url;
	final URLData data;
	final long time;

	public UrlOpenEvent(URL url, URLData data) {
		this.url = url;
		this.data = data;
		this.time = System.currentTimeMillis();
	}

	public URL getUrl(){
		return url;
	}

	public URLData getData(){
		return data;
	}

	public long getTime() {
		return time;
	}
}