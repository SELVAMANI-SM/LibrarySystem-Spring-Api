package com.librarybooksystem.validation;


import com.librarybooksystem.exception.ValidationException;
import com.librarybooksystem.model.QueryReply;

public class QueryValidation {
 public static void query(QueryReply query) throws ValidationException
 {
	 checkId(query.getUserId());
	 checkQuery(query.getUserQuery());
 }

public static void checkQuery(String userQuery) throws ValidationException {
if(userQuery==null||userQuery.trim()=="")
{
	throw new ValidationException("Invalid Query");
}
	
}

public static void checkId(Integer id) throws ValidationException {
	
	if(id<0)
	{
		throw new ValidationException("Invalid UserId");
	}
}
public static void replyCheck(String reply,Integer userId,String userQuery ) throws ValidationException
{
	if(reply==null||reply.trim()=="")
	{
		throw new ValidationException("Invalid Reply");
	}
	if(userId<0)
	{
		throw new ValidationException("Invalid UserId");
	}
	if(userQuery==null||userQuery.trim()=="")
	{
		throw new ValidationException("Invalid Query");
	}
	
}
}
