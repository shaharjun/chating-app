package com.gc.chatapp.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.gc.chatapp.entities.User;
import com.gc.chatapp.service.UserService;
import com.gc.chatapp.util.Emailer;

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
	public String registerUser(@RequestParam("emailId") String emailId,
			@RequestParam("password") String password,
			@RequestParam("fullName") String fullName,
			@RequestParam("phoneNo") long phoneNo, Model model)
	{
		// registration logic
		// 1.register with cpp
		// 2.if register with cpp is true, register with java
		// 3.if register with cpp is false, show error message
		// 4.if register with cpp is true and register with java is false, 
		// rollback cpp registration and show error message
		// 5.if register with both successful, registration successful, 
		// show success message


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
			requestJson.put("mobileNumber", phoneNo);
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
			if(response.getStatusCode().value()== 204){
				cppErrorMessage = "";
				//model.addAttribute("errorMessage", "");
				// registration successful, notify user on front end
				cppSuccessMessage = "Registration Complete";
				//model.addAttribute("successMessage", "Registration Complete");
				logger.log(Level.INFO, "User registration to CPP DB successful");

				registeredWithCPP = true;

			}
			else if(response.getStatusCode().value()== 409){
				logger.log(Level.INFO, "User already exists "
						+ response.getStatusCode().value());
				cppErrorMessage = "User already exists";
				//model.addAttribute("errorMessage", "User already exists");
				cppSuccessMessage = "";
				//model.addAttribute("successMessage", "");
			}

			logger.log(Level.INFO, "CPP registration API call ended...");
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			logger.log(Level.WARNING, e.toString());
		}

		if(registeredWithCPP) {
			// add user to JAVA application db
			long userId = userService.createUser(user);
			
			logger.log(Level.INFO, "Create user called with " + user.toString());

			// if user id is 0, user already registered
			if(userId == 0L) {
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
			globalErrorMessage = javaErrorMessage + ", " + cppErrorMessage;
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
		model.addAttribute("errorMessage", globalErrorMessage);
		model.addAttribute("successMessage", globalSuccessMessage);		

		return viewPage;
	}

	@RequestMapping(value="/login", method=RequestMethod.POST, produces="application/json")
	public String validateUser(@RequestParam("emailId") String emailId,@RequestParam("password") String password)
	{
		String response;

		JSONObject jsonObject = new JSONObject();

		String viewPage = "login";

		if(emailId.equals("root@gmail.com") && password.equals("root123")) {
			viewPage = "index";
		}

		return viewPage;
	}

}
