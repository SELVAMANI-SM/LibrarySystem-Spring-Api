package com.librarybooksystem.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarybooksystem.connection.Login;
import com.librarybooksystem.dao.UserRepository;
import com.librarybooksystem.model.User;


@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	@GetMapping("user/register")
	public String regsiter(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("userType") String userType,@RequestParam("mobile") String mobile)
	{
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setUserType(userType);
		user.setMobile(mobile);
		String result=null;
		try {
			userRepository.save(user);
			result="success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result="failed";
		}
		return result;
		
}
	
	@GetMapping("user/allUser")
	public List<User> allUser(){
		List<User> user=null;
		String user1="user";
		try {
			user=userRepository.findByuserType(user1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
		
}
	@GetMapping("user/allMember")
	public List<User> allMember(){
		List<User> user=null;
		String user1="member";
		try {
			user=userRepository.findByuserType(user1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
		
	}
	@GetMapping("user/profile/{id}")
	public Optional<User> profile(@PathVariable("id") Integer id){
		Optional<User> user=null;
		
		try {
			user=userRepository.findById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
		
	}
	
	@GetMapping("user/login")
	public int findByEmailAndPassword(@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("type") String type) throws Exception
	{
		int count=0;
		
		count=Login.loginValidator(email,password,type);
		return count;
	}

}
	
	


