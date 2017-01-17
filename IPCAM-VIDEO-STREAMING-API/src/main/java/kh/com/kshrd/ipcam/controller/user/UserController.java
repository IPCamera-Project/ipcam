package kh.com.kshrd.ipcam.controller.user;

import kh.com.kshrd.ipcam.entity.form.UserInputer;
import kh.com.kshrd.ipcam.entity.user.User;
import kh.com.kshrd.ipcam.respone.Response;
import kh.com.kshrd.ipcam.respone.ResponseCode;
import kh.com.kshrd.ipcam.respone.ResponseMessage;
import kh.com.kshrd.ipcam.respone.ResponseObject;
import kh.com.kshrd.ipcam.service.UserDetailService;
import kh.com.kshrd.ipcam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;


/**
 *
 * @author Rina
 *
 */
@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	UserDetailService userDetailService;

	@Autowired
	Environment environment;

	@GetMapping(value="/getUserById")
	@ResponseBody
	public ResponseObject<User> getUserByID(@RequestParam("ID") int id)
	{
		ResponseObject<User> userResponseObject = new ResponseObject<>();

		User data= userService.getUserById(id);
		data.setImage(getFilePath(data.getImage()));

		if(userResponseObject!=null	){
			userResponseObject.setCode(ResponseCode.QUERY_FOUND);
			userResponseObject.setMessage(ResponseMessage.USER_MESSAGE);
		}else{
			userResponseObject.setCode(ResponseCode.QUERY_NOT_FOUND);
			userResponseObject.setMessage(ResponseMessage.USER_MESSAGE);
		}
		return userResponseObject;
	}

	@GetMapping("/getUserByEmail")
	ResponseObject<User> getUserByEmail(@RequestParam("email") String email){
		ResponseObject<User> userResponseObject = new ResponseObject<>();

		User user = userService.getUserByEmail(email);
		user.setImage(getFilePath(user.getImage()));
		userResponseObject.setData(user);

		if(userResponseObject!=null	){
			userResponseObject.setCode(ResponseCode.QUERY_FOUND);
			userResponseObject.setMessage(ResponseMessage.USER_MESSAGE);
		}else{
			userResponseObject.setCode(ResponseCode.QUERY_NOT_FOUND);
			userResponseObject.setMessage(ResponseMessage.USER_MESSAGE);
		}
		return userResponseObject;
	}

	@GetMapping("/emailChecker")
	boolean emailChecker(@RequestParam("EMAIL")String email){

		if (userService.emailChecker(email)){
			return true;
		}
		else
		{
			return false;
		}
	}

	@PostMapping("/addUser")
	Response adduser(@RequestParam("USERNAME") String username, @RequestParam("EMAIL") String email
			       , @RequestParam("PASSWORD") String password, @RequestParam("IMAGE")MultipartFile multipartFile,
					 @RequestParam("ROLE_ID")  int role_id){

		Response response = new Response();
				System.out.print("fddkdfkdfkjdfkjdf"+multipartFile);
		String genName =	fileNameGen(fileNameGen(multipartFile.getOriginalFilename()));

		UserInputer userInputer = new UserInputer();
			userInputer.setUsername(username);
			userInputer.setEmail(email);
			userInputer.setPassword(password);
			userInputer.setImage(genName);
			userInputer.setRole_id(role_id);

		if(userService.addUser(userInputer)){
			response.setCode(ResponseCode.INSERT_SUCCESS);
			response.setMessage(ResponseMessage.USER_MESSAGE);
			// Save the file locally
			BufferedOutputStream stream = null;
			try {
				stream = new BufferedOutputStream(new FileOutputStream(filepath));
				stream.write(multipartFile.getBytes());
				stream.close();
				userDetailService.insertRole(userDetailService.getLastId(),userInputer.getRole_id());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			response.setCode(ResponseCode.INSERT_FAIL);
			response.setMessage(ResponseMessage.USER_MESSAGE);
		}
		return response;
	}

	@PostMapping("/addFacebookAccount")
	Response adduserWithFacebookAccount( @RequestParam("USERNAME") String username, @RequestParam("EMAIL") String email
								     	,@RequestParam("PASSWORD") String password, @RequestParam("USER_PROFILE")String url_image,
								 		 @RequestParam("USER_FACEBOOK_ID")String user_facebook_id){

		Response response = new Response();

		UserInputer userInputer = new UserInputer();
		userInputer.setUsername(username);
		userInputer.setEmail(email);
		userInputer.setPassword(password);
		userInputer.setImage(url_image);
		userInputer.setUser_facebook_id(user_facebook_id);
		userInputer.setRole_id(1);

		if(userService.adduserWithFacebookAccount(userInputer)){
			response.setCode(ResponseCode.INSERT_SUCCESS);
			response.setMessage(ResponseMessage.USER_MESSAGE);
			// Save the file locally\
			try {
				userDetailService.insertRole(userDetailService.getLastId(),userInputer.getRole_id());
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		String genName =	fileNameGen(fileNameGen(multipartFile.getOriginalFilename()));

		try{
			if(userService.updateUserImage(genName,user_id)){
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

	@PutMapping("/updateUserName")
	Response updateUserName(@RequestParam("USERNAME") String username, @RequestParam("USER_ID") int user_id){

		Response response = new Response();

		if(userService.modifierUsername(username,user_id)){
			response.setCode(ResponseCode.INSERT_SUCCESS);
			response.setMessage(ResponseMessage.USER_MESSAGE);
		}else{
			response.setCode(ResponseCode.INSERT_FAIL);
			response.setMessage(ResponseMessage.USER_MESSAGE);
		}
		return response;
	}

	@PutMapping("/updateUserPassword")
	Response updateUserPassword(@RequestParam("PASSWORD") String password, @RequestParam("USER_ID") int user_id){

		Response response = new Response();

		if(userService.modifierUsername(password,user_id)){
			response.setCode(ResponseCode.INSERT_SUCCESS);
			response.setMessage(ResponseMessage.USER_MESSAGE);
		}else{
			response.setCode(ResponseCode.INSERT_FAIL);
			response.setMessage(ResponseMessage.USER_MESSAGE);
		}
		return response;
	}

	@PutMapping("/updateUser")
	Response updateUser(@RequestParam("USERNAME") String username, @RequestParam("EMAIL") String email, @RequestParam("PASSWORD") String password){

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


	/**
	 *@Method fileNameGen
	 * @Return new file Name gen
	 */
	String filepath;

	String fileNameGen(String filename){

		String[] output = filename.split("\\.");//split string specific "."
		String randomFileName = UUID.randomUUID()+"."+"jpg";
		randomFileName = randomFileName+"";
		System.out.println(randomFileName);
			String directory = environment.getProperty("file.upload.path");
			filepath = Paths.get(directory, randomFileName).toString();



		return randomFileName;
	}
	/**
	 * @Method to get file directory
	 */
	String getFilePath(String fileName){
		String directory = environment.getProperty("file.getImage.path");
		System.out.print("images"+directory);
		filepath = Paths.get(directory, fileName).toString();

		return filepath;
	}


}