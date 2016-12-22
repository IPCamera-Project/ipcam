package kh.com.kshrd.ipcam.respone;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseList<T> extends Response {
	@JsonProperty("DATA")
	public List<T> data;
	
	public List<T> getData()
	{
		return data;
	}
	
	public void setData(ArrayList<T> data)
	{
		this.data=data;
	}
	
}
