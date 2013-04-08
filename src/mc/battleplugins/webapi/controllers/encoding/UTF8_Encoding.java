package mc.battleplugins.webapi.controllers.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UTF8_Encoding extends Encode{

	public String encode(String text) throws UnsupportedEncodingException {
		return URLEncoder.encode(text, "UTF-8");
	}
}
