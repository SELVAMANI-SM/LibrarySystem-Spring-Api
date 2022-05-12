package com.librarybooksystem.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarybooksystem.connection.ReturnBook;
import com.librarybooksystem.connection.SearchByAuthor;
import com.librarybooksystem.connection.TakeBook;
import com.librarybooksystem.dao.BookRepository;
import com.librarybooksystem.dao.TakenBookDetailsRepository;
import com.librarybooksystem.dao.UserRepository;
import com.librarybooksystem.dto.Message;
import com.librarybooksystem.exception.ValidationException;
import com.librarybooksystem.model.Book;
import com.librarybooksystem.model.TakenBookDetails;
import com.librarybooksystem.validation.BookValidation;

@RestController
public class BookController {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	TakenBookDetailsRepository takenBookDetailsRepository;
	@Autowired
	UserRepository userRepository;

	@GetMapping("book/addBook")
	public ResponseEntity<?> addBook(@RequestParam("name") String name, @RequestParam("department") String department,
			@RequestParam("author") String author, @RequestParam("status") String status,
			@RequestParam("eBookLink") String eBookLink, @RequestParam("videoLink") String videoLink,
			@RequestParam("imageLink") String imageLink) {

		Book book = new Book();

		book.setName(name);
		book.setDepartment(department);
		book.setAuthor(author);
		book.setStatus(status);
		book.setEBookLink(eBookLink);
		book.setVideoLink(videoLink);
		book.setImageLink(imageLink);

		try {
			BookValidation.bookCheck(book);
			bookRepository.save(book);
			Message message = new Message("success");
			return new ResponseEntity<>(message, HttpStatus.OK);

		} catch (ValidationException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

		catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("book/deleteById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {

		try {
			bookRepository.deleteByBookId(id);
			Message message = new Message("Success");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("book/deleteByAuthor/{author}")
	public ResponseEntity<?> deleteByAuthor(@PathVariable("author") String author) {

		try {
			bookRepository.deleteByAuthor(author);
			Message message = new Message("Success");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("book/deleteByDepartment/{department}")
	public ResponseEntity<?> deleteByDepartment(@PathVariable("department") String department) {

		try {
			bookRepository.deleteByDepartment(department);
			Message message = new Message("Success");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("book/deleteByName/{name}")
	public ResponseEntity<?> deleteByName(@PathVariable("name") String name) {

		try {
			bookRepository.deleteByName(name);
			Message message = new Message("Success");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("book/display")
	public ResponseEntity<?> bookDisplay() {
		List<Book> bookList = null;
		try {
			bookList = bookRepository.findAll();
			return new ResponseEntity<>(bookList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("book/idBooksReturn/{id}")
	public List<Book> idBooksReturn(@PathVariable("id") Integer id) {
		List<Book> bookList = null;
		try {
			// bookList = bookRepository.idBooksReturn(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookList;

	}

	@GetMapping("book/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		Optional<Book> bookList = null;
		try {
			bookList = bookRepository.findById(id);
			return new ResponseEntity<>(bookList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("book/findByDepartment/{department}")
	public ResponseEntity<?> findByDepartment(@PathVariable("department") String department) {
		List<Book> bookList = null;
		try {
			bookList = bookRepository.findByDepartment(department);
			return new ResponseEntity<>(bookList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("book/findByName/{name}")
	public ResponseEntity<?> findByName(@PathVariable("name") String name) {
		List<Book> bookList = null;
		try {
			bookList = SearchByAuthor.bookName(name);
			return new ResponseEntity<>(bookList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("book/findByAuthor/{author}")
	public ResponseEntity<?> findByAuthor(@PathVariable("author") String author) {
		List<Book> bookList = null;
		try {
			bookList = SearchByAuthor.bookAuthor(author);

			return new ResponseEntity<>(bookList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("book/updateById")
	public ResponseEntity<?> updateById(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("author") String author,
			@RequestParam("status") String status, @RequestParam("eBookLink") String eBookLink,
			@RequestParam("videoLink") String videoLink, @RequestParam("imageLink") String imageLink) {

		Book book = new Book();
		book.setId(id);
		book.setName(name);
		book.setDepartment(department);
		book.setAuthor(author);
		book.setStatus(status);
		book.setEBookLink(eBookLink);
		book.setVideoLink(videoLink);
		book.setImageLink(imageLink);
		try {
			BookValidation.bookUpdateCheck(book);
			bookRepository.save(book);
			Message message = new Message("Success");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (ValidationException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("book/takeBook")
	public String takeBookById(@RequestParam("id") Integer id, @RequestParam("userId") Integer userId,
			@RequestParam("type") String type) throws Exception {

		String result = null;
		int count = 0;

		count = TakeBook.takeBook(id);

		if (count == 1) {
			TakenBookDetails taken = new TakenBookDetails();

			LocalDate date = LocalDate.now();
			String status = "not return";

			taken.setUserId(userId);
			taken.setBookId(id);
			taken.setUserType(type);
			taken.setTakenDate(date);
			// taken.setReturnDate(date);
			taken.setStatus(status);

			takenBookDetailsRepository.save(taken);
			result = "success";
		} else {
			result = "Book Not Available";
		}
		return result;

	}

	@GetMapping("book/findId/{id}")
	public ResponseEntity<?> findId(@PathVariable("id") Integer id) throws Exception {
		int count = 0;
		try {
			count = TakeBook.findBooksId(id);
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("book/findIds/{id}")
	public ResponseEntity<?> findIds(@PathVariable("id") Integer id ) throws Exception
	{ int count=0;
	 try {
		count=TakeBook.findBooksIds(id);
		return new ResponseEntity<Integer>(count,HttpStatus.OK);
	   }catch(Exception e)
	  {
		e.printStackTrace();
		Message message = new Message("server error");
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

	@GetMapping("book/returnBook")
	public String returnBookById(@RequestParam("id") Integer id, @RequestParam("userId") Integer userId)
			throws Exception {

		String result = null;
		int count = 0;
		int count1 = 0;
		int fine = 0;

		int fine1 = 0;
		int fineAdd = 0;
		count1 = ReturnBook.checkFineBooks(id, userId);
		System.out.println(count1);
		if (count1 <= 10) {
			count = ReturnBook.returnBook(id, userId);
			if (count == 1) {
				result = "success";
			} else {
				result = "check book number";
			}
		} else {
			fine = count1 + 10 - 11;
			fine1 = ReturnBook.checkFineBooksss(userId);
			fineAdd = fine + fine1;
			userRepository.updateFine(userId, fineAdd);
			count = ReturnBook.returnBook(id, userId);
			if (count == 1) {
				result = "success";
			} else {
				result = "check book number";
			}
		}

		return result;

	}

	@GetMapping("book/eLink/{id}")
	public ResponseEntity<?> eBookLink(@PathVariable("id") Integer id) {
		Optional<Book> bookList = null;

		try {
			bookList = bookRepository.findById(id);
			return new ResponseEntity<>(bookList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("book/videoLink/{id}")
	public ResponseEntity<?> videoLink(@PathVariable("id") Integer id) {
		Optional<Book> bookList = null;
		try {
			bookList = bookRepository.findById(id);
			return new ResponseEntity<>(bookList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("book/userProcess/{id}")
	public ResponseEntity<?> userProcess(@PathVariable("id") Integer id) {
		List<TakenBookDetails> details = null;
		try {
			details = takenBookDetailsRepository.findByUserId(id);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("book/allUser")
	public ResponseEntity<?> allUser() {
		List<TakenBookDetails> details = null;
		String id = "user";
		try {
			details = takenBookDetailsRepository.findByUserType(id);

			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("book/allMember")
	public ResponseEntity<?> allMember() {
		List<TakenBookDetails> details = null;
		String id = "member";
		try {
			details = takenBookDetailsRepository.findByUserType(id);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("book/memberReturn/{id}")
	public static ResponseEntity<?> memberReturn(@PathVariable("id") int id) throws Exception {
		List<Book> book = null;
		try {
			book = TakeBook.member(id);

			return new ResponseEntity<>(book, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
