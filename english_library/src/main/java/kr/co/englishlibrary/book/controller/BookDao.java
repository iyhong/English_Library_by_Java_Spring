package kr.co.englishlibrary.book.controller;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.englishlibrary.book.service.Book;

@Repository
public class BookDao {
	@Autowired
	private SqlSessionTemplate sql;
	
	private static final Logger logger = LoggerFactory.getLogger(BookDao.class);
	//도서등록
	public int insertBook(Book book){
		logger.debug("insertBook() 메서드 호출");
		return sql.insert("book.insertBook", book);
	}
	//도서코드로 도서정보 검색
	public Book selectOneBookByCode(String bookCode){
		logger.debug("selectOneBookByCode() 메서드 호출");
		logger.debug("값:"+sql.selectOne("book.selectOneBookByCode", bookCode));
		return sql.selectOne("book.selectOneBookByCode", bookCode);
	}
}
