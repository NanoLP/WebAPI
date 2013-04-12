package mc.battleplugins.webapi.controllers;

import mc.battleplugins.webapi.controllers.timers.Scheduler;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public class BukkitServer implements MCServer {
	final Scheduler scheduler = new Scheduler();

	@Override
	public void callEvent(Event event) {
		Bukkit.getServer().getPluginManager().callEvent(event);
	}

	@Override
	public Scheduler getScheduler() {
		return scheduler;
	}

}
