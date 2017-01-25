package kr.co.englishlibrary.services;

public class State {
	private int stateNo;
	private String StateName;
	
	public int getStateNo() {
		return stateNo;
	}
	public void setStateNo(int stateNo) {
		this.stateNo = stateNo;
	}
	public String getStateName() {
		return StateName;
	}
	public void setStateName(String stateName) {
		StateName = stateName;
	}
	
	@Override
	public String toString() {
		return "State [stateNo=" + stateNo + ", StateName=" + StateName + "]";
	}
	
	
}
