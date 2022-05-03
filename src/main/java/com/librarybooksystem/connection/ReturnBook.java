package com.librarybooksystem.connection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;







public class ReturnBook {
public static int returnBook(int id,int userId) throws Exception {
		
		Connection connection;
		PreparedStatement statement;
		connection=ConnectionUtilDao.sqlConnection();
		String name="Available";
		String name1="Unavailable";
		
		String query = "UPDATE library_books SET STATUS=? WHERE id=? and STATUS=? ";
		statement = connection.prepareStatement(query);
		statement.setString(1,name);
		statement.setInt(2,id);
		statement.setString(3,name1);
		int result;
		int row=statement.executeUpdate();
		if(row==1)
		{
			result=1;
			returnBooks(id,userId);
			
		}
		else
		{
			result=0;
		}
		
		
		return result;
		
	}
public static void returnBooks(int id,int userId) throws Exception {
	Connection connection;
	connection = ConnectionUtilDao.sqlConnection();
	String name2 = "return";
	LocalDate date = LocalDate.now();
	String query1 = "update book_taken_deatils set book_status='" + name2 + "' , book_return_date ='" + date
			+ "' where user_id='" + userId + "'and book_id='"+ id+"' " ;

	Statement stm = connection.createStatement();
	stm.executeUpdate(query1);

}
}
