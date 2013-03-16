package mc.battleplugins.webapi.object;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import mc.battleplugins.webapi.controllers.encoding.Encode;


public class URLData {
	Map<String,String> data = new HashMap<String,String>();

	public void add(String key, String value) {
		data.put(key,value);
	}

	public String remove(String key){
		return data.remove(key);
	}

	public String getURLString() throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for (Entry<String,String> entry : data.entrySet()){
			
			if(entry.getKey() != null && entry.getValue() != null 
					&& entry.getKey().length() > 0 && entry.getValue().length() > 0 )
				Encode.encodeDataPair(sb, entry.getKey(), entry.getValue());
		}

		if(sb.length() > 0)
			return sb.toString();
		else return "";
	}
}
