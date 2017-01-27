package kr.co.englishlibrary.etc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.englishlibrary.etc.services.Daos;
import kr.co.englishlibrary.etc.services.Member;
@Controller
public class Controllers {
	@Autowired
	private Daos dao;
	private static final Logger logger = LoggerFactory.getLogger(Controllers.class);

	@RequestMapping(value="/main")
	public String mainView(){
		logger.debug("mainView 메서드 호출");
		return "/jsp/main";
	}
	@RequestMapping(value="/memberAdd", method=RequestMethod.GET)
	public String addMember(Model model){
		logger.debug("addMember GET 메서드 호출");
		model.addAttribute("memberLevel", dao.selectAllLevel());
		return "/jsp/addmember";
	}
	@RequestMapping(value="/memberAdd", method=RequestMethod.POST)
	public String addMember(Member member, Model model){
		logger.debug("addMember POST 메서드 호출");
		int result = dao.insertMember(member);
		if(result >0){
			logger.debug("회원등록 성공");
			return "redirect:/memberAdd";
		}else{
			logger.debug("회원등록 실패");
			model.addAttribute("message", "회원등록에 실패했습니다.");
			return "/jsp/fail";
		}
	}
}
