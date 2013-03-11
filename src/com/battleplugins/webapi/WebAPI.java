package com.battleplugins.webapi;

import org.bukkit.plugin.java.JavaPlugin;

import com.battleplugins.webapi.listener.EventListener;


/**
 * @author lDucks
 */

public class WebAPI extends JavaPlugin {

	public static WebAPI plugin;
	
	public void onEnable() {
		plugin = this;
	}
	
}
