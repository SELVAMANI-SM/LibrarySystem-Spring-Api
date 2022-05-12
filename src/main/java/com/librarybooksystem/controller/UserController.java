package com.librarybooksystem.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.librarybooksystem.connection.Login;

import com.librarybooksystem.dao.UserRepository;
import com.librarybooksystem.dto.Message;
import com.librarybooksystem.exception.ValidationException;
import com.librarybooksystem.model.User;
import com.librarybooksystem.validation.UserValidation;


@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	@PostMapping("user/register")
	public ResponseEntity<?> register(@RequestBody User user){
		try {
		UserValidation.register(user);
		
		userRepository.save(user);
		Message message = new Message("Success");
		return new ResponseEntity<>(message,HttpStatus.OK);
	     }catch(ValidationException e) {
		Message message = new Message(e.getMessage());
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
	     }
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("user/allUser")
	public ResponseEntity<?> allUser(){
		List<User> user=null;
		String user1="user";
		try {
			user=userRepository.findByuserType(user1);
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
}
	@GetMapping("user/allMember")
	public ResponseEntity<?> allMember(){
		List<User> user=null;
		String user1="member";
		try {
			user=userRepository.findByuserType(user1);
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message= new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	@GetMapping("user/profile/{id}")
	public ResponseEntity<?> profile(@PathVariable("id") Integer id) throws ValidationException{
		Optional<User> user=null;
		
		try {
			user=userRepository.findById(id);
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message= new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PostMapping("user/login")
	public ResponseEntity<?> findByEmailAndPassword(@RequestBody User user) throws Exception,ValidationException
	{
		int count=0;
		String email=user.getEmail();
		String password=user.getPassword();
		String type=user.getUserType();
		try
		{
		count=Login.loginValidator(email,password,type);
		return new ResponseEntity<Integer>(count,HttpStatus.OK);
		}
		catch(ValidationException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}

}
	
	


