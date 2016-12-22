package kh.com.kshrd.ipcam.controller.user;

import kh.com.kshrd.ipcam.entity.user.User;

import kh.com.kshrd.ipcam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * 
 * @author Phearun, Lun Sovathana
 *
 */
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getUserByID(@PathVariable("id") int id)
	{
		User data= userService.findOne(id);
		Map<String,Object> map = new HashMap<>();

		try{
			if(data != null){
				map.put("DATA",data)	;
				map.put("MESSAGE","successfull");
				map.put("STATUS","200");

			}
			else{
				map.put("MESSAGE","Unsuccessfull");
				map.put("STATUS","400");

			}
		}
		catch (Exception e){
			map.put("MESSAGE","Unsuccessfull");
			map.put("STATUS","400");
			map.put("Exception",e);
		}
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
	}

}
