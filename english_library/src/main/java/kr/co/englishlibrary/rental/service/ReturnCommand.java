package kr.co.englishlibrary.rental.service;

public class ReturnCommand {
	private String rentalCode;
	private String bookCode;
	private String bookName;
	private String memberName;
	private int totalPrice;
	private int rentalPayment;
	private int willPay;
	private String rentalStart;
	private int memberLevelPrice;
	
	
	public String getRentalStart() {
		return rentalStart;
	}
	public void setRentalStart(String rentalStart) {
		this.rentalStart = rentalStart;
	}
	public int getMemberLevelPrice() {
		return memberLevelPrice;
	}
	public void setMemberLevelPrice(int memberLevelPrice) {
		this.memberLevelPrice = memberLevelPrice;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getRentalPayment() {
		return rentalPayment;
	}
	public void setRentalPayment(int rentalPayment) {
		this.rentalPayment = rentalPayment;
	}
	public int getWillPay() {
		return willPay;
	}
	public void setWillPay(int willPay) {
		this.willPay = willPay;
	}
	@Override
	public String toString() {
		return "ReturnCommand [rentalCode=" + rentalCode + ", bookCode=" + bookCode + ", bookName=" + bookName
				+ ", memberName=" + memberName + ", totalPrice=" + totalPrice + ", rentalPayment=" + rentalPayment
				+ ", willPay=" + willPay + ", rentalStart=" + rentalStart + ", memberLevelPrice=" + memberLevelPrice
				+ "]";
	}
	
}
