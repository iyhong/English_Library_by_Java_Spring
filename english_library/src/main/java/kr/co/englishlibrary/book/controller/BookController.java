package kr.co.englishlibrary.book.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.englishlibrary.book.service.BookCommand;
import kr.co.englishlibrary.services.Genre;

@Controller
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	@Autowired
	private BookService service;
	
	//도서등록 폼
	@RequestMapping(value="/bookAdd", method=RequestMethod.GET)
	public String bookAdd(Model model){
		logger.debug("bookAdd GET 메서드 호출");
		List<Genre> list = service.getGenre();
		logger.debug("genre list:"+list.toString());
		model.addAttribute("genreList",list);
		return "/jsp/addbook";
	}
	//도서등록
	@RequestMapping(value="/bookAdd", method=RequestMethod.POST)
	public String bookAdd(BookCommand bookCommand,HttpSession session, Model model){
		logger.debug("bookAdd POST 메서드 호출");
		logger.debug("bookCommand:"+bookCommand);
		String libraryId = (String) session.getAttribute("LIBRARYID");
		logger.debug("libraryId:"+libraryId);
		int result = service.addBook(bookCommand, libraryId);
		if(result > 0){
			return "redirect:/bookAdd";
		}else{
			model.addAttribute("message","도서등록에 실패하였습니다.");
			return "/jsp/fail";
		}
	}
	//폐기등록폼
	@RequestMapping(value="/bookDisposal", method=RequestMethod.GET)
	public String bookDisposal(){
		logger.debug("bookDisposal GET 메서드 호출");
		return "/jsp/disposalbook";
	}
	//폐기등록
	@RequestMapping(value="/bookDisposal", method=RequestMethod.POST)
	public String bookDisposal(String bookCode,Model model){
		logger.debug("bookDisposal POST 메서드 호출");
		logger.debug("bookCode:"+bookCode);
		int result = service.addDisposal(bookCode);
		//없는도서코드를 입력해서 select한 결과가 null일경우 result 는 -1값을 가진다
		if(result == -1){
			model.addAttribute("message","없는 도서코드를 입력하셨습니다.");
			return "/jsp/fail";
		}
		return "redirect:/bookDisposal";
	}
}
