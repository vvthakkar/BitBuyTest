package com.bitbuy.JavaTestApp.Controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitbuy.JavaTestApp.Service.UserService;
import com.bitbuy.JavaTestApp.model.User;
import com.bitbuy.JavaTestApp.model.UserCredentials;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	public UserService userService;
	
	/*
	 * Register endpoints which takes credentials for users 
	 */
	@PostMapping("/register")
	public String createAccount(@Validated @RequestBody UserCredentials userCredentials) {
		boolean registrationStatus=userService.registerUser(userCredentials);
		
		if(registrationStatus)
			return "registered success";
		else 
			return "error";
	}
	
	/*
	 * Login endpoint which takes in username and password and matches against all available set of credentials in 
	 * Usercredentials list(Datasource)
	 */
	
	@PostMapping("/login")
	public String login(@Validated @RequestBody UserCredentials userCredentials) {
		boolean loginStatus=userService.loginUser(userCredentials);
		if(loginStatus)
			return "Login success";
		else 
			return "Incorrect Credentials";
	}
	
	/*
	 * Returns user information by UUID
	 */
	
	@GetMapping("/users/{uuid}")
	@ResponseBody
	public String getUser(@PathVariable("uuid") String uuid) {
		User user = userService.getUserByUuid(uuid);
		if(Objects.isNull(user))
			return "No Data Found";
		else
			return user.toString();
	}
	
	/*
	 * Post end point to add/update users by UUId. Tries to looks for user by uuID in data source.
	 * if no data found for that user it adds that data to datasource
	 */
	
	@PostMapping("/users/{uuid}")
	@ResponseBody
	public String addOrUpdateUser(@Validated @RequestBody User user,@PathVariable("uuid") String uuid) {
		user.setUuid(uuid);
		return userService.addOrUpdateUserData(user);
	}
}
