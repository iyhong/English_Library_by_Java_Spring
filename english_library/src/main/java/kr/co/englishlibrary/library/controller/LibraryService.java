package kr.co.englishlibrary.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.englishlibrary.etc.services.Daos;
import kr.co.englishlibrary.etc.services.Local;
import kr.co.englishlibrary.library.service.Library;

@Service
public class LibraryService {
	@Autowired
	private LibraryDao libraryDao;
	@Autowired
	private Daos dao;
	
	private static final Logger logger = LoggerFactory.getLogger(LibraryService.class);
	
	public List<Local> getLocal(){
		logger.debug("getLocal 호출");
		return dao.selectAllLocal();
	}
	
	public int addLibrary(Library library){
		logger.debug("addLibrary 호출");
		return libraryDao.insertLibrary(library);
	}
	
	public Library loginCheck(Library library){
		logger.debug("loginCheck 호출");
		return libraryDao.loginCheck(library);
	}
}
