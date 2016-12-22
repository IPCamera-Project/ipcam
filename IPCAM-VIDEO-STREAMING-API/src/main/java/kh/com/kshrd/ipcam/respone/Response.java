package kh.com.kshrd.ipcam.respone;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	@JsonProperty("CODE")
	public String code=ResponseCode.FAIL;
	
	@JsonProperty("MESSAGE")
	public String message;
	
	public String getCode()
	{
		return code;
	}
	
	public void setCode(String code)
	{
		this.code=code;
	}

	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		if(code==ResponseCode.FAIL){
			this.message="FAILED!";
		}
		else if(code==ResponseCode.INSERT_FAIL){
			this.message="INSERT "+message+" FAILED!";
		}
		else if(code==ResponseCode.INSERT_SUCCESS){
			this.message="INSERT "+message+" SUCCESSFUL!";
		}
		else if(code==ResponseCode.UPDATE_FAIL){
			this.message="UPDATE "+message+" FAILED!";
		}
		else if(code==ResponseCode.UPDATE_SUCCESS){
			this.message="UPDATE "+message+" SUCCESSFUL!";
		}
		else if(code==ResponseCode.DELETE_FAIL){
			this.message="DELETE "+message+" FAILED!";
		}
		else if(code==ResponseCode.DELETE_SUCCESS){
			this.message="DELETE "+message+" SUCCESSFUL!";
		}
		else if(code==ResponseCode.QUERY_NOT_FOUND){
			this.message="RECORD NOT FOUND!";
		}
		else if(code==ResponseCode.QUERY_FOUND){
			this.message="RECORD FOUND!";
		}
	}	
}
