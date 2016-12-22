package kh.com.kshrd.ipcam.controller.user;

import kh.com.kshrd.ipcam.entity.form.UserInputer;
import kh.com.kshrd.ipcam.entity.user.User;
import kh.com.kshrd.ipcam.respone.Response;
import kh.com.kshrd.ipcam.respone.ResponseCode;
import kh.com.kshrd.ipcam.respone.ResponseMessage;
import kh.com.kshrd.ipcam.respone.ResponseObject;
import kh.com.kshrd.ipcam.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.*;


/**
 *
 * @author Phearun, Lun Sovathana
 *
 */
@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	Environment environment;

	@GetMapping(value="/getUserById/{id}")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> getUserByID(@PathVariable("id") int id)
	{
		User data= userService.getUserById(id);
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

	@GetMapping("/getUserByEmail")
	ResponseObject<User> getUserByEmail(@RequestParam("email") String email){
		ResponseObject<User> userResponseObject = new ResponseObject<>();
		userResponseObject.setData(userService.getUserByEmail(email));
		if(userResponseObject!=null	){
			userResponseObject.setCode(ResponseCode.QUERY_FOUND);
			userResponseObject.setMessage(ResponseMessage.USER_MESSAGE);
		}else{
			userResponseObject.setCode(ResponseCode.QUERY_NOT_FOUND);
			userResponseObject.setMessage(ResponseMessage.USER_MESSAGE);
		}
		return userResponseObject;
	}


	@PostMapping("/addUser")
	Response adduser(@RequestParam("USERNAME") String username
			, @RequestParam("EMAIL") String email
			, @RequestParam("PASSWORD") String password
			, @RequestParam("IMAGE")MultipartFile multipartFile
	){

		Response response = new Response();

		String filename = multipartFile.getOriginalFilename();
		String[] output = filename.split("\\.");
		String nameGenerator = UUID.randomUUID()+"."+output[1];
		nameGenerator = nameGenerator+"";

		UserInputer userInputer = new UserInputer();
		userInputer.setUsername(username);
		userInputer.setEmail(email);
		userInputer.setPassword(password);
		userInputer.setImage(nameGenerator);
		if(userService.addUser(userInputer)){
			response.setCode(ResponseCode.INSERT_SUCCESS);
			response.setMessage(ResponseMessage.USER_MESSAGE);
		}else{
			response.setCode(ResponseCode.INSERT_FAIL);
			response.setMessage(ResponseMessage.USER_MESSAGE);
		}
		return response;
	}

	@PutMapping("/updateUserImage")
	public Response updateUserImage(@RequestParam("IMAGE")MultipartFile multipartFile,
									@RequestParam("USER_ID")int user_id){

		Response response = new Response();


		String   filename = multipartFile.getOriginalFilename();
		String[] output = filename.split("\\.");//split string specific "."
		String randomFileName = UUID.randomUUID()+"."+output[1];
		randomFileName = randomFileName+"";

		String directory = environment.getProperty("file.upload.path");
		String filepath = Paths.get(directory, randomFileName).toString();

		try{
			if(userService.updateUserImage(filepath,user_id)){
				// Save the file locally
				BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(filepath));
				stream.write(multipartFile.getBytes());
				stream.close();

				response.setCode(ResponseCode.UPDATE_SUCCESS);
				response.setMessage(ResponseMessage.USER_MESSAGE);
			}
			else {

				response.setCode(ResponseCode.UPDATE_FAIL);
				response.setMessage(ResponseMessage.USER_MESSAGE);
			}
		}catch (Exception e){
			System.out.print(e);
		}
		return response;
	}


	@PutMapping("/updateUser")
	Response updateUser(@RequestParam("USERNAME") String username
			, @RequestParam("EMAIL") String email
			, @RequestParam("PASSWORD") String password){
		Response response = new Response();
		UserInputer userInputer = new UserInputer();
		userInputer.setUsername(username);
		userInputer.setEmail(email);
		userInputer.setPassword(password);

		if(userService.addUser(userInputer)){
			response.setCode(ResponseCode.INSERT_SUCCESS);
			response.setMessage(ResponseMessage.USER_MESSAGE);
		}else{
			response.setCode(ResponseCode.INSERT_FAIL);
			response.setMessage(ResponseMessage.USER_MESSAGE);
		}
		return response;
	}

	@DeleteMapping("/removeUser")
	Response removeUser(@RequestParam("USER_ID")int user_id){
		Response response = new Response();
		if(userService.remove(user_id)){
			response.setCode(ResponseCode.DELETE_SUCCESS);
			response.setMessage(ResponseMessage.USER_MESSAGE);
		}else{
			response.setMessage(ResponseMessage.USER_MESSAGE);
			response.setCode(ResponseCode.DELETE_FAIL);
		}
		return response;
	}

}