package test.mc.battleplugins.webapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;

import mc.battleplugins.webapi.object.WebURL;
import mc.battleplugins.webapi.object.callbacks.URLResponseHandler;

public class TestWebURL extends TestCase{
	String turl = "http://www.BattlePunishments.net/grabbers/register.php";

	boolean quit = false;
	boolean validResponse = false;

	public void setup(){
		quit = false;
		validResponse = false;
	}


	public void testGetPage(){
		URL myUrl = null;
		try {
			myUrl = new URL(turl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			fail();
		}


		WebURL url = new WebURL(myUrl);
		url.addData("server", "209.236.121.173");
		url.addData("key", "10b11bc41654f56f5801434d19b4b997");

		url.getPage(new URLResponseHandler(){
			public void validResponse(BufferedReader br) throws IOException {
				System.out.println("######## in Response");
				String line = null;
				while ((line = br.readLine()) != null){
					System.out.println("line: " + line);
				}
				quit = true;
				validResponse = true;
			}

			public void invalidResponse(Exception e) {
				System.out.println("######## invalid Response");
				quit = true;
				fail();
			}
		});
		int count = 0;
		while (!quit){
			try {
				Thread.sleep(1000);
				if (count++ > 3)
					quit = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		assertTrue(validResponse);
	}

	public void testValidConnection(String caller) {
		WebURL url = null;
		try {
			url = new WebURL(new URL(turl));
		} catch (Exception e) {
			fail();
		}

		url.addData("server", "209.236.121.173");
		url.addData("connection", "10b11bc41654f56f5801434d19b4b997");
		url.sendData(caller);

//		assertTrue(valid);
	}
}
