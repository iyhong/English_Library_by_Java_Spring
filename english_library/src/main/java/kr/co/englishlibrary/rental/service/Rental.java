package kr.co.englishlibrary.rental.service;

public class Rental {
	private String rentalCode;
	private String bookCode;
	private String rentalStart;
	private String rentalEnd;
	private int memberId;
	private int	rentalPayment;
	private int rentalStateNo;
	public String getRentalCode() {
		return rentalCode;
	}
	public void setRentalCode(String rentalCode) {
		this.rentalCode = rentalCode;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getRentalStart() {
		return rentalStart;
	}
	public void setRentalStart(String rentalStart) {
		this.rentalStart = rentalStart;
	}
	public String getRentalEnd() {
		return rentalEnd;
	}
	public void setRentalEnd(String rentalEnd) {
		this.rentalEnd = rentalEnd;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getRentalPayment() {
		return rentalPayment;
	}
	public void setRentalPayment(int rentalPayment) {
		this.rentalPayment = rentalPayment;
	}
	public int getRentalStateNo() {
		return rentalStateNo;
	}
	public void setRentalStateNo(int rentalStateNo) {
		this.rentalStateNo = rentalStateNo;
	}
	@Override
	public String toString() {
		return "Rental [rentalCode=" + rentalCode + ", bookCode=" + bookCode + ", rentalStart=" + rentalStart
				+ ", rentalEnd=" + rentalEnd + ", memberId=" + memberId + ", rentalPayment=" + rentalPayment
				+ ", rentalStateNo=" + rentalStateNo + "]";
	}
	
}
