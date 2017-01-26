package kr.co.englishlibrary.rental.controller;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.englishlibrary.rental.service.Rental;
import kr.co.englishlibrary.rental.service.ReturnCommand;

@Repository
public class RentalDao {
	@Autowired
	private SqlSessionTemplate sql;
	private static final Logger logger = LoggerFactory.getLogger(RentalDao.class);

	//대여생성
	public int insertRental(Rental rental){
		logger.debug("insertRental 메서드 호출");
		return sql.insert("rental.inesrtRental", rental);
	}
	//대여테이블에서 autoincrement 값중 최대값 가져오기
	public int selectMaxAutoNum(){
		logger.debug("selectMaxAutoNum 메서드 호출");
		return sql.selectOne("rental.selectMaxAutoNum");
	}
	//하나의 대여정보 가져오기
	public ReturnCommand selectOneRental(String bookCode){
		logger.debug("selectOneRental 메서드 호출");
		return sql.selectOne("rental.selectOneRental", bookCode);
	}
	//반납시 대여정보 수정해주기
	public int updateRentalState(ReturnCommand returnCommand){
		logger.debug("updateRentalState 메서드 호출");
		return sql.update("rental.updateRentalState", returnCommand);
	}
}
