package kr.co.englishlibrary.rental.controller;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.englishlibrary.rental.service.Rental;

@Repository
public class RentalDao {
	@Autowired
	private SqlSessionTemplate sql;
	private static final Logger logger = LoggerFactory.getLogger(RentalDao.class);

	
	public int insertRental(Rental rental){
		logger.debug("insertRental 메서드 호출");
		return sql.insert("rental.inesrtRental", rental);
	}
	
	public int selectMaxAutoNum(){
		logger.debug("selectMaxAutoNum 메서드 호출");
		return sql.selectOne("rental.selectMaxAutoNum");
	}
}
