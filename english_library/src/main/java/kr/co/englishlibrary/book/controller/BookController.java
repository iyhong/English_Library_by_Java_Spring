package kr.co.englishlibrary.book.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.englishlibrary.HomeController;
import kr.co.englishlibrary.book.service.BookCommand;

@Controller
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value="/bookAdd", method=RequestMethod.GET)
	public String bookAdd(){
		logger.debug("bookAdd GET 메서드 호출");
		
		return "/library/addbook";
	}
	@RequestMapping(value="/bookAdd", method=RequestMethod.POST)
	public String bookAdd(BookCommand bookCommand){
		logger.debug("bookAdd POST 메서드 호출");
		
		return "redirect:/bookAdd";
	}
}
