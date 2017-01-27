package kr.co.englishlibrary.rental.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.englishlibrary.book.controller.BookDao;
import kr.co.englishlibrary.book.service.Book;
import kr.co.englishlibrary.etc.services.Daos;
import kr.co.englishlibrary.rental.service.Rental;
import kr.co.englishlibrary.rental.service.ReturnCommand;

@Service
@Transactional
public class RentalService {
	@Autowired
	private RentalDao rentalDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private Daos dao;

	private static final Logger logger = LoggerFactory.getLogger(RentalService.class);

	// 대여추가
	public int addRental(Rental rental, String libraryId) {
		logger.debug("addRental 메서드 호출");
		int rowCount = 0;
		// 도서코드로 도서정보 가져옴
		Book book = bookDao.selectOneBookByCode(rental.getBookCode());
		// 도서상태 조회
		int stateNum = book.getStateNo();
		logger.debug("stateNum:" + stateNum);
		// 도서상태가 1이아니면(즉 대출가능상태가 아닌경우) -1값을 리턴
		if (stateNum != 1) {
			return -1;
		}
		// memberId 로 조회해서 member를 조회하지못해 null이면
		if (dao.selectOneMember(rental.getMemberId()) == null) {
			return -2;
		}
		// rentalCode 생성하기(libraryId + 일련번호)
		String rentalCode = libraryId;
		Integer maxNum = rentalDao.selectMaxAutoNum();
		String maxNumStr = String.format("%05d", maxNum);
		rentalCode += maxNumStr;
		logger.debug("rentalCode:" + rentalCode);
		rental.setRentalCode(rentalCode);
		logger.debug("rental:" + rental);
		// rental rentalEnd 값을 입력하지 않았으면 null로 넣어줌
		if (rental.getRentalEnd() == "") {
			rental.setRentalEnd(null);
		}
		if (rental.getRentalStart() == "") {
			rental.setRentalStart(new Date().toString());
		}
		logger.debug("rentalEnd:" + rental.getRentalEnd());
		logger.debug("rentalStart:" + rental.getRentalStart());
		rowCount += rentalDao.insertRental(rental);

		logger.debug("bookFirstDay:" + book.getBookFirstDay());
		// 도서 firstday 가 null이면 오늘날짜로 수정
		if (book.getBookFirstDay() == null) {
			bookDao.updateBookFirstDay(rental.getBookCode());
		}
		logger.debug("bookTotalFirstDay" + book.getBookFirstDay());
		// 도서의 totalCount 를 가져와서 +1 해주고 맵에 담아 업데이트함
		logger.debug("bookTotalCount" + book.getBookTotalCount());
		int bookTotalCount = book.getBookTotalCount();
		logger.debug("bookTotalCount" + bookTotalCount);
		bookTotalCount++;
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("bookCode", rental.getBookCode());
		map1.put("bookTotalCount", bookTotalCount);
		rowCount += bookDao.updateBookTotalCount(map1);

		// 도서코드와 도서상태를 맵에 담아 도서상태를 수정해준다.(이미 짜여있는 쿼리 사용하기위해 따로 수정)
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("bookCode", rental.getBookCode());
		map2.put("bookState", 2);
		rowCount += bookDao.updateBookState(map2);

		return rowCount;
	}

	// 대여정보 조회
	public ReturnCommand getOneRental(String bookCode) {
		logger.debug("getOneRental 메서드 호출");
		// 도서코드로 대여정보를 들고온다
		// 대여정보중 검색할때 도서코드일치 && 대여상태가 '대여'인 것의 정보만 가져온다
		ReturnCommand returnCommand = rentalDao.selectOneRental(bookCode);
		if (returnCommand == null) {
			return null;
		}
		logger.debug("returnCommand:" + returnCommand);
		String rentalStart = returnCommand.getRentalStart();
		// 오늘날짜와 시작날짜의 차이를 구한다.
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		Date startday = null;
		try {
			startday = transFormat.parse(rentalStart);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("startday:" + startday);
		logger.debug("today:" + today);
		long diff = today.getTime() - startday.getTime();
		long diffDays = (diff / (24 * 60 * 60 * 1000));
		int diffDaysInt = (int) diffDays;
		// totalday도 넘겨주기위해 담는다.
		returnCommand.setBookTotalDay(returnCommand.getBookTotalDay()+diffDaysInt);
		logger.debug("diffDays:" + diffDays);
		// 총요금 만들어서 넣어줌
		returnCommand.setTotalPrice(returnCommand.getMemberLevelPrice() * diffDaysInt);
		// 받아야할 금액 계산(총요금-낸요금)
		returnCommand.setWillPay(returnCommand.getTotalPrice() - returnCommand.getRentalPayment());

		return returnCommand;
	}

	// 반납
	public int returnBook(ReturnCommand returnCommand) {
		logger.debug("returnBook 메서드 호출");
		int rowCount = 0;
		// 대여상태를 반납으로 수정해준다.
		rowCount += rentalDao.updateRentalState(returnCommand);

		// 도서코드로 도서 totalday를 +해준다
		logger.debug("bookTotalDay:" + returnCommand.getBookTotalDay());
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("bookCode", returnCommand.getBookCode());
		map1.put("bookTotalDay", returnCommand.getBookTotalDay());
		rowCount += bookDao.updateBookTotalDay(map1);
		// 도서코드와 도서상태를 맵에 담아 도서상태를 대여가능으로 수정준다.
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("bookCode", returnCommand.getBookCode());
		map2.put("bookState", 1);
		rowCount += bookDao.updateBookState(map2);
		return rowCount;
	}

}
