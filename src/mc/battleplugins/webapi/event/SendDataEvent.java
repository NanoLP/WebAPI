package mc.battleplugins.webapi.event;

import mc.battleplugins.webapi.object.WebUrl;



/**
 * @author lDucks
 */

public class SendDataEvent extends WebEvent{
	final WebUrl url;
	final long stop, start;

	public SendDataEvent(WebUrl url, long start) {
		this.url = url;
		this.start = start;
		this.stop = System.currentTimeMillis();
	}

	public WebUrl getUrl(){
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