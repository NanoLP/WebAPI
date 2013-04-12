package mc.battleplugins.webapi;

import mc.battleplugins.webapi.controllers.BukkitServer;
import mc.battleplugins.webapi.controllers.MCApi;

import org.bukkit.plugin.java.JavaPlugin;


/**
 * @author lDucks
 */

public class WebAPI extends JavaPlugin {

	public static WebAPI plugin;

	@Override
	public void onEnable() {
		plugin = this;
		MCApi.setServer(new BukkitServer());
	}

}
