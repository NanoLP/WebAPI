/**
 *
 * @author lDucks
 *
 */
package com.battleplugins.webapi.controllers.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author lDucks
 *
 */
public class Encode {
	public static void encodeDataPair(final StringBuilder buffer, final String key, final String value) throws UnsupportedEncodingException {
		if(buffer.length() == 0)
			buffer.append('&');
		
		buffer.append(encode(key)).append('=').append(encode(value));
	}

	public static String encode(final String text) throws UnsupportedEncodingException {
		return URLEncoder.encode(text, "UTF-8");
	}
}
