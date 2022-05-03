package com.librarybooksystem.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarybooksystem.connection.ReturnBook;
import com.librarybooksystem.connection.SearchByAuthor;
import com.librarybooksystem.connection.TakeBook;
import com.librarybooksystem.dao.BookRepository;
import com.librarybooksystem.dao.TakenBookDetailsRepository;
import com.librarybooksystem.model.Book;
import com.librarybooksystem.model.TakenBookDetails;


@RestController
public class BookController {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	TakenBookDetailsRepository takenBookDetailsRepository;
	
	@GetMapping("book/addBook")
	public String addBook (
			@RequestParam("name") String name,
			@RequestParam("department") String department,@RequestParam("author") String author,@RequestParam("status") String status,@RequestParam("eBookLink") String eBookLink,@RequestParam("videoLink") String videoLink,@RequestParam("imageLink") String imageLink) {
		
		
		
		Book book = new Book();
		
		book.setName(name);
		book.setDepartment(department);
		book.setAuthor(author);
		book.setStatus(status);
		book.setEBookLink(eBookLink);
		book.setVideoLink(videoLink);
		book.setImageLink(imageLink);
		String result=null;
		try {
	      bookRepository.save(book);
	      result="success";
	     
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		  e.getMessage();
		  result="failed";
		 
		}
		finally
		{
			System.out.println("Finish");
			
		}
		return result;
		
	}
	@GetMapping("book/deleteById/{id}")
	public String deleteById(@PathVariable("id") Integer id)
	{
		String result=null;
		try{
			bookRepository.deleteByBookId(id);
			result="success";
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
			result="failed";
		}
		finally
		{
			System.out.println("finish");
		}
		return result;
	}
	@GetMapping("book/deleteByAuthor/{author}")
	public String deleteByAuthor(@PathVariable("author") String author)
	{
		String result=null;
		try{
			bookRepository.deleteByAuthor(author);
			result="success";
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
			result="failed";
		}
		finally
		{
			System.out.println("finish");
		}
		return result;
	}
	@GetMapping("book/deleteByDepartment/{department}")
	public String deleteByDepartment(@PathVariable("department")String department)
	{
		String result=null;
		try {
			bookRepository.deleteByDepartment(department);
			result="success";
		}
		catch(Exception e){
			e.printStackTrace();
			result="failed";
			}
		return result;
		
	}
	@GetMapping("book/deleteByName/{name}")
	public String deleteByName(@PathVariable("name")String name)
	{
		String result=null;
		try {
			bookRepository.deleteByName(name);
			result="success";
		}
		catch(Exception e){
			e.printStackTrace();
			result="failed";
			}
		return result;
		
	}
	@GetMapping("book/display")
	public List<Book> bookDisplay()
	{
		List<Book> bookList =null;
		try {
			bookList= bookRepository.findAll();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//bookList="failed";
		}
		return bookList;

	
	}
	@GetMapping("book/findById/{id}")
	public Optional<Book> findById(@PathVariable("id") Integer id)
	{
		Optional<Book> bookList =null;
		try{
			bookList= bookRepository.findById(id);
			
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
			//result="failed";
		}
		
		return bookList;
	}
	@GetMapping("book/findByDepartment/{department}")
	public List<Book> findByDepartment(@PathVariable("department") String department)
	{
		List<Book> bookList =null;
		try{
			bookList= bookRepository.findByDepartment(department);
			
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
			//result="failed";
		}
		
		return bookList;
	}
	@GetMapping("book/findByName/{name}")
	public List<Book> findByName(@PathVariable("name") String name)
	{
		List<Book> bookList =null;
		try{
			bookList= bookRepository.findByNameContaining(name);
			
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
			//result="failed";
		}
		
		return bookList;
	}
	@GetMapping("book/findByAuthor/{author}")
	public List<Book> findByAuthor(@PathVariable("author") String author)
	{
		List<Book> bookList =null;
		try{
			bookList=SearchByAuthor.bookAuthor(author);
			
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
			//result="failed";
		}
		
		return bookList;
	}
	@GetMapping("book/updateById")
	public String updateById(@RequestParam("id") Integer id,@RequestParam("name") String name,
			@RequestParam("department") String department,@RequestParam("author") String author,@RequestParam("status") String status,@RequestParam("eBookLink") String eBookLink,@RequestParam("videoLink") String videoLink,@RequestParam("imageLink") String imageLink)
	{

		Book book = new Book();
		book.setId(id);
		book.setName(name);
		book.setDepartment(department);
		book.setAuthor(author);
		book.setStatus(status);
		book.setEBookLink(eBookLink);
		book.setVideoLink(videoLink);
		book.setImageLink(imageLink);
		String result=null;
		try {
			bookRepository.save(book);
			//bookRepository.updateByBookId(id,name,department,author,status,eBookLink,videoLink,imageLink);
			result="success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result="failed";
	    }
		return result;
	
	}
	@GetMapping("book/takeBook")
	public String takeBookById(@RequestParam("id") Integer id,@RequestParam("userId") Integer userId,@RequestParam("type") String type) throws Exception
	{
		
		String result=null;
		int count=0;
		
	
			count=TakeBook.takeBook(id);
			//bookRepository.takeBookById(id,statusDisplay,statusDisplay1);
		if(count==1)
		{
			TakenBookDetails taken = new TakenBookDetails();
			
			
			
          
			LocalDate date=LocalDate.now();
			String status="not return";
			
			
			taken.setUserId(userId);
			taken.setBookId(id);
			taken.setUserType(type);
		    taken.setTakenDate(date);
		  //  taken.setReturnDate(date);
			taken.setStatus(status);
			
			
			
			takenBookDetailsRepository.save(taken);
			result="success";
		}
		else
		{
			result="Book Not Available";
		}
		return result;
		
	}
	@GetMapping("book/returnBook")
	public String returnBookById(@RequestParam("id") Integer id,@RequestParam("userId") Integer userId) throws Exception
	{
		
		String result=null;
		int count=0;
	
			count=ReturnBook.returnBook(id,userId);
			
			if(count==1)
		    {
			result="success";
		}
		else {
			result="check book number";
		}
		return result;
		
		
	}
	@GetMapping("book/eLink/{id}")
	public Optional<Book> eBookLink(@PathVariable("id") Integer id){
		Optional<Book> bookList=null;
		
		try
		{
			bookList=bookRepository.findById(id);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bookList;
		
	}
	@GetMapping("book/videoLink/{id}")
	public Optional<Book> videoLink(@PathVariable("id") Integer id)
	{
		Optional<Book> bookList=null;
		try
		{
			bookList=bookRepository.findById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bookList;
	}
	@GetMapping("book/userProcess/{id}")
	public List<TakenBookDetails> userProcess(@PathVariable("id") Integer id)
	{
		List<TakenBookDetails> details=null;
		try
		{
			details=takenBookDetailsRepository.findByUserId(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return details;
	}
	@GetMapping("book/allUser")
	public List<TakenBookDetails> allUser()
	{
		List<TakenBookDetails> details=null;
		String id="user";
		try
		{
			details=takenBookDetailsRepository.findByUserType(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return details;
	}
	@GetMapping("book/allMember")
	public List<TakenBookDetails> allMember()
	{
		List<TakenBookDetails> details=null;
		String id="member";
		try
		{
			details=takenBookDetailsRepository.findByUserType(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return details;
	}
}

