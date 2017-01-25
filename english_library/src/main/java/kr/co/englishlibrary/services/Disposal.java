package kr.co.englishlibrary.services;

public class Disposal {
	private int disposalNo;
	private String bookCode;
	private String disposalBookName;
	private String disposalAuthor;
	private int genreNo;
	private String disposalPublisher;
	private String disposalRegisterDay;
	
	public int getDisposalNo() {
		return disposalNo;
	}
	public void setDisposalNo(int disposalNo) {
		this.disposalNo = disposalNo;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getDisposalBookName() {
		return disposalBookName;
	}
	public void setDisposalBookName(String disposalBookName) {
		this.disposalBookName = disposalBookName;
	}
	public String getDisposalAuthor() {
		return disposalAuthor;
	}
	public void setDisposalAuthor(String disposalAuthor) {
		this.disposalAuthor = disposalAuthor;
	}
	public int getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(int genreNo) {
		this.genreNo = genreNo;
	}
	public String getDisposalPublisher() {
		return disposalPublisher;
	}
	public void setDisposalPublisher(String disposalPublisher) {
		this.disposalPublisher = disposalPublisher;
	}
	public String getDisposalRegisterDay() {
		return disposalRegisterDay;
	}
	public void setDisposalRegisterDay(String disposalRegisterDay) {
		this.disposalRegisterDay = disposalRegisterDay;
	}
	@Override
	public String toString() {
		return "Disposal [disposalNo=" + disposalNo + ", bookCode=" + bookCode + ", disposalBookName="
				+ disposalBookName + ", disposalAuthor=" + disposalAuthor + ", genreNo=" + genreNo
				+ ", disposalPublisher=" + disposalPublisher + ", disposalRegisterDay=" + disposalRegisterDay + "]";
	}
	
	
}
