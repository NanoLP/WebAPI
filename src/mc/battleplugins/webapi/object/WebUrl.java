package mc.battleplugins.webapi.object;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

import mc.battleplugins.webapi.controllers.timers.Scheduler;
import mc.battleplugins.webapi.event.UrlOpenEvent;


/**
 *
 * @author lDucks
 *
 */

public class WebUrl {

	final URL url;
	URLData data;

	/**
	 * @param url Core URL for instance
	 * @param data Data parsed to url (if not null)
	 */
	public WebUrl(URL url, URLData data) {
		this.url = url;
		this.data = data;
	}

	/**
	 * @param url Core URL for instance
	 * @param data Data parsed to url (if not null)
	 */
	public WebUrl(URL url) {
		this.url = url;
		this.data = new URLData();
	}

	public URL getUrl() {
		return url;
	}

	public void setData(URLData data) {
		this.data = data;
	}

	public URLData getData() {
		return data;
	}

	public void addData(String key, String value){
		data.add(key,value);
	}

	public String getURLString() throws UnsupportedEncodingException{
		String urlstring = url.toString();
		if(data != null)
			urlstring = urlstring + "?" + data.getURLString();
		return urlstring;
	}

	public void openUrl() {
		Scheduler.scheduleAsynchrounousTask(new Runnable() {
			public void run() {
				try {
					URL dataurl = new URL(getURLString());
					URLConnection connection = dataurl.openConnection();

					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					reader.read();
					reader.close();

					Scheduler.scheduleSynchrounousTask(new Runnable() {
						public void run() {
							UrlOpenEvent event = new UrlOpenEvent(url, data);
							event.callEvent();
						}
					});
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
