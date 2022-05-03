package com.librarybooksystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarybooksystem.dao.QueryReplyRepository;
import com.librarybooksystem.model.QueryReply;

@RestController
public class QueryReplyController {
	@Autowired
	QueryReplyRepository queryReplyRepository;
	@GetMapping("user/query")
	public String query(@RequestParam("userId") String userId,@RequestParam("userQuery") String userQuery)
	{
		QueryReply query = new QueryReply();
		query.setUserId(userId);
		query.setUserQuery(userQuery);
		String result =null;
		try {
			queryReplyRepository.save(query);
			result="success";
		}
		catch(Exception e)
		{
			result="failed";
		}
		
		return result;
		
	}
	@GetMapping("user/queryCheck/{id}")
	public List<QueryReply> query(@RequestParam("userId") String userId)
	{
		List<QueryReply> queryList=null;
		
		try {
			queryList=queryReplyRepository.findAllByUserId(userId);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return queryList;
		
	}
	@GetMapping("user/allQueryCheck")
	public List<QueryReply> queryCheckAll()
	{
		List<QueryReply> queryList=null;
		
		try {
			queryList=queryReplyRepository.findAll();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return queryList;
		
	}
	@GetMapping("user/queryReply")
	public String queryReply(@RequestParam("userId") String userId,@RequestParam("reply") String reply)
	{
		String result=null;
		
		try {
			queryReplyRepository.queryReply(userId,reply);
			result="success";
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result="failed";
		}
		
		return result;
		
	}

}
