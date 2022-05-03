package com.librarybooksystem.controller;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarybooksystem.dao.NewsPaperRepository;
import com.librarybooksystem.model.NewsPaper;


@RestController
public class NewsPaperController {
	@Autowired
	NewsPaperRepository newsPaperRepository;
	@GetMapping("news/add")
	public String add(@RequestParam("indianExpress") String indianExpress,@RequestParam("hindu") String hindu,@RequestParam("dinamalar") String dinamalar )
	{
		LocalDate date=LocalDate.now();
		String result = null;
		NewsPaper news=new NewsPaper();
		news.setDate(date);
		news.setIndianExpress(indianExpress);
		news.setHindu(hindu);
		news.setDinamalar(dinamalar);
		try {
			newsPaperRepository.save(news);
			result="success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result="failed";
		}
		return result;
		
	}
	@GetMapping("news/indianExpress")
	public List<NewsPaper> indianExpress(){
		List<NewsPaper> news=null;
		LocalDate date=LocalDate.now();
		try {
			news=newsPaperRepository.findByDate(date);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return news;
		
	}
	@GetMapping("news/hindu")
	public List<NewsPaper> hindu(){
		List<NewsPaper> news=null;
		LocalDate date=LocalDate.now();
		try {
			news=newsPaperRepository.findByDate(date);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return news;
		
	}
	@GetMapping("news/dinamalar")
	public List<NewsPaper> dinamalar(){
		List<NewsPaper> news=null;
		
		LocalDate date=LocalDate.now();
		try {
			news=newsPaperRepository.findByDate(date);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return news;
		
	}
	@GetMapping("news/dinamalarByDate/{date}")
	public  List<NewsPaper> dinamalarByDate(@PathVariable("date") String date){
		 List<NewsPaper> news=null;
	//	Date sqlDate =Date.valueOf(date); 
		LocalDate localdate=LocalDate.parse(date); // string to LocalDate
		
		//LocalDate date=LocalDate.now();
		try {
			news=newsPaperRepository.findByDate(localdate);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return news;
		
	}
	@GetMapping("news/hinduByDate/{date}")
	public  List<NewsPaper> hinduByDate(@PathVariable("date") String date){//09-08-2022
		 List<NewsPaper> news=null;
		LocalDate localdate=LocalDate.parse(date); 
		//LocalDate date=LocalDate.now();
		try {
			news=newsPaperRepository.findByDate(localdate);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return news;
		
	}
	@GetMapping("news/IndianExpressByDate/{date}")
	public  List<NewsPaper> IndianExpressByDate(@PathVariable("date") String date){
		 List<NewsPaper> news=null;
		 
		LocalDate localdate=LocalDate.parse(date); 
		try {
			news=newsPaperRepository.findByDate(localdate);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return news;
		
	}
	@GetMapping("news/deleteNews/{date}/{toDate}")
	public String deleteNews(@PathVariable("date") String date,@PathVariable("toDate") String toDate)
	{
		LocalDate localdate=LocalDate.parse(date);
		LocalDate localdate1=LocalDate.parse(toDate);
		String result=null;
		try {
			newsPaperRepository.deleteByDateNews(localdate,localdate1);
			result="success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result="failed";
		}
		return result;
		
	}
}
