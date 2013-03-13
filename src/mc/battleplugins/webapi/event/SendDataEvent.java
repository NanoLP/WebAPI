package mc.battleplugins.webapi.event;

import mc.battleplugins.webapi.object.WebURL;



/**
 * @author lDucks
 */

public class SendDataEvent extends WebEvent{
	final WebURL url;
	final long stop, start;

	public SendDataEvent(WebURL url, long start) {
		this.url = url;
		this.start = start;
		this.stop = System.currentTimeMillis();
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
}