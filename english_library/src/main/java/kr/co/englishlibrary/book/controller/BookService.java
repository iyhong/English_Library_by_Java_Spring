package kr.co.englishlibrary.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.englishlibrary.book.service.Book;
import kr.co.englishlibrary.book.service.BookCommand;
import kr.co.englishlibrary.etc.services.Daos;
import kr.co.englishlibrary.etc.services.Genre;

@Service
@Transactional
public class BookService {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private Daos dao;
	
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	//도서등록폼에 보여줄 장르 가져오는메서드
	public List<Genre> getGenre(){
		logger.debug("getGenre() 메서드 호출");
		return dao.selectAllGenre();
	}
	//도서등록하는 메서드
	public int addBook(BookCommand bookCommand, String libraryId){
		logger.debug("addBook() 메서드 호출");
		Book book = new Book();
		//bookCommand -> book으로 옮김
		book.setLibraryId(libraryId);
		book.setGenreNo(bookCommand.getGenre());
		book.setBookName(bookCommand.getBookName());
		book.setBookAuthor(bookCommand.getBookAuthor());
		book.setBookPublisher(bookCommand.getBookPublisher());
		
		int rowCount = bookDao.insertBook(book);
		return rowCount;
	}
	//폐기등록하는 메서드
	public int addDisposal(String bookCode){
		logger.debug("addDisposal() 메서드 호출");
		int rowCount = 0;
		//도서코드로 도서정보 가져옴
		Book book = bookDao.selectOneBookByCode(bookCode);
		logger.debug("book:"+book);
		if(book==null){
			return 0;
		}else if(book.getStateNo()==2){
			return -2;
		}else if(book.getStateNo()==3){
			return -3;
		}
		//bookCode와 bookState를 map에 넣어준다.
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("bookCode", bookCode);
		//맵에 넣어줄때 도서상태 3(폐기)로 넣어줌
		map.put("bookState", 3);
		rowCount += bookDao.updateBookState(map);
		//bookCode를 조회해 도서정보를 폐기테이블에 복사한다.
		rowCount += dao.insertDisposal(bookCode);
		return rowCount;
	}
}
