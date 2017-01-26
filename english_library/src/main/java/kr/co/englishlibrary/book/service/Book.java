package kr.co.englishlibrary.book.service;

public class Book {
	private String bookCode;
	private String libraryId;
	private int stateNo;
	private int genreNo;
	private String bookName;
	private String bookAuthor;
	private String bookPublisher;
	private String bookFirstDay;
	private int bookTotalDay;
	private int bookTotalCount;
	
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}
	public int getStateNo() {
		return stateNo;
	}
	public void setStateNo(int stateNo) {
		this.stateNo = stateNo;
	}
	public int getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(int genre) {
		this.genreNo = genre;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	public String getBookFirstDay() {
		return bookFirstDay;
	}
	public void setBookFirstDay(String bookFirstDay) {
		this.bookFirstDay = bookFirstDay;
	}
	public int getBookTotalDay() {
		return bookTotalDay;
	}
	public void setBookTotalDay(int bookTotalDay) {
		this.bookTotalDay = bookTotalDay;
	}
	public int getBookTotalCount() {
		return bookTotalCount;
	}
	public void setBookTotalCount(int bookTotalCount) {
		this.bookTotalCount = bookTotalCount;
	}
	@Override
	public String toString() {
		return "Book [bookCode=" + bookCode + ", libraryId=" + libraryId + ", stateNo=" + stateNo + ", genreNo="
				+ genreNo + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookPublisher=" + bookPublisher
				+ ", bookFirstDay=" + bookFirstDay + ", bookTotalDay=" + bookTotalDay + ", bookTotalCount="
				+ bookTotalCount + "]";
	}
}
