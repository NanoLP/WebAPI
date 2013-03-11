package com.battleplugins.webapi.object;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;

import com.battleplugins.webapi.WebAPI;
import com.battleplugins.webapi.event.UrlOpenEvent;

/**
 * 
 * @author lDucks
 *
 */

public class WebUrl {

	URL url;
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
	
	public void runWithoutData() {
		Bukkit.getScheduler().runTaskAsynchronously(WebAPI.plugin, new Runnable() {
			public void run() {
				try {
					URLConnection connection = url.openConnection();

					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					reader.read();
					
					UrlOpenEvent event = new UrlOpenEvent(url, data);
					event.callEvent();
					
					reader.close();
				}catch(Exception e) {}
			}
		});
	}
}
