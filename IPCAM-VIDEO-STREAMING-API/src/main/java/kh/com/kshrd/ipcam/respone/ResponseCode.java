package kh.com.kshrd.ipcam.respone;

public interface ResponseCode {
	String FAIL="0000";
	
	String INSERT_SUCCESS="1000";
	String INSERT_FAIL="1001";
	
	String DELETE_SUCCESS="2000";
	String DELETE_FAIL="2001";
	
	String UPDATE_SUCCESS="3000";
	String UPDATE_FAIL="3001";
	
	String QUERY_FOUND="4000";
	String QUERY_NOT_FOUND="4001";
	
}
