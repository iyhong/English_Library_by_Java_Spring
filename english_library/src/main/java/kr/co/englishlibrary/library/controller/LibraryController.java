package kr.co.englishlibrary.library.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.englishlibrary.library.service.Library;

@Controller
public class LibraryController {
	@Autowired
	private LibraryService service;
	
	private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

	@RequestMapping(value="/libraryAdd", method=RequestMethod.GET)
	public String libraryAdd(Model model){
		logger.debug("libraryAdd() GET 호출");
		model.addAttribute("localList",service.getLocal());
		//logger.debug("localList:"+service.getLocal());
		return "/jsp/addlibrary";
	}
	
	@RequestMapping(value="/libraryAdd", method=RequestMethod.POST)
	public String libraryAdd(Library library){
		logger.debug("libraryAdd() POST 호출");
		logger.debug("libraryCommand:"+library);
		int rowCount = service.addLibrary(library);
		if(rowCount >0){
			logger.debug("등록성공");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String libraryLogin(){
		logger.debug("libraryLogin GET 호출");
		return "/jsp/login";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String libraryLogin(Library library,HttpSession session){
		logger.debug("libraryLogin POST 호출");
		logger.debug("library:"+library);
		Library result = service.loginCheck(library);
		if(result != null){
			logger.debug("=========로그인 성공=========");
			session.setAttribute("LIBRARYID", result.getLibraryId());
			logger.debug("session:"+session);
			return "redirect:/main";
		}else{
			logger.debug("로그인 실패");

			return "redirect:/";
		}
	}
	@RequestMapping(value="/logout")
	public String libraryLogout(HttpSession session){
		logger.debug("libraryLogout 호출");
		session.invalidate();
		logger.debug("========= 로그아웃!! 세션 종료 =========");
		return "redirect:/";
	}
}
