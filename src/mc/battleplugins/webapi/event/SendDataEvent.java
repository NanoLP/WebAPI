package mc.battleplugins.webapi.event;

import mc.battleplugins.webapi.object.WebURL;

import org.bukkit.entity.Player;



/**
 * @author lDucks
 */

public class SendDataEvent extends WebEvent{
	final WebURL url;
	final long stop, start;
	final Player caller;

	public SendDataEvent(WebURL url, long start, Player caller) {
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
	
	public Player getCaller() {
		return caller;
	}
}