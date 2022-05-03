package com.librarybooksystem.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
		
		public static int loginValidator(String email,String password,String type) throws Exception
		{
			
		Connection connection;
		PreparedStatement statement;
		connection=ConnectionUtilDao.sqlConnection();
		ResultSet rs = null;
		String query = "SELECT email,PASSWORD,id,user_type FROM user_details WHERE email=? AND user_type=?";
		
		statement = connection.prepareStatement(query);
		statement.setString(1,email);
	
		statement.setString(2,type);
		
		rs = statement.executeQuery();
		String mail = null;
		String Password=null;
		int id=0;
		int count;
		while(rs.next())
		{
		mail=rs.getString("email");
		Password=rs.getString("password");
		id=rs.getInt("id");
		}
	    if(mail==null)
		{
	    	count=-1;
		}
	    else if(Password.equals(password))
		{	
			count=id;
			
		}
		else
		{
			count=-2;
			
		}
		return count;
	}
		
}
