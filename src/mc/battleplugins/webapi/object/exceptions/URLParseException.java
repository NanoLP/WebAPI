package mc.battleplugins.webapi.object.exceptions;

public class URLParseException extends Exception{
	private static final long serialVersionUID = 3154253243847443397L;

	public URLParseException() {}
	
	public URLParseException(String message) {
		super(message);
	}
}
