package com.battleplugins.webapi;

import java.net.URL;

import com.battleplugins.webapi.controllers.encoding.Encode;
import com.battleplugins.webapi.object.WebUrl;

/**
 * @author lDucks
 *
 */
public class Example {

	public void setUpUrl() throws Exception {
		URL url = new URL("http://battleplugins.com/example.php");

		WebUrl apiurl = new WebUrl(url);
		apiurl.add("key","value");
		
		apiurl.openUrl();
	}

}
