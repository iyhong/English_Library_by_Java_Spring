package kr.co.englishlibrary.book.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.englishlibrary.book.service.Book;
import kr.co.englishlibrary.book.service.BookCommand;
import kr.co.englishlibrary.services.Daos;
import kr.co.englishlibrary.services.Genre;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private Daos dao;
	
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);

	public List<Genre> getGenre(){
		logger.debug("getGenre() 메서드 호출");
		return dao.selectAllGenre();
	}
	public int addBook(BookCommand bookCommand){
		//Book book = new Book();
		
		//bookCommand -> book으로 옯겨야함
		
		//bookDao.insertBook(book);
		return 0;
	}
}
