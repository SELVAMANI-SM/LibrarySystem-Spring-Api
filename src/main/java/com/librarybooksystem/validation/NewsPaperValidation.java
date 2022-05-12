package com.librarybooksystem.validation;

import com.librarybooksystem.exception.ValidationException;
import com.librarybooksystem.model.NewsPaper;

public class NewsPaperValidation {
	public static void checkNewsPaper(NewsPaper news) throws ValidationException
	{
		checkDinamalar(news.getDinamalar());
		checkHindu(news.getHindu());
		checkIndian(news.getIndianExpress());
		
		
	}

	public static void checkIndian(String indianExpress) throws ValidationException {
		
		if(indianExpress==null||indianExpress.trim()=="")
		{
			throw new ValidationException("Invalid IndianExpress");
		}
	}

	public static void checkHindu(String hindu) throws ValidationException {
		if(hindu==null||hindu.trim()=="")
		{
			throw new ValidationException("Invalid Hindu");
		}
		
		
	}

	public static void checkDinamalar(String dinamalar) throws ValidationException {
		
		if(dinamalar==null||dinamalar.trim()=="")
		{
			throw new ValidationException("Invalid Dinamalar");
		}
	}

}
