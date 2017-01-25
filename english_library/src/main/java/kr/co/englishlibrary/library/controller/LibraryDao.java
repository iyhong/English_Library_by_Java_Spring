package kr.co.englishlibrary.library.controller;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.englishlibrary.library.service.Library;

@Repository
public class LibraryDao {
	@Autowired
	private SqlSessionTemplate sql;
	
	private static final Logger logger = LoggerFactory.getLogger(LibraryDao.class);
	
	public int insertLibrary(Library library){
		logger.debug("insertLibrary 호출");
		return sql.insert("library.insertLibrary",library);
	}
	
	public Library loginCheck(Library library){
		logger.debug("loginCheck 호출");
		return sql.selectOne("library.loginCheck", library);
	}


}
