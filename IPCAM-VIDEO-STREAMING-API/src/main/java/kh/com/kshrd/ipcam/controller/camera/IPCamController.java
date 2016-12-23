package kh.com.kshrd.ipcam.controller.camera;
import java.util.ArrayList;

import kh.com.kshrd.ipcam.entity.camera.IPCam;
import kh.com.kshrd.ipcam.entity.form.IPCameraInputer;
import kh.com.kshrd.ipcam.entity.form.IPCameraModifier;
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
	
	@GetMapping(value="/getAllCamera")
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
	
	
	@GetMapping(value="/getCameraById")
	public ResponseObject<IPCam> getCameraByID(@RequestParam("ID") int id)
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
	
	@DeleteMapping(value="/removeCameraById")
	public Response deleteCameraByID(@RequestParam("ID") int id)
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
	
	@RequestMapping(value="/addCamera",method=RequestMethod.POST)
	public Response insertCamera(@RequestBody IPCameraInputer ipCam)
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
	
	@RequestMapping(value="/updateCamera",method=RequestMethod.PUT)
	public Response updateCameraByID(@RequestBody IPCameraModifier ipCam)
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
