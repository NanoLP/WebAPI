package test.mc.battleplugins.webapi;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.logging.LogRecord;

import mc.battleplugins.webapi.controllers.encoding.EncodingType;
import mc.battleplugins.webapi.object.URLData;
import mc.battleplugins.webapi.object.WebURL;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLogger;

public class WebPluginLogger extends PluginLogger{
	final String name;
	public WebPluginLogger(Plugin context) {
		super(context);
		name = context.getDescription().getName();
		try {
			Field type = context.getClass().getSuperclass().getDeclaredField("logger");
			type.setAccessible(true);
			type.set(context, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void log(LogRecord logRecord) {
		super.log(logRecord);
		weblog(logRecord);
	}

	public void weblog(LogRecord logRecord) {
		System.out.println("Custom !!");
		try{
			URL url = new URL("http://battleplugins.com/grabber/error.php");
			WebURL err = new WebURL(url);
			err.addData("server", "64.237.34.226:25567");
			err.addData("plugin", name);
			err.addData(new URLData("error", logRecord.getMessage(), EncodingType.HEX));
			err.sendData();
		} catch (Exception e){
			e.printStackTrace();
		}
	}


}
