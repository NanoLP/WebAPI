package mc.battleplugins.webapi.object;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import mc.battleplugins.webapi.controllers.encoding.Hex_Encoding;
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

	ConnectionType connectionType = ConnectionType.GET;

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

	public void setConnectionType(ConnectionType type){
		this.connectionType = type;
	}

	public void setEncoding(Hex_Encoding encoding) {
		data.setEncoding(encoding);
	}

	public String getURLString() throws UnsupportedEncodingException, MalformedURLException{
		String urlstring = url.toString();
		urlstring = urlstring + "?" + data.getURLString();
		return urlstring;
	}

	public void sendData(String encodingmethod) {
		sender(null);
	}

	/**
	 *
	 * @param caller The player (if any) that called the event
	 *
	 */
	public void sendData(String encodingmethod, String caller) {
		sender(caller);
	}

	private void sender(final String caller) {
		final long calltime = System.currentTimeMillis();

		Scheduler.scheduleAsynchrounousTask(new Runnable() {
			public void run() {
				try {
					URL dataurl = new URL(getURLString());
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

	public void getPage(final URLResponseHandler handler){
		Scheduler.scheduleAsynchrounousTask(new Runnable(){
			public void run() {
				BufferedReader br = null;
				try {
					URLConnection connection = null;
					switch(connectionType){
					case GET:
						connection = getGETConnection();
					case POST:
						connection = getPOSTConnection();
					}

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

	private HttpURLConnection getPOSTConnection() throws IOException {
		URL dataurl = url;

		final HttpURLConnection connection = (HttpURLConnection) dataurl.openConnection();
		/// Set the POST settings
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setAllowUserInteraction(false);
		String postdata = data.getURLString();
		connection.setRequestProperty("Content-type", "text/xml; charset=" + "UTF-8");
		connection.setRequestProperty("Content-length",String.valueOf(postdata.length()));
		/// Make sure we can timeout eventually
		connection.setConnectTimeout(conTimeout);
		connection.setReadTimeout(readTimeout);

		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		out.writeBytes(postdata);
		out.flush();
		out.close();
		return connection;
	}

	private URLConnection getGETConnection() throws IOException {
		URL dataurl = new URL(getURLString());
		final URLConnection connection = dataurl.openConnection();
		/// Make sure we can timeout eventually
		connection.setConnectTimeout(conTimeout);
		connection.setReadTimeout(readTimeout);
		return connection;
	}

}
