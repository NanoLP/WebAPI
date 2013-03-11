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
		StringBuilder data = new StringBuilder();
		Encode.encodeDataPair(data, "key", "value");

		URL url = new URL("http://battleplugins.com/example.php");

		WebUrl apiurl = new WebUrl(url, data);
		
		apiurl.openUrl();
	}

}
