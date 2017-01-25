package kr.co.englishlibrary.services;

public class Library {
	private String libraryId;
	private String libraryPw;
	private Local local;
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
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	@Override
	public String toString() {
		return "Library [libraryId=" + libraryId + ", libraryPw=" + libraryPw + ", local=" + local + "]";
	}
	
	
}
