package mc.battleplugins.webapi.object;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

import mc.battleplugins.webapi.controllers.timers.Scheduler;
import mc.battleplugins.webapi.event.SendDataEvent;
import mc.battleplugins.webapi.object.callbacks.URLResponseHandler;


/**
 *
 * @author lDucks
 *
 */

public class WebURL {

	final URL url;
	URLData data;

	int readTimeout = 5000;
	int conTimeout = 7000;

	/**
	 * @param url Core URL for instance
	 * @param data Data parsed to url (if not null)
	 */
	public WebURL(URL url, URLData data) {
		this.url = url;
		this.data = data;
	}

	/**
	 * @param url Core URL for instance
	 * @param data Data parsed to url (if not null)
	 */
	public WebURL(URL url) {
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

	public String getURLString(String encodingmethod) throws UnsupportedEncodingException{
		String urlstring = url.toString();
		urlstring = urlstring + "?" + data.getURLString(encodingmethod);
		return urlstring;
	}

	public void sendData(String encodingmethod) {
		sender(encodingmethod, null);
	}

	/**
	 *
	 * @param caller The player (if any) that called the event
	 *
	 */
	public void sendData(String encodingmethod, String caller) {
		sender(encodingmethod, caller);
	}

	private void sender(final String encodingmethod, final String caller) {
		final long calltime = System.currentTimeMillis();

		Scheduler.scheduleAsynchrounousTask(new Runnable() {
			public void run() {
				try {
					URL dataurl = new URL(getURLString(encodingmethod));
					dataurl.openConnection();

					Scheduler.scheduleSynchrounousTask(new Runnable() {
						public void run() {
							SendDataEvent event = new SendDataEvent(new WebURL(url, data), calltime, caller);
							event.callEvent();
						}
					});
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void getPage(final URLResponseHandler handler, final String encodingmethod){
		Scheduler.scheduleAsynchrounousTask(new Runnable(){
			public void run() {
				BufferedReader br = null;
				try {
					URL dataurl = new URL(getURLString(encodingmethod));

					final URLConnection connection = dataurl.openConnection();
					connection.setConnectTimeout(conTimeout);
					connection.setReadTimeout(readTimeout);

					br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					try {
						handler.validResponse(br);
					} catch (Exception e2){
						System.err.println("Failed parsing response to url " + url);
						e2.printStackTrace();
					}
				} catch (Exception e){
					e.printStackTrace();
					handler.invalidResponse(e);
				} finally {
					try { if (br != null) br.close(); } catch (Exception e){}
				}
			}
		});
	}
}
