package com.battleplugins.webapi.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.battleplugins.webapi.event.UrlCloseEvent;
import com.battleplugins.webapi.event.UrlOpenEvent;

/**
 * @author lDucks
 *
 * Listen to Specific web events
 */
public class EventListener implements Listener {

	@EventHandler
	public void urlOpen(UrlOpenEvent event) {
		UrlCloseEvent e = new UrlCloseEvent(event.getUrl(), event.getData());
		e.callEvent();
	}

}
