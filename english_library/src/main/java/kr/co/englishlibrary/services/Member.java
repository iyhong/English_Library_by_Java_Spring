package kr.co.englishlibrary.services;

public class Member {
	private int memberId;
	private String memberName;
	private String memberPhone;
	private int memberLevelNo;
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public int getMemberLevelNo() {
		return memberLevelNo;
	}
	public void setMemberLevelNo(int memberLevelNo) {
		this.memberLevelNo = memberLevelNo;
	}
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", memberPhone=" + memberPhone
				+ ", memberLevelNo=" + memberLevelNo + "]";
	}
	
}
