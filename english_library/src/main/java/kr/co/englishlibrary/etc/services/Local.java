package kr.co.englishlibrary.etc.services;

public class Local {
	private int localNo;
	private String localName;
	public int getLocalNo() {
		return localNo;
	}
	public void setLocalNo(int localNo) {
		this.localNo = localNo;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	@Override
	public String toString() {
		return "Local [localNo=" + localNo + ", localName=" + localName + "]";
	}
	
	
}
