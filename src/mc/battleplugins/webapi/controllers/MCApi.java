package mc.battleplugins.webapi.controllers;

import mc.battleplugins.webapi.controllers.timers.Scheduler;

public class MCApi {
	static MCServer server;

	public static MCServer getServer(){
		return server;
	}
	public static void setServer(MCServer server){
		MCApi.server = server;
	}

	public static Scheduler getScheduler() {
		return server.getScheduler();
	}
}
