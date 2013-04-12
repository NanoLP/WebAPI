package test.mc.battleplugins.webapi.mcapi;

import mc.battleplugins.webapi.controllers.MCServer;
import mc.battleplugins.webapi.controllers.timers.Scheduler;

import org.bukkit.event.Event;

public class TestServer implements MCServer{

	public static class TestScheduler extends Scheduler{
		@Override
		public int scheduleSynchrounousTask(Runnable task, long millis){
			return this.scheduleAsynchrounousTask(task, millis );
		}
	}
	TestScheduler scheduler = new TestScheduler();

	@Override
	public void callEvent(Event event) {
		System.out.println("  Calling event " + event);
	}

	@Override
	public Scheduler getScheduler() {
		return scheduler;
	}

}
