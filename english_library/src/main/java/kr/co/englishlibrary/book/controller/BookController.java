package kr.co.englishlibrary.book.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.englishlibrary.HomeController;
import kr.co.englishlibrary.book.service.BookCommand;
import kr.co.englishlibrary.services.Genre;

@Controller
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/bookAdd", method=RequestMethod.GET)
	public String bookAdd(Model model){
		logger.debug("bookAdd GET 메서드 호출");
		List<Genre> list = bookService.getGenre();
		logger.debug(list.toString());
		model.addAttribute("genreList",list);
		return "/jsp/addbook";
	}
	@RequestMapping(value="/bookAdd", method=RequestMethod.POST)
	public String bookAdd(BookCommand bookCommand){
		logger.debug("bookAdd POST 메서드 호출");
		
		return "redirect:/bookAdd";
	}
}
