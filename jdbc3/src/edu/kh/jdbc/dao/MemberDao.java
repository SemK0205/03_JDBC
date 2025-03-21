package edu.kh.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dto.Member;

public class MemberDao {
	
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	public int signUp(Connection conn, String memberId, String memberPw, String memberName, String memberSsn) throws Exception {

		int result = 0;
		
		try {
			
			String SQL = """
					INSERT INTO TB_MEMBER 
					VALUES(SEQ_TODO_NO.NEXTVAL, ?, ?, ?, ?, DEFAULT)
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberSsn);
			result = pstmt.executeUpdate();
			
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	public Member agreement(Connection conn, String memberId, String memberPw) throws Exception {
		
		Member member = null;
		
		try {
			
			String SQL = """
					SELECT MEMBER_NAME, MEMBER_NO
					FROM TB_MEMBER
					WHERE MEMBER_ID = ? AND MEMBER_PW = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String memberName = rs.getString("MEMBER_NAME");
				int memberNo = rs.getInt("MEMBER_NO");
				member = new Member(memberNo,memberName);
				
				return member;
			}
			
			
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
		}
		return member;
		
	}
	public Member selectAllTodo(Connection conn, int loginInfo) throws Exception {
		
		Member member = null;
		
		try {
			
			String SQL = """
					SELECT TODO_TITLE, TODO_DO, TODO_YN, 
					TO_CHAR(TODO_DATE, 'YY"년" MM"월" DD"일"') TODO_DATE
					FROM TB_TODO 
					WHERE MEMBER_NO = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, loginInfo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String todoTitle = rs.getString("TODO_TITLE");
				String todoDo = rs.getString("TODO_DO");
				String todoYn = rs.getString("TODO_YN");
				String todoDate = rs.getString("TODO_DATE");
				
				member = new Member(todoTitle, todoDo, todoYn, todoDate);
				
			}
			
			
			
			
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return member;
	}
	public int newTodo(Connection conn, String title, String todo, int loginInfo) throws Exception {
		
		int result = 0;
		
		try {
			
			String SQL = """
					INSERT INTO TB_TODO
					VALUES(?, ?, DEFAULT, DEFAULT , ?)
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, title);
			pstmt.setString(2, todo);
			pstmt.setInt(3, loginInfo);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	public int updateTitle(Connection conn, String title, int loginInfo) throws Exception {
		int result = 0;
		
		try {
			
			String SQL = """
					UPDATE TB_TODO
					SET TODO_TITLE = ?
					WHERE MEMBER_NO = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, title);
			pstmt.setInt(2, loginInfo);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}
	public int updatedetail(Connection conn, String detail, int loginInfo) throws Exception {
		int result = 0;
		try {
			
			String SQL = """
					UPDATE TB_TODO
					SET TODO_DO = ?
					WHERE MEMBER_NO = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, detail);
			pstmt.setInt(2, loginInfo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	public int updateDone(Connection conn, int loginInfo) throws Exception {
		int result = 0;
		
		try {
			
			String SQL = """
					UPDATE TB_TODO
					SET TODO_YN = 'Y'
					WHERE MEMBER_NO = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, loginInfo);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	public int updateNotDone(Connection conn, int loginInfo) throws Exception {
		
		int result = 0;
		
		try {
			
			String SQL = """
					UPDATE TB_TODO
					SET TODO_YN = DEFAULT
					WHERE MEMBER_NO = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, loginInfo);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	public int deleteTodo(Connection conn, int loginInfo) throws Exception {
		
		int result = 0;
		
		try {
			
			String SQL = """
					DELETE TB_TODO
					WHERE MEMBER_NO = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, loginInfo);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
