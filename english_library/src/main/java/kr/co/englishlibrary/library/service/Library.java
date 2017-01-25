package kr.co.englishlibrary.library.service;

public class Library {
	private String libraryId;
	private String libraryPw;
	private int local;
	
	
	public String getLibraryId() {
		return libraryId;
	}
	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}
	public String getLibraryPw() {
		return libraryPw;
	}
	public void setLibraryPw(String libraryPw) {
		this.libraryPw = libraryPw;
	}
	public int getLocal() {
		return local;
	}
	public void setLocal(int local) {
		this.local = local;
	}
	@Override
	public String toString() {
		return "Library [libraryId=" + libraryId + ", libraryPw=" + libraryPw + ", local=" + local + "]";
	}
	
	
}
