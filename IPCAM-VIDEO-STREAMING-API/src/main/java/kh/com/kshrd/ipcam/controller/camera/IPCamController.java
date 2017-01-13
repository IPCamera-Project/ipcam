package kh.com.kshrd.ipcam.controller.camera;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
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

	@RequestMapping(value="/all",method=RequestMethod.GET)
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

	@GetMapping("getCameraByUserId")
	public ResponseList<IPCam> getCameraByUserId(@RequestParam("USER_ID")int user_id){
		ArrayList<IPCam> data=(ArrayList<IPCam>) service.findCameraByUserId(user_id);
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

	@RequestMapping(value="/getCameraById",method=RequestMethod.GET)
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

	@RequestMapping(value="/removeCameraById",method=RequestMethod.DELETE)
	public Response deleteCameraByID(@RequestParam("ID") int id)
	{

		Response res=new Response();


		if(service.remove(id)){
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
	public Response updateCameraByID(@RequestBody IPCameraModifier ipCameraModifier)
	{
		Response res=new Response();
		System.out.print(ipCameraModifier.getCamera_id());
		System.out.print(ipCameraModifier.getIp_address());
		System.out.print(ipCameraModifier.getName());
		System.out.print(ipCameraModifier.getPassword());
		System.out.print(ipCameraModifier.getUsername());
		System.out.print(ipCameraModifier.getIp_address());
		System.out.print(ipCameraModifier.getWeb_port());
		System.out.print(ipCameraModifier.getRtsp_port());
		System.out.print(ipCameraModifier.getModel_id());


		if(service.update(ipCameraModifier))
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
