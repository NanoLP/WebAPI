package mc.battleplugins.webapi.controllers;

import mc.battleplugins.webapi.controllers.timers.Scheduler;

import org.bukkit.event.Event;

public interface MCServer {

	void callEvent(Event event);

	public Scheduler getScheduler();
}
