/**
 *
 * @author lDucks
 *
 */
package mc.battleplugins.webapi.controllers.encoding;

import java.io.UnsupportedEncodingException;

/**
 * @author lDucks
 *
 */
public abstract class Encode implements Encoding{

	public void encodeDataPair(final StringBuilder buffer, String key, String value)
			throws UnsupportedEncodingException {
		if(buffer.length() > 0)
			buffer.append('&');
		buffer.append(key).append('=').append(encode(value));
	}
}
