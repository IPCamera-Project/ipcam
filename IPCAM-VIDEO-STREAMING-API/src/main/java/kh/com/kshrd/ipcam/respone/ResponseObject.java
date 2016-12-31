package kh.com.kshrd.ipcam.respone;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseObject<T> extends Response {
	@JsonProperty("DATA")
	public Object data;
	
	public Object getData()
	{
		return data;
	}
	
	public void setData(Object data)
	{
		this.data=data;
	}
}
