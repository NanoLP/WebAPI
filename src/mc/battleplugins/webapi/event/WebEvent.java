package mc.battleplugins.webapi.event;

import mc.battleplugins.webapi.controllers.MCApi;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author lDucks
 *
 */
public class WebEvent extends Event{
	private static final HandlerList handlers = new HandlerList();

	public void callEvent(){
		MCApi.getServer().callEvent(this);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
