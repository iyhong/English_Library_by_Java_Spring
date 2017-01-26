package kr.co.englishlibrary.services;

public class MemberLevel {
	private int memberLevelNo;
	private String memberLevelName;
	private int price;
	public int getMemberLevelNo() {
		return memberLevelNo;
	}
	public void setMemberLevelNo(int memberLevelNo) {
		this.memberLevelNo = memberLevelNo;
	}
	public String getMemberLevelName() {
		return memberLevelName;
	}
	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "MemberLevel [memberLevelNo=" + memberLevelNo + ", memberLevelName=" + memberLevelName + ", price="
				+ price + "]";
	}
	
}
