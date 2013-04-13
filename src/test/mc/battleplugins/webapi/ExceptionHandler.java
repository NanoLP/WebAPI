package test.mc.battleplugins.webapi;

import java.net.URL;

import mc.battleplugins.webapi.controllers.encoding.EncodingType;
import mc.battleplugins.webapi.object.URLData;
import mc.battleplugins.webapi.object.WebURL;

public enum ExceptionHandler implements Thread.UncaughtExceptionHandler{
	INSTANCE;

	private ExceptionHandler(){

		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable exception) {
		err(thread,exception);
	}

	public static void enable(){}

	public static void err(Throwable exception){
		err(null, exception);
	}

	public static void err(Thread thread, Throwable exception){
		System.out.println("   Thread " + thread +"  throwing exception =" +exception.getMessage());
		System.out.println("         " + exception.getCause() +"   " + exception.getClass().getSimpleName());
		System.out.println("         TRACE = ");
		exception.printStackTrace();
		Throwable cause = exception.getCause();
		System.out.println("         CAUSE = " + cause);
		if (cause != null){
			System.out.println("         CAUSE TRACE = ");
			cause.printStackTrace();
		}
		try{
			URL url = new URL("http://battleplugins.com/grabber/error.php");
			WebURL err = new WebURL(url);
			err.addData("server", "64.237.34.226:25567");
			err.addData("plugin", "BattleArena");
			err.addData(new URLData("error", exception.getMessage(), EncodingType.HEX));
			err.sendData();
		} catch (Exception e){
			e.printStackTrace();
		}

	}
}
