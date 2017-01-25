package kr.co.englishlibrary.book.service;

public class BookCommand {
	private String bookName;
	private String bookAuthor;
	private String bookPublisher;
	private int genre;
	
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
	public int getGenre() {
		return genre;
	}
	public void setGenre(int genre) {
		this.genre = genre;
	}
	@Override
	public String toString() {
		return "BookCommand [bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookPublisher=" + bookPublisher
				+ ", genre=" + genre + "]";
	}
	
	
}
