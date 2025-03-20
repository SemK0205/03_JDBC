package edu.kh.jdbc.dto;

public class User {
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String enrollDate;
	
	public User() {}
	
	public User(int userNo, String userId, String userPw, String userName, String enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "userNo : " + userNo + ", userId : " + userId + ", userPw : " + userPw + ", userName : " + userName
				+ ", enrollDate : " + enrollDate;
	}

	public User(String userId, String userPw, String userName) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	
	

}
