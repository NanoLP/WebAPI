package mc.battleplugins.webapi.controllers.encoding;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Hex;

public class Hex_Encoding extends Encode{

	public String encode(String text) throws UnsupportedEncodingException {
		return Hex.encodeHex(text.getBytes()).toString();
	}

}
