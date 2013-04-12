package mc.battleplugins.webapi.event;

import mc.battleplugins.webapi.object.WebURL;



/**
 * @author lDucks
 */

public class SendDataEvent extends WebEvent{
	final WebURL url;
	final long stop, start;
	final String caller;

	public SendDataEvent(WebURL url, long start, String caller) {
		this.url = url;
		this.start = start;
		this.stop = System.currentTimeMillis();
		this.caller = caller;
	}

	public WebURL getURL(){
		return url;
	}

	public long getStartTime() {
		return start;
	}

	public long getStopTime() {
		return stop;
	}

	public long getDuration() {
		return stop - start;
	}

	public String getCaller() {
		return caller;
	}
	@Override
	public String toString(){
		return "[SendDataEvent url=" + url +",caller="+caller+",stop="+stop+",start="+start+"]";
	}
}