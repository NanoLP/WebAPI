package com.battleplugins.webapi.object;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.battleplugins.webapi.controllers.timers.Scheduler;
import com.battleplugins.webapi.event.UrlOpenEvent;

/**
 *
 * @author lDucks
 *
 */

public class WebUrl {

	final URL url;
	StringBuilder data;

	/**
	 * @param url Core URL for instance
	 * @param data Data parsed to url (if not null)
	 */
	public WebUrl(URL url, StringBuilder data) {
		this.url = url;
		this.data = data;
	}

	public URL getUrl() {
		return url;
	}

	public void setData(StringBuilder data) {
		this.data = data;
	}

	public StringBuilder getData() {
		return data;
	}

	public void openUrl() {
		Scheduler.scheduleAsynchrounousTask(new Runnable() {
			public void run() {
				try {
					String urlstring = url.toString();
					
					if(data != null)
						urlstring = urlstring + "?" + data.toString();
					
					URL dataurl = new URL(urlstring);
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
