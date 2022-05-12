package com.librarybooksystem.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Query {
	public static String reply(String name, int id,String name1) throws Exception {

	Connection connection;
	PreparedStatement statement;
	connection=ConnectionUtilDao.sqlConnection();
	
	
	String query = "UPDATE query_reply SET reply=? WHERE user_id=? AND user_query =?";
	statement = connection.prepareStatement(query);
	statement.setString(1,name);
	statement.setInt(2,id);
	statement.setString(3,name1);
     String  result=null;
	int row =statement.executeUpdate();
	if(row==1)
	{
		result="success";
		
	}
	else
	{
		result="failed";
	}
	
	
	return result;
	
	
}
}
