package edu.kh.jdbc.service;


import java.sql.Connection;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dao.MemberDao;
import edu.kh.jdbc.dto.Member;


public class MemberService {
	
	
	private MemberDao dao = new MemberDao();

	public int signUp(String memberId, String memberPw, String memberName, String memberSsn) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.signUp(conn, memberId, memberPw, memberName, memberSsn);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public Member agreement(String memberId, String memberPw) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member member = dao.agreement(conn, memberId, memberPw);
		
		
		JDBCTemplate.close(conn);
		
		return member;
	}

	public Member selectAllTodo(int loginInfo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member member = dao.selectAllTodo(conn,loginInfo);
		
		
		JDBCTemplate.close(conn);
		
		return member;
	}

	public int newTodo(String title, String todo, int loginInfo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		int result = dao.newTodo(conn, title, todo, loginInfo);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updateTitle(String title, int loginInfo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.updateTitle(conn, title, loginInfo);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updateDetail(String detail, int loginInfo) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.updatedetail(conn, detail, loginInfo);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updateDone(int loginInfo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.updateDone(conn, loginInfo);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updateNotDone(int loginInfo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.updateNotDone(conn, loginInfo);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int deleteTodo(int loginInfo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.deleteTodo(conn, loginInfo);
		
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
