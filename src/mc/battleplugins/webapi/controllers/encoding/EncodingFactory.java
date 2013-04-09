package mc.battleplugins.webapi.controllers.encoding;

public class EncodingFactory {
	public static Encoding createEncoding(EncodingType type){
		switch(type){
		case BASE64:
			return new Base64_Encoding();
		case HEX:
			return new Hex_Encoding();
		case UTF8:
			return new UTF8_Encoding();
		default:
			return new UTF8_Encoding();
		}
	}
}
