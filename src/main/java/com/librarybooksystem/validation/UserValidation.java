package com.librarybooksystem.validation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.librarybooksystem.connection.ConnectionUtilDao;
import com.librarybooksystem.exception.ValidationException;
import com.librarybooksystem.model.User;

public class UserValidation {
	
	public static void  register(User user) throws Exception,ValidationException
	{
		
		NameCheck(user.getName());
		GmailCheck(user.getEmail());
		PasswordCheck(user.getPassword());
		MobileCheck(user.getMobile());
	    IdCheck(user.getUserType());
		fineCheck(user.getFine());
		ValidatorMobile(user.getMobile());
		ValidatorEmail(user.getEmail());
		
	
	}
	public  static int fineCheck(int fine) throws ValidationException {
		if(fine!=0)
		{
			throw new ValidationException("Enter valid Fine");
		}
		else
		{
			return 1;
		}
		
		
	}
	public static int NameCheck(String userName)throws ValidationException
	{
		//System.out.println(userName);
		if(userName==null|| userName.isBlank())
		{
			throw new ValidationException("Enter vaild Name");
		}
		else
			return 1;
		
	}
	public static int GmailCheck(String userGmail) throws ValidationException
	{
		if(!userGmail.contains("@")|| !userGmail.contains("."))
		{
			throw new ValidationException("Enter valid Gmail");
		}
		else
		{
			return 1;
		}
		
	}
	public static  int PasswordCheck(String userPassword) throws ValidationException
	{
		if(userPassword.length() < 8 && userPassword.length() > 16)
		{
			throw new ValidationException("Enter valid password it must contain 8 to 16 character");
		}
		else
			return 1;
	}
	public static int MobileCheck(String userMobile) throws ValidationException
	{
		int count1=0;
		char[] mobile=userMobile.toCharArray();
		if(userMobile.length()>10||userMobile.length()<10)
		{
			
		throw new ValidationException("Enter valid Number ");
		
		}
		else
			count1++;
		for(char i:mobile)
		{
			if(Character.isAlphabetic(i))
			{
			
				throw new ValidationException("Enter Only Numeric Value");
				
				
			}
			else
				count1++;
		
		}
		if(count1==11)
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
		
	}
	public static int IdCheck(String string) throws ValidationException
	{
		if(string==null||string.trim()=="")
		{
			throw new ValidationException("Enter your correct Id");
		}
		else
			return 1;
	
	

 
}
	public static  void ValidatorEmail(String email) throws Exception,ValidationException
	{
	Connection connection;
	PreparedStatement statement;
	connection=ConnectionUtilDao.sqlConnection();
	ResultSet rs = null;
	String query = "SELECT email FROM user_details WHERE email= ?";
	statement = connection.prepareStatement(query);
	statement.setString(1,email);
	
	rs = statement.executeQuery();
	String mail = null;
	
	while(rs.next())
	{
	mail=rs.getString("email");

	}
	if(mail==null)
	{

	}
	

	else
	{
		throw new ValidationException("Already existing user");
	}
}
	public static  void  ValidatorMobile(String mobile) throws Exception,ValidationException
	{
	Connection connection;
	PreparedStatement statement;
	connection=ConnectionUtilDao.sqlConnection();
	ResultSet rs = null;
	String query = "SELECT mobile FROM user_details WHERE mobile= ? ";
	statement = connection.prepareStatement(query);
	statement.setString(1,mobile);
	
	rs = statement.executeQuery();
	String number = null;
	
	while(rs.next())
	{
	number=rs.getString("mobile");

	}
	if(number==null)
	{
	
	}
	else
	{
	  throw new ValidationException("Already existing user"); 
	}
}
}
