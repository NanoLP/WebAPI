package com.battleplugins.webapi.object;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.TimerTask;

import com.battleplugins.webapi.controllers.timers.Scheduler;
import com.battleplugins.webapi.event.UrlCloseEvent;
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

	public void openWithoutData() {
		Scheduler.scheduleAsynchrounousTask(new TimerTask() {
			@Override
			public void run() {
				try {
					URLConnection connection = url.openConnection();

					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					reader.read();

					UrlOpenEvent event = new UrlOpenEvent(url, data);
					event.callEvent();

					reader.close();

					Scheduler.scheduleSynchrounousTask(new Runnable() {
						public void run() {
							UrlCloseEvent event = new UrlCloseEvent(url, data);
							event.callEvent();
						}
					});

				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void openWithData() {
		Scheduler.scheduleAsynchrounousTask(new Runnable() {
			public void run() {
				try {
					URL dataurl = new URL(url.toString() + "?" + data.toString());
					URLConnection connection = dataurl.openConnection();

					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					reader.read();

					UrlOpenEvent event = new UrlOpenEvent(url, data);
					event.callEvent();

					reader.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
