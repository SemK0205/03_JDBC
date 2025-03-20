package edu.kh.jdbc.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dao.UserDao;
import edu.kh.jdbc.dto.User;


public class UserService {
	
	private UserDao dao = new UserDao();
	
	public boolean dupUser(String userId) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		boolean flag = dao.dupUser(userId, conn);
		
		JDBCTemplate.close(conn);
		
		return flag;
		
	}
	

	public int insertUser(User user) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.insertUser(user, conn);
		
		if(result > 0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}


	public List<User> selectAll() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<User> userList = dao.selectAll(conn);
		
		JDBCTemplate.close(conn);
		
		
		return userList;
	}


	public List<User> selectName(String includeName) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<User> userList = dao.selectName(conn, includeName);
		
		JDBCTemplate.close(conn);
		
		return userList;
	}


	public User selectUser(int input) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		User user = dao.selectUser(conn, input);
		
		JDBCTemplate.close(conn);
		
		return user;
	}

	

}
