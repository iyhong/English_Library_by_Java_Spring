package kr.co.englishlibrary.services;

public class Genre {
	private int genre_no;
	private String genre_name;
	
	public int getGenre_no() {
		return genre_no;
	}
	public void setGenre_no(int genre_no) {
		this.genre_no = genre_no;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	
	@Override
	public String toString() {
		return "Genre [genre_no=" + genre_no + ", genre_name=" + genre_name + "]";
	}
	
	
}
