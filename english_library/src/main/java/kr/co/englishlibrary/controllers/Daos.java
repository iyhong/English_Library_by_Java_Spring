package kr.co.englishlibrary.controllers;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.englishlibrary.HomeController;
import kr.co.englishlibrary.services.Genre;

@Repository
public class Daos {
	@Autowired
	private SqlSessionTemplate sql;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public List<Genre> selectAllGenre(){
		logger.debug("selectAllGenre() 메서드 호출");
		return sql.selectList("mapper.selectAllGenre");
	}
}
