package mc.battleplugins.webapi.controllers.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UTF8_Encoding implements Encoding{

	@Override
	public void encodeDataPair(StringBuilder buffer, String key, String value)
			throws UnsupportedEncodingException {
		if(buffer.length() > 0)
			buffer.append('&');
		buffer.append(encode(key)).append('=').append(encode(value));
	}

	@Override
	public String encode(String text) throws UnsupportedEncodingException {
		return URLEncoder.encode(text, "UTF-8");
	}
}
