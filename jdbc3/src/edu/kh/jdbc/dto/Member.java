package edu.kh.jdbc.dto;

public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberSsn;
	private String enrollDate;
	
	private String todoTile;
	private String todoDo;
	private String todoYn;
	private String todoDate;
	
	public Member() {}
	
	public Member(String todoTile, String todoDo, String todoYn, String todoDate) {
		super();
		this.todoTile = todoTile;
		this.todoDo = todoDo;
		this.todoYn = todoYn;
		this.todoDate = todoDate;
	}

	public String getTodoTile() {
		return todoTile;
	}

	public void setTodoTile(String todoTile) {
		this.todoTile = todoTile;
	}

	public String getTodoDo() {
		return todoDo;
	}

	public void setTodoDo(String todoDo) {
		this.todoDo = todoDo;
	}

	public String getTodoYn() {
		return todoYn;
	}

	public void setTodoYn(String todoYn) {
		this.todoYn = todoYn;
	}

	public String getTodoDate() {
		return todoDate;
	}

	public void setTodoDate(String todoDate) {
		this.todoDate = todoDate;
	}

	public Member(int memberNo, String memberId, String memberPw, String memberName, String memberSsn,
			String enrollDate) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberSsn = memberSsn;
		this.enrollDate = enrollDate;
	}
	
	

	public Member(int memberNo, String memberName) {
		super();
		this.memberNo = memberNo;
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "TODO_List [제목=" + todoTile + ", 할일=" + todoDo + ", 완료여부=" + todoYn + ", 작성일=" + todoDate
				+ "]";
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberSsn() {
		return memberSsn;
	}

	public void setMemberSsn(String memberSsn) {
		this.memberSsn = memberSsn;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	
	

}
