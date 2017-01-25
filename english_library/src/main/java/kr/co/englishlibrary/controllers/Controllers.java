package kr.co.englishlibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controllers {
	@RequestMapping(value="/main")
	public String mainView(){
		return "/jsp/main";
	}
}
