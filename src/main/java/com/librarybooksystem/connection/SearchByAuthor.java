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
	String query="SELECT * FROM library_books WHERE author LIKE '%"+name+"%'";
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
		String eBookLink=result.getString("e_book_link");
		String videoLink=result.getString("video_link");
		String imageLink=result.getString("image_link");
		
		book.setId(bookId);
		book.setName(bookName);
		book.setAuthor(author);
	    book.setDepartment(dept);
		book.setStatus(statusBook);
		book.setEBookLink(eBookLink);
		book.setVideoLink(videoLink);
		book.setImageLink(imageLink);
		
	
	
		
		booksdetails.add(book);
		
		
	}
	return booksdetails;
}


public static  List<Book> bookName( String name) throws Exception
{
	List<Book> booksdetails=  new ArrayList<Book>();
	Connection connection=ConnectionUtilDao.sqlConnection();
	Statement statement=null;
	ResultSet result=null;
	//System.out.println(name);
	String query="SELECT * FROM library_books WHERE NAME LIKE '%"+name+"%'";
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
		String eBookLink=result.getString("e_book_link");
		String videoLink=result.getString("video_link");
		String imageLink=result.getString("image_link");
		
		book.setId(bookId);
		book.setName(bookName);
		book.setAuthor(author);
	    book.setDepartment(dept);
		book.setStatus(statusBook);
		book.setEBookLink(eBookLink);
		book.setVideoLink(videoLink);
		book.setImageLink(imageLink);
		
	
	
		
		booksdetails.add(book);
		
		
	}
	return booksdetails;
}
}