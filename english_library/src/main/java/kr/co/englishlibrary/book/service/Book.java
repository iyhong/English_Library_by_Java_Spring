package kr.co.englishlibrary.book.service;

import kr.co.englishlibrary.services.Genre;
import kr.co.englishlibrary.services.State;

public class Book {
	private String bookCode;
	private String libraryId;
	private State state;
	private Genre genre;
	private String bookName;
	private String bookAuthor;
	private String bookPublisher;
	private String bookFirstDay;
	private String bookTotalDay;
	private String bookTotalCount;
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
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
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
	public String getBookTotalDay() {
		return bookTotalDay;
	}
	public void setBookTotalDay(String bookTotalDay) {
		this.bookTotalDay = bookTotalDay;
	}
	public String getBookTotalCount() {
		return bookTotalCount;
	}
	public void setBookTotalCount(String bookTotalCount) {
		this.bookTotalCount = bookTotalCount;
	}
	@Override
	public String toString() {
		return "Book [bookCode=" + bookCode + ", libraryId=" + libraryId + ", state=" + state + ", genre=" + genre
				+ ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookPublisher=" + bookPublisher
				+ ", bookFirstDay=" + bookFirstDay + ", bookTotalDay=" + bookTotalDay + ", bookTotalCount="
				+ bookTotalCount + "]";
	}
	
	
}
