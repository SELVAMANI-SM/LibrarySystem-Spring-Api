package com.librarybooksystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarybooksystem.connection.Query;
import com.librarybooksystem.dao.QueryReplyRepository;
import com.librarybooksystem.dto.Message;
import com.librarybooksystem.exception.ValidationException;
import com.librarybooksystem.model.QueryReply;
import com.librarybooksystem.validation.QueryValidation;

@RestController
public class QueryReplyController {
	@Autowired
	QueryReplyRepository queryReplyRepository;
	@GetMapping("user/query")
	public ResponseEntity<?> query(@RequestParam("userId") Integer userId,@RequestParam("userQuery") String userQuery)
	{
		QueryReply query = new QueryReply();
		query.setUserId(userId);
		query.setUserQuery(userQuery);
		try {
			QueryValidation.query(query);
			queryReplyRepository.save(query);
			Message message=new Message("success");
			return new ResponseEntity<>(message,HttpStatus.OK);
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
	@GetMapping("user/queryChecks/{id}")
	public ResponseEntity<?> query(@PathVariable("id") Integer userId)
	{
		List<QueryReply> queryList=null;
		
		try {
			queryList=queryReplyRepository.findByUserId(userId);
			return new ResponseEntity<>(queryList,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	@GetMapping("user/allQueryCheck")
	public ResponseEntity<?> queryCheckAll()
	{
		List<QueryReply> queryList=null;
		
		try {
			queryList=queryReplyRepository.findAll();
			return new ResponseEntity<>(queryList,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("user/queryReply")
	public ResponseEntity<?> queryReply(@RequestParam("userId") Integer userId,@RequestParam("reply") String reply,@RequestParam("userQuery") String userQuery)
	{
		
		
		try {
			QueryValidation.replyCheck(reply, userId, userQuery);
			Query.reply(reply, userId,userQuery);
			Message message= new Message("success");
			return new ResponseEntity<>(message,HttpStatus.OK);
			
			
		}
		catch(ValidationException e)
		{
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
