package kh.com.kshrd.ipcam.controller.camera;
import java.util.ArrayList;

import kh.com.kshrd.ipcam.entity.camera.IPCam;
import kh.com.kshrd.ipcam.respone.Response;
import kh.com.kshrd.ipcam.respone.ResponseCode;
import kh.com.kshrd.ipcam.respone.ResponseList;
import kh.com.kshrd.ipcam.respone.ResponseMessage;
import kh.com.kshrd.ipcam.respone.ResponseObject;
import kh.com.kshrd.ipcam.service.impl.IPCamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author Hum Chivorn
 *
 */
@RestController()
@RequestMapping("api/camera")
public class IPCamController {
	@Autowired
	private IPCamServiceImpl service;
	
	@RequestMapping(value="/getALlCamera",method=RequestMethod.GET)
	public ResponseList<IPCam> getAllCamera()
	{
		ArrayList<IPCam> data=(ArrayList<IPCam>) service.findAll();
		ResponseList<IPCam> res=new ResponseList<IPCam>();
		
		if(data.size()>0){
			res.setCode(ResponseCode.QUERY_FOUND);
			res.setMessage(ResponseMessage.CAMERA_MESSAGE);
			res.setData(data);
		}
		else{
			res.setCode(ResponseCode.QUERY_NOT_FOUND);
			res.setMessage(ResponseMessage.CAMERA_MESSAGE);
		}
		return res;
	}
	
	
	@RequestMapping(value="/getCameraById/{id}",method=RequestMethod.GET)
	public ResponseObject<IPCam> getCameraByID(@PathVariable("id") int id)
	{
		IPCam data= service.findOne(id);
		ResponseObject<IPCam> res=new ResponseObject<IPCam>();
		
		if(data != null){
			res.setCode(ResponseCode.QUERY_FOUND);
			res.setMessage(ResponseMessage.CAMERA_MESSAGE);
			res.setData(data);
		}
		else{
			res.setCode(ResponseCode.QUERY_NOT_FOUND);
			res.setMessage(ResponseMessage.CAMERA_MESSAGE);
		}
		return res;
	}
	
	@RequestMapping(value="/removeCameraById/{id}",method=RequestMethod.DELETE)
	public Response deleteCameraByID(@PathVariable("id") int id)
	{
	
		boolean status=service.remove(id);
		Response res=new Response();
		if(status){
			res.setCode(ResponseCode.DELETE_SUCCESS);
			res.setMessage(ResponseMessage.CAMERA_MESSAGE);
		}
		else{
			res.setCode(ResponseCode.DELETE_FAIL);
			res.setMessage(ResponseMessage.CAMERA_MESSAGE);
		}
		return res;
	}
	
	@RequestMapping(value="/camera",method=RequestMethod.POST)
	public Response insertCamera(@RequestBody IPCam ipCam)
	{
		Response res=new Response();
		if(service.save(ipCam)){
			res.setCode(ResponseCode.INSERT_SUCCESS);
			res.setMessage(ResponseMessage.CAMERA_MESSAGE);
		}
		else{
			res.setCode(ResponseCode.INSERT_FAIL);
			res.setMessage(ResponseMessage.CAMERA_MESSAGE);
		}
		
		return res;
		
	}
	
	@RequestMapping(value="/camera",method=RequestMethod.PUT)
	public Response updateCameraByID(@RequestBody IPCam ipCam)
	{
		Response res=new Response();
		
		if(service.update(ipCam))
		{
			res.setCode(ResponseCode.UPDATE_SUCCESS);
			res.setMessage(ResponseMessage.CAMERA_MESSAGE);
		}
		else
		{
			res.setCode(ResponseCode.UPDATE_FAIL);
			res.setMessage(ResponseMessage.CAMERA_MESSAGE);
		}
	
		return res;
	}
	
}
