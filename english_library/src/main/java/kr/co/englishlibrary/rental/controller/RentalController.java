package kr.co.englishlibrary.rental.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.englishlibrary.rental.service.Rental;

@Controller
public class RentalController {
	@Autowired
	private RentalService service;
	
	private static final Logger logger = LoggerFactory.getLogger(RentalController.class);
	//대여 폼
	@RequestMapping(value="/bookRent",method=RequestMethod.GET)
	public String bookRent(){
		logger.debug("bookRent GET 메서드 호출");
		return "/jsp/rental";
	}
	//대여
	@RequestMapping(value="/bookRent",method=RequestMethod.POST)
	public String bookRent(Rental rental,Model model,HttpSession session){
		logger.debug("bookRent GET 메서드 호출");
		logger.debug("rental:"+rental);
		String libraryId = (String) session.getAttribute("LIBRARYID");
		int result = service.addRental(rental,libraryId);
		if(result == -1){
			model.addAttribute("message","해당도서는 대출불가합니다.");
			return "/jsp/fail";
		}else if (result >0){
			logger.debug("대여 성공");
			return "redirect:/bookRent";
		}else{
			logger.debug("대여 실패");
			model.addAttribute("message", "도서대여에 실패하였습니다.");
			return "/jsp/fail";
		}
	}
	//반납 폼
	@RequestMapping(value="/bookReturn",method=RequestMethod.GET)
	public String bookReturn(){
		logger.debug("bookRent GET 메서드 호출");
		return "/jsp/return";
	}
	//반납
	@RequestMapping(value="/bookReturn",method=RequestMethod.POST)
	public String bookReturn(Rental rental){
		logger.debug("bookRent GET 메서드 호출");
		
		return "redirect:/bookReturn";
	}
}
