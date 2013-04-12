package test.mc.battleplugins.webapi;

import mc.battleplugins.webapi.controllers.MCApi;
import test.mc.battleplugins.webapi.mcapi.TestServer;


public class TestWebExceptions {


	public void setUp(){
		System.out.println("Setting up test server");
		MCApi.setServer(new TestServer());
	}

	public static void main(String args[]){
		ExceptionHandler.enable();
		TestWebExceptions et = new TestWebExceptions();
		et.setUp();

		try {
			new Err("Blearghh!");
		} catch(Exception e){
			// i am caught
		}

//		new Err("Grargh!");

		et.testFail();
	}

	public void testFail(){
		Integer.valueOf("not a number");
	}

	public static class Err{
		public Err(String msg){
			throw new IllegalStateException(msg);
		}
	}
}
