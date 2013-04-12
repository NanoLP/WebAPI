package test.mc.battleplugins.webapi;

import java.net.URL;

import junit.framework.TestCase;
import mc.battleplugins.webapi.object.WebURL;

public class Example extends TestCase {

	public void testSetUpUrl() throws Exception {
		URL url = new URL("http://battleplugins.com/example.php");
		WebURL apiurl = new WebURL(url);
		apiurl.addData("key","value");
		apiurl.sendData("UTF-8");
	}

}
