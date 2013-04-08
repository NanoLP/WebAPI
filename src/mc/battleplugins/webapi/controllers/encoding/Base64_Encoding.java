package mc.battleplugins.webapi.controllers.encoding;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64_Encoding extends Encode{

	@Override
	public String encode(String text) throws UnsupportedEncodingException {
		return Base64.encodeBase64(text.getBytes()).toString();
	}

}
