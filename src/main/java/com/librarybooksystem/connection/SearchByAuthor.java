package com.librarybooksystem.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.librarybooksystem.model.Book;

public class SearchByAuthor {
public static  List<Book> bookAuthor( String name) throws Exception
{
	List<Book> booksdetails=  new ArrayList<Book>();
	Connection connection=ConnectionUtilDao.sqlConnection();
	Statement statement=null;
	ResultSet result=null;
	//System.out.println(name);
	String query="SELECT * FROM library_books WHERE Author LIKE '%"+name+"%'";
	statement=connection.createStatement();
	result=statement.executeQuery(query);
	Book book=null;
	while(result.next())
	{
		book=new Book();
		Integer bookId=result.getInt("id");
		String bookName=result.getString("name");
		String dept=result.getString("department");
		String author=result.getString("author");
		String statusBook=result.getString("STATUS");
		
		book.setId(bookId);
		book.setName(bookName);
		book.setAuthor(author);
	    book.setDepartment(dept);
		book.setStatus(statusBook);
	
	
		
		booksdetails.add(book);
		
		
	}
	return booksdetails;
}
}