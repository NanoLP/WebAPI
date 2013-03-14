package mc.battleplugins.webapi.object.callbacks;

import java.io.BufferedReader;
import java.io.IOException;

public interface URLResponseHandler {

	void validResponse(BufferedReader br) throws IOException;

	void invalidResponse(Exception e);

}
