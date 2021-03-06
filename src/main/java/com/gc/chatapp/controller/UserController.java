package com.gc.chatapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.gc.chatapp.controller.dto.MessageDto;
import com.gc.chatapp.controller.dto.UserDto;
import com.gc.chatapp.entities.ChatMessage;
import com.gc.chatapp.entities.User;
import com.gc.chatapp.service.ChatService;
import com.gc.chatapp.service.UserService;
import com.gc.chatapp.util.Emailer;
import com.gc.chatapp.util.ProfilePicture;
import com.google.gson.Gson;

@Controller

public class UserController {

	private static final Logger logger =
			Logger.getLogger(UserController.class.getName());

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String defaultPage()
	{
		return "index";
	}

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index()
	{
		return "index";
	}

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register()
	{
		return "register";
	}


	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login()
	{
		return "login";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)	
	public @ResponseBody String registerUser(@RequestBody UserDto requestUserDto)
	{
		// registration logic
		// 1.register with cpp
		// 2.if register with cpp is true, register with java
		// 3.if register with cpp is false, show error message
		// 4.if register with cpp is true and register with java is false, 
		// rollback cpp registration and show error message
		// 5.if register with both successful, registration successful, 
		// show success message

		User returnedUser = null;

		// default view page for registration
		String viewPage="register";

		// indicates if user is registered with java db successfully 
		boolean registeredWithJava = false;
		// indicates if user is registered with cpp db successfully
		boolean registeredWithCPP = false;

		String javaErrorMessage = "";
		String javaSuccessMessage = "";

		String cppErrorMessage = "";
		String cppSuccessMessage = "";

		String globalErrorMessage = "";
		String globalSuccessMessage = "";

		String emailId = requestUserDto.getEmailId();
		String fullName = requestUserDto.getFullName();
		String password = requestUserDto.getPassword();
		long phoneNo = Long.parseLong(requestUserDto.getPhoneNo());

		// create User object from request parameters
		User user = new User();
		user.setEmailId(emailId);
		user.setFullName(fullName);
		user.setPassword(password);
		user.setPhoneNo(phoneNo);

		//add user to CPP application db

		// prepare request JSON
		JSONObject requestJson=new JSONObject();
		try{	
			requestJson.put("name", fullName);
			requestJson.put("email", emailId);
			requestJson.put("mobileNumber", Long.toString(phoneNo));
			requestJson.put("password", password);
		}catch(JSONException e){
			logger.log(Level.INFO,"Could not create register request JSON");
		}

		HttpHeaders headers = new HttpHeaders();

		// setting http header - content type
		headers.setContentType(MediaType.APPLICATION_JSON);

		// creating request entity
		HttpEntity<String> entity = new HttpEntity<String>(requestJson.toString(),headers);

		// cpp registration API url
		String url = "http://172.31.11.110:8192/gc/userservice/users";

		logger.log(Level.INFO, "Calling CPP Api for registration");

		// calling API with PUT method to create user
		ResponseEntity<Object> response;

		try {
			response = restTemplate.exchange(url, HttpMethod.PUT, entity, Object.class);
			if(response.getStatusCode().value()== 200){

				cppErrorMessage = "";
				//model.addAttribute("errorMessage", "");
				// registration successful, notify user on front end
				cppSuccessMessage = "Registration Complete";
				//model.addAttribute("successMessage", "Registration Complete");
				logger.log(Level.INFO, "User registration to CPP DB successful");

				registeredWithCPP = true;

			}
			else {
				logger.log(Level.INFO, "Malformed Request(204)");
			}
			logger.log(Level.INFO, "CPP registration API call ended...");

		} catch (HttpClientErrorException e) {

			HttpStatus status = e.getStatusCode();
			if (status != HttpStatus.NO_CONTENT) {
				logger.log(Level.INFO, "User already exists ");
			}

			cppErrorMessage = "User already exists";
			//model.addAttribute("errorMessage", "User already exists");
			cppSuccessMessage = "";
			//model.addAttribute("successMessage", "");


			logger.log(Level.WARNING, e.toString());
		}

		if(registeredWithCPP) {
			// add user to JAVA application db
			returnedUser = userService.createUser(user);

			logger.log(Level.INFO, "Create user called with " + user.toString());

			// if user id is 0, user already registered
			if(returnedUser == null) {
				javaErrorMessage = "User already exists";
				javaSuccessMessage = "";
				//			model.addAttribute("errorMessage", "User already exists");
				//			model.addAttribute("successMessage", "");
				registeredWithJava = false;
			}
			// else, registration to Java DB was successful
			else {
				// send registration confirmation email to user's registered email id
				Emailer.send(user.getEmailId(), "Welcome to Global Chat Connect",
						"Hi " + user.getFullName() +
						". You have successfully registered with GCC." +
						"Your registered phone no is " + user.getPhoneNo());

				javaErrorMessage = "";
				javaSuccessMessage = "Registration Complete";

				// error message set to blank
				//			model.addAttribute("errorMessage", "");
				// registration successful, notify user on front end
				//			model.addAttribute("successMessage", "Registration Complete");
				registeredWithJava = true;
				//			viewPage="login";
			}

		}

		else {
			// delete user from cpp db(rollback registration)
			// delete method requires session id(user roles (eg admin role) have not
			// been implemented, can't be done now
		}

		String statusCode = "";

		if(registeredWithJava && !registeredWithCPP) {
			globalErrorMessage = cppErrorMessage;
			globalSuccessMessage = cppSuccessMessage;			
		}
		else if(!registeredWithJava && registeredWithCPP) {
			globalErrorMessage = javaErrorMessage;
			globalSuccessMessage = javaSuccessMessage;
		}
		else if(!registeredWithJava && !registeredWithCPP) {
			// can be changed to an application level error later
			globalErrorMessage = cppErrorMessage + " " + javaErrorMessage; 
			// we check for blank at front end
			globalSuccessMessage = javaSuccessMessage + cppSuccessMessage;
		}
		else if(registeredWithJava && registeredWithCPP){
			globalSuccessMessage = "Registration Complete";
			globalErrorMessage = "";
			viewPage = "login";
		}
		else {
			globalSuccessMessage = "";
			globalErrorMessage = "User already registered";
		}

		UserDto userDto = new UserDto();
		userDto.setUserId(returnedUser.getUserId());
		userDto.setGlobalErrorMessage(globalErrorMessage);
		userDto.setGlobalSuccessMessage(globalSuccessMessage);

		Gson gson = new Gson();

		return gson.toJson(userDto);
	}

	@RequestMapping(value="/login", method=RequestMethod.POST,
			produces="application/json", consumes="application/json")
	public @ResponseBody String login(@RequestBody UserDto requestUserDto)
	{
		// Login logic
		// 1. Login to Java DB to get user's own data and contact data(user's contacts)
		// 2. Login to CPP DB to get session ID
		// 3. Send all data in a DTO to Firebase
		// 4. If all the three tasks above succeed, pass this user's details
		// and session id to index.jsp, to set it in localStorage

		boolean javaLoginSuccess = false;
		boolean cppLoginSuccess = false;
		boolean firebaseLoginSuccess = false;

		String response;

		UserDto userDto = null;

		JSONObject jsonObject = new JSONObject();

		String loginMessage = "Incorrect username or password";

		String sessionId = "";

		String emailId = requestUserDto.getEmailId();
		String password = requestUserDto.getPassword();

		logger.log(Level.INFO, emailId + ":" + password);

		// get user object from java db
		User user = userService.login(emailId, password);

		if(user != null) {
			// java login succeeded
			javaLoginSuccess = true;
		}

		if(javaLoginSuccess) {
			// cpp login attempt
			// prepare request JSON
			JSONObject requestJson = new JSONObject();
			try{	
				requestJson.put("email", emailId);
				requestJson.put("password", password);

			}catch(JSONException e){
				logger.log(Level.INFO,"Could not create register request JSON");
			}

			HttpHeaders headers = new HttpHeaders();

			// setting http header - content type
			headers.setContentType(MediaType.APPLICATION_JSON);

			// creating request entity
			HttpEntity<String> entity = new HttpEntity<String>(requestJson.toString(),headers);

			// cpp login API url
			String url = "http://172.31.11.110:8192/gc/userauthservice/login/";

			logger.log(Level.INFO, "Calling CPP Api for login");

			// calling API with POST method to login user
			ResponseEntity<Object> loginResponse;

			try {
				loginResponse = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
				if(loginResponse.getStatusCode().value()== 200){
					logger.log(Level.INFO, "User login to CPP DB successful");

					Gson gson = new Gson();

					String loginResponseJson = loginResponse.getBody().toString();
					userDto = gson.fromJson(loginResponseJson, UserDto.class);
					sessionId = userDto.getSessionId();

					logger.log(Level.INFO, "UserDTO after cpp login : " + userDto);
					logger.log(Level.INFO, "Session Id after cpp login : " + userDto.getSessionId());

					cppLoginSuccess = true;

				}
				else if(loginResponse.getStatusCode().value()== 401){
					logger.log(Level.INFO, "Incorrect username or password "
							+ loginResponse.getStatusCode().value());
					cppLoginSuccess = false;
				}

				logger.log(Level.INFO, "CPP login API call ended...");

			} catch (RestClientException e) {
				// TODO Auto-generated catch block
				logger.log(Level.WARNING, e.toString());
			}
			// cpp login code ends
		}

		if(javaLoginSuccess && cppLoginSuccess) {
			userDto = new UserDto();
			userDto.setEmailId(emailId);
			userDto.setSessionId(sessionId);
			userDto.setUserStatus(true);
			// not returning other fields now
		}
		else {
			userDto = new UserDto();
			userDto.setUserStatus(false);
		}

		Gson gson = new Gson();

		return gson.toJson(userDto);
	}

	@RequestMapping(value="/updateProfile", method=RequestMethod.POST,
			produces="application/json", consumes="application/json")
	public @ResponseBody String updateProfile(@RequestBody UserDto requestUserDto)
	{

		String response;

		UserDto userDto = new UserDto();;

		JSONObject jsonObject = new JSONObject();

		String emailId = requestUserDto.getEmailId();
		String phoneNo = requestUserDto.getPhoneNo();
		String profilePictureUrl = requestUserDto.getProfilePictureUrl();
		String fullName = requestUserDto.getFullName();

		logger.log(Level.INFO, emailId + ":" + phoneNo + ":" + profilePictureUrl 
				+ fullName);

		User user = new User();
		user.setEmailId(emailId);
		user.setFullName(fullName);
		user.setPhoneNo(Long.parseLong(phoneNo));
		try {
			user.setProfilePictureURL(ProfilePicture.storeBase64(emailId, profilePictureUrl));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.log(Level.INFO, e.toString());
		}

		// get user object from java db
		boolean updateProfileSuccess = userService.updateProfile(user);

		if(updateProfileSuccess) {			
			userDto.setUserStatus(true);
		}
		else {
			userDto.setUserStatus(false);
		}

		Gson gson = new Gson();

		return gson.toJson(userDto);
	}

	@RequestMapping(value="/getUserByEmail", method=RequestMethod.POST,
			produces="application/json", consumes="application/json")
	public @ResponseBody String getUserByEmail(@RequestBody UserDto requestUserDto)

	{

		String response;

		UserDto userDto = new UserDto();

		JSONObject jsonObject = new JSONObject();

		String emailToSearch = userDto.getEmailId();

		logger.log(Level.INFO, "Searching for email: " + emailToSearch);

		// get user object from java db
		User user = userService.getUserByEmail(userDto.getEmailId());

		try {
			user.setProfilePictureURL(ProfilePicture.getBase64(user.getEmailId()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.log(Level.INFO, e.toString());
		} finally {
			if(user != null) {			
				userDto.setUserStatus(true);
			}
			else {
				userDto.setUserStatus(false);
			}
		}

		Gson gson = new Gson();

		return gson.toJson(userDto);
	}

	@RequestMapping(value="/getUserByName", method=RequestMethod.POST,
			produces="application/json", consumes="application/json")
	public @ResponseBody String getUsersByName(@RequestBody UserDto requestUserDto)
	{
		String response;

		UserDto userDto = new UserDto();

		JSONObject jsonObject = new JSONObject();

		String nameToSearch = userDto.getFullName();

		logger.log(Level.INFO, "Searching for name: " + nameToSearch);

		List<User> tempUserList = new ArrayList<>();
		List<User> userList = new ArrayList<>();

		// get user object from java db
		tempUserList = userService.getUsersByName(userDto.getFullName());

		// convert url link to base64 form for ui rendering
		for(User user: tempUserList) {
			try {
				user.setProfilePictureURL(ProfilePicture.getBase64(user.getEmailId()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.log(Level.INFO, e.toString());
			}
			userList.add(user);
		}

		if(userList == null || userList.isEmpty()) {			
			userDto.setUserStatus(false);
		}
		else {
			userDto.setUserStatus(true);
		}

		userDto.setUserList(userList);

		Gson gson = new Gson();

		return gson.toJson(userDto);
	}

	@RequestMapping(value="/getAllUsers", method=RequestMethod.POST,
			produces="application/json", consumes="application/json")
	public @ResponseBody String getAllUsers()

	{
		List<User> tempUserList = new ArrayList<>();
		List<User> userList = new ArrayList<>();

		// get user object from java db
		tempUserList = userService.getAllUsers();

		// convert url link to base64 form for ui rendering
		for(User user: tempUserList) {
			try {
				user.setProfilePictureURL(ProfilePicture.getBase64(user.getEmailId()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.log(Level.INFO, e.toString());
			}
			userList.add(user);
		}


		Gson gson = new Gson();

		return gson.toJson(userList);
	}

	@RequestMapping(value="/addContact", method=RequestMethod.POST,
			produces="application/json", consumes="application/json")
	public @ResponseBody String addContact(@RequestBody UserDto requestUserDto)
	{

		String response;

		UserDto userDto = new UserDto();

		JSONObject jsonObject = new JSONObject();

		String emailId = requestUserDto.getEmailId();
		String phoneNo = requestUserDto.getPhoneNo();
		String profilePictureUrl = "";
		try {
			profilePictureUrl = ProfilePicture.storeBase64(emailId,
					requestUserDto.getProfilePictureUrl());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.log(Level.INFO, e1.toString());
		}
		String fullName = requestUserDto.getFullName();
		long userId = requestUserDto.getUserId();		

		logger.log(Level.INFO, emailId + ":" + phoneNo + ":" + profilePictureUrl 
				+ fullName);

		User userToBeAdded = new User();
		userToBeAdded.setEmailId(emailId);
		userToBeAdded.setFullName(fullName);
		userToBeAdded.setPhoneNo(Long.parseLong(phoneNo));
		userToBeAdded.setProfilePictureURL(profilePictureUrl);
		userToBeAdded.setUserId(userId);
		
		User currentUser = new User();
		currentUser.setEmailId(requestUserDto.getRequestCreatorEmail());;
		
		boolean addContactSuccess = userService.addUserToUserContacts(userToBeAdded, currentUser);

		if(addContactSuccess) {			
			userDto.setUserStatus(true);
		}
		else {
			userDto.setUserStatus(false);
		}

		Gson gson = new Gson();

		return gson.toJson(userDto);
	}
}
