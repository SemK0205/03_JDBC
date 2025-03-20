package edu.kh.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dto.User;

public class UserDao {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public boolean dupUser(String userId, Connection conn) throws Exception {
		
		boolean flag = true;
		
		try {
			
			String SQL = """
					SELECT USER_ID FROM TB_USER WHERE USER_ID = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) flag = false;
			
			
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return flag;
	}

	public int insertUser(User user, Connection conn) throws Exception {
		
		int result = 0;
		
		try {
			
			String SQL = """
					INSERT INTO TB_USER
					VALUES (SEQ_USER_NO.NEXTVAL, ?, ?, ?, DEFAULT)
					ORDER BY 1
					""";
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public List<User> selectAll(Connection conn) throws Exception {
		
		User user = null;
		
		List<User> userList = new ArrayList<User>();
		
		try {
			
			String SQL = """
					SELECT USER_NO, USER_ID, USER_PW, USER_NAME,
					TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"') ENROLL_DATE
					FROM TB_USER
					ORDER BY 1
					""";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			
			while(rs.next()) {
				
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				
				user = new User (userNo, userId, userPw, userName, enrollDate);
				
				userList.add(user);
				
			}
			
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		
		return userList;
	}


	public List<User> selectName(Connection conn, String includeName) throws Exception {

		User user = null;
		List<User> userList = new ArrayList<User>();
		String like = "%"+includeName+"%";
		try {
			
			String SQL = """
					SELECT USER_NO, USER_ID, USER_PW, USER_NAME,
					TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"') ENROLL_DATE
					FROM TB_USER
					WHERE USER_NAME LIKE ?
					ORDER BY 1
					""";
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, like);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				
				user = new User (userNo, userId, userPw, userName, enrollDate);
				
				userList.add(user);
				
			}
			
			
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		
		return userList;
	}

	public User selectUser(Connection conn, int input) throws Exception {
		
		User user = null;
		
		try {
			
			String SQL = """
					SELECT *
					FROM TB_USER
					WHERE USER_NO = ?
					""";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, input);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				
				user = new User (userNo, userId, userPw, userName, enrollDate);
				
			}
			
			
		} finally {
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
		}
		
		return user;
	}

}
