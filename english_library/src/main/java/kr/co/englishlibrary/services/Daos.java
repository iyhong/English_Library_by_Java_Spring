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
	//장르전체 가져오기
	public List<Genre> selectAllGenre(){
		logger.debug("selectAllGenre() 메서드 호출");
		return sql.selectList("mapper.selectAllGenre");
	}
	//지역전체 가져오기
	public List<Local> selectAllLocal(){
		logger.debug("selectAllLocal() 메서드 호출");
		return sql.selectList("mapper.selectAllLocal");
	}
	//폐기등록하기
	public int insertDisposal(Disposal disposal){
		logger.debug("insertDisposal() 메서드 호출");
		return sql.insert("mapper.disposalBook", disposal);
	}
	//회원등급 가져오기
	public List<MemberLevel> selectAllLevel(){
		return sql.selectList("mapper.selectAllLevel");
	}
	//회원등록하기
	public int insertMember(Member member){
		return sql.insert("mapper.insertMember", member);
	}
}
