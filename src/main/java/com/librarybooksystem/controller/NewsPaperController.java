package com.librarybooksystem.controller;
import java.time.LocalDate;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarybooksystem.dao.NewsPaperRepository;
import com.librarybooksystem.dto.Message;
import com.librarybooksystem.exception.ValidationException;
import com.librarybooksystem.model.NewsPaper;
import com.librarybooksystem.validation.NewsPaperValidation;


@RestController
public class NewsPaperController {
	@Autowired
	NewsPaperRepository newsPaperRepository;
	@GetMapping("news/add")
	public ResponseEntity<?> add(@RequestParam("indianExpress") String indianExpress,@RequestParam("hindu") String hindu,@RequestParam("dinamalar") String dinamalar ) throws ValidationException
	{
		LocalDate date=LocalDate.now();
		NewsPaper news=new NewsPaper();
		news.setDate(date);
		news.setIndianExpress(indianExpress);
		news.setHindu(hindu);
		news.setDinamalar(dinamalar);
		
		try {
			NewsPaperValidation.checkNewsPaper(news);
			newsPaperRepository.save(news);
			Message message = new Message("Success");
			return new ResponseEntity<>(message,HttpStatus.OK);
		}
		catch(ValidationException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		     }
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("news/indianExpress")
	public ResponseEntity<?> indianExpress(){
		List<NewsPaper> news=null;
		LocalDate date=LocalDate.now();
		try {
			news=newsPaperRepository.findByDate(date);
			return new ResponseEntity<>(news,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		
	}
	@GetMapping("news/hindu")
	public ResponseEntity<?> hindu(){
		List<NewsPaper> news=null;
		LocalDate date=LocalDate.now();
		try {
			news=newsPaperRepository.findByDate(date);
			return new ResponseEntity<>(news,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();

			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
	}
	@GetMapping("news/dinamalar")
	public ResponseEntity<?> dinamalar(){
		List<NewsPaper> news=null;
		
		LocalDate date=LocalDate.now();
		try {
			news=newsPaperRepository.findByDate(date);
			return new ResponseEntity<>(news,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();

			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		
	}
	@GetMapping("news/dinamalarByDate/{date}")
	public  ResponseEntity<?> dinamalarByDate(@PathVariable("date") String date){
		 List<NewsPaper> news=null;
	
		LocalDate localdate=LocalDate.parse(date);
	
		try {
			news=newsPaperRepository.findByDate(localdate);
			return new ResponseEntity<>(news,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();

			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
	}
	@GetMapping("news/hinduByDate/{date}")
	public  ResponseEntity<?> hinduByDate(@PathVariable("date") String date){//09-08-2022
		 List<NewsPaper> news=null;
		LocalDate localdate=LocalDate.parse(date); 
		
		try {
			news=newsPaperRepository.findByDate(localdate);
			return new ResponseEntity<>(news,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();

			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("news/IndianExpressByDate/{date}")
	public  ResponseEntity<?> IndianExpressByDate(@PathVariable("date") String date){
		 List<NewsPaper> news=null;
		 
		LocalDate localdate=LocalDate.parse(date); 
		try {
			news=newsPaperRepository.findByDate(localdate);
			return new ResponseEntity<>(news,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();

			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	@GetMapping("news/deleteNews")
	public ResponseEntity<?> deleteNews(@RequestParam("date") String date,@RequestParam("toDate") String toDate)
	{
		LocalDate localdate=LocalDate.parse(date);
		LocalDate localdate1=LocalDate.parse(toDate);
		try {
			newsPaperRepository.deleteByDateNews(localdate,localdate1);

			Message message = new Message("delete");
			return new ResponseEntity<>(message,HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Message message = new Message("server error");
			return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
}
