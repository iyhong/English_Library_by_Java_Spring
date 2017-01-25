package kr.co.englishlibrary.services;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Daos {
	@Autowired
	private SqlSessionTemplate sql;
	private static final Logger logger = LoggerFactory.getLogger(Daos.class);

	public List<Genre> selectAllGenre(){
		logger.debug("selectAllGenre() 메서드 호출");
		return sql.selectList("mapper.selectAllGenre");
	}
	
	public List<Local> selectAllLocal(){
		logger.debug("selectAllLocal() 메서드 호출");
		
		return sql.selectList("mapper.selectAllLocal");
	}
}
