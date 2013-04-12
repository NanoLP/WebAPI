package mc.battleplugins.webapi.object;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import mc.battleplugins.webapi.controllers.encoding.Encoding;
import mc.battleplugins.webapi.controllers.encoding.EncodingFactory;
import mc.battleplugins.webapi.controllers.encoding.EncodingType;
import mc.battleplugins.webapi.controllers.encoding.UTF8_Encoding;


public class URLData {
	Map<String,String> data = new HashMap<String,String>();
	Encoding encoding = new UTF8_Encoding();

	public URLData() {}
	public URLData(String key, String value) {
		add(key, value);
	}

	public URLData(String key, String value, EncodingType type) {
		setEncoding(type);
		add(key, value);
	}

	public void add(String key, String value) {
		data.put(key,value);
	}

	public String remove(String key){
		return data.remove(key);
	}

	public void setEncoding(EncodingType type){
		this.encoding = EncodingFactory.createEncoding(type);
	}

	public String getURLString() throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for (Entry<String,String> entry : data.entrySet()){
			if(entry.getKey() != null && entry.getValue() != null
					&& entry.getKey().length() > 0 && entry.getValue().length() > 0 )
				encoding.encodeDataPair(sb, entry.getKey(), entry.getValue());
		}

		if(sb.length() > 0)
			return sb.toString();
		else return "";
	}

	@Override
	public String toString(){
		try {
			return getURLString();
		} catch (UnsupportedEncodingException e) {
			return super.toString();
		}
	}
}
