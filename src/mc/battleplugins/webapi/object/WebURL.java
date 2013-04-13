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
import java.util.ArrayList;
import java.util.List;

import mc.battleplugins.webapi.controllers.MCApi;
import mc.battleplugins.webapi.controllers.encoding.EncodingType;
import mc.battleplugins.webapi.event.SendDataEvent;
import mc.battleplugins.webapi.object.callbacks.URLResponseHandler;


/**
 *
 * @author lDucks
 *
 */

public class WebURL {

	final URL url;
	List<URLData> data = new ArrayList<URLData>();

	int readTimeout = 5000;
	int conTimeout = 7000;

	ConnectionType connectionType = ConnectionType.GET;

	/**
	 * @param url Core URL for instance
	 * @param data Data parsed to url (if not null)
	 */
	public WebURL(URL url) {
		this.url = url;
		this.data = new ArrayList<URLData>();
	}

	/**
	 * @param url Core URL for instance
	 * @param data2 Data parsed to url (if not null)
	 */
	public WebURL(URL url, List<URLData> data) {
		this.url = url;
		this.data = data;
	}

	public URL getUrl() {
		return url;
	}

	public void setData(URLData data) {
		this.data.clear();
		this.data.add(data);
	}

	public List<URLData> getData() {
		return data;
	}

	public void addData(String key, String value){
		data.add(new URLData(key,value));
	}

	public void addData(URLData urlData) {
		this.data.add(urlData);
	}

	public void setConnectionType(ConnectionType type){
		this.connectionType = type;
	}

	public void setEncoding(EncodingType type) {
		for (URLData d : data){
			d.setEncoding(type);}
	}

	public String getURLString() throws UnsupportedEncodingException, MalformedURLException{
		return (url.toString() +"?"+getDataString());
	}

	public String getDataString() throws UnsupportedEncodingException, MalformedURLException{
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (URLData d : data){
			if (!first) sb.append("&");
			sb.append(d.getURLString());
			first = false;
		}
		return sb.toString();
	}

	public void sendData() {
		sender(null);
	}

	/**
	 *
	 * @param caller The player (if any) that called the event
	 *
	 */
	public void sendData(String caller) {
		sender(caller);
	}

	private void sender(final String caller) {
		final long calltime = System.currentTimeMillis();

		MCApi.getScheduler().scheduleAsynchrounousTask(new Runnable() {
			public void run() {
				try {
					URL dataurl = new URL(getURLString());
					dataurl.openConnection();

					MCApi.getScheduler().scheduleSynchrounousTask(new Runnable() {
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
		MCApi.getScheduler().scheduleAsynchrounousTask(new Runnable(){
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
		String postdata = this.getDataString();
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

	@Override
	public String toString(){
		try{
			return "[WebURL url="+url+"?"+getDataString()+"]";
		} catch (Exception e){
			return "[WebURL url="+url+" data="+data+"]";
		}
	}

}
