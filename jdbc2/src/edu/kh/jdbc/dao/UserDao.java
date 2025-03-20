package edu.kh.jdbc.dao;

// import static : 지정된 경로에 존재하는 static 구문을 모두 얻어와
// 클래스명.메서드명() 이 아닌 메서드명() 만 작성해도 호출 가능하게 함.
import static edu.kh.jdbc.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.dto.User;

// (Model 중 하나)DAO (Data Access Object)
// 데이터가 저장된 곳에 접근하는 용도의 객체
// -> DB에 접근하여 Java에서 원하는 결과를 얻기위해
// SQL을 수행하고 결과를 반환 받는 역할
public class UserDao {
	
	// 필드
	// - DB 접근 관련한 JDBC 객체 참조 변수 미리 선언
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 메서드
	/** 전달받은 Connevtion을 이용해서 DB에 접근하여
	 * 전달받은 아이디(input)와 일치하는 User 정보를 DB조회하기
	 * @param conn : Service에서 생성한 Connection 객체
	 * @param input : View에서 입력받은 아이디
	 * @return 아이디가 일치하는 회원의 User 또는 null
	 */
	public User selectId(Connection conn, String input) {
		
		// 1. 결과 저장용 변수 선언
		User user = null;
		
		try {
			
			// 2. SQL 작성
			String SQL = """
					SELECT * FROM TB_USER WHERE USER_ID = ?
					""";
			
			// 3. PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(SQL);
			
			// 4. ? (위치홀더) 에  알맞은 값 세팅
			pstmt.setString(1, input);
			
			// 5. SQL 수행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			
			// 6. 조회 결과가 있을 경우
			// + 중복되는 아이디가 없다고 가정
			// -> 1행만 조회되기 때문에 while문 보다는 if를 사용하는게 효과적
			
			if(rs.next()) {
				// 첫 행에 데이터가 존재한다면 
				
				// 각 컬럼의 값 얻어오기
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				// java.sql.Date
				Date enrollDate = rs.getDate("ENROLL_DATE");
				
				// 조회된 컬럼값을 이용해서 User 객체 생성
				user = new User(userNo, 
						userId, 
						userPw, 
						userName, 
						enrollDate.toString());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 사용한 JDBC 객체 자원 반환(close)
				
				//JDBCTemplate.close(pstmt);
				//JDBCTemplate.close(rs);
				close(pstmt);
				close(rs);
				
				// Connection 객체는 생성된 Service에서 close!
				
		}
		
		return user; // 결과 반환 (생성된 User 객체 또는 null)
	}
	
	public List<User> selectAll(Connection conn) {
		
		List<User> userList = new ArrayList<User>();
		
		try {
			
			String SQL = """
					SELECT USER_NO, USER_ID, USER_PW, USER_NAME,
					TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"') ENROLL_DATE
					FROM TB_USER
					ORDER BY USER_NO
					""";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			
			
			
			while(rs.next()) {
				
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				
				userList.add(new User(
						userNo, 
						userId, 
						userPw, 
						userName, 
						enrollDate));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			close(stmt);
			close(rs);
			
		}
		
		return userList;
	}

	
	public User selectUser(Connection conn, int input) {
		
		User user = null;
		
		try {
			
			String SQL = """
					SELECT * FROM TB_USER WHERE USER_NO = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, input);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_Name");
				String enrollDate = rs.getString("ENROLL_DATE");
				
				user = new User(userNo,userId,userPw,userName,enrollDate.toString());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			
		}
		
		return user;
	}

	public String insertUser(String id, String pw, String name, Connection conn) throws Exception {
		String newUser = null;
		
		// SQL 수행 중 발생하는 예외를
		// catch로 처리하지 않고, throws를 이용해서 호출부로 던져 처리
		// -> catch 문 필요없다!
		
		try {
			
			String SQL = """
					INSERT INTO TB_USER
					VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, DEFAULT)
					""";
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			
			if(pstmt.executeUpdate()>0) {
				newUser = "등록에 성공하였습니다.";
			};
			
		} finally {
			close(pstmt);
		}
		
		return newUser;
	}

	public String deleteUser(Connection conn, int input) {
		
		String user = null;
		
		try {
			
			String SQL = """
					DELETE FROM TB_USER
					WHERE USER_NO = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setInt(1, input);
			
			if(pstmt.executeUpdate()>0) {
				user = "삭제 성공";
			};
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return user;
	}

	public String updateUser(Connection conn, String updateId, String updatePw, String updateName) {
String user = null;
		
		try {
			
			String SQL = """
					UPDATE TB_USER
					SET USER_NAME = ?
					WHERE USER_ID = ? AND USER_PW = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, updateName);
			pstmt.setString(2, updateId);
			pstmt.setString(3, updatePw);
			
			if(pstmt.executeUpdate()>0) {
				user = "수정 성공";
			};
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return user;
	}

	public List<User> selectName(Connection conn, String select) throws Exception{
		
		List<User> user = new ArrayList<User>();
		
		String find = "%"+select+"%";
		
		try {
			
			String SQL = """
					SELECT USER_NO, USER_ID, USER_PW, USER_NAME,
					TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"') ENROLL_DATE
					FROM TB_USER
					WHERE USER_NAME LIKE ?
					ORDER BY USER_NO
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, find);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				
				user.add(new User(userNo, userId, userPw, userName, enrollDate.toString())) ;
				
				
			}
			
			
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return user;
	}

	public int dupUser(String id, Connection conn) throws Exception {
		int user = 0;
		
		try {
			
			String SQL = """
					SELECT USER_ID FROM TB_USER WHERE USER_ID = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = 1;
			}
			
		} finally {
			close(pstmt);
			close(rs);
			
		}
		
		
		
		return user;
	}

	public int selectUserNo(Connection conn, String userId, String userPw) throws Exception {
		
		int userNo = 0;
		
		try {
			
			String SQL = """
					SELECT USER_NO FROM TB_USER WHERE USER_ID = ? AND USER_PW = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userNo = rs.getInt("USER_NO");
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
			
		}
		
		return userNo;
	}

	public int updateName(Connection conn, int userNo, String userName) throws Exception {
		
		int user = 0;
		
		try {
			
			String SQL = """
					UPDATE TB_USER
					SET USER_NAME = ?
					WHERE USER_NO = ?
					""";
			
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, userName);
			pstmt.setInt(2, userNo);
			
			user = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return user;
	}

	public void multiInsertUser(Connection conn, List<User> userList, boolean flag) throws Exception {
		
		try {
			
			for(User e : userList) {
			String SQL = """
					INSERT INTO TB_USER
					VALUES (SEQ_USER_NO.NEXTVAL, ?, ?, ?, DEFAULT)
					""";
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, e.getUserId());
			pstmt.setString(2, e.getUserPw());
			pstmt.setString(3, e.getUserName());
			
			if(pstmt.executeUpdate()==0) {
				flag = false;
			}
			
			}
			
		} finally {
			close(pstmt);
		}
		
		return ;
	}

	
}
