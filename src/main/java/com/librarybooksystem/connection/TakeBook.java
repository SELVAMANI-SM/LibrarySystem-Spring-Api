package com.librarybooksystem.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;

public class TakeBook {
public static int takeBook(int id) throws Exception {
		
		Connection connection;
		PreparedStatement statement;
		connection=ConnectionUtilDao.sqlConnection();
		String name="Unavailable";
		String name1="Available";
		
		String query = "UPDATE library_books SET STATUS=? WHERE id=? AND STATUS=?";
		statement = connection.prepareStatement(query);
		statement.setString(1,name);
		statement.setInt(2,id);
		statement.setString(3,name1);
	     int  result=0;
		int row =statement.executeUpdate();
		if(row==1)
		{
			result=1;
			//addId(user,type,id);
		}
		else
		{
			result=0;
		}
		
		
		return result;
		
		
	}

}
   
