package test.mc.battleplugins.webapi;

import java.net.URL;

import mc.battleplugins.webapi.object.WebURL;

public class Example {

	public void setUpUrl() throws Exception {
		URL url = new URL("http://battleplugins.com/example.php");

		WebURL apiurl = new WebURL(url);
		apiurl.addData("key","value");
		
		apiurl.sendData();
	}

}
