package mc.battleplugins.webapi.controllers.encoding;

import java.io.UnsupportedEncodingException;

public interface Encoding {
	public void encodeDataPair(final StringBuilder buffer, final String key, final String value)
			throws UnsupportedEncodingException;
	public String encode(final String text) throws UnsupportedEncodingException;
}
