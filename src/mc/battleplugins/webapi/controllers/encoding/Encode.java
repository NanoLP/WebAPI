/**
 *
 * @author lDucks
 *
 */
package mc.battleplugins.webapi.controllers.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * @author lDucks
 *
 */
public class Encode {
	public static void encodeDataPair(final StringBuilder buffer, String key, String value, String encodingmethod) throws UnsupportedEncodingException {
		if(buffer.length() > 0)
			buffer.append('&');

		key = encode(key, encodingmethod);
		value = encode(value, encodingmethod);
		
		buffer.append(key + '=' + value);
	}
	
	public static String encode(String text, String encodingmethod) throws UnsupportedEncodingException {
		if(encodingmethod.equalsIgnoreCase("UTF-8")) {
			return URLEncoder.encode(text, "UTF-8");
		}else if(encodingmethod.equalsIgnoreCase("Base64")) {
			return Base64.encodeBase64(text.getBytes()).toString();
		}else if(encodingmethod.equalsIgnoreCase("Hex")) {
			return Hex.encodeHex(text.getBytes()).toString();
		}else
			throw new NullPointerException();
	}
}
