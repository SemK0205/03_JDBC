package edu.kh.jdbc.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dao.UserDao;
import edu.kh.jdbc.dto.User;

// (Model 중 하나)Service : 비즈니스 로직을 처리하는 계층, 
// 데이터를 가공하고 트랜잭션(commit, rollback) 관리 수행
public class UserService {
	
	// 필드
	private UserDao dao = new UserDao();

	
	
	// 메서드
	/** 전달받은 아이디와 일치하는 User 정보 반환 서비스
	 * @param input (입력된 아이디)
	 * @return 아이디가 일치하는 회원 정보가 담긴 User 객체,
	 * 			없으면 null 반환
	 */
	public User selectId(String input) {
		
		// 1. 커넥션 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. 데이터 가공(할게 없으면 넘어감)
		
		// 3. DAO 메서드 호출 결과 반환
		User user = dao.selectId(conn, input);
		
		// 4. DML(commit/rollback)
		
		// 5. 다 쓴 커넥션 자원 반환
		JDBCTemplate.close(conn);
		
		// 6. 결과를 View에게 리턴
		
		return user;
	}



	/**
	 * @return
	 */
	
	public  List<User> selectAll() {
		Connection conn = JDBCTemplate.getConnection();
		
		List<User> user = dao.selectAll(conn);
		
		JDBCTemplate.close(conn);
		
		return user;
	}




	public User selectUser(int input) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		User user = dao.selectUser(conn, input);
		
		JDBCTemplate.close(conn);
		return user;
	}



	public String insertUser(String id, String pw, String name) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		String user = dao.insertUser(id, pw, name, conn);
		
		if(user == null) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		return user;
	}



	public String deleteUser(int input) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		String user = dao.deleteUser(conn, input);
		
		if(user == null) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return user;
	}



	public String updateUser(String updateId, String updatePw, String updateName) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		String user = dao.updateUser(conn, updateId, updatePw, updateName);
		
		if(user == null) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return user;
	}



	public List<User> selectName(String select) throws Exception{
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<User> user = dao.selectName(conn, select);
		
		JDBCTemplate.close(conn);
		
		return user;
	}



	public int dupUser(String id) throws Exception{
		
		Connection conn = JDBCTemplate.getConnection();
		
		int user = dao.dupUser(id, conn);
		
		JDBCTemplate.close(conn);
		
		return user;
	}



	public int selectUserNo(String userId, String userPw) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int userNo = dao.selectUserNo(conn, userId, userPw);
		
		JDBCTemplate.close(conn);
		
		return userNo;
	}



	public int updateName(int userNo, String userName) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int user = dao.updateName(conn, userNo, userName);
		
		if(user > 0 ) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return user;
	}



	public void multiInsertUser(List<User> userList, boolean flag) throws Exception{
		
		Connection conn = JDBCTemplate.getConnection();
		
		dao.multiInsertUser(conn, userList, flag);
		
		if(flag) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		
		JDBCTemplate.close(conn);
		
		return ;
	}
	
	

}
