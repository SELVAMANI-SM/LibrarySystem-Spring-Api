package com.librarybooksystem.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.librarybooksystem.model.Book;


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
			
		}
		else
		{
			result=0;
		}
		
		
		return result;
		
		
	}


public static int findBooksId(int id) throws Exception {
	
	Connection connection;
	PreparedStatement statement;
	connection=ConnectionUtilDao.sqlConnection();
	ResultSet rs = null;
	String name="not return";
	
	
	String query = "select book_id from book_taken_deatils WHERE user_id=? AND book_status=?";
	statement = connection.prepareStatement(query);
	statement.setInt(1,id);
	statement.setString(2,name);
	
	rs = statement.executeQuery();
	
	int id1=0;
	
	while(rs.next())
	{
	
	id1=rs.getInt("book_id");
	}
    
	return id1;
	
	
}
public static int findBooksIds(int id) throws Exception {
	
	Connection connection;
	PreparedStatement statement;
	connection=ConnectionUtilDao.sqlConnection();
	ResultSet rs = null;
	String name="not return";
	
	
	String query = "select count(book_id) from book_taken_deatils WHERE user_id=? AND book_status=?";
	statement = connection.prepareStatement(query);
	statement.setInt(1,id);
	statement.setString(2,name);
	
	rs = statement.executeQuery();
	
	int id1=0;
	
	while(rs.next())
	{
	
	id1=rs.getInt("count(book_id)");
	}
    
	return id1;
	
	
}
public static List<Book> member(int id) throws Exception
{
	{
		List<Book> booksdetails=  new ArrayList<Book>();
		Connection connection=ConnectionUtilDao.sqlConnection();
		Statement statement=null;
		ResultSet result=null;
		String name ="not return";
		
		String query="SELECT library_books.id,library_books.Name,library_books.department,library_books.author,library_books.STATUS,library_books.image_link FROM library_books INNER JOIN book_taken_deatils ON library_books.id = book_taken_deatils.book_id WHERE book_taken_deatils.user_id='"+id+"' AND  book_taken_deatils.book_status='"+name+"'";
		statement=connection.createStatement();
		result=statement.executeQuery(query);
		Book book=null;
		while(result.next())
		{
			book=new Book();
			Integer bookId=result.getInt("library_books.id");
			String bookName=result.getString("library_books.Name");
			String dept=result.getString("library_books.department");
			String author=result.getString("library_books.author");
			String statusBook=result.getString("library_books.STATUS");
			
			String imageLink=result.getString("library_books.image_link");
			
			book.setId(bookId);
			book.setName(bookName);
			book.setAuthor(author);
		    book.setDepartment(dept);
			book.setStatus(statusBook);
			
			book.setImageLink(imageLink);
			
		
		
			
			booksdetails.add(book);
			
			
		}
		return booksdetails;
	}
}
}



   
