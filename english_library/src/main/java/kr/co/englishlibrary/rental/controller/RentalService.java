package kr.co.englishlibrary.rental.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.englishlibrary.book.controller.BookDao;
import kr.co.englishlibrary.rental.service.Rental;

@Service
@Transactional
public class RentalService {
	@Autowired
	private RentalDao rentalDao;
	@Autowired
	private BookDao bookDao;
	private static final Logger logger = LoggerFactory.getLogger(RentalService.class);
	
	//대여추가
	public int addRental(Rental rental,String libraryId){
		logger.debug("addRental 메서드 호출");
		int rowCount = 0;
		//도서상태 조회해서 대출불가상태이면 -1값을 리턴
		int stateNum = bookDao.selectBookState(rental.getBookCode());
		
		logger.debug("stateNum:"+stateNum);
		//도서상태가 1이아니면(즉 대출가능상태가 아닌경우) -1값을 리턴
		if(stateNum!=1){
			return -1;
		}
		//rentalCode 생성하기(libraryId + 일련번호)
		String rentalCode = libraryId;
		Integer maxNum = rentalDao.selectMaxAutoNum();
		String maxNumStr = String.format("%05d", maxNum);
		rentalCode += maxNumStr;
		logger.debug("rentalCode:"+rentalCode);
		rental.setRentalCode(rentalCode);
		logger.debug("rental:"+rental);
		//rental rentalEnd 값을 입력하지 않았으면 null로 넣어줌
		if(rental.getRentalEnd()==""){
			rental.setRentalEnd(null);
		}
		logger.debug("rentalEnd:"+rental.getRentalEnd());
		rowCount += rentalDao.insertRental(rental);
		
		//도서코드와 도서상태를 맵에 담아 도서상태를 수정해준다.
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("bookCode", rental.getBookCode());
		map.put("bookState", 2);
		rowCount += bookDao.updateBookStateDisposal(map);
		return rowCount;
	}
	
}
