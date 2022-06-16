package com.dev.spring.security.jwt.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.dev.spring.security.jwt.entity.User;
import com.dev.spring.security.jwt.entity.UserRequest;
import com.dev.spring.security.jwt.entity.UserResponse;
import com.dev.spring.security.jwt.service.IUserService;
import com.dev.spring.security.jwt.util.JWTUtil;


@Controller
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private JWTUtil util;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	
	@PostMapping("/saveUser")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		
		Integer id = userService.saveUser(user);
		String message= "User with id '"+id+"' saved succssfully!";
		//return new ResponseEntity<String>(message, HttpStatus.OK);
		return ResponseEntity.ok(message);
	}
	
	@PostMapping("/loginUser")
	public ResponseEntity<UserResponse> login(@RequestBody UserRequest request){
		
		//Validate username/password with DB(required in case of Stateless Authentication)
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getUsername(), request.getPassword()));
		String token =util.generateToken(request.getUsername());
		return ResponseEntity.ok(new UserResponse(token,"Token generated successfully!"));
	}
	
	@PostMapping("/getData")
	public ResponseEntity<String> testAfterLogin(Principal p){
		return ResponseEntity.ok("You are accessing data after a valid Login. You are :" +p.getName());
	}

	@GetMapping("/admin/loadusers")
	public List <User> sayHello(){
		List<User> lst=(List<User>) restTemplate.getForObject("http://localhost:5561/mainapp/loadusers",User.class);
		return lst;
		
	}

	@GetMapping("/admin/loaduser")
	public List<User> getAllProducts(){
		return (List<User>) restTemplate.getForObject("http://localhost:5561/mainapp/loaduser",User.class);
		//return restTemplate.getForObject("http://localhost:8765/api/product/names", List.class);
	}
}